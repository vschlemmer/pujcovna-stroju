package cz.muni.fi.pa165.pujcovnastrojuDAO;

import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.MachineTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.Revision;
import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.UserTypeEnum;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* * 
  * @author matej.fucek
 */
public class RevisionDAOTest extends TestCase {
    private RevisionDAO revDAO;
    
    @Before
    @Override
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        revDAO = new RevisionDAOImpl(em);
    }
     
    /**
     * Creates a sample revision
     * 
     * @return sample revision
     */
    public Revision createSampleRevision() {
        Revision revision = new Revision();
        revision.setComment("Opraveno");
        revision.setRevDate(new Timestamp(System.currentTimeMillis()));
        revision.setMachine(createSampleMachine());
        revision.setSystemUser(createSampleUser());
        return revision;
    }
    
    /**
     * Creates a sample machine
     * 
     * @return sample machine
     */
    public Machine createSampleMachine() {
            Machine machine1= new Machine();
            machine1.setLabel("bla bla");
            machine1.setDecription("BAAALGER");
            machine1.setType(MachineTypeEnum.BULDOZER);
            return  machine1; 
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
        return user;
    }
                    
     /**
      * Test revision creation
      */
    public void testCreate() {
        Revision revision1 = createSampleRevision();
        assertNull(revision1.getRevID());
        Revision revision2 = revDAO.create(revision1);
        assertNotNull(revision1.getRevID());
        assertNotNull(revision2.getRevID());
        assertEquals(revision1,revision2);
        assertEquals(revision1.getRevID(), revision2.getRevID());
        assertEquals(revision1.getMachine(), revision2.getMachine());
        assertEquals(revision1.getComment(), revision2.getComment());
        assertEquals(revision1.getRevDate(), revision2.getRevDate());
        assertEquals(revision1.getSystemUser(), revision2.getSystemUser());
        revDAO.delete(revision1);
        try {
            revDAO.create(null);
            fail();
        }
        catch(IllegalArgumentException ex){
        }
    }
    
     /**
     * Test reading a Revision
     */
    public void testRead() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        Revision revision2 = revDAO.read(revision1.getRevID());
        assertEquals(revision1,revision2);
        assertEquals(revision1.getRevID(), revision2.getRevID());
        assertEquals(revision1.getMachine(), revision2.getMachine());
        assertEquals(revision1.getComment(), revision2.getComment());
        assertEquals(revision1.getRevDate(), revision2.getRevDate());
        assertEquals(revision1.getSystemUser(), revision2.getSystemUser());
        revDAO.delete(revision1);
    }
     
    /**
     * Test updating a revision
     */
    public void testUpdate() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        revDAO.update(revision1);
        Revision revision2 = revDAO.read(revision1.getRevID());
        assertEquals(revision1,revision2);
        assertEquals(revision1.getRevID(), revision2.getRevID());
        assertEquals(revision1.getMachine(), revision2.getMachine());
        assertEquals(revision1.getComment(), revision2.getComment());
        assertEquals(revision1.getRevDate(), revision2.getRevDate());
        assertEquals(revision1.getSystemUser(), revision2.getSystemUser());
        revDAO.delete(revision1);
    }
    
    /**
     * Test deleting a Revison
     */
    public void testDelete() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        revDAO.delete(revision1);
        assertNull(revDAO.read(revision1.getRevID()));
    }
    
    /**
     * Test retrieving all revisions
     */
    public void testFindAllRevision() {
        Revision revision1 = createSampleRevision();
        Revision revision2 = createSampleRevision();
        revDAO.create(revision1);
        revDAO.create(revision2);
        List<Revision> revlist1 = new ArrayList<>();
        revlist1.add(revision1);
        revlist1.add(revision2);
        assertEquals(revlist1, revDAO.findAllrevisions());
        revDAO.delete(revision1);
        revDAO.delete(revision2);
    }
    
    /**
     * Test retrieving revisions by date
     */
    public void testFindRevisionsByDate(){
        Revision revision1 = createSampleRevision();
        Revision revision2 = createSampleRevision();
        revDAO.create(revision1);
        revDAO.create(revision2);
        List<Revision> revisions1 = new ArrayList<>();
        revisions1.add(revision1);
        revisions1.add(revision2);
        Timestamp dateFrom = new Timestamp(System.currentTimeMillis()-36000000);
        Timestamp dateTo = new Timestamp(System.currentTimeMillis()+36000000);
        List<Revision> revisions2 = revDAO.findRevisionsByDate(dateFrom, dateTo);
        assertEquals(revisions1, revisions2);
    }
}
    