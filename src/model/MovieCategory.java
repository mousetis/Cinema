	 package model;

import java.util.Objects;

public class MovieCategory {
	String categoryID;
	String categoryName;
	
	public MovieCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieCategory(String categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public MovieCategory(String categoryID) {
		super();
		this.categoryID = categoryID;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieCategory other = (MovieCategory) obj;
		return Objects.equals(categoryID, other.categoryID);
	}

	@Override
	public String toString() {
		return "MovieCategory [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
	
	
	
	
}
