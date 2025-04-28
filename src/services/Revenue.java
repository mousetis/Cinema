package services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Revenue {

    private dao.Revenue revenueDAO = new dao.Revenue();

    public List<Map<String, Object>> getDailyRevenue(LocalDate salesDate) {
        return revenueDAO.getDailyRevenue(Date.valueOf(salesDate));
    }

    public List<Map<String, Object>> getMonthlyRevenue(int salesMonth) {
        return revenueDAO.getMonthlyRevenue(salesMonth);
    }

    public List<Map<String, Object>> getMonthlyRevenue(int salesMonth, int salesYear) {
        return revenueDAO.getMonthlyRevenue(salesMonth, salesYear);
    }

    public List<Map<String, Object>> getYearlyRevenue(int salesYear) {
        return revenueDAO.getYearlyRevenue(salesYear);
    }

}