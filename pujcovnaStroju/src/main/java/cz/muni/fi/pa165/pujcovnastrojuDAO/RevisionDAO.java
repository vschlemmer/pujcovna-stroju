package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.util.List;
import cz.muni.fi.pa165.pujcovnastroju.Revision;
/**
 * * @author Matej Fucek
 */
public interface RevisionDAO {
    
    /**
     * Creates a new Revision
     * 
     * @param revID to be created
     * @return created Revision
     * @throws IllegalArgumentException if the Revision is null
     */ 
    public Revision create (Revision revID);

    /**
     * Deletes an Revision
     * 
     * @param revID to be deleted
     * @return deleted RevID
     * @throws IllegalArgumentException if the RevID is null
     */
    public Revision delete (Revision revID);   

    /**
     * Updates given revID
     * 
     * @param revID to be updated
     * @return updated RevID
     * @throws IllegalArgumentException if the RevID is null
     */
    public Revision update (Revision revID);
    
    /**
     * Returns all Revisions
     * 
     * @return list of all Revision 
     */
    public List<Revision> findAllrevisions();

    /**
     * Reads Revision with given revID
     * 
     * @param revID of the Revision
     * @return Revision with the given revID
     * @throws IllegalArgumentException if the revID is null
     */
    public Revision read (Long revID);
    
    /**
     * Returns serviced machine for specific revision ID  
     * 
     * @param revID
     * @return name of the machine in specific revision
     * @throws IllegalArgumentException if the revID is null
     */
    public String findRevisionedMachine(Long revID);

}