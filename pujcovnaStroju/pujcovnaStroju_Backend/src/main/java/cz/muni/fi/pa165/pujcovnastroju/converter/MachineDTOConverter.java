package cz.muni.fi.pa165.pujcovnastroju.converter;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;

/**
 * 
 * @author Michal Merta 374015
 */
public class MachineDTOConverter {

        /**
	 * Converts DTO to entity object
	 * 
	 * @param dto
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * @return Machine object or null if dto is null
	 */
	public static Machine dtoToEntity(MachineDTO dto, boolean bounded) {
		if (dto == null)
			return null;

		Machine entity = new Machine();
		entity.setId(dto.getId());
		entity.setType(MachineTypeDTOConverter.dtoToEntity(dto.getType()));
		entity.setLabel(dto.getLabel());
		entity.setDescription(dto.getDescription());
		if (!bounded) {
			entity.setLoans(LoanDTOConverter.listToEntities(dto.getLoans(),
					true));
			entity.setRevisions(RevisionDTOConverter.listToEntities(
					dto.getRevisions()));
		}
		return entity;
	}

        /**
	 * Converts entity object to DTO
	 * 
	 * @param entity
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * 
	 * @return MachineDTO or null if entity is null
	 */
	public static MachineDTO entityToDto(Machine entity, boolean bounded) {
		if (entity == null)
			return null;

		MachineDTO dto = new MachineDTO();
		dto.setId(entity.getId());
		dto.setType(MachineTypeDTOConverter.entityToDto(entity.getType()));
		dto.setLabel(entity.getLabel());
		dto.setDescription(entity.getDescription());
		if (!bounded) {
			dto.setLoans(LoanDTOConverter.listToDTOs(entity.getLoans(), true));
			dto.setRevisions(RevisionDTOConverter.listToDto(entity.getRevisions()));
		}
		return dto;
	}

        /**
	 * Converts list of entity objects to list of DTOs
	 * 
	 * @param list
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * @return list of DTOs or null if list is null
	 */
	public static List<MachineDTO> listToDto(List<Machine> list, boolean bounded) {
		if (list == null)
			return null;

		List<MachineDTO> resultList = new ArrayList<>();
		for (Machine entity : list) {
			resultList.add(MachineDTOConverter.entityToDto(entity, bounded));
		}
		return resultList;
	}

        /**
	 * Converts list of DTOs to list of entity objects
	 * 
	 * @param listDTO
	 *            to be converted
	 * @param bounded
	 *            if false inner collections will be also transformed, otherwise
	 *            are null
	 * @return list of Machines or null if listDTO is null
	 */
	public static List<Machine> listToEntities(List<MachineDTO> listDTO,
			boolean bounded) {
		if (listDTO == null)
			return null;

		List<Machine> resultList = new ArrayList<>();
		for (MachineDTO dto : listDTO) {
			resultList.add(MachineDTOConverter.dtoToEntity(dto, bounded));
		}
		return resultList;
	}
}
