package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.Connected;

public class ShowTimes {
	static Connection con = Connected.getConnection();
	
	
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
}
