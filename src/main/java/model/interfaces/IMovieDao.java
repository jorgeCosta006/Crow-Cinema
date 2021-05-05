package model.interfaces;

import java.util.List;

import model.entities.Movie;

public interface IMovieDao {
	public void createOrUpdateMovie(Movie movie);
	
	public List<Movie> listOfMovies();
}
