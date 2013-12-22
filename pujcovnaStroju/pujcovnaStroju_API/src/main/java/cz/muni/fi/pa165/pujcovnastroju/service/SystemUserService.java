package cz.muni.fi.pa165.pujcovnastroju.service;

import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;

/**
 * 
 * @author Vojtech Schlemmer
 */
public interface SystemUserService {
	/**
	 * Persist SystemUserDTO into database
	 * 
	 * @param userDTO
	 *            to be persisted
	 * @return persisted user
	 * @throws DataAccessException
	 *             if the userDTO is null
	 */
	public SystemUserDTO create(SystemUserDTO userDTO);

	/**
	 * returns SystemUserDTO with given id
	 * 
	 * @param id
	 *            id of the user
	 * @return SystemUserDTO with the given id
	 * @throws DataAccessException
	 *             if the id is null
	 */
	public SystemUserDTO read(Long id);

	/**
	 * Updates given SystemUserDTO
	 * 
	 * @param userDTO
	 *            to be updated
	 * @return updated SystemUserDTO
	 * @throws DataAccessException
	 *             if the user is null
	 */
	public SystemUserDTO update(SystemUserDTO userDTO);

	/**
	 * 
	 * @param userDTO
	 *            to be deleted
	 * @return deleted SystemUserDTO
	 * @throws DataAccessException
	 *             if the user is null
	 */
	public SystemUserDTO delete(SystemUserDTO userDTO);

	/**
	 * Returns all users
	 * 
	 * @return list of all users
	 */
	public List<SystemUserDTO> findAllSystemUsers();

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
	 * @return list of users that match the given parameters
	 */
	public List<SystemUserDTO> getSystemUsersByParams(String firstName,
			String lastName, UserTypeEnumDTO type);

        /**
         * Retrieves users of given types
         * 
         * @param types list of types
         * @return list of users of given types
         */
        public List<SystemUserDTO> getSystemUsersByTypeList(List<UserTypeEnumDTO> types);
        
        /**
         * Retrieves user of given username
         * 
         * @param username
         * @return user with given username
         */
        public SystemUserDTO getSystemUserByUsername(String username);
}
