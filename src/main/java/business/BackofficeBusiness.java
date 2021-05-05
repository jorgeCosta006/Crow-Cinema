package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DiscountDao;
import model.dao.GenreDao;
import model.dao.LanguangeDao;
import model.dao.LocationDao;
import model.dao.MovieDao;
import model.dao.MovieTheaterDao;
import model.dao.RoomDao;
import model.dao.SlotDao;
import model.entities.Discount;
import model.entities.Genre;
import model.entities.Language;
import model.entities.Location;
import model.entities.Movie;
import model.entities.MovieTheater;
import model.entities.Room;
import model.entities.Slot;
import model.interfaces.IDiscountDao;
import model.interfaces.IGenreDao;
import model.interfaces.ILanguageDao;
import model.interfaces.ILocationDao;
import model.interfaces.IMovieDao;
import model.interfaces.IMovieTheaterDao;
import model.interfaces.IRoomDao;
import model.interfaces.ISlotDao;

public class BackofficeBusiness {

	public List<Genre> returnListOfGenres() {
		IGenreDao gd = new GenreDao();
		List<Genre> listOfGenres = gd.listOfGenre();
		return listOfGenres;
	}

	public void createOrUpdateGenre(Genre genre, String name) {
		IGenreDao gd = new GenreDao();

		if (genre == null) {
			genre = new Genre(name);
		} else {
			genre.setName(name);
		}

		gd.createOrUpdateGenre(genre);
	}

	public void deleteGenre(Genre genre) {
		IGenreDao gd = new GenreDao();
		gd.deleteGenre(genre);
	}

	public List<Location> returnListOfLocation() {
		ILocationDao ld = new LocationDao();
		List<Location> listOfLocations = ld.listOfLocations();
		return listOfLocations;
	}

	public void createOrUpdateLocation(Location location, String name) {
		ILocationDao ld = new LocationDao();

		if (location == null) {
			location = new Location(name);
		} else {
			location.setName(name);
		}

		ld.createOrUpdateLocation(location);
	}

	public void deleteLocation(Location location) {
		ILocationDao ld = new LocationDao();
		ld.deleteLocation(location);
	}

	public List<Language> returnListOfLanguage() {
		ILanguageDao ld = new LanguangeDao();
		List<Language> listOfLanguage = ld.listOfLanguage();
		return listOfLanguage;
	}

	public void createOrUpdateLanguage(Language language, String name) {
		ILanguageDao ld = new LanguangeDao();

		if (language == null) {
			language = new Language(name);
		} else {
			language.setName(name);
		}

		ld.createOrUpdateLanguage(language);
	}

	public void deleteLanguage(Language language) {
		ILanguageDao ld = new LanguangeDao();
		ld.deleteLanguage(language);
	}

	public List<MovieTheater> returnListOfMovieTheater() {
		IMovieTheaterDao mtd = new MovieTheaterDao();
		List<MovieTheater> listOfMovieTheater = mtd.listOfMovieTheaters();
		return listOfMovieTheater;
	}

	public void createOrUpdateMovieTheater(MovieTheater movieTheater, String name, Location location) {
		IMovieTheaterDao mtd = new MovieTheaterDao();
		if (movieTheater == null) {
			movieTheater = new MovieTheater(location, null, name, true);
		} else {
			movieTheater.setName(name);
			movieTheater.setLocation(location);

		}
		mtd.createOrUpdateMovieTheater(movieTheater);
	}

	public void changeActiveStateMovieTheater(MovieTheater movieTheater) {
		IMovieTheaterDao mtd = new MovieTheaterDao();
		mtd.createOrUpdateMovieTheater(movieTheater);
	}

	public List<Movie> returnListOfMovie() {
		IMovieDao md = new MovieDao();
		List<Movie> listOfMovies = md.listOfMovies();
		return listOfMovies;
	}

	public void createOrUpdateMovie(Movie movie, String name, String description, byte[] image, List<Genre> genres,
			Language language, Date releaseDate, String ageControl) {
		IMovieDao md = new MovieDao();
		if (movie == null) {
			movie = new Movie(name, description, image, genres, language, releaseDate, ageControl, true);
		} else {
			movie.setName(name);
			movie.setDescription(description);
			movie.setImage(image);
			movie.setGenres(genres);
			movie.setLanguage(language);
			movie.setReleaseDate(releaseDate);
			movie.setAgeControl(ageControl);
		}
		md.createOrUpdateMovie(movie);
	}

