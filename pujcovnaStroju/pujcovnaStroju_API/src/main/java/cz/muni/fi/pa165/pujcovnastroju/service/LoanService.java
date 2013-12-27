package cz.muni.fi.pa165.pujcovnastroju.service;

import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.LoanStateEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

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
	 * @throws DataAccessException
	 *             when loan is null
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') "
			+ "OR (#loanDTO.loanState.typeLabel == 'BOOKED' AND "
			+ "#loanDTO.customer.username == principal.username AND "
			+ "(hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')))")
	public LoanDTO create(LoanDTO loanDTO);

	/**
	 * Updates given loanDTO
	 * 
	 * @param loanDTO
	 *            to be updated
	 * @return updated loanDTO
	 * @throws DataAccessException
	 *             when loan or loan.id is null
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') "
			+ "OR (#loanDTO.loanState.typeLabel == 'BOOKED' AND "
			+ "#loanDTO.customer.username == principal.username AND "
			+ "(hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')))")
	public LoanDTO update(LoanDTO loanDTO);

	/**
	 * Reads loanDTO with given id from database
	 * 
	 * @param id
	 *            of the loanDTO to be read
	 * @return loanDTO or null if it's not presented in db
	 * @throws DataAccessException
	 *             when id is null
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') OR "
			+ "hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')")
	@PostAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') OR "
			+ "(returnObject.customer.username == principal.username AND "
			+ "(hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')))")
	public LoanDTO read(Long id);

	/**
	 * Removes given loanDTO from database
	 * 
	 * @param id
	 *            of the loanDTO to be deleted
	 * @return deleted loanDTO
	 * @throws DataAccessException
	 *             when id is null
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') "
			+ "OR (#loanDTO.loanState.typeLabel == 'BOOKED' AND "
			+ "#loanDTO.customer.username == principal.username AND "
			+ "(hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')))")
	public LoanDTO delete(LoanDTO loanDTO);

	/**
	 * Returns all loanDTOs stored in the database
	 * 
	 * @return list of loanDTOs
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') "
			+ "OR hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')")
	@PostFilter("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') OR "
			+ "((hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')) AND "
			+ "filterObject.customer.username == principal.username)")
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
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') "
			+ "OR hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')")
	@PostFilter("hasRole('ADMINISTRATOR') OR hasRole('EMPLOYEE') OR "
			+ "((hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')) AND "
			+ "filterObject.customer.username == principal.username)")
	public List<LoanDTO> getLoansByParams(Date loanedFrom, Date loanedTill,
			LoanStateEnumDTO loanStateEnumDTO, SystemUserDTO loanedBy,
			MachineDTO includedMachine);
}
