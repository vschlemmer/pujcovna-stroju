/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
	
	 public static Machine dtoToEntity(MachineDTO dto) {
		 Machine entity = new Machine();
		 entity.setId(dto.getId());
		 entity.setType(MachineTypeDTOConverter.dtoToEntity(dto.getType()));
		 entity.setLabel(dto.getLabel());
		 entity.setDecription(dto.getDecription());
		 return entity;
	 }
	 
	 public static MachineDTO entityToDto(Machine entity) {
		 MachineDTO dto = new MachineDTO();
		 dto.setId(entity.getId());
		 dto.setType(MachineTypeDTOConverter.entityToDto(entity.getType()));
		 dto.setLabel(entity.getLabel());
		 dto.setDecription(entity.getDecription());
		 return dto;
	 }
	 
	 public static List<MachineDTO> listToDto(List<Machine> list) {
		 List<MachineDTO> resultList = new ArrayList<>();
		 for (Machine entity: list) {
			 resultList.add(MachineDTOConverter.entityToDto(entity));
		 }
		 return resultList;
	 }
}
