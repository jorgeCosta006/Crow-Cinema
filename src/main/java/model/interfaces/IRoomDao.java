package model.interfaces;

import java.util.List;

import model.entities.Room;

public interface IRoomDao {

	public void createOrUpdateRoom(Room room);
	
	public List<Room> listOfRooms();
}
