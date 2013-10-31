package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;

/**
 *
 * @author xguttner
 */
public class LoanStateEnumDTOConverter {
    
	public static LoanStateEnum dtoToEntity(LoanStateEnumDTO loanStateEnumDTO) {
	    LoanStateEnum loanStateEnum = LoanStateEnum.valueOf(loanStateEnumDTO.typeLabel);
	    return loanStateEnum;
	}
	
	public static LoanStateEnumDTO entityToDto(LoanStateEnum loanStateEnum) {
	    LoanStateEnumDTO loanStateEnumDTO = new LoanStateEnumDTO();
	    loanStateEnumDTO.setId(Long.valueOf(loanStateEnum.ordinal()));
	    loanStateEnumDTO.setTypeLabel(loanStateEnum.name());
	    return loanStateEnumDTO;
	}
}
