package model.dao;

import javax.persistence.EntityManager;

import model.entities.Slot;
import model.interfaces.ISlotDao;
import utilities.ConEntityManager;

public class SlotDao implements ISlotDao{
	
	public void createOrUpdateSlot(Slot slot) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(slot);
		em.getTransaction().commit();
	}
	
	public void deleteSlot(Slot slot) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.remove(slot);
		em.getTransaction().commit();
	}
}
