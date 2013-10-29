package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;

/**
 * 
 * @author Michal Merta 374015
 * 
 */
public interface MachineDAO {

	/**
	 * persist machine into database
	 * 
	 * @param machine
	 * @return persisted object
	 * @throws IllegalArgumentException
	 *             when given object, it's label or type are null
	 */
	public Machine create(Machine machine);

	/**
	 * updates given object
	 * 
	 * @param machine
	 * @return updated object
	 * @throws IllegalArgumentException
	 *             when given object, it's label or type are null
	 */
	public Machine update(Machine machine);

	/**
	 * returns Machine with given id
	 * 
	 * @param id
	 * @return Machine object or null if it's not presented in database
	 * @throws IllegalArgumentException
	 *             wher id is null or lower then zero
	 */
	public Machine read(Long id);

	/**
	 * deletes given Machine from database
	 * 
	 * @param machine
	 * @throws IllegalArgumentException
	 *             when machine or machine.id are null
	 */
	public void delete(Machine machine);

	/**
	 * returns all machines presented in database
	 * 
	 * @return List of all machines
	 */
	public List<Machine> getAllMachines();

	/**
	 * returns all machines of given MachineTypeEnum
	 * 
	 * @param type
	 * @return List of all machines with given type in database
	 */
	public List<Machine> getMachinesByType(MachineTypeEnum type);

}
