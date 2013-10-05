
package cz.muni.fi.pa165.pujcovnastrojuDAO;

import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.junit.Before;

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
        userDAO = new SystemUserDAOImpl(emf);
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
        user.setEmploee(true);
        return user;
        
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
        assertEquals(user1.isEmploee(), user2.isEmploee());
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
        List<SystemUser> userlist1 = new ArrayList<SystemUser>();
        userlist1.add(user1);
        userlist1.add(user2);
        assertEquals(userlist1, userDAO.findAllSystemUsers());
        userDAO.delete(user1);
        userDAO.delete(user2);
    }
    
    /**
     * Test retrieving users by first and last name
     */
    public void testFindSystemUserByName(){
        SystemUser user1 = createSampleUser();
        userDAO.create(user1);
        List<SystemUser> userList1 = new ArrayList<SystemUser>();
        userList1.add(user1);
        List<SystemUser> userList2 = userDAO.findSystemUserByName(user1.getFirstName(), user1.getLastName());
        assertEquals(userList1, userList2);
        userDAO.delete(user1);
    }
}
