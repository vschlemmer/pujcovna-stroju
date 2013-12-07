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

	private static StringToMachineTypeEnumDTOConverter converter = new StringToMachineTypeEnumDTOConverter();

	MachineService machineService;

	@Autowired
	public MachineRestController(MachineService machineService) {
		this.machineService = machineService;
	}

	@RequestMapping(value = "/list")
	public HttpEntity<byte[]> listMachines(ModelMap map,
			HttpServletResponse response,
			@RequestParam(required = false) String type) {

		MachineTypeEnumDTO typeDTO = converter.convert(type);

		List<MachineDTO> listMachines = null;
		try {
			listMachines = machineService.getMachineDTOsByParams(null, null,
					typeDTO, null, null, null, null);
			if (listMachines == null) {
				listMachines = new ArrayList<>();
			}
		} catch (UnsupportedTypeException e) {
			return GenericController.returnErrorXML("Unknown machine type: "
					+ type);
		} catch (DataAccessException e) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		}

		StringBuilder builder = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		builder.append("<response status=\"success\">");
		builder.append("<machines numFound=\"" + listMachines.size() + "\">");
		for (MachineDTO machine : listMachines) {
			builder.append(DTOtoXMLConverter.machineDTOtoXML(machine));
		}
		builder.append("</machines>");
		builder.append("</response>");

		return GenericController.returnXML(builder.toString());
	}

	@RequestMapping(value = "/detail")
	public HttpEntity<byte[]> detailOfMachine(ModelMap map,
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

		MachineDTO machine = null;
		try {
			machine = machineService.read(lid);
			if (machine == null) {
				return GenericController.returnErrorXML("Machine with id: "
						+ lid + " not found");
			}
		} catch (DataAccessException e) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		}

		StringBuilder builder = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		builder.append("<response status=\"success\">");
		builder.append(DTOtoXMLConverter.machineDTOtoXML(machine));
		builder.append("</response>");

		return GenericController.returnXML(builder.toString());
	}

	@RequestMapping(value = "/add")
	public HttpEntity<byte[]> addMachine(ModelMap map,
			HttpServletResponse response,
			@RequestParam(required = false) String label,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String type) {

		List<String> errorMessages = new ArrayList<>();
		if (label == null || label.isEmpty()) {
			errorMessages.add("Missing required argument: label");
		}
		if (type == null || type.isEmpty()) {
			errorMessages.add("Missing required argument: type");
		}
		if (description == null || description.isEmpty()) {
			errorMessages.add("Missing required argument: description");
		}

		if (!errorMessages.isEmpty()) {
			return GenericController.returnErrorXML(errorMessages);
		}

		MachineDTO machine = new MachineDTO();
		machine.setLabel(label);
		machine.setDescription(description);
		machine.setType(converter.convert(type.toUpperCase()));

		MachineDTO created = null;
		try {
			created = machineService.create(machine);
		} catch (UnsupportedTypeException e) {
			return GenericController
					.returnErrorXML("Unsupported machine type: " + type);
		} catch (DataAccessException e) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		}

		if (created == null) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		} else {
			return GenericController
					.returnSuccessXML("Machine created with id:"
							+ created.getId());
		}

	}

	@RequestMapping(value = "/delete")
	public HttpEntity<byte[]> deleteMachine(ModelMap map,
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
			MachineDTO machine = machineService.read(lid);
			if (machine == null) {
				return GenericController.returnErrorXML("Machine with id: "
						+ lid + " not found");
			}
			machineService.delete(machine);
		} catch (DataAccessException e) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		}

		return GenericController.returnSuccessXML("Machine with id " + lid
				+ " successfully deleted");
	}

	@RequestMapping(value = "/update")
	public HttpEntity<byte[]> updateMachine(ModelMap map,
			HttpServletResponse response,
			@RequestParam(required = false) String id,
			@RequestParam(required = false) String label,
			@RequestParam(required = false) String description,
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
		if (label == null || label.isEmpty() || type == null || type.isEmpty()
				|| description == null || description.isEmpty()) {
			errorMessages
					.add("None of arguments (label, description, type) set, nothing to update");
		}

		MachineDTO machine = null;
		MachineDTO updated = null;
		MachineTypeEnumDTO typeDTO = converter.convert(type);
		try {
			machine = machineService.read(lid);
			if (machine == null) {
				return GenericController.returnErrorXML("Machine with id: "
						+ lid + " not found");
			}
			if (label != null && !label.isEmpty()) {
				machine.setLabel(label);
			}
			if (description != null && !description.isEmpty()) {
				machine.setDescription(description);
			}
			if (typeDTO.getTypeLabel() != null) {
				machine.setType(typeDTO);
			}
			updated = machineService.update(machine);
		} catch (UnsupportedTypeException e) {
			return GenericController
					.returnErrorXML("Unsupported machine type: " + type);
		} catch (DataAccessException e) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		}

		if (updated == null) {
			return GenericController
					.returnErrorXML("Error occured during processing request");
		} else {
			return GenericController.returnSuccessXML("Machine with id: "
					+ updated.getId() + " successfully updated");
		}

	}

}
