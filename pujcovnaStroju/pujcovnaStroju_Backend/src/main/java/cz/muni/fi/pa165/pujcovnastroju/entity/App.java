package cz.muni.fi.pa165.pujcovnastroju.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAO;
import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAOImpl;

public class App {
	public static void main(String[] args) {
		SystemUser user1 = new SystemUser();
		user1.setFirstName("Pepa");
		user1.setLastName("Prvni");
		user1.setType(UserTypeEnum.CUSTOMER);
		user1.setRevisions(null);
		user1.setLoans(null);

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		SystemUserDAO userDAO = new SystemUserDAOImpl(em);
		userDAO.create(user1);
		for (SystemUser u : userDAO.findAllSystemUsers()) {
			System.out.println(u);
		}
	}
}
