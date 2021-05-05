package model.dao;

import javax.persistence.EntityManager;
import model.entities.Wallet;
import model.interfaces.IWalletDao;
import utilities.ConEntityManager;

public class WalletDao implements IWalletDao {
	
	public void createOrUpdateWallet(Wallet wallet) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(wallet);
		em.getTransaction().commit();
	}
}
