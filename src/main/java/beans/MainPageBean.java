package beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.jboss.weld.context.RequestContext;
import org.primefaces.PrimeFaces;

import business.BackofficeBusiness;
import business.HistoryBusiness;
import business.MainPageBusiness;
import model.entities.Discount;
import model.entities.Movie;
import model.entities.MovieTheater;
import model.entities.Room;
import model.entities.SalesMovement;
import model.entities.Slot;

@ManagedBean(name = "mainPageBean", eager = true)
@ViewScoped
public class MainPageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Movie> listOfMovies;
	private List<MovieTheater> listOfMovieTheaters;
	private MovieTheater movieTheater;

	private Movie selectedMovie;
	private Date selectedDate;
	private Slot selectedSlot;

	private List<Slot> listOfSlots;
	private List<Date> listOfDates;
	private List<Discount> listOfDiscounts;

	private int ticketsQuantity;
	private double ticketsMoney;
	private double moneyInserted;
	private int availableSeats;
	private double ticketValue;
	private HashMap<Discount, Integer> numberOfTicketsByDiscount;

	@PostConstruct
	public void construct() {
		BackofficeBusiness bob = new BackofficeBusiness();
		listOfMovieTheaters = bob.returnListOfMovieTheater();
		if (listOfMovieTheaters != null && movieTheater == null)
			movieTheater = listOfMovieTheaters.get(0);
	}

	public List<Movie> getListOfMovies() {
		MainPageBusiness mpb = new MainPageBusiness();
		listOfMovies = mpb.returnListOfMoviesByMovieTheater(movieTheater);
		return listOfMovies.stream().distinct().collect(Collectors.toList());
	}

	public void setListOfMovies(List<Movie> listOfMovies) {
		this.listOfMovies = listOfMovies;
	}

	public MovieTheater getMovieTheater() {
		return movieTheater;
	}

	public void setMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
	}

	public List<MovieTheater> getListOfMovieTheaters() {
		return listOfMovieTheaters;
	}

	public void setListOfMovieTheaters(List<MovieTheater> listOfMovieTheaters) {
		this.listOfMovieTheaters = listOfMovieTheaters;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public List<Slot> getListOfSlots() {
		return listOfSlots;
	}

	public void setListOfSlots(List<Slot> listOfSlots) {
		this.listOfSlots = listOfSlots;
	}

	public Date getSelectedDate() {
		if (selectedDate == null) {
			Date date = new Date(System.currentTimeMillis());
			selectedDate = date;
		}
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Slot getSelectedSlot() {
		return selectedSlot;
	}

	public void setSelectedSlot(Slot selectedSlot) {
		this.selectedSlot = selectedSlot;
	}

	public List<Date> getListOfDates() {
		return listOfDates;
	}

	public void setListOfDates(List<Date> listOfDates) {
		this.listOfDates = listOfDates;
	}

	public double getTicketsMoney() {
		return ticketsMoney;
	}

	public void setTicketsMoney(double ticketsMoney) {
		this.ticketsMoney = ticketsMoney;
	}

	public double getMoneyInserted() {
		return moneyInserted;
	}

	public void setMoneyInserted(double moneyInserted) {
		this.moneyInserted = moneyInserted;
	}

	public int getTicketsQuantity() {
		return ticketsQuantity;
	}

	public void setTicketsQuantity(int ticketsQuantity) {
		this.ticketsQuantity = ticketsQuantity;
	}

	public int getAvailableSeats() {
		int capacity = 0;
		int occupied = 0;
		List<Room> listOfRooms = movieTheater.getRooms();
		HistoryBusiness hb = new HistoryBusiness();
		List<SalesMovement> listOfSalesMovement = hb.returnSalesMovementList();
		for (Room room : listOfRooms) {
			List<Slot> listOfSLots = room.getSlots();
			for (Slot slot : listOfSLots) {
				if (slot == selectedSlot)
					capacity = room.getCapacity();
			}
		}
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		if (listOfSalesMovement != null) {
			for (SalesMovement salesMovement : listOfSalesMovement) {
				if (salesMovement.getSlot() == selectedSlot) {
					String one = ft.format(salesMovement.getDay());
					String two = ft.format(selectedDate);
					if (one.contains(two)) {
						occupied += salesMovement.numberOfTickets;
					}
				}
			}
		}

		availableSeats = capacity - occupied;

		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void onChangeMovieTheater(ValueChangeEvent event) {
		movieTheater = (MovieTheater) event.getNewValue();
		PrimeFaces.current().ajax().update("mpMovies");
	}

	public void selectMovie(Movie movie) {
		selectedMovie = movie;
		if (selectedDate == null)
			selectedDate = getSelectedDate();
		MainPageBusiness mpb = new MainPageBusiness();
		listOfSlots = mpb.returnSchedulesOfMovie(selectedMovie, movieTheater, selectedDate);
		listOfDates = mpb.returnDatesOfMovie(movie, movieTheater);
		selectedSlot = null;
		PrimeFaces.current().executeScript("PF('sMovie').show()");
	}

	public void selectDate(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			selectedDate = (Date) event.getNewValue();
			MainPageBusiness mpb = new MainPageBusiness();
			listOfSlots = mpb.returnSchedulesOfMovie(selectedMovie, movieTheater, selectedDate);
		}
	}

	public void selectHour(ValueChangeEvent event) {
		selectedSlot = (Slot) event.getNewValue();
	}

	public List<Discount> getListOfDiscounts() {
		return listOfDiscounts;
	}

	public void setListOfDiscounts(List<Discount> listOfDiscounts) {
		this.listOfDiscounts = listOfDiscounts;
	}

	public double getTicketValue() {
		return ticketValue;
	}

	public void setTicketValue(double ticketValue) {
		this.ticketValue = ticketValue;
	}

	public HashMap<Discount, Integer> getNumberOfTicketsByDiscount() {
		return numberOfTicketsByDiscount;
	}

	public void setNumberOfTicketsByDiscount(HashMap<Discount, Integer> numberOfTicketsByDiscount) {
		this.numberOfTicketsByDiscount = numberOfTicketsByDiscount;
	}

	public void ticketBuyingSection() {
		MainPageBusiness mpb = new MainPageBusiness();
		listOfDiscounts = mpb.returnListOfDiscount();
		PrimeFaces.current().ajax().update("buyTickets");
		PrimeFaces.current().executeScript("PF('sMovie').hide()");
		PrimeFaces.current().executeScript("PF('buyTicketsDialog').show()");
	}

	public void backButton() {
		PrimeFaces.current().executeScript("PF('buyTicketsDialog').hide()");
		PrimeFaces.current().executeScript("PF('sMovie').show()");
	}

	public void buyTickets() {
		if (selectedDate != null && selectedDate != null & ticketsQuantity != 0) {
			if (moneyInserted >= ticketsMoney) {
				MainPageBusiness mpb = new MainPageBusiness();
				mpb.buyTickets(ticketsQuantity, selectedSlot, selectedDate, movieTheater);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Tickets Bought!");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				PrimeFaces.current().executeScript("PF('buyTicketsDialog').hide()");
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Not enough money!");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	public void groupTicketsByDiscount(Discount discount) {

		if (numberOfTicketsByDiscount == null)
			numberOfTicketsByDiscount = new HashMap<Discount, Integer>();

		ticketValue = selectedSlot.getTicketPrice();
		ticketsMoney = 0;
		numberOfTicketsByDiscount.put(discount, ticketsQuantity);
		for (Map.Entry<Discount, Integer> row : numberOfTicketsByDiscount.entrySet()) {
			double ticketWithDiscount = ticketValue - (ticketValue * row.getKey().getValue() / 100);
			ticketsMoney +=  row.getValue() * ticketWithDiscount;
		}
	}
}
