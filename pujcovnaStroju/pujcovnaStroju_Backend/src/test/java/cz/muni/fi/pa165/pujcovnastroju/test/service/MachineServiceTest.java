package cz.muni.fi.pa165.pujcovnastroju.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.muni.fi.pa165.pujcovnastroju.converter.MachineTypeDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;
import cz.muni.fi.pa165.pujcovnastroju.service.MachineService;

public class MachineServiceTest extends AbstractTest {

	@Autowired
	private MachineService service;

	@Test
	public void testCreateNewMachine() {
		MachineDTO m = getSampleDTO();
		MachineDTO dto;
		dto = service.create(m);
		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertEquals(dto.getType(), m.getType());
		assertEquals(dto.getLabel(), m.getLabel());
	}

	@Test
	public void testUpdateMachine() {
		MachineDTO dto = getSampleDTO();
		MachineDTO dtoNew = getSampleDTO();
		dtoNew = service.create(dto);
		assertNotNull(dtoNew);
		dto.setId(dtoNew.getId());
		assertEquals(dto, dtoNew);
		dto.setLabel("small drill");
		dto = service.update(dto);
		assertFalse(dto.equals(dtoNew));
		assertTrue(dto.getDecription().equals(dtoNew.getDecription()));
	}

	@Test
	public void testReadMachine() {
		MachineDTO dto = getSampleDTO();
		dto = service.create(dto);
		MachineDTO dtoNew = service.read(dto.getId());
		assertTrue(dto.equals(dtoNew));
	}

	@Test
	public void testGetAllMachines() {
		MachineDTO dto1 = getSampleDTO();
		MachineDTO dto2 = getSampleDTO();
		dto2.setDecription("medium drill");
		service.create(dto1);
		service.create(dto2);
		List<MachineDTO> list = service.getAllMachines();
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).getLabel().equals(dto1.getLabel())
				|| list.get(0).getLabel().equals(dto2.getLabel()));
		assertTrue(list.get(1).getLabel().equals(dto1.getLabel())
				|| list.get(1).getLabel().equals(dto2.getLabel()));
	}

	@Test
	public void testGetMachineByTepe() {
		MachineDTO dto1 = getSampleDTO();
		MachineDTO dto2 = getSampleDTO();
		dto2.setDecription("medium buldozer");
		dto2.setType(MachineTypeDTOConverter
				.entityToDto(MachineTypeEnum.BULDOZER));
		service.create(dto1);
		service.create(dto2);
		List<MachineDTO> list = service
				.getMachineDTOsByType(MachineTypeDTOConverter
						.entityToDto(MachineTypeEnum.BULDOZER));

		assertTrue(list.size() == 1);
		assertTrue(list.get(0).getType().getTypeLabel() == MachineTypeEnum.BULDOZER.toString());
		assertTrue(list.get(0).getDecription().equals("medium buldozer"));
	}

	private MachineDTO getSampleDTO() {
		MachineDTO m = new MachineDTO();
		m.setLabel("drill");
		m.setType(MachineTypeDTOConverter.entityToDto(MachineTypeEnum.DRILL));
		m.setDecription("big drill");
		return m;
	}
}
