package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.MachineTypeEnum;

public class MachineDAOImpl implements MachineDAO{
	
	private EntityManager entityManager;
	
	public MachineDAOImpl() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
	     entityManager = emf.createEntityManager();
	}
	
	public Machine create(Machine machine) {
		entityManager.getTransaction().begin();
		//TODO check values
		entityManager.persist(machine);
		entityManager.getTransaction().commit();
		return machine;
	}

	public Machine update(Machine machine) {
		entityManager.getTransaction().begin();
		Machine toBeUpdated = entityManager.find(Machine.class, machine.getId());
		if (toBeUpdated != null) {
			toBeUpdated.setLabel(machine.getLabel());
			toBeUpdated.setDecription(machine.getDecription());
			toBeUpdated.setType(machine.getType());
		} else {
			toBeUpdated = machine;
			entityManager.persist(machine);
		}
		entityManager.getTransaction().commit();
		return toBeUpdated;
	}

	public Machine read(Long id) {
		entityManager.getTransaction().begin();
		Machine result = entityManager.find(Machine.class, id);
		entityManager.getTransaction().commit();
		return result;
	}

	public void delete(Machine machine) {
		entityManager.getTransaction().begin();
		Machine toBeDeleted = entityManager.find(Machine.class, machine.getId());
		if (toBeDeleted != null) {
			entityManager.remove(toBeDeleted);
		}
		entityManager.getTransaction().commit();
	}

	public List<Machine> getAllMachines() {
		entityManager.getTransaction().begin();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Machine> cq = cb.createQuery(Machine.class);
		Root<Machine> machines = cq.from(Machine.class);
		cq.select(machines);
		TypedQuery<Machine> q = entityManager.createQuery(cq);
		
		entityManager.getTransaction().commit();
		return q.getResultList();
	}

	public List<Machine> getMachinesByType(MachineTypeEnum type) {
		entityManager.getTransaction().begin();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Machine> criteriaQuery = criteriaBuilder.createQuery(Machine.class);
		Root<Machine> rootMachines = criteriaQuery.from(Machine.class);
		
		ParameterExpression<MachineTypeEnum> param = criteriaBuilder.parameter(MachineTypeEnum.class);
		criteriaQuery.select(rootMachines).where(criteriaBuilder.equal(rootMachines.get("type"), param));
		
		TypedQuery<Machine> q = entityManager.createQuery(criteriaQuery);
		q.setParameter(param, type);
		List<Machine> machineList= q.getResultList();
		
		entityManager.getTransaction().commit();
		return machineList;
	}



	
	

}
