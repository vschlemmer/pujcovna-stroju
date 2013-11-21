package cz.muni.fi.pa165.pujcovnastroju.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cz.muni.fi.pa165.pujcovnastroju.dao.LoanDAO;
import cz.muni.fi.pa165.pujcovnastroju.dao.LoanDAOImpl;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 *
 * @author Ondřej Güttner
 */
public class LoanDAOTest extends TestCase {
    
    private EntityManager em;
    private LoanDAO loanDAO;
    
    @Before
    @Override
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
	em = emf.createEntityManager();
        loanDAO = new LoanDAOImpl(em);
    }
    
    public Loan createSampleLoan() {
        Loan loan = new Loan();
        loan.setLoanTime(new Date(System.currentTimeMillis()));
        loan.setReturnTime(new Date(System.currentTimeMillis()+36000000));
        SystemUser su = new SystemUser();
        su.setLastName("Smith");
        su.setFirstName("Paul");
        su.setType(UserTypeEnum.CUSTOMER);
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
	em.getTransaction().begin();
        Loan result = loanDAO.create(loan);
	em.getTransaction().commit();
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
	em.getTransaction().begin();
        loanDAO.create(loan);
	em.getTransaction().commit();
        loan.setLoanTime(new Date(System.currentTimeMillis()+10));
        loan.setReturnTime(new Date(System.currentTimeMillis()+20));
        loan.setCustomer(new SystemUser());
        loan.setMachines(null);
        loan.setLoanState(LoanStateEnum.LOANED);
        Loan expResult = loan;
	em.getTransaction().begin();
        Loan result = loanDAO.update(loan);
	em.getTransaction().commit();
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
        em.getTransaction().begin();
	loanDAO.create(loan);
	em.getTransaction().commit();
        Long id = loan.getId();
        Loan expResult = loan;
	em.getTransaction().begin();
        Loan result = loanDAO.read(id);
	em.getTransaction().commit();
        assertEquals(expResult, result);
        assertEquals(expResult.getCustomer(), result.getCustomer());
        assertEquals(expResult.getLoanState(), result.getLoanState());
        assertEquals(expResult.getLoanTime(), result.getLoanTime());
        assertEquals(expResult.getReturnTime(), result.getReturnTime());
        assertEquals(expResult.getMachines(), result.getMachines());
    }

    /**
     * Test of delete method, of class LoanDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Loan loan = createSampleLoan();
	em.getTransaction().begin();
        loanDAO.create(loan);
	em.getTransaction().commit();
        Long id = loan.getId();
        Loan expResult = loan;
	em.getTransaction().begin();
        Loan result = loanDAO.delete(id);
	em.getTransaction().commit();
        assertEquals(expResult, result);
//        assertNull(loanDAO.read(expResult.getId()));
    }

    /**
     * Test of getAllLoans method, of class LoanDAO.
     */
    @Test
    public void testGetAllLoans() {
        System.out.println("getAllLoans");
        Loan loan = createSampleLoan();
	em.getTransaction().begin();
        loanDAO.create(loan);
	em.getTransaction().commit();
        List expResult = new ArrayList();
        expResult.add(loan);
	em.getTransaction().begin();
        List result = loanDAO.getAllLoans();
	em.getTransaction().commit();
        assertEquals(expResult, result);
        Loan loan2 = createSampleLoan();
	em.getTransaction().begin();
        loanDAO.create(loan2);
	em.getTransaction().commit();
        expResult.add(loan2);
	em.getTransaction().begin();
        result = loanDAO.getAllLoans();
	em.getTransaction().commit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoansByParams method, of class LoanDAO.
     */
    @Test
    public void testGetLoansByParams() {
        System.out.println("getLoansByParams");
        Date loanedFrom = new Date(System.currentTimeMillis()+5000);
        Date loanedTill = new Date(System.currentTimeMillis()+5001);
        LoanStateEnum loanState = LoanStateEnum.RETURNED;
        
        Loan loan = createSampleLoan();
	em.getTransaction().begin();
        loanDAO.create(loan);
        
        Loan loan2 = createSampleLoan();
        loan2.setLoanState(loanState);
        loan2.setLoanTime(loanedFrom);
        loan2.setReturnTime(loanedTill);
        loanDAO.create(loan2);
	em.getTransaction().commit();
        
        SystemUser loanedBy = loan2.getCustomer();
        Machine includedMachine = loan2.getMachines().get(0);
        
        List expResult = new ArrayList();
        expResult.add(loan);
        expResult.add(loan2);
        
	em.getTransaction().begin();
        List result = loanDAO.getLoansByParams(null, null, null, null, null);
	em.getTransaction().commit();
        assertEquals(expResult, result);
        
        expResult.remove(loan);
	em.getTransaction().begin();
        result = loanDAO.getLoansByParams(loanedFrom, loanedTill, loanState, loanedBy, includedMachine);
	em.getTransaction().commit();
        assertEquals(expResult, result);
    }
}
