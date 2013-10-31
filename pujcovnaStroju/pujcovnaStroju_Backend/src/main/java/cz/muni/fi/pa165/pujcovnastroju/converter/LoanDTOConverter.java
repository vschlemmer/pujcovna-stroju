/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xguttner
 */
public class LoanDTOConverter {
    public static Loan dtoToEntity(LoanDTO loanDTO) {
	Loan loan = new Loan();
	loan.setId(loanDTO.getId());
	loan.setCustomer(SystemUserDTOConverter.dtoToEntity(loanDTO.getCustomer()));
	loan.setLoanTime(loanDTO.getLoanTime());
	loan.setReturnTime(loanDTO.getReturnTime());
	loan.setLoanState(LoanStateEnumDTOConverter.dtoToEntity(loanDTO.getLoanState()));
	loan.setMachines(MachineDTOConverter.listToEntities(loanDTO.getMachines()));
	return loan;
    }
    
    public static LoanDTO entityToDTO(Loan loan) {
	LoanDTO loanDTO = new LoanDTO();
	loanDTO.setId(loan.getId());
	loanDTO.setCustomer(SystemUserDTOConverter.entityToDTO(loan.getCustomer()));
	loanDTO.setLoanTime(loan.getLoanTime());
	loanDTO.setReturnTime(loan.getReturnTime());
	loanDTO.setLoanState(LoanStateEnumDTOConverter.entityToDto(loan.getLoanState()));
	loanDTO.setMachines(MachineDTOConverter.listToDto(loan.getMachines()));
	return loanDTO;
    }
    
    public static List<Loan> listToEntities(List<LoanDTO> loanDTOs) {
	List<Loan> loans = new ArrayList<>();
	for (LoanDTO loanDTO : loanDTOs) {
	    loans.add(LoanDTOConverter.dtoToEntity(loanDTO));
	}
	return loans;
    }
    
    public static List<LoanDTO> listToDTOs(List<Loan> loans) {
	List<LoanDTO> loanDTOs = new ArrayList<>();
	for (Loan loan : loans) {
	    loanDTOs.add(LoanDTOConverter.entityToDTO(loan));
	}
	return loanDTOs;
    }
}
