package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SalesMovement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int salesMovementId;
	
	@ManyToOne
	@JoinColumn(name="slotId")
	public Slot slot;
	
	public int numberOfTickets;
	
	public double totalPayed;
	public Date day;
	
	@ManyToOne
	@JoinColumn(name="movieTheaterId")
	public MovieTheater movieTheater;
	
	public SalesMovement() {}

	public SalesMovement(Slot slot, int numberOfTickets, double totalPayed, MovieTheater movieTheater, Date day) {
		this.slot = slot;
		this.numberOfTickets = numberOfTickets;
		this.totalPayed = totalPayed;
		this.movieTheater = movieTheater;
		this.day = day;
	}
	
	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public double getTotalPayed() {
		return totalPayed;
	}

	public void setTotalPayed(double totalPayed) {
		this.totalPayed = totalPayed;
	}

	public MovieTheater getMovieTheater() {
		return movieTheater;
	}

	public void setMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
	}

	public int getSalesMovementId() {
		return salesMovementId;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	};
}
