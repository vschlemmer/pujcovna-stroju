package cz.muni.fi.pa165.pujcovnaStroju.rest.controller;

import cz.muni.fi.pa165.pujcovnaStroju.rest.converter.DTOtoXMLConverter;
import cz.muni.fi.pa165.pujcovnaStroju.web.converter.StringToSystemUserTypeEnumDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.UnsupportedTypeException;
import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.service.SystemUserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * REST controller for system users
 * 
 * @author Vojtech Schlemmer
 * 
 */
@Controller
@RequestMapping("/rest/user")
public class SystemUserRestController {
    
    private static StringToSystemUserTypeEnumDTOConverter converter = new StringToSystemUserTypeEnumDTOConverter();
    private SystemUserService userService;
    
    @Autowired
    public SystemUserRestController(SystemUserService userService) {
            this.userService = userService;
    }
    
    @RequestMapping(value = "/list")
    public HttpEntity<byte[]> listUsers(ModelMap map,
                    HttpServletResponse response,
                    @RequestParam(required = false) String firstName,
                    @RequestParam(required = false) String lastName,
                    @RequestParam(required = false) String userName,
                    @RequestParam(required = false) String type) {
        UserTypeEnumDTO typeDTO = null;
        if (type != null){
            typeDTO = converter.convert(type);
        }
        List<SystemUserDTO> listUsers = null;
        try {
            listUsers = userService.getSystemUsersByParams(firstName, lastName, typeDTO, userName);
                if (listUsers == null) {
                    listUsers = new ArrayList<>();
                }
        } catch (UnsupportedTypeException e) {
            return GenericController.returnErrorXML("Unknown user type: "
                                + type);
        } catch (DataAccessException e) {
            return GenericController
                    .returnErrorXML("Error occured during processing request");
        }
        StringBuilder builder = new StringBuilder(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        builder.append("<response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">");
        builder.append("<users numFound=\"" + listUsers.size() + "\">");
        for (SystemUserDTO user : listUsers) {
            builder.append(DTOtoXMLConverter.systemUserDTOtoXML(user));
        }
        builder.append("</users>");
        builder.append("</response>");
        return GenericController.returnXML(builder.toString());
    }
    
    @RequestMapping(value = "/add")
    public HttpEntity<byte[]> addUser(ModelMap map,
                    HttpServletResponse response,
                    @RequestParam(required = false) String firstName,
                    @RequestParam(required = false) String lastName,
                    @RequestParam(required = false) String userName,
                    @RequestParam(required = false) String password,
                    @RequestParam(required = false) String type) {
        List<String> errorMessages = new ArrayList<>();
        if (firstName == null || firstName.isEmpty()) {
            errorMessages.add("Missing required argument: firstName");
        }
        if (lastName == null || lastName.isEmpty()) {
            errorMessages.add("Missing required argument: lastName");
        }
        if (type == null || type.isEmpty()) {
            errorMessages.add("Missing required argument: type");
        }
        if (!errorMessages.isEmpty()) {
            return GenericController.returnErrorXML(errorMessages);
        }
        SystemUserDTO user = new SystemUserDTO();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(password);
        user.setType(converter.convert(type.toUpperCase()));
        SystemUserDTO created = null;
        try {
            created = userService.create(user);
        } catch (UnsupportedTypeException e) {
            return GenericController
                        .returnErrorXML("Unsupported user type: " + type);
        } catch (DataAccessException e) {
            return GenericController
                        .returnErrorXML("Error occured during processing request");
        }
        if (created == null) {
            return GenericController
                        .returnErrorXML("Error occured during processing request");
        } else {
            return GenericController
                        .returnSuccessXML("User created with id:"
                                                + created.getId());
        }
    }
    
    @RequestMapping(value = "/detail")
    public HttpEntity<byte[]> detailOfUser(ModelMap map,
                    HttpServletResponse response,
                    @RequestParam(required = false) String id) {
        if (id == null || id.isEmpty()) {
            return GenericController
                        .returnErrorXML("Missing required argument: id");
        }
        Long lid;
        try {
            lid = Long.valueOf(id);
        } catch (NumberFormatException e) {
            return GenericController
                        .returnErrorXML("Wrong format of argument: id");
        }
        SystemUserDTO user = null;
        try {
            user = userService.read(lid);
            if (user == null) {
                return GenericController.returnErrorXML("User with id: "
                            + lid + " not found");
            }
        } catch (DataAccessException e) {
            return GenericController
                        .returnErrorXML("Error occured during processing request");
        }
        StringBuilder builder = new StringBuilder(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        builder.append("<response status=\"success\">");
        builder.append(DTOtoXMLConverter.systemUserDTOtoXML(user));
        builder.append("</response>");
        return GenericController.returnXML(builder.toString());
    }
    
    @RequestMapping(value = "/delete")
    public HttpEntity<byte[]> deleteUser(ModelMap map,
                    HttpServletResponse response,
                    @RequestParam(required = false) String id) {
        if (id == null || id.isEmpty()) {
            return GenericController
                        .returnErrorXML("Missing required argument: id");
        }
        Long lid;
        try {
            lid = Long.valueOf(id);
        } catch (NumberFormatException e) {
            return GenericController
                        .returnErrorXML("Wrong format of argument: id");
        }
        try {
            SystemUserDTO user = userService.read(lid);
            if (user == null) {
                return GenericController.returnErrorXML("User with id: "
                            + lid + " not found");
            }
            userService.delete(user);
        } catch (DataAccessException e) {
            return GenericController
                        .returnErrorXML("Error occured during processing request");
        }
        return GenericController.returnSuccessXML("User with id " + lid
                    + " successfully deleted");
        
    }
    
    @RequestMapping(value = "/update")
    public HttpEntity<byte[]> updateUser(ModelMap map,
                    HttpServletResponse response,
                    @RequestParam(required = false) String id,
                    @RequestParam(required = false) String firstName,
                    @RequestParam(required = false) String lastName,
                    @RequestParam(required = false) String type) {
        Long lid = null;
        List<String> errorMessages = new ArrayList<>();
        if (id == null || id.isEmpty()) {
            errorMessages.add("Missing required argument: id");
        } else {
            try {
                lid = Long.valueOf(id);
            } catch (NumberFormatException e) {
                errorMessages.add("Wrong format of argument: id");
            }
        }
        if (firstName == null || firstName.isEmpty() || type == null || type.isEmpty()
                        || lastName == null || lastName.isEmpty()) {
            errorMessages
                .add("None of arguments (firstName, lastName, type) set, nothing to update");
        }
        SystemUserDTO user = null;
        SystemUserDTO updated = null;
        try {
            user = userService.read(lid);
            if (user == null) {
                return GenericController.returnErrorXML("User with id: "
                                    + lid + " not found");
            }
            if (firstName != null && !firstName.isEmpty()) {
                user.setFirstName(firstName);
            }
            if (lastName != null && !lastName.isEmpty()) {
                user.setLastName(lastName);
            }	
            if (type != null && !type.isEmpty()){
                UserTypeEnumDTO typeDTO = typeDTO = converter.convert(type);
                if (typeDTO.getTypeLabel() != null) {
                    user.setType(typeDTO);
                }
            }
            updated = userService.update(user);
        } catch (UnsupportedTypeException e) {
            return GenericController
                    .returnErrorXML("Unsupported user type: " + type);
        } catch (DataAccessException e) {
            return GenericController
                    .returnErrorXML("Error occured during processing request");
        }
        if (updated == null) {
            return GenericController
                    .returnErrorXML("Error occured during processing request");
        } else {
            return GenericController.returnSuccessXML("User with id: "
                    + updated.getId() + " successfully updated");
        }
    }
    
}
