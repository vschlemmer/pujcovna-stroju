
package cz.muni.fi.pa165.pujcovnastroju.test.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.Before;

import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAO;
import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAOImpl;
import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import cz.muni.fi.pa165.pujcovnastroju.entity.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 *
 * @author Vojtech Schlemmer
 */
public class SystemUserDAOTest extends TestCase {
    private SystemUserDAO userDAO;
    
    @Before
    @Override
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        userDAO = new SystemUserDAOImpl(em);
    }
    
    /**
     * Creates a sample user
     * 
     * @return sample user
     */
    public SystemUser createSampleUser(){
        SystemUser user = new SystemUser();
        user.setFirstName("Tomas");
        user.setLastName("Jedno");
        user.setType(UserTypeEnum.CUSTOMER);
        List<Loan> loans = new ArrayList<>();
        loans.add(createSampleLoan());
        user.setLoans(loans);
        List<Revision> revisions = new ArrayList<>();
        revisions.add(createSampleRevision());
        user.setRevisions(revisions);
        return user;
    }
    
    /**
     * Creates a sample loan
     * 
     * @return sample loan
     */
    public Loan createSampleLoan(){
        Loan loan1 = new Loan();
        loan1.setLoanState(LoanStateEnum.BOOKED);
        loan1.setLoanTime(new Timestamp(System.currentTimeMillis()));
        loan1.setMachines(null);
        loan1.setReturnTime(new Timestamp(System.currentTimeMillis()+36000000));
        return loan1;
    }
    
    /**
     * Creates a sample revision
     * 
     * @return sample revision
     */
    public Revision createSampleRevision(){
        Revision revision1 = new Revision();
        revision1.setComment("comment");
        revision1.setSystemUser(null);
        revision1.setSystemUser(null);
        revision1.setRevDate(new Timestamp(System.currentTimeMillis()));
        revision1.setMachine(null);
        return revision1;
    }
    
    /**
     * Test creation of a user
     */
    public void testCreate(){
        SystemUser user1 = createSampleUser();
        assertNull(user1.getId());
        SystemUser user2 = userDAO.create(user1);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
        assertEquals(user1,user2);
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getType(), user2.getType());
        userDAO.delete(user1);
        try{
            userDAO.create(null);
            fail();
        }catch(IllegalArgumentException ex){
            // ok
        }
    }
    
    /**
     * Test reading a user
     */
    public void testRead(){
        SystemUser user1 = createSampleUser();
        userDAO.create(user1);
        SystemUser user2 = userDAO.read(user1.getId());
        assertEquals(user1,user2);
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getType(), user2.getType());
        userDAO.delete(user1);
    }
    
    /**
     * Test updating a user
     */
    public void testUpdate(){
        SystemUser user1 = createSampleUser();
        userDAO.create(user1);
        user1.setLastName("Dve");
        userDAO.update(user1);
        SystemUser user2 = userDAO.read(user1.getId());
        assertEquals(user1,user2);
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getType(), user2.getType());
        assertEquals("Dve", user2.getLastName());
        userDAO.delete(user1);
    }
    
    /**
     * Test deleting a user
     */
    public void testDelete(){
        SystemUser user1 = createSampleUser();
        userDAO.create(user1);
        userDAO.delete(user1);
        assertNull(userDAO.read(user1.getId()));
    }
    
    /**
     * Test retrieving all users
     */
    public void testFindAllSystemUsers(){
        SystemUser user1 = createSampleUser();
        SystemUser user2 = createSampleUser();
        user2.setLastName("Dve");
        userDAO.create(user1);
        userDAO.create(user2);
        List<SystemUser> userlist1 = new ArrayList<>();
        userlist1.add(user1);
        userlist1.add(user2);
        assertEquals(userlist1, userDAO.findAllSystemUsers());
        userDAO.delete(user1);
        userDAO.delete(user2);
    }
    
    /**
     * Test retrieving users by given parameters
     */
    public void testGetSystemUsersByParams(){
        SystemUser user1 = createSampleUser();
        SystemUser user2 = createSampleUser();
        user2.setLastName("Dve");
        SystemUser user3 = createSampleUser();
        user3.setLastName("Tri");
        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.create(user3);
        List<SystemUser> userList1 = userDAO.getSystemUsersByParams("Tomas", null, UserTypeEnum.CUSTOMER);
        assertEquals(userList1, userDAO.findAllSystemUsers());
        assertEquals(3, userList1.size());
    }
    
}
