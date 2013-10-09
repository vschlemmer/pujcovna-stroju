package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.junit.Before;


import cz.muni.fi.pa165.pujcovnastroju.Revision;

/* * 
  * @author matej.fucek
 */
public class RevisionDOATest extends TestCase {
    private RevisionDAO revDAO;
    @Before
    @Override
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        revDAO = new RevisionDAOImpl(emf);
    }
     
    /**
     * Creates a sample revision
     * 
     * @return sample revision
     */
    
    public Revision createSampleRevision() {
        Revision revision = new Revision();
        revision.setPerformedBy("NovakJozef");
        revision.setMachine("bager");
        revision.setComment("Opraveno");
        revision.setRevDate("13.06.2013");
        return revision;
    }
    
    
     /**
      * Creates  revision
      * 
      * @return new revision
      */
    public void testCreate() {
        Revision revision1 = createSampleRevision();
        assertNull(revision1.getRevID());
        Revision revision2 = revDAO.create(revision1);
        assertNotNull(revision1.getRevID());
        assertNotNull(revision2.getRevID());
        assertEquals(revision1,revision2);
        assertEquals(revision1.getRevID(), revision2.getRevID());
        assertEquals(revision1.getPerformedBy(), revision2.getPerformedBy());
        assertEquals(revision1.getMachine(), revision2.getMachine());
        assertEquals(revision1.getComment(), revision2.getComment());
        assertEquals(revision1.getRevDate(), revision2.getRevDate());
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
     *  @return specific revision
     */
    public void testRead() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        Revision revision2 = revDAO.read(revision1.getRevID());
        assertEquals(revision1,revision2);
        assertEquals(revision1.getRevID(), revision2.getRevID());
        assertEquals(revision1.getPerformedBy(), revision2.getPerformedBy());
        assertEquals(revision1.getMachine(), revision2.getMachine());
        assertEquals(revision1.getComment(), revision2.getComment());
        assertEquals(revision1.getRevDate(), revision2.getRevDate());
        revDAO.delete(revision1);
    }
     
    /**
     * Test updating a revision
     * @return update revision
     */
    public void testUpdate() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        revision1.setPerformedBy("BobKarak");
        revDAO.update(revision1);
        Revision revision2 = revDAO.read(revision1.getRevID());
        assertEquals(revision1,revision2);
        assertEquals(revision1.getRevID(), revision2.getRevID());
        assertEquals(revision1.getMachine(), revision2.getMachine());
        assertEquals(revision1.getComment(), revision2.getComment());
        assertEquals(revision1.getRevDate(), revision2.getRevDate());
        assertEquals("BobKarak", revision2.getPerformedBy());
        revDAO.delete(revision1);
    }
    
    /**
     * Test deleting a Revison
     * @return delete revision
     */
    public void testDelete() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        revDAO.delete(revision1);
        assertNull(revDAO.read(revision1.getRevID()));
    }
    
    /**
     * Test retrieving all revisions
     * return all revisions
     */
    public void testFindAllRevision() {
        Revision revision1 = createSampleRevision();
        Revision revision2 = createSampleRevision();
        revision2.setPerformedBy("Priezvisko2");
        revDAO.create(revision1);
        revDAO.create(revision2);
        List<Revision> revlist1 = new ArrayList<Revision>();
        revlist1.add(revision1);
        revlist1.add(revision2);
        assertEquals(revlist1, revDAO.findAllrevisions());
        revDAO.delete(revision1);
        revDAO.delete(revision2);
    }
    
     /**
      * Test retrieving specific machine name from specific revision
      * return specific machine
      */
    public void testFindSpecificRevisionedMachine() {
        Revision revision1 = createSampleRevision();
        revDAO.create(revision1);
        assertEquals(revDAO.findRevisionedMachine(revision1.getRevID()), revision1.getMachine());
        revDAO.delete(revision1);
        
    }

}
    