/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.sql.Timestamp;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import junit.framework.TestCase;
import cz.muni.fi.pa165.pujcovnastroju.Loan;
import cz.muni.fi.pa165.pujcovnastroju.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.MachineTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gitti
 */
public class LoanDAOTest extends TestCase {
    
    private LoanDAO loanDAO;
    
    @Before
    @Override
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        loanDAO = new LoanDAOImpl(emf);
    }
    
    public Loan createSampleLoan() {
        Loan loan = new Loan();
        loan.setLoanTime(new Timestamp(System.currentTimeMillis()));
        loan.setReturnTime(new Timestamp(System.currentTimeMillis()+36000000));
        SystemUser su = new SystemUser();
        su.setLastName("Smith");
        su.setFirstName("Paul");
        su.setEmploee(false);
        loan.setCustomer(su);
        loan.setLoanState(LoanStateEnum.BOOKED);
        List<Machine> machines = new ArrayList<Machine>();
        Machine machine1 = new Machine();
        machine1.setLabel("machine1");
        machine1.setType(MachineTypeEnum.DRILL);
        Machine machine2 = new Machine();
        machine2.setLabel("machine2");
        machine2.setType(MachineTypeEnum.BULDOZER);
        machines.add(machine1);
        machines.add(machine2);
        loan.setMachines(machines);
        return loan;
    }

    /**
     * Test of create method, of class LoanDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Loan loan = createSampleLoan();
        assertNull(loan.getId());
        Loan expResult = loan;
        Loan result = loanDAO.create(loan);
        assertEquals(expResult, result);
        assertEquals(expResult.getCustomer(), result.getCustomer());
        assertEquals(expResult.getLoanState(), result.getLoanState());
        assertEquals(expResult.getLoanTime(), result.getLoanTime());
        assertEquals(expResult.getReturnTime(), result.getReturnTime());
        assertEquals(expResult.getMachines(), result.getMachines());
        assertNotNull(expResult.getId());
        assertNotNull(result.getId());
        
        SystemUser su = loan.getCustomer();
        loanDAO.delete(result.getId());
        assertNotNull(su.getId());
    }

    /**
     * Test of update method, of class LoanDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Loan loan = createSampleLoan();
        loanDAO.create(loan);
        loan.setLoanTime(new Timestamp(System.currentTimeMillis()+10));
        loan.setReturnTime(new Timestamp(System.currentTimeMillis()+20));
        loan.setCustomer(new SystemUser());
        loan.setMachines(null);
        loan.setLoanState(LoanStateEnum.LOANED);
        Loan expResult = loan;
        Loan result = loanDAO.update(loan);
        assertEquals(expResult, result);
        assertEquals(expResult.getCustomer(), result.getCustomer());
        assertEquals(expResult.getLoanState(), result.getLoanState());
        assertEquals(expResult.getLoanTime(), result.getLoanTime());
        assertEquals(expResult.getReturnTime(), result.getReturnTime());
        assertEquals(expResult.getMachines(), result.getMachines());
    }

    /**
     * Test of read method, of class LoanDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Loan loan = createSampleLoan();
        loanDAO.create(loan);
        Long id = loan.getId();
        Loan expResult = loan;
        Loan result = loanDAO.read(id);
        assertEquals(expResult, result);
        assertEquals(expResult.getCustomer(), result.getCustomer());
        assertEquals(expResult.getLoanState(), result.getLoanState());
        assertEquals(expResult.getLoanTime(), result.getLoanTime());
        assertEquals(expResult.getReturnTime(), result.getReturnTime());
        //assertEquals(expResult.getMachines(), result.getMachines());
    }

    /**
     * Test of delete method, of class LoanDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Loan loan = createSampleLoan();
        loanDAO.create(loan);
        Long id = loan.getId();
        Loan expResult = loan;
        Loan result = loanDAO.delete(id);
        assertEquals(expResult, result);
        assertNull(loanDAO.read(expResult.getId()));
    }

    /**
     * Test of getAllLoans method, of class LoanDAO.
     */
    @Test
    public void testGetAllLoans() {
        System.out.println("getAllLoans");
        Loan loan = createSampleLoan();
        loanDAO.create(loan);
        List expResult = new ArrayList();
        expResult.add(loan);
        List result = loanDAO.getAllLoans();
        assertEquals(expResult, result);
        Loan loan2 = createSampleLoan();
        loanDAO.create(loan2);
        expResult.add(loan2);
        result = loanDAO.getAllLoans();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoansByParams method, of class LoanDAO.
     */
    @Test
    public void testGetLoansByParams() {
        System.out.println("getLoansByParams");
        Timestamp loanedFrom = new Timestamp(System.currentTimeMillis()+5000);
        Timestamp loanedTill = new Timestamp(System.currentTimeMillis()+5001);
        LoanStateEnum loanState = LoanStateEnum.RETURNED;
        
        Loan loan = createSampleLoan();
        loanDAO.create(loan);
        
        Loan loan2 = createSampleLoan();
        loan2.setLoanState(loanState);
        loan2.setLoanTime(loanedFrom);
        loan2.setReturnTime(loanedTill);
        loanDAO.create(loan2);
        
        SystemUser loanedBy = loan2.getCustomer();
        Machine includedMachine = loan2.getMachines().get(0);
        
        List expResult = new ArrayList();
        expResult.add(loan);
        expResult.add(loan2);
        
        List result = loanDAO.getLoansByParams(null, null, null, null, null);
        assertEquals(expResult, result);
        
        expResult.remove(loan);
        result = loanDAO.getLoansByParams(loanedFrom, loanedTill, loanState, loanedBy, includedMachine);
        assertEquals(expResult, result);
    }
}
