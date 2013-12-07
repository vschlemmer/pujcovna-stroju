package cz.muni.fi.pa165.pujcovnaStroju.rest.controller;

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

import cz.muni.fi.pa165.pujcovnaStroju.rest.converter.DTOtoXMLConverter;
import cz.muni.fi.pa165.pujcovnaStroju.web.converter.StringToMachineTypeEnumDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.UnsupportedTypeException;
import cz.muni.fi.pa165.pujcovnastroju.service.MachineService;

/**
 * REST controller for machines
 * 
 * @author Michal Merta
 * 
 */
@Controller
@RequestMapping("/rest/machine")
public class MachineRestController {

	MachineService machineService;

	@Autowired
	public MachineRestController(MachineService machineService) {
		this.machineService = machineService;
	}

	@RequestMapping(value = "/list")
	public HttpEntity<byte[]> getXml(ModelMap map,
			HttpServletResponse response,
			@RequestParam(required = false) String type) {

		StringBuilder builder = new StringBuilder();
		builder.append("<response>");

		StringToMachineTypeEnumDTOConverter converter = new StringToMachineTypeEnumDTOConverter();
		MachineTypeEnumDTO typeDTO = converter.convert(type);
		
		List<MachineDTO> listMachines = null;
		try {
			listMachines = machineService.getMachineDTOsByParams(null, null,
					typeDTO, null, null, null, null);
			if (listMachines == null) {
				listMachines = new ArrayList<>();
			}
		} catch (UnsupportedTypeException e) {
			return GenericController.returnErrorXML("Unknown machine type: " + type);
		} catch (DataAccessException e) {
			return GenericController
					.returnErrorXML("Error occured during request processing");
		}
		
		builder.append("<machines>");
		System.out.println(listMachines);
		for (MachineDTO machine : listMachines) {
			builder.append(DTOtoXMLConverter.machineDTOtoXML(machine));
		}
		builder.append("</machines>");
		builder.append("</response>");

		return GenericController.returnXML(builder.toString());
	}

}
