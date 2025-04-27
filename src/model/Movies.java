package model;

import java.time.LocalDate;
import java.util.Objects;

public class Movies {

	String img;
	String movieID;
	MovieCategory movieCategory;
	String title;
	int duration;
	String description;
	int ageRating;
	LocalDate releaseDate;
	
	
	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Movies(String img, String movieID, MovieCategory movieCategory, String title, int duration,
			String description, int ageRating, LocalDate releaseDate) {
		super();
		this.img = img;
		this.movieID = movieID;
		this.movieCategory = movieCategory;
		this.title = title;
		this.duration = duration;
		this.description = description;
		this.ageRating = ageRating;
		this.releaseDate = releaseDate;
	}
	
	public Movies(String img, MovieCategory movieCategory, int duration, String description, int ageRating,
			LocalDate releaseDate) {
		super();
		this.img = img;
		this.movieCategory = movieCategory;
		this.duration = duration;
		this.description = description;
		this.ageRating = ageRating;
		this.releaseDate = releaseDate;
	}

	public Movies(String movieID) {
		super();
		this.movieID = movieID;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	

	public MovieCategory getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(MovieCategory movieCategory) {
		this.movieCategory = movieCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getAgeRating() {
		return ageRating;
	}


	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}


	public LocalDate getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(movieID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movies other = (Movies) obj;
		return Objects.equals(movieID, other.movieID);
	}


	@Override
	public String toString() {
		return "Movies [movieID=" + movieID + ", title=" + title + ", duration=" + duration + ", description="
				+ description + ", ageRating=" + ageRating + ", releaseDate=" + releaseDate + "]";
	}
	
	
}
