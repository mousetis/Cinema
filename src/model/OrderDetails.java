package model;

public class OrderDetails {

	Orders orderID;
	Products productID;
	int quantity;
	Double price;
	int discount;
	
	public OrderDetails(Orders orderID, Products productID, int quantity, Double price, int discount) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders getOrderID() {
		return orderID;
	}

	public void setOrderID(Orders orderID) {
		this.orderID = orderID;
	}

	public Products getProductID() {
		return productID;
	}

	public void setProductID(Products productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + ", price="
				+ price + ", discount=" + discount + "]";
	}
	
	
}
