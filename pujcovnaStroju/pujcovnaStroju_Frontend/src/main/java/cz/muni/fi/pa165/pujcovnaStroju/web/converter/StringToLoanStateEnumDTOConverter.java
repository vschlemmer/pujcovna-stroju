package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author xguttner
 */
public class StringToLoanStateEnumDTOConverter implements
		Converter<String, LoanStateEnumDTO> {
	public LoanStateEnumDTO convert(String source) {
		LoanStateEnumDTO type = new LoanStateEnumDTO();
		type.setTypeLabel(source);
		return type;
	}
}
