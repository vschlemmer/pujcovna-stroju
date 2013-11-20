package cz.muni.fi.pa165.pujcovnastroju.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.pujcovnastroju.converter.LoanDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.converter.LoanStateEnumDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.converter.MachineDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.converter.SystemUserDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dao.LoanDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import cz.muni.fi.pa165.pujcovnastroju.service.LoanService;

/**
 * 
 * @author xguttner
 */
@Service("loanService")
@Transactional
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanDAO loanDAO;

	@Override
	public LoanDTO create(LoanDTO loanDTO) {
		LoanDTO ldto = null;
		Loan loan = null;
		try {
			loan = loanDAO.create(LoanDTOConverter.dtoToEntity(loanDTO,false));
			ldto = LoanDTOConverter.entityToDTO(loan,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during storing loan.", e);
		}

		return ldto;
	}

	@Override
	public LoanDTO update(LoanDTO loanDTO) {
		LoanDTO ldto = null;
		Loan loan = null;
		try {
			loan = loanDAO.update(LoanDTOConverter.dtoToEntity(loanDTO,false));
			ldto = LoanDTOConverter.entityToDTO(loan,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during updating loan.", e);
		}

		return ldto;
	}

	@Override
	public LoanDTO read(Long id) {
		LoanDTO ldto = null;
		Loan loan = null;
		try {
			loan = loanDAO.read(id);
			ldto = LoanDTOConverter.entityToDTO(loan,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during reading loan.", e);
		}

		return ldto;
	}

	@Override
	public LoanDTO delete(Long id) {
		LoanDTO ldto = null;
		Loan loan = null;
		try {
			loan = loanDAO.delete(id);
			ldto = LoanDTOConverter.entityToDTO(loan,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during deleting loan.", e);
		}

		return ldto;
	}

	@Override
	public List<LoanDTO> getAllLoans() {
		List<LoanDTO> ldtos = null;
		List<Loan> loans = null;

		loans = loanDAO.getAllLoans();
		ldtos = LoanDTOConverter.listToDTOs(loans,false);

		return ldtos;
	}

	@Override
	public List<LoanDTO> getLoansByParams(Date loanedFrom, Date loanedTill,
			LoanStateEnumDTO loanStateEnumDTO, SystemUserDTO loanedBy,
			MachineDTO includedMachine) {
		List<LoanDTO> ldtos = null;
		List<Loan> loans = null;

		loans = loanDAO.getLoansByParams(loanedFrom, loanedTill,
				LoanStateEnumDTOConverter.dtoToEntity(loanStateEnumDTO),
				SystemUserDTOConverter.dtoToEntity(loanedBy,false),
				MachineDTOConverter.dtoToEntity(includedMachine,false));
		ldtos = LoanDTOConverter.listToDTOs(loans,false);

		return ldtos;
	}

}
