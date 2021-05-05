package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.dao.DiscountDao;
import model.dao.MoneyMovementDao;
import model.dao.MovieDao;
import model.dao.MovieTheaterDao;
import model.dao.SalesMovementDao;
import model.entities.Discount;
import model.entities.Movie;
import model.entities.MovieTheater;
import model.entities.Room;
import model.entities.SalesMovement;
import model.entities.Slot;
import model.interfaces.IDiscountDao;
import model.interfaces.IMoneyMovementDao;
import model.interfaces.IMovieDao;
import model.interfaces.IMovieTheaterDao;
import model.interfaces.ISalesMovementDao;

public class MainPageBusiness {

	public MovieTheater getMovieTheatherById(int movieTheaterId) {
		IMovieTheaterDao mtd = new MovieTheaterDao();
		MovieTheater movieTheater = mtd.findMovieTheaterById(movieTheaterId);

		return movieTheater;
	}

	public List<Movie> returnListOfMoviesByMovieTheater(MovieTheater movieTheater) {
		List<Room> listOfRooms = movieTheater.getRooms();
		List<Movie> listOfMovies = new ArrayList<Movie>();
		Calendar c = Calendar.getInstance();

		for (Room room : listOfRooms) {
			for (Slot slot : room.getSlots()) {
				if (!c.getTime().after(slot.getEndDay())) {
					Movie movie = slot.getMovie();
					listOfMovies.add(movie);
				}
			}
		}

		return listOfMovies;
	}

	public List<Slot> returnSchedulesOfMovie(Movie movie, MovieTheater movieTheater, Date day) {
		List<Room> listOfRooms = movieTheater.getRooms();
		List<Slot> listOfSLots = new ArrayList<Slot>();

		for (Room room : listOfRooms) {
			for (Slot slot : room.getSlots()) {
				if (slot.getInitialDay().before(day) && slot.getEndDay().after(day)) {
					if (slot.getMovie().equals(movie))
						listOfSLots.add(slot);
				}
			}
		}

		return listOfSLots;
	}

	public List<Date> returnDatesOfMovie(Movie movie, MovieTheater movieTheater) {
		List<Room> listOfRooms = movieTheater.getRooms();
		List<Slot> listOfSLots = new ArrayList<Slot>();

		for (Room room : listOfRooms) {
			for (Slot slot : room.getSlots()) {
				if (slot.getMovie().equals(movie))
					listOfSLots.add(slot);
			}
		}

		List<Date> datesInRange = new ArrayList<>();
		for (Slot slot : listOfSLots) {
			Calendar calendar = new GregorianCalendar();
			Calendar c = Calendar.getInstance();
			calendar.setTime(slot.getInitialDay());

			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(slot.getEndDay());

			while (calendar.before(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(result);
				calendar.add(Calendar.DATE, 1);
			}
		}

		return datesInRange;
	}

	public void buyTickets(int ticketsQuantity, Slot slot, Date day, MovieTheater movieTheater) {
		ISalesMovementDao smd = new SalesMovementDao();
		IMoneyMovementDao mmd = new MoneyMovementDao();

		SalesMovement salesMovement = new SalesMovement(slot, ticketsQuantity, ticketsQuantity * slot.getTicketPrice(),
				movieTheater, day);
		smd.createOrUpdateSalesMovement(salesMovement);

//		MoneyMovement moneyMovement = new MoneyMovement();
	}
	
	public List<Discount> returnListOfDiscount(){
		IDiscountDao dd = new DiscountDao();
		List<Discount> list = dd.listOfDiscounts();
		return list;
	}
}
