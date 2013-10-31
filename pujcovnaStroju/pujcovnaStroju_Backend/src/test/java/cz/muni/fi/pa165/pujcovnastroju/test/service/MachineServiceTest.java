package cz.muni.fi.pa165.pujcovnastroju.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.muni.fi.pa165.pujcovnastroju.converter.MachineDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.service.MachineService;


public class MachineServiceTest extends AbstractTest{
	
	@Autowired
	private MachineService service;
	
	@Test
	public void testCreateNewMachine() {
		Machine m = new Machine();
		m.setLabel("sss");
		m.setType(MachineTypeEnum.DRILL);
		service.create(MachineDTOConverter.entityToDto(m));
		
	}
}
