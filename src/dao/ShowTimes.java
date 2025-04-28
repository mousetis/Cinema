package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import connectDB.Connected;
import model.Movies;
import model.Room;
import model.Showtimes;

public class ShowTimes {
	static Connection con = Connected.getConnection();
	
	//hàm đổi từ localdate sang date
	public static java.sql.Date localDateToSqlDate(LocalDate localDate) {
	    return (localDate != null) ? java.sql.Date.valueOf(localDate) : null;
	}
	
	//hàm đổi từ date thành localdate
	public static LocalDate sqlDateToLocalDate(java.sql.Date sqlDate) {
	    return (sqlDate != null) ? sqlDate.toLocalDate() : null;
	}
	
	//hàm đổi từ time sang localtime
	public LocalTime convertSqlTimeToLocalTime(Time sqlTime) {
	    if (sqlTime == null) {
	        return null;
	    }
	    return sqlTime.toLocalTime();
	}
	
	//hàm đổi từ localtime sang time
	public Time convertLocalTimeToSqlTime(LocalTime localTime) {
	    if (localTime == null) {
	        return null;
	    }
	    return Time.valueOf(localTime);
	}
	
	//lấy id vé từ bảng showtimes
	public static String getFirstTicketIDFromShowTimes(String showTimeID) {
		String ticketID = null;
		String sql = "select top 1 TicketID from Ticket where ShowtimeID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, showTimeID);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				ticketID = res.getString("TicketID");
			}
			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketID;
	}
	
	//lấy lịch chiếu
	public ArrayList<model.Showtimes> getListShowtimes(){
		ArrayList<model.Showtimes> list = new ArrayList<model.Showtimes>();
		String sql = "select s.ShowtimeID, m.Title, r.RoomName, s.ShowDateTime, s.StartedTime, s.EndTime \r\n"
				+ "from [dbo].[Showtimes] s join [dbo].[Movies] m on s.MovieID = m.MovieID\r\n"
				+ "	join [dbo].[Room] r on s.RoomID = r.RoomID";
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			
			while(res.next()) {
				model.Showtimes st = new Showtimes();
				model.Movies mv = new Movies();
				model.Room room = new Room();
				
				mv.setTitle(res.getString("Title"));
				room.setRoomName(res.getString("RoomName"));
				st.setShowtimeID(res.getString("ShowtimeID"));
				st.setShowDateTime(sqlDateToLocalDate(res.getDate("ShowDateTime")));
				st.setStartedTime(convertSqlTimeToLocalTime(res.getTime("StartedTime")));
				st.setEndTime(convertSqlTimeToLocalTime(res.getTime("EndTime")));
				st.setMovieID(mv);
				st.setRoomID(room);
				
				list.add(st);
			}
		} catch (SQLException e) {
			System.err.println("Không thể lấy danh sách lịch chiếu: " + e.getMessage());
		}
		return list;
	}
	
	//tìm lịch chiếu theo phòng và ngày chiếu
	public ArrayList<model.Showtimes> findShowtime(String roomName, Date showDate){
		ArrayList<model.Showtimes> list = new ArrayList<Showtimes>();
		String sql = "select s.ShowtimeID, m.Title, r.RoomName, s.ShowDateTime, s.StartedTime, s.EndTime\r\n"
				+ "from [dbo].[Showtimes] s join [dbo].[Movies] m on s.MovieID = m.MovieID\r\n"
				+ "	 join [dbo].[Room] r on s.RoomID = r.RoomID\r\n"
				+ "where s.ShowDateTime = ? or r.RoomName like ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDate(1, showDate);
			stmt.setString(2, roomName);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				model.Showtimes st = new Showtimes();
				model.Movies mv = new Movies();
				model.Room room = new Room();
				
				mv.setTitle(res.getString("Title"));
				room.setRoomName(res.getString("RoomName"));
				st.setShowtimeID(res.getString("ShowtimeID"));
				st.setShowDateTime(sqlDateToLocalDate(res.getDate("ShowDateTime")));
				st.setStartedTime(convertSqlTimeToLocalTime(res.getTime("StartedTime")));
				st.setEndTime(convertSqlTimeToLocalTime(res.getTime("EndTime")));
				st.setMovieID(mv);
				st.setRoomID(room);
				
				list.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Xoá lịch chiếu
	public boolean deleteShowtime(String showtimeID) {
		String sql = "delete from Showtimes where ShowtimeID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, showtimeID);
			int res = stmt.executeUpdate();
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Sửa lịch chiếu
	public boolean updateShowtime(String showtimeID, model.Showtimes st) {
		String sql = "update Showtimes set ShowtimeID = ?, MovieID = ?, RoomID = ?, ShowDateTime = ?, StartedTime = ?, EndTime = ? where ShowtimeID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, st.getShowtimeID());
			stmt.setString(2, st.getMovieID().getMovieID());
			stmt.setString(3, st.getRoomID().getRoomID());
			stmt.setDate(4, localDateToSqlDate(st.getShowDateTime()));
			stmt.setTime(5, convertLocalTimeToSqlTime(st.getStartedTime()));
			stmt.setTime(6, convertLocalTimeToSqlTime(st.getEndTime()));
			stmt.setString(7, showtimeID);
			
			int res = stmt.executeUpdate();
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//random mã 
	public String randomID(String ch, String tableName, String tableID) throws SQLException{
		Random random = new Random();
		String ID;
		boolean exists = false;
		do {
			int number = random.nextInt(9000) + 1000;
			ID = ch + number;
			
			String sql = "select count(*) from " + tableName +" where " + tableID + " = ?";
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, ID);
				ResultSet res = ps.executeQuery();
				if(res.next()) {
					exists = res.getInt(1) > 0;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				exists = true;
			}
		}while(exists);
		return ID;
	}
			
	
	//thêm phim
	public boolean addShowtime(model.Showtimes st) {
		String sql = "insert into Showtimes(ShowtimeID, MovieID, RoomID, ShowDateTime, StartedTime, EndTime)\r\n"
					+ "values(?, ?, ?, ?, ?, ?)";
		try {
			if(st != null) {
				String ID = randomID("ST", "Showtimes", "ShowtimeID");
				st.setShowtimeID(ID);
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, st.getShowtimeID());
				stmt.setString(2, st.getMovieID().getMovieID());
				stmt.setString(3, st.getRoomID().getRoomID());
				stmt.setDate(4, localDateToSqlDate(st.getShowDateTime()));
				stmt.setTime(5, convertLocalTimeToSqlTime(st.getStartedTime()));
				stmt.setTime(6, convertLocalTimeToSqlTime(st.getEndTime()));
				
				int res = stmt.executeUpdate();
				return res > 0;
			}
		} catch (SQLException e) {
			System.err.println("Can not add Showtimes: " + e.getMessage());
		}
		return false;
	}
	
	//lấy mã phòng từ tên phòng
	public String getRoomIDByRoomName(String name) {
		String sql = "select RoomID from Room where RoomName = ?";
		String strRes = null;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				strRes = res.getString("RoomID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strRes;
	}
	
	//lấy mã phim từ tên phim
		public String getMovieIDByMovieName(String name) {
			String sql = "select MovieID from Movies where Title = ?";
			String strRes = null;
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, name);
				ResultSet res = stmt.executeQuery();
				if(res.next()) {
					strRes = res.getString("MovieID");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return strRes;
		}
}
