package cz.muni.fi.pa165.pujcovnaStroju.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.muni.fi.pa165.pujcovnaStroju.rest.converter.DTOtoXMLConverter;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.service.MachineService;

@Controller
@RequestMapping("/rest/machine")
public class MachineRestController {

	MachineService machineService;

	@Autowired
	public MachineRestController(MachineService machineService) {
		this.machineService = machineService;
	}

	@RequestMapping(value="/list")
	public HttpEntity<byte[]> getXml(ModelMap map, HttpServletResponse response) {

		StringBuilder builder = new StringBuilder();
		builder.append("<response>");
		builder.append("<machines>");
		for(MachineDTO machine: machineService.getAllMachines()) {
			builder.append(DTOtoXMLConverter.machineDTOtoXML(machine));
		}
		builder.append("</machines>");
		builder.append("</response>");
		
	    byte[] documentBody = builder.toString().getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
	}
}
