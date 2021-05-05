package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.SalesMovement;
import model.interfaces.ISalesMovementDao;
import utilities.ConEntityManager;

public class SalesMovementDao implements ISalesMovementDao{
	
	public void createOrUpdateSalesMovement(SalesMovement salesMovement) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(salesMovement);
		em.getTransaction().commit();
	}
	
	public List<SalesMovement> listOfSalesMovements() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from SalesMovement p");
		if(q.getResultList().isEmpty())
			return null;
		
		List<SalesMovement> list = q.getResultList();
		return list;
	}
}
