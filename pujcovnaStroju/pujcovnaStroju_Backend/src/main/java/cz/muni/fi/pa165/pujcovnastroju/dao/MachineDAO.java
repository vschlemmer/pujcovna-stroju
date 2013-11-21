package cz.muni.fi.pa165.pujcovnastroju.dao;

import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;
import java.util.Date;

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
	 * @return deleted machine
	 * @throws IllegalArgumentException
	 *             when machine or machine.id are null
	 */
	public Machine delete(Machine machine);

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

	/**
	 * returns all machines with given parameters
	 * 
	 * @param label
	 * @param description
	 * @param type
	 * @param loan
	 * @param revision
	 * @param freeFrom 
	 * @param freeTill 
	 * @return List of all machines satisfying given parameters
	 */
	public List<Machine> getMachinesByParams(String label, String description, MachineTypeEnum type, Loan loan, Revision revision, Date freeFrom, Date freeTill);
}
