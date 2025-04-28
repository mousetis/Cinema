package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.Connected;

public class Revenue {

    static Connection con = Connected.getConnection();

    public static List<Map<String, Object>> getDailyRevenue(java.sql.Date salesDate) {
        List<Map<String, Object>> revenueList = new ArrayList<>();
        String sql = "{call GetDailyRevenue(?)}";
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = con.prepareCall(sql);
            callableStatement.setDate(1, salesDate);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> revenue = new HashMap<>();
                revenue.put("SalesDate", resultSet.getDate("SalesDate"));
                revenue.put("TicketRevenue", resultSet.getDouble("TicketRevenue"));
                revenue.put("PopcornRevenue", resultSet.getDouble("PopcornRevenue"));
                revenue.put("DrinkRevenue", resultSet.getDouble("DrinkRevenue"));
                revenue.put("TotalRevenue", resultSet.getDouble("TotalRevenue"));
                revenueList.add(revenue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (callableStatement != null) callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return revenueList;
    }

    public List<Map<String, Object>> getMonthlyRevenue(int salesMonth) {
        return getMonthlyRevenue(salesMonth, java.time.Year.now().getValue());
    }

    public static List<Map<String, Object>> getMonthlyRevenue(int salesMonth, int salesYear) {
        List<Map<String, Object>> revenueList = new ArrayList<>();
        String sql = "{call GetMonthlyRevenue(?, ?)}";
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, salesMonth);
            callableStatement.setInt(2, salesYear);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> revenue = new HashMap<>();
                revenue.put("SalesMonthYear", resultSet.getString("SalesMonthYear"));
                revenue.put("TicketRevenue", resultSet.getDouble("TicketRevenue"));
                revenue.put("PopcornRevenue", resultSet.getDouble("PopcornRevenue"));
                revenue.put("DrinkRevenue", resultSet.getDouble("DrinkRevenue"));
                revenue.put("TotalRevenue", resultSet.getDouble("TotalRevenue"));
                revenueList.add(revenue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (callableStatement != null) callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return revenueList;
    }

    public static List<Map<String, Object>> getYearlyRevenue(int salesYear) {
        List<Map<String, Object>> revenueList = new ArrayList<>();
        String sql = "{call GetYearlyRevenue(?)}";
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, salesYear);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> revenue = new HashMap<>();
                revenue.put("SalesYear", resultSet.getInt("SalesYear"));
                revenue.put("TicketRevenue", resultSet.getDouble("TicketRevenue"));
                revenue.put("PopcornRevenue", resultSet.getDouble("PopcornRevenue"));
                revenue.put("DrinkRevenue", resultSet.getDouble("DrinkRevenue"));
                revenue.put("TotalRevenue", resultSet.getDouble("TotalRevenue"));
                revenueList.add(revenue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (callableStatement != null) callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return revenueList;
    }
}