package model;

import java.util.Objects;

public class Seat {
	String seatID;
	String seatNUmber;
	String seatType;
	Room roomID;
	String status;
	
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(String seatID, String seatNUmber, String seatType, Room roomID, String status) {
		super();
		this.seatID = seatID;
		this.seatNUmber = seatNUmber;
		this.seatType = seatType;
		this.roomID = roomID;
		this.status = status;
	}

	public String getSeatID() {
		return seatID;
	}

	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public String getSeatNUmber() {
		return seatNUmber;
	}

	public void setSeatNUmber(String seatNUmber) {
		this.seatNUmber = seatNUmber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public Room getRoomID() {
		return roomID;
	}

	public void setRoomID(Room roomID) {
		this.roomID = roomID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seatID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(seatID, other.seatID);
	}

	@Override
	public String toString() {
		return "Seat [seatID=" + seatID + ", seatNUmber=" + seatNUmber + ", seatType=" + seatType + ", roomID=" + roomID
				+ ", status=" + status + "]";
	}
	
	
}
