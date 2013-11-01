package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 * 
 * @author Vojtech Schlemmer
 */
public class SystemUserDAOImpl implements SystemUserDAO {

    private EntityManager em;

    public SystemUserDAOImpl(EntityManager em) {
        this.em = em;
    }

    public SystemUser create(SystemUser user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("Argument user must be set");
        }
        em.persist(user);
        SystemUser user1 = em.find(SystemUser.class, user.getId());
        return user1;
    }

    public SystemUser read(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Argument id must be set");
        }
        return em.find(SystemUser.class, id);
    }

    public SystemUser update(SystemUser user) throws IllegalArgumentException {
        if (user == null){
            throw new IllegalArgumentException("Argument user must be set");
        }    
        SystemUser user1 = em.merge(user);
        em.persist(user1);
        return em.find(SystemUser.class, user1.getId());
    }

    public SystemUser delete(SystemUser user) throws IllegalArgumentException {
        if (user == null){
            throw new IllegalArgumentException("Argument user must be set");
        }
        SystemUser user1 = em.merge(user);
        em.remove(user1);
        return em.find(SystemUser.class, user.getId());
    }

    public List<SystemUser> findAllSystemUsers() throws IllegalArgumentException {
        TypedQuery<SystemUser> query1 = em.createQuery(
                        "SELECT u FROM SystemUser u", SystemUser.class);
        return query1.getResultList();
    }

    public List<SystemUser> getSystemUsersByParams(String firstName,
                    String lastName, UserTypeEnum type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SystemUser> cq = cb.createQuery(SystemUser.class);
        Root<SystemUser> userRoot = cq.from(SystemUser.class);
        cq.select(userRoot);
        if (firstName != null)
            cq.where(cb.equal(userRoot.get("firstName"), firstName));
        if (lastName != null)
            cq.where(cb.equal(userRoot.get("lastName"), lastName));
        if (type != null)
            cq.where(cb.equal(userRoot.get("type"), type));
        return em.createQuery(cq).getResultList();
    }
}
