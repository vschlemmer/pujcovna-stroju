package cz.muni.fi.pa165.pujcovnastrojuDAO;

import cz.muni.fi.pa165.pujcovnastroju.Loan;
import cz.muni.fi.pa165.pujcovnastroju.LoanStateEnum;
import cz.muni.fi.pa165.pujcovnastroju.Machine;
import cz.muni.fi.pa165.pujcovnastroju.SystemUser;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LoanDAOImpl implements LoanDAO {
    
    private EntityManagerFactory emf;
    
    public LoanDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Loan create(Loan loan) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        
        em.close();
        
        return loan;
    }

    public Loan update(Loan loan) {
        if (loan != null) {
            EntityManager em = emf.createEntityManager();
            
            em.getTransaction().begin();
            if (loan.getId() != null) {
                Loan loanStored = em.find(Loan.class, loan.getId());
                if (loanStored != null) em.merge(loan);
                else em.persist(loan);
            }
            else em.persist(loan);
            em.getTransaction().commit();
            
            em.close();
        }
        return loan;
    }

    public Loan read(Long id) {
        EntityManager em = emf.createEntityManager();
        
        Loan loan = (Loan) em.find(Loan.class, id);
        
        em.close();
        
        return loan;
    }

    public Loan delete(Long id) {
        Loan loan = null;
        if (id != null) {
            EntityManager em = emf.createEntityManager();
            
            em.getTransaction().begin();
            loan = em.find(Loan.class, id);
            em.remove(loan);
            em.getTransaction().commit();
            
            em.close();
        }
        return loan;
    }

    public List<Loan> getAllLoans() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l", Loan.class);
        return query.getResultList();
    }

    public List<Loan> getLoansByParams(Timestamp loanedFrom, Timestamp loanedTill, LoanStateEnum loanState, SystemUser loanedBy, Machine includedMachine) {
        EntityManager em = emf.createEntityManager();
        /*String queryString = "SELECT l FROM Loan l WHERE 1=1";
        if (loanedFrom != null) queryString += " AND l.loanedFrom >= :arg1";
        if (loanedTill != null) queryString += " AND l.loanedTill <= :arg2";
        if (loanedBy != null) queryString += " AND l.customer IN (:arg3)";
        if (includedMachine != null) queryString += " AND l.includedMachines >= :arg1";
        TypedQuery<Loan> query = em.createQuery(queryString, Loan.class);*/
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Loan> cQuery = cb.createQuery(Loan.class);
        Root<Loan> loanRoot = cQuery.from(Loan.class);
        cQuery.select(loanRoot);
        if (loanedFrom != null) {
            Expression<Timestamp> loanedFromExp = loanRoot.get("loanTime");
            cQuery.where(cb.greaterThanOrEqualTo(loanedFromExp, loanedFrom));
        }
        if (loanedTill != null) {
            Expression<Timestamp> loanedTillExp = loanRoot.get("returnTime");
            cQuery.where(cb.greaterThanOrEqualTo(loanedTillExp, loanedTill));
        }
        if (loanState != null) cQuery.where(cb.equal(loanRoot.get("loanState"), loanState));
        if (loanedBy != null) cQuery.where(cb.equal(loanRoot.get("customer"), loanedBy));
        if (includedMachine != null) {
            Expression<Machine> machineExp = loanRoot.get("machines");
            Predicate machinePred = machineExp.in(includedMachine);
            cQuery.where(machinePred);
        }
        
        return em.createQuery(cQuery).getResultList();
    }
    
}
