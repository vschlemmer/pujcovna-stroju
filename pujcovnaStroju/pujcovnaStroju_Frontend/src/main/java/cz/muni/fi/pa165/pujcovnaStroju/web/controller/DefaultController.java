package cz.muni.fi.pa165.pujcovnaStroju.web.controller;

import cz.muni.fi.pa165.pujcovnastroju.converter.UserTypeDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.security.UserDetailsServiceImpl;
import cz.muni.fi.pa165.pujcovnastroju.service.SystemUserService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling root request 
 * @author Michal Merta
 *
 */
@Controller
public class DefaultController {

    private static SystemUserService userService;
    private boolean defaultAdminCreated = false;

    @Autowired
    public DefaultController(SystemUserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String viewIndes(ModelMap model) {
        model.addAttribute("pageTitle", "lang.listMachinesTitle");
        addHeaderFooterInfo(model);
        if (!defaultAdminCreated){
            createDefaultAdmin();
            defaultAdminCreated = true;
        }
        return "index";
    }
	
	/**
	 * adds header and footer content to rendered page
	 */
	static ModelMap addHeaderFooterInfo(ModelMap model) {
		model.addAttribute("pageFooter", "lang.footer");
		model.addAttribute("pageHeader", "lang.header");
                addLeftMenuSecurity(model);
		return model;
	}
	
	/**
	 * adds static left menu content to rendered page
	 * @param model
	 * @return
	 */
	static ModelMap addLeftMenu(ModelMap model) {
		return model;
	}
        
        static ModelMap addLeftMenuSecurity(ModelMap model){
            model.addAttribute("userType", getLoggedUserType());
            return model;
        }
        
        static void createDefaultAdmin(){
            SystemUserDTO user = new SystemUserDTO();
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setUsername("admin");
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode("admin");
            user.setPassword(hashedPassword);
            user.setType(UserTypeDTOConverter.entityToDto(UserTypeEnum.CUSTOMERINDIVIDUAL));
            userService.create(user);
        }
        
        static String getLoggedUserType(){
            String userType = "";
            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
                if (SecurityContextHolder.getContext().getAuthentication() != null){
                    String authUserString = SecurityContextHolder.getContext().
                            getAuthentication().getName();
                    SystemUserDTO authUser = userService.getSystemUserByUsername(authUserString);
                    if (authUser != null){
                        userType = authUser.getType().getTypeLabel();
                    }
                }
            }
            return userType;
        }
}
