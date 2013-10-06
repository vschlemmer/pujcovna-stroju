package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.MachineTypeEnum;

public interface MachineDAO {

	public Machine create(Machine machine);
	public Machine update(Machine machine);
	public Machine read(Long id);
	public void delete(Machine machine);
	public List<Machine> getAllMachines();
	public List<Machine> getMachinesByType(MachineTypeEnum type);
	
}
