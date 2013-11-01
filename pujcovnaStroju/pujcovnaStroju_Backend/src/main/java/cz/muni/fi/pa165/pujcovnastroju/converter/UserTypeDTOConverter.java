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
        if (dto == null) return null;
        UserTypeEnum type = UserTypeEnum.valueOf(dto.getTypeLabel());
        return type;
    }

    public static UserTypeEnumDTO entityToDto(UserTypeEnum type) {
        if (type == null) return null;
        UserTypeEnumDTO dto = new UserTypeEnumDTO();
        dto.setId(Long.valueOf(type.ordinal()));
        dto.setTypeLabel(type.name());
        return dto;
    }
}
