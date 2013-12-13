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

        /**
	 * Converts DTO to entity object
	 * 
	 * @param dto
	 *            to be converted
	 * @return UserTypeEnum object or null if dto is null
	 */
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

        /**
	 * Converts entity object to DTO
	 * 
	 * @param type
	 *            to be converted
	 * 
	 * @return UserTypeEnumDTO or null if type is null
	 */
	public static UserTypeEnumDTO entityToDto(UserTypeEnum type) {
		if (type == null)
			return null;
		UserTypeEnumDTO dto = new UserTypeEnumDTO();
		dto.setId(Long.valueOf(type.ordinal()));
		dto.setTypeLabel(type.name());
		return dto;
	}

        /**
	 * Converts list of DTOs to list of entity objects
	 * 
	 * @param dtoTypes
	 *            to be converted
	 * @return list of UserTypeEnum or null if dtoTypes is null
	 */
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

        /**
	 * Converts list of entity objects to list of DTOs
	 * 
	 * @param types
	 *            to be converted
	 * @return list of UserTypeEnumDTO or null if types is null
	 */
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
