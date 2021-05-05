package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int genreId;
	
	public String name;
	
	public Genre() {}

	public Genre(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGenreId() {
		return genreId;
	};
	
	@Override
	public String toString() {
	    return String.format("%s[genreId=%d]", getClass().getSimpleName(), getGenreId());
	}
}
