package cz.muni.fi.pa165.pujcovnastrojuDAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import cz.muni.fi.pa165.pujcovnastroju.Revision;

/**
 *
 * @author Matej Fucek
 */
public class RevisionDAOImpl implements RevisionDAO {

    private EntityManagerFactory emf;
    
    public RevisionDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Revision create(Revision revision) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(revision);
        em.getTransaction().commit();
        Revision revision1 = em.find(Revision.class, revision.getRevID());
        em.close();
        return revision1;
    }

    public Revision delete(Revision revision) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Revision revision1 = em.merge(revision);
        em.remove(revision1);
        em.getTransaction().commit();
        Revision revision2 = em.find(Revision.class, revision.getRevID());
        em.close();
        return revision2;
    }

    public Revision update(Revision revision) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Revision revision1 = em.merge(revision);
        em.persist(revision1);
        em.getTransaction().commit();
        Revision revision2 = em.find(Revision.class, revision1.getRevID());
        em.close();
        return revision2;
    }

    public List<Revision> findAllrevisions() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Revision> query1 = em.createQuery("SELECT u FROM Revision u",
                                                        Revision.class);
        return query1.getResultList();   
    }

    public Revision read(Long RevID) {
        EntityManager em = emf.createEntityManager();
        return em.find(Revision.class, RevID);
    }
    
   public String findRevisionedMachine(Long revID) {
        EntityManager em = emf.createEntityManager();
        Revision revision1= em.find(Revision.class, revID);
        return revision1.getMachine();
   }
  
}