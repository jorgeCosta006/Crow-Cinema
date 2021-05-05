package model.interfaces;

import java.util.List;

import model.entities.Location;

public interface ILocationDao {
	
	public void createOrUpdateLocation(Location location);
	
	public void deleteLocation(Location location);
	
	public List<Location> listOfLocations();
	
	public Location findLocationByName(String name);
}
