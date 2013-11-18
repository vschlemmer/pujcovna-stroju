package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public final class StringToSystemUserDTOConverter implements
		Converter<String, SystemUserDTO> {
    
    @Autowired
    private SystemUserService systemUserService;
    
	public SystemUserDTO convert(String id) {
		System.out.println("convert user "+id);
		SystemUserDTO systemUserDTO = systemUserService.read(Long.parseLong(id));
		return systemUserDTO;
	}
}
