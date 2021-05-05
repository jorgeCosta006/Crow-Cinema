package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Discount;
import model.interfaces.IDiscountDao;
import utilities.ConEntityManager;

public class DiscountDao implements IDiscountDao {

	// We don't remove discounts, we change isActive to false;
	public void createOrUpdateDiscount(Discount discount) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(discount);
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

	public List<Discount> listOfDiscounts() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Discount p");
		List<Discount> list = q.getResultList();
		return list;
	}
}
