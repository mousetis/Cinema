package services;

import java.util.ArrayList;

import dao.ShowTimes;

public class Movies {
	dao.Movies daoMovie;
	dao.ShowTimes daoShowtime;
	dao.Ticket daoTicket;

	public Movies(dao.Movies dao) {
		this.daoMovie = new dao.Movies();
	}
	
	//lấy danh sách phim
	public ArrayList<model.Movies> getList() {
		ArrayList<model.Movies> list = daoMovie.getListMovie();
		return list;
	}
	
	//tìm phim
	public ArrayList<model.Movies> findMovie(String keyID, String keyName){
		ArrayList<model.Movies> list = daoMovie.findMovies(keyID, keyName);
		return list;
	}
	
	//xoá phim
	public boolean delete(String keyID) { 
		return daoMovie.deleteMovies(keyID);
	}
	
	//cập nhật phim
	public boolean update(String keyID, model.Movies mv) {
		return daoMovie.updateMovies(keyID, mv);
	}
	
	//thêm phim
	public boolean add(model.Movies mv) {
		return daoMovie.addMovie(mv);
	}
	
	//lấy dữ liệu 1 phim
	public model.Movies getMovie(String keyID){
		model.Movies mv = daoMovie.getMoive(keyID);
		return mv;
	}
	
	//lấy giá vé
	public double getPriceByMovieID(String movieID) {
		String showtimeID = daoMovie.getShowtimeIDFromMovies(movieID);
		if(showtimeID != null) {
			String ticketID = daoShowtime.getFirstTicketIDFromShowTimes(showtimeID);
			if(ticketID != null) {
				return daoTicket.getPriceFromTicket(ticketID);
			}
		}
		return 0;
	}
	
	//lấy mã vé
	public String getTicketIDByMovieID(String movieID) {
		String showtimeID = daoMovie.getShowtimeIDFromMovies(movieID);
		if(showtimeID != null) {
			String ticketID = daoShowtime.getFirstTicketIDFromShowTimes(showtimeID);
			if(ticketID != null) {
				return ticketID;
			}
		}
		return null;
	}
	
	//cập nhật giá
	public boolean updatePrice(String ticketID, double newPrice) {
		return daoTicket.updatePrice(ticketID, newPrice);
	}
	
	//thêm giá theo mã phim
	public boolean addPriceByMovieID(String movieID, double newPrice) {
		String ticketID = getTicketIDByMovieID(movieID);
		return daoTicket.addPrice(ticketID, newPrice);
	}
}
