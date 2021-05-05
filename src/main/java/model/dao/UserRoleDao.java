package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.UserRole;
import model.interfaces.IUserRoleDao;
import utilities.ConEntityManager;

public class UserRoleDao implements IUserRoleDao {
	
	public UserRole findUserRoleByName(String name) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from UserRole p where name = '" + name + "'");
		if(q.getResultList().isEmpty())
			return null;
		
		UserRole userRole = (UserRole) q.getSingleResult();
		return userRole;
	}
	
	public List<UserRole> listOfRoles() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from UserRole");
		List<UserRole> list = q.getResultList();
		return list;
	}
}
