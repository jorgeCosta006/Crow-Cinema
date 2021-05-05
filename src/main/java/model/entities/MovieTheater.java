package model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class MovieTheater {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieTheaterId;
	
	@ManyToOne
	@JoinColumn(name="locationId")
	private Location location;
	
	@OneToMany
	private List<Room> rooms;
	
	private String name;
	private Boolean isActive;
	
	public MovieTheater() {}

	public MovieTheater(Location location, List<Room> rooms, String name, Boolean isActive) {
		this.location = location;
		this.rooms = rooms;
		this.name = name;
		this.isActive = isActive;
	}

	public int getMovieTheaterId() {
		return movieTheaterId;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
	    return String.format("%s[movieTheaterId=%d]", getClass().getSimpleName(), getMovieTheaterId());
	}
}
