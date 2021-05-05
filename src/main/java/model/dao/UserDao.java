package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.User;
import model.interfaces.IUserDao;
import utilities.ConEntityManager;

public class UserDao implements IUserDao {
	
	//We don't remove user, we change isActive to false;
	public void createOrUpdateUser(User user) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public User findUserByEmailAndPassword(String email, String password) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from User p where email = '" + email + "' and password = '" + password + "'");
		if(q.getResultList().isEmpty())
			return null;
		
		User user = (User) q.getSingleResult();
		return user;
	}
	
	public User findUserByEmail(String email) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from User p where email = '" + email + "'");
		if(q.getResultList().isEmpty())
			return null;
		
		User user = (User) q.getSingleResult();
		return user;
	}
}
