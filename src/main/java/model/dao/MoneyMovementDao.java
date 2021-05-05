package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.MoneyMovement;
import model.interfaces.IMoneyMovementDao;
import utilities.ConEntityManager;

public class MoneyMovementDao implements IMoneyMovementDao {
	
	public void createOrUpdateMoneyMovement(MoneyMovement moneyMovement) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(moneyMovement);
		em.getTransaction().commit();
	}
	
	public List<MoneyMovement> listOfMoneyMovements() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from MoneyMovement p");
		if(q.getResultList().isEmpty())
			return null;
		
		List<MoneyMovement> list = q.getResultList();
		return list;
	}
}
