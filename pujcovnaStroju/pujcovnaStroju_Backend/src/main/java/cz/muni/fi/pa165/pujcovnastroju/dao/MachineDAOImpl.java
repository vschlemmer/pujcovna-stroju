package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.pujcovnastroju.entity.Machine;
import cz.muni.fi.pa165.pujcovnastroju.entity.MachineTypeEnum;

/**
 * 
 * @author Michal Merta 374015
 * 
 */
@Repository
public class MachineDAOImpl implements MachineDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public MachineDAOImpl(EntityManager em) {
		if (em == null) {
			throw new IllegalArgumentException("arbument 'em' must be set");
		}
		entityManager = em;
	}

	public MachineDAOImpl() {
	}

	public Machine create(Machine machine) {
		if (machine == null) {
			throw new IllegalArgumentException("unset argument 'machine");
		}
		if (machine.getLabel() == null) {
			throw new IllegalArgumentException("argument 'label' must be set");
		}
		if (machine.getLabel() == null) {
			throw new IllegalArgumentException("argument 'type' must be set");
		}
		entityManager.persist(machine);
		return machine;
	}

	public Machine update(Machine machine) {
		if (machine == null) {
			throw new IllegalArgumentException("unset argument 'machine'");
		}
		if (machine.getId() == null) {
			throw new IllegalArgumentException("argument 'id' must be set");
		}
		Machine toBeUpdated = entityManager
				.find(Machine.class, machine.getId());
		if (toBeUpdated != null) {
			toBeUpdated.setLabel(machine.getLabel());
			toBeUpdated.setDecription(machine.getDecription());
			toBeUpdated.setType(machine.getType());
		} else {
			toBeUpdated = machine;
			entityManager.persist(machine);
		}
		return toBeUpdated;
	}

	public Machine read(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("unset argument 'id'");
		}
		Machine result = entityManager.find(Machine.class, id);
		return result;
	}

	public void delete(Machine machine) {
		if (machine == null) {
			throw new IllegalArgumentException("unset argument 'machine'");
		}
		if (machine.getId() == null) {
			throw new IllegalArgumentException("'id' must be set");
		}
		Machine toBeDeleted = entityManager
				.find(Machine.class, machine.getId());
		if (toBeDeleted != null) {
			entityManager.remove(toBeDeleted);
		}
	}

	public List<Machine> getAllMachines() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Machine> cq = cb.createQuery(Machine.class);
		Root<Machine> machines = cq.from(Machine.class);
		cq.select(machines);
		TypedQuery<Machine> q = entityManager.createQuery(cq);

		return q.getResultList();
	}

	public List<Machine> getMachinesByType(MachineTypeEnum type) {
		if (type == null) {
			throw new IllegalArgumentException("unset argument 'type");
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Machine> criteriaQuery = criteriaBuilder
				.createQuery(Machine.class);
		Root<Machine> rootMachines = criteriaQuery.from(Machine.class);

		ParameterExpression<MachineTypeEnum> param = criteriaBuilder
				.parameter(MachineTypeEnum.class);
		criteriaQuery.select(rootMachines).where(
				criteriaBuilder.equal(rootMachines.get("type"), param));

		TypedQuery<Machine> q = entityManager.createQuery(criteriaQuery);
		q.setParameter(param, type);
		List<Machine> machineList = q.getResultList();

		return machineList;
	}

}
