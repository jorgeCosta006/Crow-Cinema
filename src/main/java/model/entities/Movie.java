package model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String name;
	private String description;
	
	@Lob
	private byte[] image;
	
	@ManyToMany
	private List<Genre> genres;
	
	@ManyToOne
	@JoinColumn(name="languageId")
	private Language language;
	
	private Date releaseDate;
	private String ageControl;
	private Boolean isActive;
	
	public Movie() {}

	public Movie(String name, String description, byte[] image, List<Genre> genres, Language language, Date releaseDate,
			String ageControl, Boolean isActive) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.genres = genres;
		this.language = language;
		this.releaseDate = releaseDate;
		this.ageControl = ageControl;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAgeControl() {
		return ageControl;
	}

	public void setAgeControl(String ageControl) {
		this.ageControl = ageControl;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getMovieId() {
		return movieId;
	};
}
