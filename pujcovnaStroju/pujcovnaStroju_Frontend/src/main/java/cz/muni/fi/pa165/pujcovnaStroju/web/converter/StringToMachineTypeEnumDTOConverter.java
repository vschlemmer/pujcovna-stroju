package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import org.springframework.core.convert.converter.Converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;

public final class StringToMachineTypeEnumDTOConverter implements
		Converter<String, MachineTypeEnumDTO> {
	public MachineTypeEnumDTO convert(String source) {
		System.out.println("convert");
		MachineTypeEnumDTO type = new MachineTypeEnumDTO();
		type.setTypeLabel(source);
		return type;
	}
}
