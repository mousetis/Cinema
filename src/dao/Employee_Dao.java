package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.Connected;
import model.Customer;
import model.Employee;
import model.MovieCategory;
import model.Movies;
import model.OrderDetails;
import model.Orders;
import model.Products;
import model.Room;
import model.Seat;
import model.Showtimes;
import model.Ticket;

public class Employee_Dao {
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
			}
		} catch (SQLException e) {
			System.err.println("Can not get data of Employee: " + e.getMessage());
		}

		return employee;
	}



	//lấy danh sách phim
	public ArrayList<Movies> getListMovie(){
		ArrayList<Movies> listMovies = new ArrayList<Movies>();
		String sql = "Select * from Movies m join MovieType mt on m.MovieID = mt.MovieID join MovieCategory mc on mt.CategoryID = mc.CategoryID";
		try {
			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while(res.next()) {

				String movieID = res.getString("MovieID");
				String title  = res.getString("Title");
				int duration = res.getInt("Duration");
				String description = res.getString("Description");
				int ageRating = res.getInt("AgeRating");
				LocalDate releaseDate = sqlDateToLocalDate(res.getDate("ReleaseDate"));
				String img = res.getString("Img");
				String categoryID = res.getString("CategoryID");
				String categoryName = res.getString("CategoryName");
				MovieCategory movieCategory = new MovieCategory(categoryID, categoryName);

				Movies movie = new Movies(img, movieID, movieCategory, title, duration, description, ageRating, releaseDate);
				listMovies.add(movie);
			}

		} catch (SQLException e) {
			System.err.println("Không thể lấy danh sách phim: " + e.getMessage());
		}
		return listMovies;
	}

	//lấy danh sách phòng
	public ArrayList<Room> getListRoom(){
		ArrayList<Room> listRoom = new ArrayList<Room>();
		String sql = "Select * from Room";
		try {
			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while(res.next()) {

				String roomID = res.getString("RoomID");
				String roomName  = res.getString("RoomName");
				int Capacity = res.getInt("Capacity");
				String RoomType = res.getString("RoomType");
				String RoomStatus = res.getString("RoomStatus");


				Room room = new Room(roomID, roomName, Capacity, RoomType, RoomStatus);
				listRoom.add(room);
			}

		} catch (SQLException e) {
			System.err.println("Không thể lấy danh sách phòng: " + e.getMessage());
		}
		return listRoom;
	}

	//lấy danh sách phòng
	public ArrayList<Products> getListProduct(){
		ArrayList<Products> listProducts = new ArrayList<Products>();
		String sql = "Select * from Products";
		try {
			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while(res.next()) {

				String productID = res.getString("ProductID");
				String productName  = res.getString("ProductName");
				double price = res.getDouble("Price");
				String productType = res.getString("ProductType");

				Products p = new Products(productID, productName, price, productType);
				listProducts.add(p);
			}

		} 
		catch (SQLException e) {
			System.err.println("Không thể lấy danh sách phòng: " + e.getMessage());
		}
		return listProducts;
	}


	//lấy danh sách ghế
	public ArrayList<Seat> getListSeat(){
		ArrayList<Seat> listSeat = new ArrayList<Seat>();
		String sql = "Select * from Seat s join Room r on s.RoomID = r.RoomID";
		try {
			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while(res.next()) {

				String seatID = res.getString("SeatID");
				String seatNumber  = res.getString("SeatNumber");
				String seatType = res.getString("SeatType");

				String roomID = res.getString("RoomID");
				String roomName = res.getString("RoomName");
				int capacity = res.getInt("Capacity");
				String roomType = res.getString("RoomType");
				String roomStatus = res.getString("RoomStatus");
				String status = res.getString("Status");

				Room room = new Room(roomID, roomName, capacity, roomType, roomStatus);


				Seat seat = new Seat(seatID, seatNumber, seatType, room, status);
				listSeat.add(seat);
			}

		} catch (SQLException e) {
			System.err.println("Không thể lấy danh sách ghế: " + e.getMessage());
		}
		return listSeat;
	}

	//Thêm hóa đơn mới
	public boolean insertOrder(Orders order) {		
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into " + "Orders values(?, ?, ?, ?, ?)");
			stmt.setString(1, order.getOrderID());
			stmt.setString(2,  order.getCustomerID().getCustomerID());
			stmt.setString(3,  order.getEmployeeID().getEmployeeID());
			stmt.setDate(4, java.sql.Date.valueOf(order.getOrderDate().toLocalDate()));
			stmt.setDouble(5,   order.getTotal());

			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	//Thêm chi hóa đơn mới
	public boolean insertOrderDetails(OrderDetails od) {		
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into " + "OrderDetails values(?, ?, ?, ?, ?)");
			stmt.setString(1, od.getOrderID().getOrderID());
			stmt.setString(2,  od.getProductID().getProductID());
			stmt.setInt(3,  od.getQuantity());
			stmt.setDouble(4, od.getPrice());
			stmt.setDouble(5,   od.getDiscount());

			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	//Thêm vé mới
	public boolean insertTicket(Ticket t) {		
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into " + "Ticket values(?, ?, ?, ?, ?)");
			stmt.setString(1, t.getTicketID());
			stmt.setString(2,  t.getCustomerID().getCustomerID());
			stmt.setString(3,  t.getShowtimeID().getShowtimeID());
			stmt.setString(4, t.getSeatID().getSeatID());
			stmt.setDouble(5,   t.getPrice());

			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	//Thêm khách hàng mới
	public boolean insertCustomer(Customer c) {		
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into " + "Customer values(?, ?, ?, ?)");
			stmt.setString(1, c.getCustomerID());
			stmt.setString(2,  c.getCustomerName());
			stmt.setString(3,  c.getPhone());
			stmt.setString(4, c.getEmail());


			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	public boolean updateSeat(Seat seat) {
	    int n = 0;

	    try {
	        stmt = con.prepareStatement(
	            "UPDATE Seat SET Status = ? WHERE RoomID = ? AND SeatNumber = ?"
	        );

	        stmt.setString(1, "Đã đặt");
	        stmt.setString(2, seat.getRoomID().getRoomID());
	        stmt.setString(3, seat.getSeatNUmber());

	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return n > 0;
	}



	public boolean processPayment(Orders order, List<OrderDetails> orderDetails, List<Ticket> tickets, List<Seat> seatsToUpdate) {
		try {
			con.setAutoCommit(false); // Bắt đầu transaction

			// 1. Thêm hóa đơn
			if (!insertOrder(order)) {
				con.rollback();
				return false;
			}

			// 2. Thêm chi tiết hóa đơn
			for (OrderDetails od : orderDetails) {
				if (!insertOrderDetails(od)) {
					con.rollback();
					return false;
				}
			}

			// 3. Thêm vé và cập nhật ghế
			for (int i = 0; i < tickets.size(); i++) {
				Ticket t = tickets.get(i);
				Seat s = seatsToUpdate.get(i);

				if (!insertTicket(t) || !updateSeat(s)) {
					con.rollback();
					return false;
				}
			}

			con.commit(); // Commit nếu mọi thứ thành công
			return true;
		} 
		catch (SQLException e) {
			try {
				con.rollback();
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			System.err.println("Lỗi trong quá trình thanh toán: " + e.getMessage());
			e.printStackTrace();
			return false;
		} 
		finally {
			try {
				con.setAutoCommit(true);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Products getProductByName(String name) {
	    try {
	        String sql = "SELECT * FROM Products WHERE ProductName = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return new Products(
	                rs.getString("ProductID"),
	                rs.getString("ProductName"),
	                rs.getDouble("Price"),
	                rs.getString("ProductType")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public Showtimes getShowTime(String movieID) {
	    try {
	        String sql = "SELECT ShowtimeID, MovieID, RoomID, ShowDateTime, StartedTime, EndTime " +
	                     "FROM Showtimes WHERE MovieID = ?";
	        
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, movieID);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            // Lấy các đối tượng Movies và Room từ ID
	            Movies movie = new Movies(rs.getString("MovieID"));
	            Room room = new Room(rs.getString("RoomID"));
	            
	            // Lấy ngày và giờ từ các cột tương ứng
	            LocalDate showDate = rs.getDate("ShowDateTime").toLocalDate();
	            LocalTime startTime = rs.getTime("StartedTime").toLocalTime();
	            LocalTime endTime = rs.getTime("EndTime").toLocalTime();
	            
	            return new Showtimes(
	                rs.getString("ShowtimeID"),
	                movie,
	                room,
	                showDate,
	                startTime,
	                endTime
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
