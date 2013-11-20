/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.converter;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;

/**
 * 
 * @author xguttner
 */
public class LoanDTOConverter {

	/**
	 * Converts DTO to entity object
	 * 
	 * @param loanDTO
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * @return Loan object or null if loanDTO is null
	 */
	public static Loan dtoToEntity(LoanDTO loanDTO, boolean bounded) {
		if (loanDTO == null)
			return null;

		Loan loan = new Loan();
		loan.setId(loanDTO.getId());
		loan.setLoanState(LoanStateEnumDTOConverter.dtoToEntity(loanDTO
				.getLoanState()));
		loan.setLoanTime(loanDTO.getLoanTime());
		loan.setReturnTime(loanDTO.getReturnTime());
		if (!bounded) {

			loan.setCustomer(SystemUserDTOConverter.dtoToEntity(
					loanDTO.getCustomer(), bounded));
			loan.setMachines(MachineDTOConverter.listToEntities(
					loanDTO.getMachines(), true));
		}
		return loan;
	}

	/**
	 * Converts entity object to DTO
	 * 
	 * @param loan
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * 
	 * @return LoanDTO or null if loan is null
	 */
	public static LoanDTO entityToDTO(Loan loan, boolean bounded) {
		if (loan == null)
			return null;

		LoanDTO loanDTO = new LoanDTO();
		loanDTO.setId(loan.getId());
		loanDTO.setLoanTime(loan.getLoanTime());
		loanDTO.setReturnTime(loan.getReturnTime());
		loanDTO.setLoanState(LoanStateEnumDTOConverter.entityToDto(loan
				.getLoanState()));
		if (!bounded) {
			loanDTO.setCustomer(SystemUserDTOConverter.entityToDTO(
					loan.getCustomer(), true));
			loanDTO.setMachines(MachineDTOConverter.listToDto(
					loan.getMachines(), true));
		}
		return loanDTO;
	}

	/**
	 * Converts list of DTOs to list of entity objects
	 * 
	 * @param loanDTOs
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * @return list of Loan or null if loanDTOs is null
	 */
	public static List<Loan> listToEntities(List<LoanDTO> loanDTOs,
			boolean bounded) {
		if (loanDTOs == null)
			return null;

		List<Loan> loans = new ArrayList<>();
		for (LoanDTO loanDTO : loanDTOs) {
			loans.add(LoanDTOConverter.dtoToEntity(loanDTO, bounded));
		}
		return loans;
	}

	/**
	 * Converts list of entity objects to list of DTOs
	 * 
	 * @param loans
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * @return list of DTOs or null if loans is null
	 */
	public static List<LoanDTO> listToDTOs(List<Loan> loans, boolean bounded) {
		if (loans == null)
			return null;

		List<LoanDTO> loanDTOs = new ArrayList<>();
		for (Loan loan : loans) {
			loanDTOs.add(LoanDTOConverter.entityToDTO(loan, bounded));
		}
		return loanDTOs;
	}
}
