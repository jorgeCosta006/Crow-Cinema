package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Location;
import model.interfaces.ILocationDao;
import utilities.ConEntityManager;

public class LocationDao implements ILocationDao {
	
	public void createOrUpdateLocation(Location location) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(location);
		em.getTransaction().commit();
	}
	
	public void deleteLocation(Location location) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.remove(location);
		em.getTransaction().commit();
	}
	
	public List<Location> listOfLocations() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Location p");
		if(q.getResultList().isEmpty())
			return null;
		
		List<Location> list = q.getResultList();
		return list;
	}
	
	public Location findLocationByName(String name) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Location p where name = '" + name + "'");
		if(q.getResultList().isEmpty())
			return null;
		
		Location location = (Location) q.getSingleResult();
		return location;
	}
}
