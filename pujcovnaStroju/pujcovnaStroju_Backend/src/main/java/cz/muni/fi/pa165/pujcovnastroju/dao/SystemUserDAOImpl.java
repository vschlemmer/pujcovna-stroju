package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;
import java.util.ArrayList;
import javax.persistence.criteria.Predicate;

/**
 * 
 * @author Vojtech Schlemmer
 */
@Repository
public class SystemUserDAOImpl implements SystemUserDAO {

	@PersistenceContext
	private EntityManager em;

	public SystemUserDAOImpl() {
	};

	public SystemUserDAOImpl(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public SystemUser create(SystemUser user) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("Argument user must be set");
		}
		em.persist(user);
		return user;
	}

	public SystemUser read(Long id) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("Argument id must be set");
		}
		SystemUser user = em.find(SystemUser.class, id);
		if (user != null) {
			user.getLoans().size();
			user.getRevisions().size();
		}
		return user;
	}

	public SystemUser update(SystemUser user) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("Argument user must be set");
		}
		SystemUser user1 = em.merge(user);
		em.persist(user1);
		return em.find(SystemUser.class, user1.getId());
	}

	public SystemUser delete(SystemUser user) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("Argument user must be set");
		}
		SystemUser user1 = em.find(SystemUser.class, user.getId());
		em.remove(user1);
		return user1;
	}

	public List<SystemUser> findAllSystemUsers()
			throws IllegalArgumentException {
		TypedQuery<SystemUser> query1 = em.createQuery(
				"SELECT u FROM SystemUser u", SystemUser.class);
		return query1.getResultList();
	}

	public List<SystemUser> getSystemUsersByParams(String firstName,
			String lastName, UserTypeEnum type, String userName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SystemUser> cq = cb.createQuery(SystemUser.class);
		Root<SystemUser> userRoot = cq.from(SystemUser.class);
		cq.select(userRoot);
		if (firstName != null)
			cq.where(cb.equal(userRoot.get("firstName"), firstName));
		if (lastName != null)
			cq.where(cb.equal(userRoot.get("lastName"), lastName));
		if (userName != null)
			cq.where(cb.equal(userRoot.get("username"), userName));
		if (type != null)
			cq.where(cb.equal(userRoot.get("type"), type));
		return em.createQuery(cq).getResultList();
	}
        
        public List<SystemUser> getSystemUsersByTypeList(List<UserTypeEnum> types){
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SystemUser> cq = cb.createQuery(SystemUser.class);
            Root<SystemUser> userRoot = cq.from(SystemUser.class);
            cq.select(userRoot);
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> disjunction = new ArrayList<>();
            for (UserTypeEnum type : types) {
                disjunction.add(cb.equal(userRoot.get("type"), cb.literal(type)));
            }
            predicates.add(cb.or(disjunction.toArray(new Predicate[disjunction.size()])));
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            return em.createQuery(cq).getResultList();
        }
        
        public SystemUser getSystemUserByUsername(String username){
            List<SystemUser> users = new ArrayList<>();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SystemUser> cq = cb.createQuery(SystemUser.class);
            Root<SystemUser> userRoot = cq.from(SystemUser.class);
            cq.select(userRoot);
            if (username != null)
                    cq.where(cb.equal(userRoot.get("username"), username));
			else return null;
            
            users = em.createQuery(cq).getResultList();
            if (users.isEmpty()){
                return null;
            }
            return users.get(0);
        }
}
