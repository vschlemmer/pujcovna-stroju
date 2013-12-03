package cz.muni.fi.pa165.pujcovnastroju.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

import cz.muni.fi.pa165.pujcovnastroju.converter.SystemUserDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.converter.UserTypeDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.service.SystemUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vojtech Schlemmer
 */
@Service("userService")
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserDAO userDao;

	@Override
	public SystemUserDTO create(SystemUserDTO userDTO) {
		SystemUser user = null;
		SystemUserDTO userDTO2 = null;
		try {
			user = userDao.create(SystemUserDTOConverter.dtoToEntity(userDTO,false));
			userDTO2 = SystemUserDTOConverter.entityToDTO(user,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during storing user", e);
		}
		return userDTO2;
	}

	@Override
	public SystemUserDTO read(Long id) {
		SystemUser user = null;
		SystemUserDTO userDTO = null;
		try {
			user = userDao.read(id);
			userDTO = SystemUserDTOConverter.entityToDTO(user,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during storing user", e);
		}
		return userDTO;
	}

	@Override
	public SystemUserDTO update(SystemUserDTO userDTO) {
		SystemUser user = null;
		SystemUserDTO userDTO2 = null;
		try {
			user = userDao.update(SystemUserDTOConverter.dtoToEntity(userDTO,false));
			userDTO2 = SystemUserDTOConverter.entityToDTO(user,false);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during storing user", e);
		}
		return userDTO2;
	}

	@Override
	public SystemUserDTO delete(SystemUserDTO userDTO) {
		try {
			userDao.delete(SystemUserDTOConverter.dtoToEntity(userDTO,false));
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during storing user", e);
		}
		return userDTO;
	}

	@Override
	public List<SystemUserDTO> findAllSystemUsers() {
		List<SystemUser> userList = userDao.findAllSystemUsers();
                return SystemUserDTOConverter.listToDTO(userList,false);
	}

	@Override
	public List<SystemUserDTO> getSystemUsersByParams(String firstName,
			String lastName, UserTypeEnumDTO type) {
		List<SystemUser> userList = userDao.getSystemUsersByParams(firstName,
				lastName, UserTypeDTOConverter.dtoToEntity(type));
		return SystemUserDTOConverter.listToDTO(userList,false);
	}
        
        @Override
        public List<SystemUserDTO> getSystemUsersByTypeList(List<UserTypeEnumDTO> types) {
            List<SystemUser> userList = userDao.getSystemUsersByTypeList(
                    UserTypeDTOConverter.listToEntities(types));
            return SystemUserDTOConverter.listToDTO(userList,false);
	}
}
