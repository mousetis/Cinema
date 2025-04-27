package model;

import java.util.Objects;

public class Account {
	String accountID;
	String userName;
	String password;
	Employee employeeID;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String accountID, String userName, String password, Employee employeeID) {
		super();
		this.accountID = accountID;
		this.userName = userName;
		this.password = password;
		this.employeeID = employeeID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employee employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountID, other.accountID);
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", userName=" + userName + ", password=" + password + ", employeeID="
				+ employeeID + "]";
	}
	
	
	
}
