package cz.muni.fi.pa165.pujcovnastroju.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cz.muni.fi.pa165.pujcovnastroju.dao.MachineDAOImpl;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;

public class MachineDAOTest extends TestCase {
	
	
	private MachineDAOImpl testedObject;
	EntityManager em;
	
	@Before
	@Override
	public void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
	    em = emf.createEntityManager();
		testedObject = new MachineDAOImpl(em);
	}
	
	public Machine getSampleMachine() {
		Machine m1 = new Machine();
		m1.setLabel("Buldozer b1");
		m1.setDescription("Red buldozer with yellow stripes");
		m1.setType(MachineTypeEnum.BULDOZER);
		return m1;
	}
	
	public void createSampleMachines() {
		em.getTransaction().begin();
		testedObject.create(getSampleMachine());
		Machine m2 = new Machine();
		m2.setLabel("Buldozer b2");
		m2.setDescription("Red buldozer with white stripes");
		m2.setType(MachineTypeEnum.BULDOZER);
		Machine m3 = new Machine();
		m3.setLabel("Drill");
		m3.setDescription("Red drill with with broken trigger");
		m3.setType(MachineTypeEnum.DRILL);
		Machine m4 = new Machine();
		m4.setLabel("Buldozer b4");
		m4.setDescription("Red buldozer with white spots");
		m4.setType(MachineTypeEnum.BULDOZER);
		testedObject.create(m2);
		testedObject.create(m3);
		testedObject.create(m4);
		em.getTransaction().commit();
	}
	
	@Test
	public void testConstruct() {
		try {
			MachineDAOImpl obj = new MachineDAOImpl(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			return;
		}
		fail();
	}
	
	@Test
	public void testCreateMachine() {
		Machine m1 = getSampleMachine();
		em.getTransaction().begin();
		Machine m2 = testedObject.create(m1);
		em.getTransaction().commit();
		assertTrue(m1.equals(m2));
	}
	
	

	@Test
	public void testCreateMachineWrongArg() {
		boolean thrown = false;
		try {
			testedObject.create(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			Machine m = getSampleMachine();
			m.setLabel(null);
			testedObject.create(m);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			thrown = true;
		}
		assertTrue(thrown);
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
	public void testUpdateMachineWrongArgs() {
		boolean thrown = false;
		try {
			testedObject.update(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			Machine m = getSampleMachine();
			m.setLabel(null);
			testedObject.update(m);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testDeleteMachine() {
		createSampleMachines();
		List<Machine> list = testedObject.getAllMachines();
		assertEquals(4, list.size());
		
		Machine toBeDeleted = list.get(2);
		Machine dummyMachine = (Machine) toBeDeleted.clone();
		
		em.getTransaction().begin();
		testedObject.delete(toBeDeleted);
		em.getTransaction().commit();
		list = testedObject.getAllMachines();
		assertEquals(3, list.size());
		for (Machine m: list) {
			if (m.equals(dummyMachine)) {
				fail("machine wasn't deleted");
			}
		}
	}
	
	@Test
	public void testDeleteMachineWrongArgs() {
		boolean thrown = false;
		try {
			testedObject.update(null);
		} catch ( Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
			Machine m = getSampleMachine();
			m.setId(null);
			testedObject.update(m);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			thrown = true;
		}
		assertTrue(thrown);
	}
		
	@Test
	public void testReadMachineWrongArgs() {
		try {
			testedObject.read(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			return;
		}
		fail();
	}
	
}