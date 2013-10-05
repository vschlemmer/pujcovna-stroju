package cz.muni.fi.pa165.pujcovnastrojuDAO;

import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import java.util.List;

/**
 *
 * @author Vojtech Schlemmer
 */
public interface SystemUserDAO {
    /**
     * Creates a new user
     * 
     * @param user to be created
     * @return created user
     * @throws IllegalArgumentException if the user is null
     */
    public SystemUser create(SystemUser user);
    
    /**
     * Reads user with given id
     * 
     * @param id id of the user
     * @return SystemUser with the given id
     * @throws IllegalArgumentException if the id is null
     */
    public SystemUser read(Long id);
    
    /**
     * Updates given user
     * 
     * @param user to be updated
     * @return updated user
     * @throws IllegalArgumentException if the user is null
     */
    public SystemUser update(SystemUser user);
    
    /**
     * 
     * @param user to be deleted
     * @return deleted user
     * @throws IllegalArgumentException if the user is null
     */
    public SystemUser delete(SystemUser user);
    
    /**
     * Returns all users
     * 
     * @return list of all users
     */
    public List<SystemUser> findAllSystemUsers();
    
    /**
     * Returns list of users with given first name and last name
     * 
     * @param firstName first name of users
     * @param lastName last name of users
     * @return list of users with given name
     * @throws IllegalArgumentException if firstName or lastName is null
     */
    public List<SystemUser> findSystemUserByName(String firstName, String lastName);
    
}
