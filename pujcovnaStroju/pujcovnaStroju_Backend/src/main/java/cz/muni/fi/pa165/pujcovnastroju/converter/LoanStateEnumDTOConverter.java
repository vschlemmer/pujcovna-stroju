package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;

/**
 *
 * @author xguttner
 */
public class LoanStateEnumDTOConverter {
	/**
	 * Converts DTO to entity object.
	 *
	 * @param loanStateEnumDTO - dto object to be converted
	 * @return value of LoanStateEnum or null if loanStateEnumDTO is null
	 */
	public static LoanStateEnum dtoToEntity(LoanStateEnumDTO loanStateEnumDTO) {
	    if (loanStateEnumDTO == null) return null;
	    
	    LoanStateEnum loanStateEnum = LoanStateEnum.valueOf(loanStateEnumDTO.typeLabel);
	    return loanStateEnum;
	}
	
	/**
	 * Converts entity object to DTO.
	 *
	 * @param loanStateEnum - entity object to be converted
	 * @return LoanStateEnumDTO object or null if loanStateEnum is null
	 */
	public static LoanStateEnumDTO entityToDto(LoanStateEnum loanStateEnum) {
	    if (loanStateEnum == null) return null;
	    
	    LoanStateEnumDTO loanStateEnumDTO = new LoanStateEnumDTO();
	    loanStateEnumDTO.setId(Long.valueOf(loanStateEnum.ordinal()));
	    loanStateEnumDTO.setTypeLabel(loanStateEnum.name());
	    return loanStateEnumDTO;
	}
}
