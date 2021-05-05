package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int slotId;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	private Date initialTime;
	private Date endTime;

	private Date initialDay;
	private Date endDay;
	
	private double ticketPrice;

	public Slot() {
	}

	public Slot(Movie movie, Date initialTime, Date endTime, Date initialDay, Date endDay, double ticketPrice) {
		this.movie = movie;
		this.initialTime = initialTime;
		this.endTime = endTime;
		this.initialDay = initialDay;
		this.endDay = endDay;
		this.ticketPrice = ticketPrice;
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

	public int getSlotId() {
		return slotId;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	};
}
