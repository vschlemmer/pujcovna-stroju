package cz.muni.fi.pa165.pujcovnastrojuDAO;

import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vojtech Schlemmer
 */
public class SystemUserDAOImpl implements SystemUserDAO {
    
    private EntityManagerFactory emf;
    
    public SystemUserDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }

    public SystemUser create(SystemUser user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        SystemUser user1 = em.find(SystemUser.class, user.getId());
        em.close();
        return user1;
    }

    public SystemUser read(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(SystemUser.class, id);
    }

    public SystemUser update(SystemUser user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SystemUser user1 = em.merge(user);
        em.persist(user1);
        em.getTransaction().commit();
        SystemUser user2 = em.find(SystemUser.class, user1.getId());
        em.close();
        return user2;
    }

    public SystemUser delete(SystemUser user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SystemUser user1 = em.merge(user);
        em.remove(user1);
        em.getTransaction().commit();
        SystemUser user2 = em.find(SystemUser.class, user.getId());
        em.close();
        return user2;
    }

    public List<SystemUser> findAllSystemUsers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<SystemUser> query1 = em.createQuery("SELECT u FROM SystemUser u", 
                                                        SystemUser.class);
        return query1.getResultList();
    }

    public List<SystemUser> findSystemUserByName(String firstName, String lastName) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<SystemUser> query1 = em.createQuery("SELECT u FROM SystemUser u " +
                "WHERE u.firstName = :arg1 AND u.lastName = :arg2", SystemUser.class);
        query1.setParameter("arg1", firstName);
        query1.setParameter("arg2", lastName);
        return query1.getResultList();
    }
    
}
