package services;

import java.util.ArrayList;

import dao.Employee_Dao;
import model.Movies;
import model.Room;
import model.Seat;

public class Employee_Services {
	dao.Employee_Dao dao;
	
	public Employee_Services(Employee_Dao dao) {
		this.dao = dao;
	}
	
	// lấy danh sách phim
	public ArrayList<Movies> getListMovie() {
		ArrayList<Movies> listMovie = new ArrayList<Movies>();
		listMovie = dao.getListMovie();
		return listMovie;
	}
	
	// lấy danh sách phòng
	public ArrayList<Room> getListRoom() {
		ArrayList<Room> listRoom = new ArrayList<Room>();
		listRoom = dao.getListRoom();
		return listRoom;
	}
	
	// lấy danh sách ghế
	public ArrayList<Seat> getListSeat() {
		ArrayList<Seat> listSeat = new ArrayList<Seat>();
		listSeat = dao.getListSeat();
		return listSeat;
	}
	
}
