package cz.muni.fi.pa165.pujcovnaStroju.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping("/")
	public String viewIndes(ModelMap model) {
		model.addAttribute("pageTitle", "lang.listMachinesTitle");
		model.addAttribute("pageFooter", "lang.footer");
		return "index";
	}
}
