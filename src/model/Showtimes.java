package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Showtimes {
	String showtimeID;
	Movies movieID;
	Room roomID;
	LocalDate showDateTime;
	LocalTime startedTime;
	LocalTime endTime;
	
	public Showtimes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Showtimes(String showtimeID, Movies movieID, Room roomID, LocalDate showDateTime, LocalTime startedTime,
			LocalTime endTime) {
		super();
		this.showtimeID = showtimeID;
		this.movieID = movieID;
		this.roomID = roomID;
		this.showDateTime = showDateTime;
		this.startedTime = startedTime;
		this.endTime = endTime;
	}

	

	public String getShowtimeID() {
		return showtimeID;
	}

	public void setShowtimeID(String showtimeID) {
		this.showtimeID = showtimeID;
	}

	public Movies getMovieID() {
		return movieID;
	}

	public void setMovieID(Movies movieID) {
		this.movieID = movieID;
	}

	public Room getRoomID() {
		return roomID;
	}

	public void setRoomID(Room roomID) {
		this.roomID = roomID;
	}

	public LocalDate getShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(LocalDate showDateTime) {
		this.showDateTime = showDateTime;
	}

	public LocalTime getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(LocalTime startedTime) {
		this.startedTime = startedTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(showtimeID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Showtimes other = (Showtimes) obj;
		return Objects.equals(showtimeID, other.showtimeID);
	}

	@Override
	public String toString() {
		return "Showtimes [showtimeID=" + showtimeID + ", movieID=" + movieID + ", roomID=" + roomID + ", startedTime="
				+ startedTime + ", endTime=" + endTime + "]";
	}
	
	
}
