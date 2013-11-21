package cz.muni.fi.pa165.pujcovnastroju.dao;

import cz.muni.fi.pa165.pujcovnastroju.entity.Loan;
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
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Query;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Subquery;
import org.hibernate.criterion.Subqueries;

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
			toBeUpdated.setDescription(machine.getDescription());
			toBeUpdated.setType(machine.getType());
			toBeUpdated.setLoans(machine.getLoans());
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
		result.getLoans().size();
		System.out.println("=+++++++++++++++++++++++++++++++++++++=");
		System.out.println(result.getLoans());
//		result.getRevisions().size();
		return result;
	}

	public Machine delete(Machine machine) {
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
		return toBeDeleted;
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
	
	public List<Machine> getMachinesByParams(String label, String description, MachineTypeEnum type, Loan loan, Revision revision, Date freeFrom, Date freeTill) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Machine> criteriaQuery = criteriaBuilder
				.createQuery(Machine.class);
		Root<Machine> rootMachines = criteriaQuery.from(Machine.class);
		criteriaQuery.select(rootMachines);
		
		if (label != null) {
		    criteriaQuery.where(criteriaBuilder.equal(rootMachines.get("label"), label));
		}
		
		if (description != null) {
		    criteriaQuery.where(criteriaBuilder.equal(rootMachines.get("description"), description));
		}
		
		if (type != null) {
		    criteriaQuery.where(criteriaBuilder.equal(rootMachines.get("type"), type));
		}
		
		if (loan != null) {
		    Expression<Collection> loansExp = rootMachines.get("loans");
		    criteriaQuery.where(criteriaBuilder.isMember(loan, loansExp));
		}
		
		if (revision != null) {
		    Expression<Collection> revisionExp = rootMachines.get("revisions");
		    criteriaQuery.where(criteriaBuilder.isMember(revision, revisionExp));
		}

		if (freeFrom != null || freeTill != null) {
		    Query query = entityManager.createQuery(
			"select machine from Machine machine "+
			" where machine.id not in" +
			" (select machine.id from Machine machine join machine.loans loan where loan.loanTime <= :lTime AND loan.returnTime >= :rTime)")
			    .setParameter("lTime", freeFrom).setParameter("rTime", freeTill);
 
			return query.getResultList();
			
			
			
		    /*Subquery<Machine> subquery = criteriaQuery.subquery(Machine.class);
		    Root<Machine> machineRoot = subquery.from(Machine.class);
		    Join<Machine, Loan> join = machineRoot.join("loans");
		    subquery.select(machineRoot);
		    if (freeFrom != null)  {
			Expression<Date> loanTime = join.get("loanTime");
			criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(loanTime, freeFrom));
		    }
		    if (freeTill != null) {
			Expression<Date> returnTime = join.get("returnTime");
			criteriaQuery.where(criteriaBuilder.lessThanOrEqualTo(returnTime, freeTill));
		    }
		    criteriaQuery.where(criteriaBuilder.in(subquery));*/
		}
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
}
