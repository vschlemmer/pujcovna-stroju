/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.pujcovnastroju.converter.MachineDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.converter.MachineTypeDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dao.MachineDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.service.MachineService;

/**
 * 
 * @author Michal Merta 374015
 */
@Service("machineService")
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineDAO machineDao;

	@Override
	public MachineDTO create(MachineDTO machineDTO) {
		MachineDTO dto = null;
		Machine machine = null;
		try {
			machine = machineDao.create(MachineDTOConverter
					.dtoToEntity(machineDTO));
			dto = MachineDTOConverter.entityToDto(machine);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during storing Machine", e);
		}
		return dto;
	}

	@Override
	public MachineDTO update(MachineDTO machineDTO) {
		MachineDTO dto = null;
		Machine machine = null;
		try {
			machine = machineDao.update(MachineDTOConverter
					.dtoToEntity(machineDTO));
			dto = MachineDTOConverter.entityToDto(machine);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during updating Machine", e);
		}
		return dto;
	}

	@Override
	public MachineDTO read(Long id) {
		MachineDTO dto = null;
		Machine machine = null;
		try {
			machine = machineDao.read(id);
			dto = MachineDTOConverter.entityToDto(machine);
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException(
					"Error occured during retrieving Machine", e);
		}
		return dto;
	}

	@Override
	public void delete(MachineDTO machineDTO) {
		try {
			machineDao.delete(MachineDTOConverter.dtoToEntity(machineDTO));
		} catch (IllegalArgumentException e) {
			throw new DataAccessResourceFailureException (
					"Error occured during deleting Machine", e);
		}
	}

	@Override
	public List<MachineDTO> getAllMachines() {
		List<Machine> machineList = machineDao.getAllMachines();
		return MachineDTOConverter.listToDto(machineList);
	}

	@Override
	public List<MachineDTO> getMachineDTOsByType(MachineTypeEnumDTO type) {
		List<Machine> machineList = machineDao
				.getMachinesByType(MachineTypeDTOConverter.dtoToEntity(type));
		return MachineDTOConverter.listToDto(machineList);
	}

}
