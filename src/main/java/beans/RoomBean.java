package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import business.BackofficeBusiness;
import model.entities.Movie;
import model.entities.Room;
import model.entities.Slot;

@ManagedBean(name = "roomBean", eager = true)
@ViewScoped
public class RoomBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int capacity;
	private List<Room> listOfRooms;
	private List<Slot> listOfSlots;
	private List<Movie> listOfMovies;
	private Room room;
	private Slot slot;
	
	private Movie movie;
	private Date initialTime;
	private Date endTime;
	
	private Date initialDay;
	private Date endDay;
	private List<Date> range;
	private double ticketPrice;
	
	@PostConstruct
	public void construct() {
		BackofficeBusiness bb = new BackofficeBusiness();
		listOfRooms = bb.returnListOfRoom();
		listOfMovies = bb.returnListOfMovie();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<Slot> getListOfSlots() {
		return listOfSlots;
	}
	public void setListOfSlots(List<Slot> listOfSlots) {
		this.listOfSlots = listOfSlots;
	}
	public Slot getSlot() {
		return slot;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public List<Room> getListOfRooms() {
		return listOfRooms;
	}

	public void setListOfRooms(List<Room> listOfRooms) {
		this.listOfRooms = listOfRooms;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Date getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Date initialTime) {
		this.initialTime = initialTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getInitialDay() {
		return initialDay;
	}

	public void setInitialDay(Date initialDay) {
		this.initialDay = initialDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	
	public List<Date> getRange() {
		return range;
	}

	public void setRange(List<Date> range) {
		this.range = range;
	}
	
	public List<Movie> getListOfMovies() {
		return listOfMovies;
	}

	public void setListOfMovies(List<Movie> listOfMovies) {
		this.listOfMovies = listOfMovies;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public void createOrUpdateRoom() {
		BackofficeBusiness bb = new BackofficeBusiness();
		if (this.name == null || this.capacity == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data!"));
		} else {
			bb.createOrUpdateRoom(room, name, capacity);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("roomList.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void changeActiveStateRoom(Room room, boolean state) {
		BackofficeBusiness bb = new BackofficeBusiness();
		room.setActive(state);
		bb.changeActiveStateRoom(room);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("roomList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoom(Room room) {
		this.room = room;
		this.name = room.getName();
		this.capacity = room.getCapacity();
		PrimeFaces.current().executeScript("PF('crRoom').show()");
	}
	
	public void updateRoomSlots(Room room) {
		this.room = room;
		PrimeFaces.current().executeScript("PF('crAddSlot').show()");
	}
	
	public void addSlotToRoom() {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.addSlotsToRoom(room, movie, initialTime, endTime, range.get(0), range.get(1), ticketPrice);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("roomList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSLotInRoom(Slot slot) {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.deleteSlotInRoom(slot);
	}
	
}
