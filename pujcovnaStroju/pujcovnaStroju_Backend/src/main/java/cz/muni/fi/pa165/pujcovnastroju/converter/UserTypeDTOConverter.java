package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 * UserType enumeration DTO converter
 * 
 * @author Vojtech Schlemmer
 */
public class UserTypeDTOConverter {
    public static UserTypeEnum dtoToEntity(UserTypeEnumDTO dto) {
            UserTypeEnum type = UserTypeEnum.valueOf(dto.getTypeLabel());
            return type;
    }

    public static UserTypeEnumDTO entityToDto(UserTypeEnum type) {
            UserTypeEnumDTO dto = new UserTypeEnumDTO();
            dto.setId(Long.valueOf(type.ordinal()));
            dto.setTypeLabel(type.name());
            return dto;
    }
}
