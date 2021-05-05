package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Room;
import model.interfaces.IRoomDao;
import utilities.ConEntityManager;

public class RoomDao implements IRoomDao {
	
	public void createOrUpdateRoom(Room room) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(room);
		em.getTransaction().commit();
	}

//	public Discount findDiscountById(int discountId) {
//		EntityManager em = ConEntityManager.getInstance();
//		Query q = em.createQuery("Select p from Discount p where discountId = " + discountId + "");
//		if (q.getResultList().isEmpty())
//			return null;
//
//		Discount discount = (Discount) q.getSingleResult();
//		return discount;
//	}

	public List<Room> listOfRooms() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Room p");
		List<Room> list = q.getResultList();
		return list;
	}
}
