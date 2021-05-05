package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Language;
import model.interfaces.ILanguageDao;
import utilities.ConEntityManager;

public class LanguangeDao implements ILanguageDao {
	
	public void createOrUpdateLanguage(Language language) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(language);
		em.getTransaction().commit();
	}
	
	public void deleteLanguage(Language language) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.remove(language);
		em.getTransaction().commit();
	}
	
	public List<Language> listOfLanguage() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Language p");
		if(q.getResultList().isEmpty())
			return null;
		
		List<Language> list = q.getResultList();
		return list;
	}
	
	public Language findLanguageByName(String name) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Genre p where name = '" + name + "'");
		if(q.getResultList().isEmpty())
			return null;
		
		Language language = (Language) q.getSingleResult();
		return language;
	}
}
