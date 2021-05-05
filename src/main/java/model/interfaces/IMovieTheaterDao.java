package model.interfaces;

import java.util.List;

import model.entities.MovieTheater;

public interface IMovieTheaterDao {
	
	public void createOrUpdateMovieTheater(MovieTheater movieTheater);
	
	public MovieTheater findMovieTheaterById(int movieTheaterId);
	
	public List<MovieTheater> listOfMovieTheaters();
}
