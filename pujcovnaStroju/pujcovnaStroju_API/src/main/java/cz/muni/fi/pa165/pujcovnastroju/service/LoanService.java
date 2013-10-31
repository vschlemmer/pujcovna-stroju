package cz.muni.fi.pa165.pujcovnastroju.service;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xguttner
 */
public interface LoanService {
	/**
	 * Persist given loanDTO into database
	 * 
	 * @param loanDTO
	 *            to be persisted
	 * @return persisted loanDTO
	 * @throws IllegalArgumentException
	 *             when loan is null
	 */
	public LoanDTO create(LoanDTO loanDTO);

	/**
	 * Updates given loanDTO
	 * 
	 * @param loanDTO
	 *            to be updated
	 * @return updated loanDTO
	 * @throws IllegalArgumentException
	 *             when loan or loan.id is null
	 */
	public LoanDTO update(LoanDTO loanDTO);

	/**
	 * Reads loanDTO with given id from database
	 * 
	 * @param id
	 *            of the loanDTO to be read
	 * @return loanDTO or null if it's not presented in db
	 * @throws IllegalArgumentException
	 *             when id is null
	 */
	public LoanDTO read(Long id);

	/**
	 * Removes given loanDTO from database
	 * 
	 * @param id
	 *            of the loanDTO to be deleted
	 * @return deleted loanDTO
	 * @throws IllegalArgumentException
	 *             when id is null
	 */
	public LoanDTO delete(Long id);

	/**
	 * Returns all loanDTOs stored in the database
	 * 
	 * @return list of loanDTOs
	 */
	public List<LoanDTO> getAllLoans();

	/**
	 * Returns loanDTOs with the given parameters stored in the database
	 * 
	 * @param loanedFrom
	 *            - determines the smallest loanTime of the returned loanDTOs
	 * @param loanedTill
	 *            - determines the largest returnTime of the returned loanDTOs
	 * @param loanStateEnumDTO
	 *            - determines the current state of loanDTO
	 * @param loanedBy
	 *            - user whose loans are to be returned
	 * @param includedMachine
	 *            - machine of which loans are to be returned
	 * @return list of the loanDTOs satisfying the parameters
	 */
	public List<LoanDTO> getLoansByParams(Date loanedFrom, Date loanedTill,
			LoanStateEnumDTO loanStateEnumDTO, SystemUserDTO loanedBy,
			MachineDTO includedMachine);
}