	public void deleteMovie(Movie movie) {
		IMovieDao md = new MovieDao();
		movie.setActive(false);
		md.createOrUpdateMovie(movie);
	}

	public List<Room> returnListOfRoom() {
		IRoomDao rd = new RoomDao();
		List<Room> listOfRoom = rd.listOfRooms();
		return listOfRoom;
	}

	public void createOrUpdateRoom(Room room, String name, int capacity) {
		IRoomDao rd = new RoomDao();

		if (room == null) {
			room = new Room(name, capacity, null, true);
		} else {
			room.setName(name);
			room.setCapacity(capacity);
		}

		rd.createOrUpdateRoom(room);
	}

	public void changeActiveStateRoom(Room room) {
		IRoomDao rd = new RoomDao();
		rd.createOrUpdateRoom(room);
	}

	public void addRoomsToMovieTheater(MovieTheater movieTheater, Room room) {
		IMovieTheaterDao MTD = new MovieTheaterDao();
		List<Room> listOfRooms = movieTheater.getRooms();
		boolean roomExists = false;

		for (Room roomValue : listOfRooms) {
			if (roomValue.getRoomId() == room.getRoomId())
				roomExists = true;
		}

		if (!roomExists) {
			listOfRooms.add(room);
			movieTheater.setRooms(listOfRooms);
			MTD.createOrUpdateMovieTheater(movieTheater);
		}

	}

	public void addSlotsToRoom(Room room, Movie movie, Date initialTime, Date endTime, Date initialDay, Date endDay,
			double ticketPrice) {
		IRoomDao rd = new RoomDao();
		ISlotDao sd = new SlotDao();

		List<Slot> listOfSlots = room.getSlots();
		if (listOfSlots == null) {
			listOfSlots = new ArrayList<Slot>();
		}
		Slot newSlot = new Slot(movie, initialTime, endTime, initialDay, endDay, ticketPrice);
		sd.createOrUpdateSlot(newSlot);
		listOfSlots.add(newSlot);
		room.setSlots(listOfSlots);
		rd.createOrUpdateRoom(room);

	}

	public void addRoomToMovieTheater(MovieTheater movieTheater, Room room) {
		IMovieTheaterDao mtd = new MovieTheaterDao();
		List<Room> list = movieTheater.getRooms();
		if (list == null) {
			list = new ArrayList<Room>();
		}
		list.add(room);
		movieTheater.setRooms(list);
		mtd.createOrUpdateMovieTheater(movieTheater);
	}

	public void deleteSlotInRoom(Slot slot) {
		ISlotDao sd = new SlotDao();
		IRoomDao rd = new RoomDao();
		List<Room> listOfRooms = rd.listOfRooms();
		for (Room room : listOfRooms) {
			for (Slot slotValue : room.getSlots()) {
				if (slotValue.getSlotId() == slot.getSlotId()) {
					List<Slot> listOfSlots = room.getSlots();
					listOfSlots.remove(slotValue);
					room.setSlots(listOfSlots);
					rd.createOrUpdateRoom(room);
				}
			}
		}

		sd.deleteSlot(slot);
	}

	public List<Discount> returnListOfDiscount() {
		IDiscountDao dd = new DiscountDao();
		List<Discount> list = dd.listOfDiscounts();
		return list;
	}

	public void createOrUpdateDiscount(Discount discount, String name, double value) {
		IDiscountDao dd = new DiscountDao();

		if (discount == null) {
			discount = new Discount(name, value, true);
		} else {
			discount.setName(name);
			discount.setValue(value);
		}

		dd.createOrUpdateDiscount(discount);
	}

	public void changeDiscountActiveOrInactive(Discount discount) {
		IDiscountDao dd = new DiscountDao();
		if (discount.getIsActive().equals(true)) {
			discount.setActive(false);
		} else if (discount.getIsActive().equals(false)) {
			discount.setActive(true);
		}

		dd.createOrUpdateDiscount(discount);
	}
}
