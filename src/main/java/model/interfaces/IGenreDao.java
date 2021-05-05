package model.interfaces;

import java.util.List;

import model.entities.Genre;

public interface IGenreDao {

	public void createOrUpdateGenre(Genre genre);
	
	public void deleteGenre(Genre genre);
	
	public List<Genre> listOfGenre();
	
	public Genre findGenreByName(String name);
}
