package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.UnsupportedTypeException;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;

/**
 * Basic Machine type enumeration DTO converter
 * 
 * @author Michal Merta 374015
 * 
 */
public class MachineTypeDTOConverter {

        
        /**
	 * Converts DTO to entity object
	 * 
	 * @param dto
	 *            to be converted
	 * @return MachineTypeEnum object or null if dto is null
	 */
        public static MachineTypeEnum dtoToEntity(MachineTypeEnumDTO dto) {
		if (dto == null)
			return null;
		MachineTypeEnum type;
		try {
			 type = MachineTypeEnum.valueOf(dto.typeLabel);
		} catch (IllegalArgumentException e) {
			throw new UnsupportedTypeException("Unsuported machine type");
		}
		return type;
	}

        /**
	 * Converts entity object to DTO
	 * 
	 * @param type
	 *            to be converted
	 * 
	 * @return MachineTypeEnumDTO or null if type is null
	 */
	public static MachineTypeEnumDTO entityToDto(MachineTypeEnum type) {
		if (type == null)
			return null;

		MachineTypeEnumDTO dto = new MachineTypeEnumDTO();
		dto.setId(Long.valueOf(type.ordinal()));
		dto.setTypeLabel(type.name());
		return dto;
	}
}
