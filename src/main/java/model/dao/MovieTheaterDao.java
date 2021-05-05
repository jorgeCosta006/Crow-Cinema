package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.MovieTheater;
import model.interfaces.IMovieTheaterDao;
import utilities.ConEntityManager;

public class MovieTheaterDao implements IMovieTheaterDao {

	// We don't remove movieTheater, we change isActive to false;
	public void createOrUpdateMovieTheater(MovieTheater movieTheater) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(movieTheater);
		em.getTransaction().commit();
	}

	public MovieTheater findMovieTheaterById(int movieTheaterId) {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from MovieTheater p where movieTheaterId = " + movieTheaterId + "");
		if (q.getResultList().isEmpty())
			return null;

		MovieTheater movieTheater = (MovieTheater) q.getSingleResult();
		return movieTheater;
	}

	public List<MovieTheater> listOfMovieTheaters() {
		EntityManager em = ConEntityManager.getInstance();
		Query q = em.createQuery("Select p from MovieTheater p");
		if(q.getResultList().isEmpty())
			return null;
		
		List<MovieTheater> list = q.getResultList();
		return list;
	}
}
