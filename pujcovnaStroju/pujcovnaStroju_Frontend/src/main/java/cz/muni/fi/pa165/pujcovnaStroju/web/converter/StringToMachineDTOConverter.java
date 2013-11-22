package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import org.springframework.core.convert.converter.Converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;

public class StringToMachineDTOConverter implements
		Converter<String, MachineDTO> {

	@Override
	public MachineDTO convert(String arg0) {
		MachineDTO machine = new MachineDTO();
		System.out.println("CONVERT");
		try {
			Long id = Long.valueOf(arg0);
			machine.setId(id);
			return machine;
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
