package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vojtech Schlemmer
 */
public class SystemUserDTOConverter {

    public static SystemUser dtoToEntity(SystemUserDTO userDTO) {
	if (userDTO == null) return null;
	
        SystemUser user = new SystemUser();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setType(UserTypeDTOConverter.dtoToEntity(userDTO.getType()));
        return user;
    }
    
    public static SystemUserDTO entityToDTO(SystemUser systemUser) {
	if (systemUser == null) return null;
	
        SystemUserDTO userDTO = new SystemUserDTO();
        userDTO.setId(systemUser.getId());
        userDTO.setFirstName(systemUser.getFirstName());
        userDTO.setLastName(systemUser.getLastName());
        userDTO.setType(UserTypeDTOConverter.entityToDto(systemUser.getType()));
        return userDTO;
    }
    
    public static List<SystemUserDTO> listToDTO(List<SystemUser> users){
	if (users == null) return null;
	
        List<SystemUserDTO> usersDTO = new ArrayList<>();
        for (SystemUser user : users){
            usersDTO.add(entityToDTO(user));
        }
        return usersDTO;
    }
    
    public static List<SystemUser> listToEntities(List<SystemUserDTO> usersDTO){
	if (usersDTO == null) return null;
	
        List<SystemUser> users = new ArrayList<>();
        for (SystemUserDTO userDTO : usersDTO){
            users.add(dtoToEntity(userDTO));
        }
        return users;
    }
}
