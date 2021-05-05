package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Genre;
import model.interfaces.IGenreDao;
import utilities.ConEntityManager;

public class GenreDao implements IGenreDao {
	
	public void createOrUpdateGenre(Genre genre) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(genre);
		em.getTransaction().commit();
	}
	
	public void deleteGenre(Genre genre) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.remove(genre);
		em.getTransaction().commit();
	}
	
	public List<Genre> listOfGenre() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Genre p");
		if(q.getResultList().isEmpty())
			return null;
		
		List<Genre> list = q.getResultList();
		return list;
	}
	
	public Genre findGenreByName(String name) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Genre p where name = '" + name + "'");
		if(q.getResultList().isEmpty())
			return null;
		
		Genre genre = (Genre) q.getSingleResult();
		return genre;
	}
}
