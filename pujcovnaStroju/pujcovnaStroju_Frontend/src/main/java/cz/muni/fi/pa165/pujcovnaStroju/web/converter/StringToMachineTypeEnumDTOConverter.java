package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import org.springframework.core.convert.converter.Converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;

public final class StringToMachineTypeEnumDTOConverter implements
		Converter<String, MachineTypeEnumDTO> {
	public MachineTypeEnumDTO convert(String source) {
		MachineTypeEnumDTO type = new MachineTypeEnumDTO();
		if (source != null) {
			source = source.toUpperCase();
		}
		type.setTypeLabel(source);
		return type;
	}
}
