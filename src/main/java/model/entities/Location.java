package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locationId;
	
	private String name;
	
	public Location() {}

	public Location(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLocationId() {
		return locationId;
	};

	@Override
	public String toString() {
	    return String.format("%s[locationId=%d]", getClass().getSimpleName(), getLocationId());
	}
}
