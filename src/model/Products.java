package model;

import java.util.Objects;

public class Products {

	String productID;
	String productName;
	Double price;
	String productType;
	
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(String productID, String productName, Double price, String productType) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.productType = productType;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Objects.equals(productID, other.productID);
	}

	@Override
	public String toString() {
		return "Products [productID=" + productID + ", productName=" + productName + ", price=" + price
				+ ", productType=" + productType + "]";
	}
	
	
}
