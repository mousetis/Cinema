package services;


import java.sql.Date;
import java.util.ArrayList;

import dao.ShowTimes;

public class Showtimes {
	dao.ShowTimes daoST;
	dao.Movies daoMV;
	ArrayList<model.Showtimes> listST = new ArrayList<model.Showtimes>();
	
	
	
	public Showtimes(dao.ShowTimes dao) {
		this.daoST = new ShowTimes();
	}

	//lấy danh sách lịch chiếu
	public ArrayList<model.Showtimes> getList(){
		return daoST.getListShowtimes();
	}
	
	//tìm lịch chiếu
	public ArrayList<model.Showtimes> findShowtime(String roomName, Date stDate){
		return daoST.findShowtime(roomName, stDate);
	}
	
	//xoá lịch chiếu
	public boolean delete(String stID) {
		return daoST.deleteShowtime(stID);
	}
	
	//sửa lịch chiếu 
	public boolean update(String showtimeID, model.Showtimes st) {
		return daoST.updateShowtime(showtimeID, st);
	}
	
	//thêm phim
	public boolean add(model.Showtimes st) {
		return daoST.addShowtime(st);
	}
	
	//lấy mã phim
	public String getMovieID(String name) {
		return daoST.getMovieIDByMovieName(name);
	}
	
	//lấy mã phòng
	public String getRoomID(String name) {
		return daoST.getRoomIDByRoomName(name);
	}
}
