package cz.muni.fi.pa165.pujcovnaStroju.web.controller;

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

	@RequestMapping("/")
	public String viewIndes(ModelMap model) {
		model.addAttribute("pageTitle", "lang.listMachinesTitle");
		addHeaderFooterInfo(model);
		return "index";
	}
	
	/**
	 * adds header and footer content to rendered page
	 */
	static ModelMap addHeaderFooterInfo(ModelMap model) {
		model.addAttribute("pageFooter", "lang.footer");
		model.addAttribute("pageHeader", "lang.header");
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
}
