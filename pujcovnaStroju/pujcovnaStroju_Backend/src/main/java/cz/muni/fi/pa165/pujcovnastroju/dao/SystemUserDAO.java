package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 * 
 * @author Vojtech Schlemmer
 */
public interface SystemUserDAO {
	/**
	 * Creates a new user
	 * 
	 * @param user
	 *            to be created
	 * @return created user
	 * @throws IllegalArgumentException
	 *             if the user is null
	 */
	public SystemUser create(SystemUser user) throws IllegalArgumentException;

	/**
	 * Reads user with given id
	 * 
	 * @param id
	 *            id of the user
	 * @return SystemUser with the given id
	 * @throws IllegalArgumentException
	 *             if the id is null
	 */
	public SystemUser read(Long id) throws IllegalArgumentException;

	/**
	 * Updates given user
	 * 
	 * @param user
	 *            to be updated
	 * @return updated user
	 * @throws IllegalArgumentException
	 *             if the user is null
	 */
	public SystemUser update(SystemUser user) throws IllegalArgumentException;

	/**
	 * Deletes given user
	 * 
	 * @param user
	 *            to be deleted
	 * @return deleted user
	 * @throws IllegalArgumentException
	 *             if the user is null
	 */
	public SystemUser delete(SystemUser user) throws IllegalArgumentException;

	/**
	 * Returns all users
	 * 
	 * @return list of all users
	 */
	public List<SystemUser> findAllSystemUsers();

	/**
	 * Retrieves system users that match given parameters. If any parameter is
	 * null, it won't be involved in lookup
	 * 
	 * @param firstName
	 *            first name of users
	 * @param lastName
	 *            last name of users
	 * @param type
	 *            type of users
	 * @param username
	 * @return list of users that match the given parameters
	 */
	public List<SystemUser> getSystemUsersByParams(String firstName,
			String lastName, UserTypeEnum type, String userName);

        /**
         * Retrieves users of given types
         * 
         * @param types list of types
         * @return list of users of given types
         */
        public List<SystemUser> getSystemUsersByTypeList(List<UserTypeEnum> types);
        
        /**
         * Retrieves user of given username
         * 
         * @param username username of the user
         * @return user with given username
         */
        public SystemUser getSystemUserByUsername(String username);
}
