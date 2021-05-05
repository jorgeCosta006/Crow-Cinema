package beans;

import java.io.ByteArrayInputStream;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import business.BackofficeBusiness;
import model.entities.Discount;
import model.entities.Genre;
import model.entities.Language;
import model.entities.Location;
import model.entities.Movie;
import model.entities.MovieTheater;
import model.entities.Room;

@ManagedBean(name = "backofficeBean", eager = true)
@ViewScoped
public class BackofficeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Date releaseDate;
	private String ageControl;
	private double value;

	private List<Genre> listOfGenres;
	private List<Location> listOfLocations;
	private List<Language> listOfLanguages;
	private List<MovieTheater> listOfMovieTheaters;
	private List<Movie> listOfMovies;
	private List<Room> listOfRooms;
	private List<Discount> listOfDiscounts;

	private List<Genre> selectedGenres;

	private Genre genre;
	private Location location;
	private Language language;
	private MovieTheater movieTheater;
	private Movie movie;
	private Room room;
	private Discount discount;

	private CroppedImage croppedImage;
	private UploadedFile originalImageFile;

	@PostConstruct
	public void construct() {
		BackofficeBusiness bb = new BackofficeBusiness();
		listOfGenres = bb.returnListOfGenres();
		listOfLocations = bb.returnListOfLocation();
		listOfLanguages = bb.returnListOfLanguage();
		listOfMovieTheaters = bb.returnListOfMovieTheater();
		listOfMovies = bb.returnListOfMovie();
		listOfRooms = bb.returnListOfRoom();
		listOfDiscounts = bb.returnListOfDiscount();
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

	public List<Genre> getListOfGenres() {
		return listOfGenres;
	}

	public void setListOfGenres(List<Genre> listOfGenres) {
		this.listOfGenres = listOfGenres;
	}

	public List<Location> getListOfLocations() {
		return listOfLocations;
	}

	public void setListOfLocations(List<Location> listOfLocations) {
		this.listOfLocations = listOfLocations;
	}

	public List<Language> getListOfLanguages() {
		return listOfLanguages;
	}

	public void setListOfLanguages(List<Language> listOfLanguages) {
		this.listOfLanguages = listOfLanguages;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<MovieTheater> getListOfMovieTheaters() {
		return listOfMovieTheaters;
	}

	public void setListOfMovieTheaters(List<MovieTheater> listOfMovieTheaters) {
		this.listOfMovieTheaters = listOfMovieTheaters;
	}

	public MovieTheater getMovieTheater() {
		return movieTheater;
	}

	public void setMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
	}

	public List<Movie> getListOfMovies() {
		return listOfMovies;
	}

	public void setListOfMovies(List<Movie> listOfMovies) {
		this.listOfMovies = listOfMovies;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Genre> getSelectedGenres() {
		return selectedGenres;
	}

	public void setSelectedGenres(List<Genre> selectedGenres) {
		this.selectedGenres = selectedGenres;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public UploadedFile getOriginalImageFile() {
		return originalImageFile;
	}

	public void setOriginalImageFile(UploadedFile originalImageFile) {
		this.originalImageFile = originalImageFile;
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

	public List<Discount> getListOfDiscounts() {
		return listOfDiscounts;
	}

	public void setListOfDiscounts(List<Discount> listOfDiscounts) {
		this.listOfDiscounts = listOfDiscounts;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void createOrUpdateGenre() {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.createOrUpdateGenre(genre, name);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("genreList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateGenre(Genre genre) {
		this.genre = genre;
		this.name = genre.getName();
		PrimeFaces.current().executeScript("PF('crGenre').show()");
	}

	public void newGenre() {
		this.name = null;
		PrimeFaces.current().executeScript("PF('crGenre').show()");
	}

	public void deleteGenre(Genre genre) {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.deleteGenre(genre);

//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Deleted! List of Genres updated."));

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("genreList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOrUpdateLocation() {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.createOrUpdateLocation(location, name);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("locationList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateLocation(Location location) {
		this.location = location;
		this.name = location.getName();
		PrimeFaces.current().executeScript("PF('crLocation').show()");
	}

	public void newLocation() {
		this.name = null;
		PrimeFaces.current().executeScript("PF('crLocation').show()");
	}

	public void deleteLocation(Location location) {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.deleteLocation(location);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("locationList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOrUpdateLanguage() {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.createOrUpdateLanguage(language, name);
//		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":three");
//		PrimeFaces.current().ajax().update("three");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("languageList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateLanguage(Language language) {
		this.language = language;
		this.name = language.getName();
		PrimeFaces.current().executeScript("PF('crLanguage').show()");
	}

	public void newLanguage() {
		this.name = null;
		PrimeFaces.current().executeScript("PF('crLanguage').show()");
	}

	public void deleteLanguage(Language language) {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.deleteLanguage(language);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("languageList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOrUpdateMovieTheater() {
		BackofficeBusiness bb = new BackofficeBusiness();
		if (this.name == null || this.location == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data!"));
		} else {
			bb.createOrUpdateMovieTheater(movieTheater, name, location);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("movieTheaterList.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
		this.name = movieTheater.getName();
		this.location = movieTheater.getLocation();
		PrimeFaces.current().executeScript("PF('crMovieTheater').show()");
	}

	public void newMovieTheater() {
		this.name = null;
		PrimeFaces.current().executeScript("PF('crMovieTheater').show()");
	}

	public void changeActiveStateMovieTheater(MovieTheater movieTheater, boolean state) {
		BackofficeBusiness bb = new BackofficeBusiness();
		movieTheater.setActive(state);
		bb.changeActiveStateMovieTheater(movieTheater);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("movieTheaterList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOrUpdateMovie() {
		BackofficeBusiness bb = new BackofficeBusiness();
		byte[] content = null;
		if (originalImageFile != null) {
			content = originalImageFile.getContent();
		}
		// if (this.name == null || this.description == null ||
		// this.selectedGenres.isEmpty() || this.language == null
//				|| this.releaseDate == null || this.ageControl == null) {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data!"));
//		} else {
		bb.createOrUpdateMovie(movie, name, description, content, selectedGenres, language, releaseDate, ageControl);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("movieList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		}
	}

	public void deleteMovie(Movie movie) {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.deleteMovie(movie);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("movieList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateMovie(Movie movie) {
		this.movie = movie;
		PrimeFaces.current().executeScript("PF('crMovie').show()");
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.originalImageFile = null;
		this.croppedImage = null;
		UploadedFile file = event.getFile();
		if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
			this.originalImageFile = file;
			FacesMessage msg = new FacesMessage("Successful", this.originalImageFile.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

//	public void crop() {
//		if (this.croppedImage == null || this.croppedImage.getBytes() == null
//				|| this.croppedImage.getBytes().length == 0) {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropped successfully."));
//		}
//	}
//
//	public StreamedContent getImage() {
//		return DefaultStreamedContent.builder()
//				.contentType(originalImageFile == null ? null : originalImageFile.getContentType()).stream(() -> {
//					if (originalImageFile == null || originalImageFile.getContent() == null
//							|| originalImageFile.getContent().length == 0) {
//						return null;
//					}
//
//					try {
//						return new ByteArrayInputStream(originalImageFile.getContent());
//					} catch (Exception e) {
//						e.printStackTrace();
//						return null;
//					}
//				}).build();
//	}
//
//	public StreamedContent getCropped() {
//		return DefaultStreamedContent.builder()
//				.contentType(originalImageFile == null ? null : originalImageFile.getContentType()).stream(() -> {
//					if (croppedImage == null || croppedImage.getBytes() == null
//							|| croppedImage.getBytes().length == 0) {
//						return null;
//					}
//
//					try {
//						return new ByteArrayInputStream(this.croppedImage.getBytes());
//					} catch (Exception e) {
//						e.printStackTrace();
//						return null;
//					}
//				}).build();
//	}

	public void updateMovieTheaterRooms(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
		PrimeFaces.current().executeScript("PF('crAddRoom').show()");
	}

	public void addRoomToMovieTheater() {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.addRoomsToMovieTheater(movieTheater, room);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("movieTheaterList.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOrUpdateDiscount() {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.createOrUpdateDiscount(discount, name, value);
		if (discount != null) {
			PrimeFaces.current().ajax().update("discountTable");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Table updated.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("discountList.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateDiscount(Discount discount) {
		this.discount = discount;
		this.name = discount.getName();
		this.value = discount.getValue();

		PrimeFaces.current().executeScript("PF('crDiscount').show()");
	}

	public void newDiscount() {
		this.discount = null;
		this.name = null;
		this.value = 0;
		PrimeFaces.current().executeScript("PF('crDiscount').show()");
	}

	public void changeDiscountActiveOrInactive(Discount discount) {
		BackofficeBusiness bb = new BackofficeBusiness();
		bb.changeDiscountActiveOrInactive(discount);
		PrimeFaces.current().ajax().update("discountTable");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Table updated.");
		PrimeFaces.current().dialog().showMessageDynamic(message);
	}
}
