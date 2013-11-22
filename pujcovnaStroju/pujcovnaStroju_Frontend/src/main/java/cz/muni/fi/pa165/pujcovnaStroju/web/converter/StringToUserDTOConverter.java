package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import org.springframework.core.convert.converter.Converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;

public class StringToUserDTOConverter implements Converter<String, SystemUserDTO> {
	
	@Override
	public SystemUserDTO convert(String arg0) {
		SystemUserDTO user = new SystemUserDTO();
		try {
			Long id = Long.valueOf(arg0);
			user.setId(id);
			return user;
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
