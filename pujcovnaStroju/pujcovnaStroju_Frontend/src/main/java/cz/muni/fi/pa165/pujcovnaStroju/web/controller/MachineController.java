package cz.muni.fi.pa165.pujcovnaStroju.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.service.MachineService;

@Controller
@RequestMapping("/machine")
public class MachineController {

	MachineService machineService;

	@Autowired
	public MachineController(MachineService machineService) {
		this.machineService = machineService;
		System.out.println("CONT");
	}

	@RequestMapping("")
	public String redirectToList(ModelMap model) {
		return "redirect:/machine/list";
	}

	@RequestMapping("/")
	public String redirectToListBackslash(ModelMap model) {
		return "redirect:/machine/list";
	}

	@RequestMapping("/list")
	public ModelAndView listMachines(ModelMap model) {
		model.addAttribute("machines", machineService.getAllMachines());
		model.addAttribute("list", "list of machines");
		model.addAttribute("pageTitle", "lang.listMachinesTitle");
		model.addAttribute("pageFooter", "lang.footer");
		return new ModelAndView("listMachines","command",new MachineDTO());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("machine") MachineDTO machine,
			BindingResult result) {

		machineService.create(machine);

		return "redirect:list";
	}

	@RequestMapping("/{id}")
	public void viewMachine(@PathVariable String id, ModelMap model) {
		model.addAttribute("message", "view " + id);
	}


}
