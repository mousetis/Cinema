package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import connectDB.Connected;
import model.Employee;
import model.MovieCategory;

public class Movies {
	static Connection con = Connected.getConnection();
	
	//hàm đổi từ localdate sang date
	public static java.sql.Date localDateToSqlDate(LocalDate localDate) {
	    return (localDate != null) ? java.sql.Date.valueOf(localDate) : null;
	}
		
	//hàm đổi từ date thành localdate
	public static LocalDate sqlDateToLocalDate(java.sql.Date sqlDate) {
	    return (sqlDate != null) ? sqlDate.toLocalDate() : null;
	}
	
	//lấy danh sách phim
	public ArrayList<model.Movies> getListMovie(){
		ArrayList<model.Movies> list = new ArrayList<model.Movies>();
		String sql = "select * from [dbo].[Movies]";
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			
			while(res.next()) {
				model.Movies movie = new model.Movies();
				movie.setMovieID(res.getString("MovieID"));
				movie.setTitle(res.getString("Title"));
				movie.setDuration(res.getInt("Duration"));
				movie.setDescription(res.getString("Description"));
				movie.setReleaseDate(sqlDateToLocalDate(res.getDate("ReleaseDate")));
				movie.setImg(res.getString("Img"));
				movie.setAgeRating(res.getInt("AgeRating"));
				
				list.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//tìm phim
		public ArrayList<model.Movies> findMovies(String keyID, String keyName){
			ArrayList<model.Movies> list = new ArrayList<model.Movies>();
			String sql = "select * from Movies where MovieID like ? or Title like ?";
			String findID = null;
			String findName = null;
			
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
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
					model.Movies movie = new model.Movies();
					movie.setMovieID(res.getString("MovieID"));
					movie.setTitle(res.getString("Title"));
					movie.setDuration(res.getInt("Duration"));
					movie.setDescription(res.getString("Description"));
					movie.setAgeRating(res.getInt("AgeRating"));
					movie.setReleaseDate(sqlDateToLocalDate(res.getDate("ReleaseDate")));
					movie.setImg(res.getString("Img"));
					
					list.add(movie);
				}
			} catch (SQLException e) {
				System.err.println("Không thể tìm phim: " + e.getMessage());
			}
			return list;
		}
		
		
		//xoá phim
		public boolean deleteMovies(String keyID) {
			String sql = "delete from Movies where MovieID like ?";
			try {
				if(keyID != null && !keyID.isEmpty()) {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, keyID);
					int res = stmt.executeUpdate();
					return res > 0;
				}
				
			} catch (SQLException e) {
				System.err.println("Không thể xoá phim: " + e.getMessage());
			}
			return false;
		}
		

		//cập nhật phim
		public boolean updateMovies(String keyID, model.Movies movie){
			String sql ="update Movies set MovieID = ?, Title = ?, Duration = ?, Description = ?, AgeRating = ?, ReleaseDate = ? where MovieID = ?";
			try {
				if(keyID != null & !keyID.isEmpty()) {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, movie.getMovieID());
					stmt.setString(2, movie.getTitle());
					stmt.setInt(3, movie.getDuration());
					stmt.setString(4, movie.getDescription());
					stmt.setInt(5, movie.getAgeRating());
					stmt.setDate(6, localDateToSqlDate(movie.getReleaseDate()));
					stmt.setString(7, keyID);
					
					int res = stmt.executeUpdate();
					return res > 0;
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
		
		//thêm phim
		public boolean addMovie(model.Movies movie) {
			String sql = "insert into Employee(MovieID, Title, Duration, Description, AgeRating, ReleaseDate) values(?, ?, ?, ?, ?, ?)";
			try {
				if(movie != null) {
					
					String ID = randomID("M","Movies", "MovieID");
					movie.setMovieID(ID);
					PreparedStatement stm;
					stm = con.prepareStatement(sql);
					stm.setString(1, movie.getMovieID());
					stm.setString(2, movie.getTitle());
					stm.setInt(3, movie.getDuration());
					stm.setString(4, movie.getDescription());
					stm.setInt(5, movie.getAgeRating());
					stm.setDate(6, localDateToSqlDate(movie.getReleaseDate()));
					
					
					int res = stm.executeUpdate();
					return res > 0;
				}
			} catch (SQLException e) {
				System.err.println("Can not add employee: " + e.getMessage());
			}
			return false;
		}
		
		//lấy dữ liệu phim
		public model.Movies getMoive(String keyID) {
			model.Movies movie = new model.Movies();
			String sql = "select * from Movies where MovieID = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, keyID);
				ResultSet res = stmt.executeQuery();
				
				if(res.next()) {
				movie.setMovieID(res.getString("MovieID"));
				movie.setTitle(res.getString("Title"));
				movie.setDuration(res.getInt("Duration"));
				movie.setDescription(res.getString("Description"));
				movie.setAgeRating(res.getInt("AgeRating"));
				movie.setAgeRating(res.getInt("AgeRating"));
				movie.setReleaseDate(sqlDateToLocalDate(res.getDate("ReleaseDate")));
				movie.setImg(res.getString("Img"));
				}
			} catch (SQLException e) {
				System.err.println("Can not get data of Movie: " + e.getMessage());
			}
			
			return movie;
		}
		
		//lấy showtimes theo movieID
		public static String getShowtimeIDFromMovies(String movieID) {
			String showtimeID = null;
			String sql = "select top 1 ShowtimeID from Showtimes where MovieID = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, movieID);
				ResultSet res = stmt.executeQuery();
				
				if(res.next()) {
					showtimeID = res.getString("ShowtimeID");
				}
				res.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return showtimeID;
		}
}
