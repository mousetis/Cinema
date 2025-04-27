package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.Connected;

public class Ticket {
	
	static Connection con = Connected.getConnection();
	
	//lấy giá từ bảng vé
	public static Double getPriceFromTicket(String ticketID) {
		Double price = 0.0;
		String sql = "select Price from Ticket where TicketID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ticketID);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				price = res.getDouble("Price");
			}
			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
	
	//cập nhật giá vé
	public static boolean updatePrice(String ticketID, double newPrice) {
		String sql = "update Ticket set Price = ? where TicketID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, newPrice);
			stmt.setString(2, ticketID);
			int res = stmt.executeUpdate();
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//thêm giá vé
	public static boolean addPrice(String ticketID, double price) {
		String sql = "insert into Ticket(Price) values(?) where TicketID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, price);
			stmt.setString(2, ticketID);
			
			int res = stmt.executeUpdate();
			return res > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
