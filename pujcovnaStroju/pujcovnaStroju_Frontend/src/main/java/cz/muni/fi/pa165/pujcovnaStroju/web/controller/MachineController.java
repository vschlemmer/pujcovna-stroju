package cz.muni.fi.pa165.pujcovnaStroju.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String listMachines(ModelMap model) {
		MachineDTO dto = new MachineDTO();
		dto.setLabel("sss");
		dto.setDescription("eee");
		MachineTypeEnumDTO type = new MachineTypeEnumDTO();
		type.setId(1L);
		type.setTypeLabel("BULDOZER");
		dto.setType(type);
		System.out.println(machineService);
		System.out.println("xxx"); 
	    machineService.create(dto);
		model.addAttribute("machines", machineService.getAllMachines());
		model.addAttribute("list", "list of machines");
		model.addAttribute("pageTitle", "lang.listMachinesTitle");
		model.addAttribute("pageFooter", "lang.footer");
		return "listMachines";
	}

	@RequestMapping("/{id}")
	public void viewMachine(@PathVariable String id,ModelMap model) {
		model.addAttribute("message", "view " + id);
	}

	@RequestMapping("/add")
	public String addMachine(ModelMap model) {
		model.addAttribute("message", "adding machine");
		return "hello";
	}

}
