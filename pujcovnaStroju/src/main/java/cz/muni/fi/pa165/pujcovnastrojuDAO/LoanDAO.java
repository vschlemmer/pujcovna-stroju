package cz.muni.fi.pa165.pujcovnastrojuDAO;

import cz.muni.fi.pa165.pujcovnastroju.Loan;
import cz.muni.fi.pa165.pujcovnastroju.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author gitti
 */
public interface LoanDAO {
    
    /**
     * 
     * @param loan to be created
     * @return created loan
     */
    public Loan create(Loan loan);
    
    /**
     * 
     * @param loan to be updated
     * @return updated loan
     */
    public Loan update(Loan loan);
    
    /**
     * 
     * @param id of the loan to be read
     * @return loan
     */
    public Loan read(Long id);
    
    /**
     * 
     * @param id of the loan to be deleted
     * @return deleted loan
     */
    public Loan delete(Long id);
    
    /**
     * 
     * @return all loans stored in the system 
     */
    public List<Loan> getAllLoans();
    
    /**
     * 
     * @param loanedFrom - determines the smallest loanTime of the returned loans
     * @param loanedTill - determines the largest returnTime of the returned loans
     * @param loanState - determines the current state of loan
     * @param loanedBy - user whose loans are to be returned
     * @param includedMachine - machine of which loans are to be returned
     * @return list of the loans satisfying the parameters
     */
    public List<Loan> getLoansByParams(Timestamp loanedFrom, Timestamp loanedTill, LoanStateEnum loanState, SystemUser loanedBy, Machine includedMachine);
}
