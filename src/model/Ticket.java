package model;

import java.util.Objects;

public class Ticket {
	String ticketID;
	Customer customerID;
	Showtimes showtimeID;
	Seat seatID;
	Double price;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(String ticketID, Customer customerID, Showtimes showtimeID, Seat seatID, Double price) {
		super();
		this.ticketID = ticketID;
		this.customerID = customerID;
		this.showtimeID = showtimeID;
		this.seatID = seatID;
		this.price = price;
	}

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}

	public Showtimes getShowtimeID() {
		return showtimeID;
	}

	public void setShowtimeID(Showtimes showtimeID) {
		this.showtimeID = showtimeID;
	}

	public Seat getSeatID() {
		return seatID;
	}

	public void setSeatID(Seat seatID) {
		this.seatID = seatID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ticketID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(ticketID, other.ticketID);
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", customerID=" + customerID + ", showtimeID=" + showtimeID
				+ ", seatID=" + seatID + ", price=" + price + "]";
	}
	
	
}
