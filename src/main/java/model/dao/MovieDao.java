package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Movie;
import model.interfaces.IMovieDao;
import utilities.ConEntityManager;

public class MovieDao implements IMovieDao {
	
	public void createOrUpdateMovie(Movie movie) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(movie);
		em.getTransaction().commit();
	}
	
	public List<Movie> listOfMovies() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from Movie p where isActive =" + 1);
		if(q.getResultList().isEmpty())
			return null;
		
		List<Movie> list = q.getResultList();
		return list;
	}
}
