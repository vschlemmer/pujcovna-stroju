package cz.muni.fi.pa165.pujcovnastroju.converter;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.UnsupportedTypeException;
import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 * UserType enumeration DTO converter
 * 
 * @author Vojtech Schlemmer
 */
public class UserTypeDTOConverter {
	public static UserTypeEnum dtoToEntity(UserTypeEnumDTO dto) {
		if (dto == null)
			return null;
		UserTypeEnum type;
		try {
			type = UserTypeEnum.valueOf(dto.getTypeLabel());
		} catch (IllegalArgumentException e) {
			throw new UnsupportedTypeException("Unsuported user type");
		}
		return type;
	}

	public static UserTypeEnumDTO entityToDto(UserTypeEnum type) {
		if (type == null)
			return null;
		UserTypeEnumDTO dto = new UserTypeEnumDTO();
		dto.setId(Long.valueOf(type.ordinal()));
		dto.setTypeLabel(type.name());
		return dto;
	}

	public static List<UserTypeEnum> listToEntities(
			List<UserTypeEnumDTO> dtoTypes) {
		if (dtoTypes == null)
			return null;
		List<UserTypeEnum> types = new ArrayList<>();
		for (UserTypeEnumDTO dtoType : dtoTypes) {
			types.add(dtoToEntity(dtoType));
		}
		return types;
	}

	public static List<UserTypeEnumDTO> listToDTO(List<UserTypeEnum> types) {
		if (types == null)
			return null;
		List<UserTypeEnumDTO> dtoTypes = new ArrayList<>();
		for (UserTypeEnum type : types) {
			dtoTypes.add(entityToDto(type));
		}
		return dtoTypes;
	}
}
