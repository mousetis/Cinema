package model;

import java.util.Objects;

public class Customer {

	String customerID;
	String customerName;
	String phone;
	String email;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String customerID, String customerName, String phone, String email) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.phone = phone;
		this.email = email;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerID, other.customerID);
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
	
}
