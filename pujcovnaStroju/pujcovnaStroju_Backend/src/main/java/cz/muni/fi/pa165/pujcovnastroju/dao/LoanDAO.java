package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;

/**
 * 
 * @author Ondřej Güttner
 */
public interface LoanDAO {

	/**
	 * Persist given loan into database
	 * 
	 * @param loan
	 *            to be created
	 * @return created loan
	 * @throws IllegalArgumentException
	 *             when loan is null
	 */
	public Loan create(Loan loan);

	/**
	 * Updates given loan
	 * 
	 * @param loan
	 *            to be updated
	 * @return updated loan
	 * @throws IllegalArgumentException
	 *             when loan or loan.id is null
	 */
	public Loan update(Loan loan);

	/**
	 * Reads loan with given id from database
	 * 
	 * @param id
	 *            of the loan to be read
	 * @return loan
	 * @throws IllegalArgumentException
	 *             when id is null
	 */
	public Loan read(Long id);

	/**
	 * Removes given loan from database
	 * 
	 * @param id
	 *            of the loan to be deleted
	 * @return deleted loan
	 * @throws IllegalArgumentException
	 *             when id is null
	 */
	public Loan delete(Long id);

	/**
	 * Returns all loans stored in the database
	 * 
	 * @return list of loans
	 */
	public List<Loan> getAllLoans();

	/**
	 * Returns loans with the given parameters stored in the database
	 * 
	 * @param loanedFrom
	 *            - determines the smallest loanTime of the returned loans
	 * @param loanedTill
	 *            - determines the largest returnTime of the returned loans
	 * @param loanState
	 *            - determines the current state of loan
	 * @param loanedBy
	 *            - user whose loans are to be returned
	 * @param includedMachine
	 *            - machine of which loans are to be returned
	 * @return list of the loans satisfying the parameters
	 */
	public List<Loan> getLoansByParams(Date loanedFrom, Date loanedTill,
			LoanStateEnum loanState, SystemUser loanedBy,
			Machine includedMachine);
}
