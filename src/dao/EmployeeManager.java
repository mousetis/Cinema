package dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import connectDB.Connected;
import model.Employee;

public class EmployeeManager {
	
	private Connection con = Connected.getConnection();
	private PreparedStatement stmt;
	
	//hàm đổi từ localdate sang date
	public static java.sql.Date localDateToSqlDate(LocalDate localDate) {
	    return (localDate != null) ? java.sql.Date.valueOf(localDate) : null;
	}
	
	//hàm đổi từ date thành localdate
	public static LocalDate sqlDateToLocalDate(java.sql.Date sqlDate) {
	    return (sqlDate != null) ? sqlDate.toLocalDate() : null;
	}

	
	//lấy danh sách nhân viên
	public ArrayList<Employee> getListEmployee(){
		ArrayList<Employee> list = new ArrayList<Employee>();
		String sql = "select * from Employee";
		String gender = null;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				Employee employee  = new Employee();
				employee.setEmployeeID(res.getString("EmployeeID"));
				employee.setEmployeeName(res.getString("EmployeeName"));
				employee.setAddress(res.getString("Address"));
				gender = res.getString("Gender");
				if(gender.equalsIgnoreCase("Nam")) {
					employee.setGender(true);
				}else {
					employee.setGender(false);
				}
				employee.setEmail(res.getString("Email"));
				employee.setRole(res.getString("Role"));
				employee.setPhone("Phone");
				employee.setHireDate(sqlDateToLocalDate(res.getDate("HireDate")));
				employee.setSalary(res.getDouble("Salary"));
				employee.setImg(res.getString("Img"));//===========================Mới thêm=============================
				
				list.add(employee);
			}
			
		} catch (SQLException e) {
			System.err.println("Không thể lấy danh sách nhân viên: " + e.getMessage());
		}
		return list;
	}

	//tìm nhân viên
	public ArrayList<Employee> findEmployee(String keyID, String keyName){
		ArrayList<Employee> listEmp = new ArrayList<Employee>();
		String sql = "select * from Employee where EmployeeID like ? or EmployeeName like ?";
		String findID = null;
		String findName = null;
		String gender = null;
		
		try {
			stmt  = con.prepareStatement(sql);
			if(keyID != null && !keyID.isEmpty()) {
				findID = keyID;
			}
			if(keyName != null && !keyName.isEmpty()) {
				findName = "%"+keyName+"%";
			}
			stmt.setString(1, findID);
			stmt.setString(2, findName);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				Employee employee  = new Employee();
				employee.setEmployeeID(res.getString("EmployeeID"));
				employee.setEmployeeName(res.getString("EmployeeName"));
				employee.setAddress(res.getString("Address"));
				gender = res.getString("Gender");
				if(gender.equalsIgnoreCase("Nam")) {
					employee.setGender(true);
				}else {
					employee.setGender(false);
				}
				employee.setEmail(res.getString("Email"));
				employee.setRole(res.getString("Role"));
				employee.setPhone("Phone");
				employee.setHireDate(sqlDateToLocalDate(res.getDate("HireDate")));
				employee.setSalary(res.getDouble("Salary"));
				employee.setImg(res.getString("Img"));//===========================Mới thêm=============================
				
				listEmp.add(employee);
			}
		} catch (SQLException e) {
			System.err.println("Không thể tìm nhân viên: " + e.getMessage());
		}
		return listEmp;
	}
	
	
	//xoá nhân viên
	public boolean deleteEmployee(String keyID) {
		String sql = "delete from Employee where EmployeeID like ?";
		try {
			if(keyID != null && !keyID.isEmpty()) {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, keyID);
				ResultSet res = stmt.executeQuery();
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println("Không thể xoá nhân viên: " + e.getMessage());
		}
		return false;
	}
	

	//cập nhật nhân viên
	public boolean updateEmployee(String keyID, Employee nv){
		String sql ="update Employee set EmployeeID = ?, EmployeeName = ?, Role = ?, Phone = ?, Email = ?, Address = ?, HireDate = ?, Salary = ?, Gender = ? where EmployeeID = ?";
		try {
			if(keyID != null & !keyID.isEmpty()) {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, nv.getEmployeeID());
				stmt.setString(2, nv.getEmployeeName());
				stmt.setString(3, nv.getRole());
				stmt.setString(4, nv.getPhone());
				stmt.setString(5, nv.getEmail());
				stmt.setString(6, nv.getAddress());
				stmt.setDate(7, localDateToSqlDate(nv.getHireDate()));
				stmt.setDouble(8, nv.getSalary());
				if(nv.isGender()) {
					stmt.setString(9, "Nam");
				} else {
					stmt.setString(9, "Nữ");
				}
				stmt.setString(10, keyID);
				
				ResultSet res = stmt.executeQuery();
				return true;
			}
		} catch (SQLException e) {
			System.err.println("Can not update for: " + keyID + ": " + e.getMessage());
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
	
		//thêm nhân viên
		public boolean addEmployee(Employee nv, String email, String password) {
			String sql = "insert into Employee(EmployeeID, EmployeeName, Role, Phone, Email, Gender) values(?, ?, ?, ?, ?, ?)";
			try {
				if(nv != null) {
					
					String ID = randomID("E","Employee", "EmployeeID");
					nv.setEmployeeID(ID);
					PreparedStatement stm;
					stm = con.prepareStatement(sql);
					stm.setString(1, nv.getEmployeeID());
					stm.setString(2, nv.getEmployeeName());
					stm.setString(3, nv.getRole());
					stm.setString(4, nv.getPhone());
					stm.setString(5, nv.getEmail());
					if(nv.isGender()) {
						stm.setString(6, "Nam");
					} else {
						stm.setString(6, "Nữ");
					}
					
					int res = stm.executeUpdate();
					if(res > 0) {
						String accountID = randomID("A", "Account", "AccountID");
						String employeeID = nv.getEmployeeID();
						String sqlInsertAccount = "insert into Account values (?, ?, ?, ?)";
						PreparedStatement psAccount = con.prepareStatement(sqlInsertAccount);
						psAccount.setString(1, accountID);
						psAccount.setString(2, email);
						psAccount.setString(3, password);
						psAccount.setString(4, employeeID);
						
						int resAccount = psAccount.executeUpdate();
						return resAccount > 0;
					}	
				}
			} catch (SQLException e) {
				System.err.println("Can not add employee: " + e.getMessage());
			}
			return false;
		}
	
	//lấy dữ liệu nhân viên
	public Employee getEmployee(String keyID) {
		Employee employee = new Employee();
		String sql = "select * from Employee where EmployeeID = ?";
		String gender = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, keyID);
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
			employee.setEmployeeID(res.getString("EmployeeID"));
			employee.setEmployeeName(res.getString("EmployeeName"));
			employee.setAddress(res.getString("Address"));
			gender = res.getString("Gender");
			if(gender.equalsIgnoreCase("Nam")) {
				employee.setGender(true);
			} else {
				employee.setGender(false);
			}
			employee.setEmail(res.getString("Email"));
			employee.setRole(res.getString("Role"));
			employee.setPhone(res.getString("Phone"));
			employee.setHireDate(sqlDateToLocalDate(res.getDate("HireDate")));
			employee.setSalary(res.getDouble("Salary"));
			employee.setImg(res.getString("Img"));  //===========================Mới thêm=============================
			}
		} catch (SQLException e) {
			System.err.println("Can not get data of Employee: " + e.getMessage());
		}
		
		return employee;
	}
	
}
