package cz.muni.fi.pa165.pujcovnastroju;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import cz.muni.fi.pa165.pujcovnastrojuDAO.SystemUserDAO;
import cz.muni.fi.pa165.pujcovnastrojuDAO.SystemUserDAOImpl;

public class App 
{
    public static void main( String[] args )
    {
        SystemUser user1 = new SystemUser();
        user1.setFirstName("Pepa");
        user1.setLastName("Prvni");
        user1.setEmploee(true);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        SystemUserDAO userDAO = new SystemUserDAOImpl(emf);
        userDAO.create(user1);
        for (SystemUser u : userDAO.findAllSystemUsers()){
            System.out.println(u);
        }
    }
}
