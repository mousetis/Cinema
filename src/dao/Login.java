package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.Connected;
import model.Employee;

public class Login {
	Connection con = Connected.getConnection();
	
	//kiểm tra tên và mật khẩu
	
	//kiểm tra tài khoản đăng nhập
	public Employee checkAccount(String user, String password) {
		String sql = "select * from [dbo].[Account] a JOIN [dbo].[Employee] e ON e.EmployeeID = a.EmployeeID where a.Username like ? and a.Password like ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, password);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				Employee nv = new Employee();
				String keyID = res.getString("EmployeeID");
				nv = new EmployeeManager().getEmployee(keyID);
				return nv;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
