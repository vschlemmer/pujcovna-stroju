package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;
import org.springframework.core.convert.converter.Converter;

public final class StringToSystemUserTypeEnumDTOConverter implements
		Converter<String, UserTypeEnumDTO> {
	public UserTypeEnumDTO convert(String source) {
		System.out.println("convert");
		UserTypeEnumDTO type = new UserTypeEnumDTO();
		type.setTypeLabel(source);
		return type;
	}
}
