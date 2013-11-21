/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.service;

import cz.muni.fi.pa165.pujcovnastroju.dto.LoanDTO;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;

/**
 * 
 * @author Michal Merta
 */
public interface MachineService {

	/**
	 * persist MachineDTO into database
	 * 
	 * @param MachineDTO
	 * @return persisted object
	 * @throws IllegalArgumentException
	 *             when given object, it's label or type are null
	 */
	public MachineDTO create(MachineDTO machineDTO);

	/**
	 * updates given object
	 * 
	 * @param MachineDTO
	 * @return updated object
	 * @throws DataAccessException
	 *             when given object, it's label or type are null
	 */
	public MachineDTO update(MachineDTO machineDTO);

	/**
	 * returns MachineDTO with given id
	 * 
	 * @param id
	 * @return MachineDTO object or null if it's not presented in database
	 * @throws DataAccessException
	 *             wher id is null or lower then zero
	 */
	public MachineDTO read(Long id);

	/**
	 * deletes given MachineDTO from database
	 * 
	 * @param MachineDTO
	 * @throws DataAccessException
	 *             when MachineDTO or MachineDTO.id are null
	 */
	public MachineDTO delete(MachineDTO machine);

	/**
	 * returns all MachineDTOs presented in database
	 * 
	 * @return List of all MachineDTOs
	 */
	public List<MachineDTO> getAllMachines();

	/**
	 * returns all MachineDTOs of given MachineTypeEnumDTO
	 * 
	 * @param type
	 * @return List of all MachineDTOs with given type in database
	 */
	public List<MachineDTO> getMachineDTOsByType(MachineTypeEnumDTO type);

	/**
	 * returns all machineDTOs with given parameters
	 * 
	 * @param label
	 * @param description
	 * @param machineTypeDTO
	 * @param loan
	 * @param revision
	 * @return List of all machineDTOs satisfying given parameters
	 */
	public List<MachineDTO> getMachineDTOsByParams(String label, String description, MachineTypeEnumDTO machineTypeDTO, LoanDTO loan, RevisionDTO revision);
}
