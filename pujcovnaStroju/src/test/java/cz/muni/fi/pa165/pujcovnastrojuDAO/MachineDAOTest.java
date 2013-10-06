package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.MachineTypeEnum;

import junit.framework.TestCase;

public class MachineDAOTest extends TestCase {
	
	
	private MachineDAOImpl testedObject;
	
	@Before
	@Override
	public void setUp() {
		testedObject = new MachineDAOImpl();
	}
	
	public Machine getSampleMachine() {
		Machine m1 = new Machine();
		m1.setLabel("Buldozer b1");
		m1.setDecription("Red buldozer with yellow stripes");
		m1.setType(MachineTypeEnum.BULDOZER);
		return m1;
	}
	
	public void createSampleMachines() {
		testedObject.create(getSampleMachine());
		Machine m2 = new Machine();
		m2.setLabel("Buldozer b2");
		m2.setDecription("Red buldozer with white stripes");
		m2.setType(MachineTypeEnum.BULDOZER);
		Machine m3 = new Machine();
		m3.setLabel("Drill");
		m3.setDecription("Red drill with with broken trigger");
		m3.setType(MachineTypeEnum.DRILL);
		Machine m4 = new Machine();
		m4.setLabel("Buldozer b4");
		m4.setDecription("Red buldozer with white spots");
		m4.setType(MachineTypeEnum.BULDOZER);
		testedObject.create(m2);
		testedObject.create(m3);
		testedObject.create(m4);
	}
	
	@Test
	public void testCreateMachine() {
		Machine m1 = getSampleMachine();
		Machine m2 = testedObject.create(m1);
		System.out.println(m1);
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testGetAllMaChines() {
		List<Machine> result;
		result = testedObject.getAllMachines();
		assertTrue(result.isEmpty());
		createSampleMachines();
		result = testedObject.getAllMachines();
		System.out.println(result);
		assertNotNull(result);
		assertEquals(4, result.size());
	}
	
	@Test
	public void testGetMachineByType() {
		createSampleMachines();
		assertTrue(testedObject.getMachinesByType(MachineTypeEnum.CRANE).isEmpty());
		assertEquals(1, testedObject.getMachinesByType(MachineTypeEnum.DRILL).size());
		assertEquals(3, testedObject.getMachinesByType(MachineTypeEnum.BULDOZER).size());
	}
	
	@Test
	public void testUpdateMachine() {
		createSampleMachines();
		List<Machine> list = testedObject.getMachinesByType(MachineTypeEnum.DRILL);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		Machine toBeUpdated = list.get(0);
		Machine dummyMachine = (Machine) toBeUpdated.clone();
		
		dummyMachine.setType(MachineTypeEnum.CRANE);
		
		toBeUpdated.setType(MachineTypeEnum.CRANE);
		Machine updated = testedObject.update(toBeUpdated);
		assertEquals(dummyMachine, updated);
	}
	
	@Test
	public void testDeleteMachine() {
		createSampleMachines();
		List<Machine> list = testedObject.getAllMachines();
		assertEquals(4, list.size());
		
		Machine toBeDeleted = list.get(2);
		Machine dummyMachine = (Machine) toBeDeleted.clone();
		
		testedObject.delete(toBeDeleted);
		
		list = testedObject.getAllMachines();
		assertEquals(3, list.size());
		for (Machine m: list) {
			if (m.equals(dummyMachine)) {
				fail("machine wasn't deleted");
			}
		}
	}
	
	
}