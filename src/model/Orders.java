package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Orders {

	String orderID;
	Employee employeeID;
	Customer customerID;
	LocalDateTime orderDate;
	Double total;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(String orderID, Employee employeeID, Customer customerID, LocalDateTime orderDate, Double total) {
		super();
		this.orderID = orderID;
		this.employeeID = employeeID;
		this.customerID = customerID;
		this.orderDate = orderDate;
		this.total = total;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Employee getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employee employeeID) {
		this.employeeID = employeeID;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(orderID, other.orderID);
	}

	@Override
	public String toString() {
		return "Orders [orderID=" + orderID + ", employeeID=" + employeeID + ", customerID=" + customerID
				+ ", orderDate=" + orderDate + ", total=" + total + "]";
	}
	
	
	
}
