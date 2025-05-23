package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JCalendar;

public class ThongKeDT extends JPanel implements ActionListener{
	private JLabel lbTitle;
	private JRadioButton rdNgay;
	private JRadioButton rdThang;
	private JRadioButton rdNam;
	private ButtonGroup rdGroup;
	private JCalendar calendar;
	private JSpinner spnThang;
	private JSpinner spnNam;
	private JButton btnTimKiem;
	private JTable table;
	private JLabel lbTitle2;
	private DefaultTableModel tblmodel;
	private TableRowSorter<TableModel> sorter;
	private NumberEditor editor;


	public ThongKeDT() {
		Font font = new Font ("Arial",Font.BOLD,15);
		Font font2 = new Font ("Arial",Font.BOLD,20);
		Font font3 = new Font ("Arial",Font.BOLD,18);
		
		//Phần khung chính
		Box boxCent = new Box(BoxLayout.X_AXIS);
		
		//Phần khung bên trái
		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLeft = new Box(BoxLayout.Y_AXIS);
		
		//Field title của phần khung bên trái
		Box boxLeft1 = new Box(BoxLayout.X_AXIS);
		boxLeft1.add(lbTitle = new JLabel("BỘ LỌC THỐNG KÊ"));
		lbTitle.setFont(font2);
		lbTitle.setForeground(Color.BLUE);
		boxLeft.add(boxLeft1);
		
		//Phần khung con chứa lựa chọn thời gian nằm trên khung bên trái
		Box boxLeft2 = new Box(BoxLayout.Y_AXIS);
		boxLeft2.setBorder(BorderFactory.createTitledBorder("Theo thời gian"));
		boxLeft2.setPreferredSize(new Dimension(250, 500));
		
		// Radio chọn lọc theo kiểu thời gian
        rdNgay = new JRadioButton("Theo ngày");
        rdNgay.setFont(font);
        rdThang = new JRadioButton("Theo tháng");
        rdThang.setFont(font);
        rdNam = new JRadioButton("Theo năm");
        rdNam.setFont(font);
        rdGroup = new ButtonGroup();
        rdGroup.add(rdNgay);
        rdGroup.add(rdThang);
        rdGroup.add(rdNam);
        rdNgay.setSelected(true);
		
        //Field chứa nút radio ngày nằm trên phần khung con của phần bên trái
		JPanel pnlNgay = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// Calendar chọn ngày
        calendar = new JCalendar();
        calendar.setPreferredSize(new Dimension(200, 150));
        pnlNgay.add(rdNgay);
        boxLeft2.add(pnlNgay);
        boxLeft2.add(calendar);
        
        //Field chứa nút radio tháng nằm trên phần khung con của phần bên trái
        JPanel pnlThang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Spinner chọn tháng
        Box boxThang = new Box(BoxLayout.X_AXIS);
        SpinnerNumberModel monthModel = new SpinnerNumberModel(1, 1, 12, 1);  // giúp có thể chọn một giá trị số bằng cách bấm nút tăng/giảm. Giá trị ban đầu là 1, giá trị nhỏ nhất là 1, giá trị lớn nhất là 12, bước nhảy là 1 
        spnThang = new JSpinner(monthModel);
        spnThang.setPreferredSize(new Dimension(5, 25));
        pnlThang.add(rdThang);
        boxLeft2.add(pnlThang);
        boxThang.add(Box.createHorizontalStrut(20));
        boxThang.add(spnThang);
        boxThang.add(Box.createHorizontalStrut(130));
        boxLeft2.add(boxThang);
        
        //Field chứa nút radio năm nằm trên phần khung con của phần bên trái
        JPanel pnlNam = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Spinner chọn năm
        Box boxNam = new Box(BoxLayout.X_AXIS);
        SpinnerNumberModel yearModel = new SpinnerNumberModel(2024, 2000, 2100, 1); // giúp có thể chọn một giá trị số bằng cách bấm nút tăng/giảm. Giá trị ban đầu là 2014, giá trị nhỏ nhất là 2000, giá trị lớn nhất là 2100, bước nhảy là 1 
        spnNam = new JSpinner(yearModel);
        editor = new JSpinner.NumberEditor(spnNam, "#"); //định dạng kiểu số nguyên không có phần thập phân.
        spnNam.setEditor(editor);
        spnNam.setPreferredSize(new Dimension(5, 25));
        pnlNam.add(rdNam);
        boxLeft2.add(pnlNam);
        boxNam.add(Box.createHorizontalStrut(20));
        boxNam.add(spnNam);
        boxNam.add(Box.createHorizontalStrut(130));
        boxLeft2.add(boxNam);
        
        //Field chứa nút chức năng nằm bên trái
        boxLeft2.add(Box.createVerticalStrut(15));
        boxLeft2.add(btnTimKiem = new JButton("Tìm kiếm"));
        btnTimKiem.setFont(font);
        btnTimKiem.setBackground(Color.GREEN);
        btnTimKiem.setPreferredSize(new Dimension(100, 30));
        
        //Thêm phần khung bên trái vào khung chính
        boxLeft.add(boxLeft2);
        pnlLeft.add(boxLeft);
        boxCent.add(pnlLeft);
        boxCent.add(Box.createHorizontalStrut(50));
		
		//============================================================================
		//Phần khung bên phải
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		pnlRight.setBorder(BorderFactory.createTitledBorder(""));
		
		//Field title của phần khung bên phải
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight.add(Box.createVerticalStrut(15));
		boxRight1.add(lbTitle2 = new JLabel("BẢNG THỐNG KÊ DOANH THU"));
		lbTitle2.setFont(font3);
		lbTitle2.setForeground(Color.GREEN);
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(30));
		
		//Phần table nằm trên phần bên phải
		Box boxTable = new Box(BoxLayout.Y_AXIS);
		String[] colNames = {"Thời gian", "Doanh thu vé", "Doanh thu bắp", "Doanh thu nước", "Tổng"};
		tblmodel = new DefaultTableModel(colNames, 0);
		table = new JTable(tblmodel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(600, 400)); // chỉnh chiều rộng của bảng
		boxTable.add(scrollPane);
		sorter = new TableRowSorter<TableModel>(tblmodel);
		table.setRowSorter(sorter);
		boxRight.add(boxTable);
		
		//Thêm phần bên phải vào phần khung chính
		pnlRight.add(boxRight);
		boxCent.add(pnlRight);
		
		//Thêm phần khung chính vào JFrame
        add(boxCent);
		
//		setSize(1000, 600);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);
        
        rdNgay.addActionListener(this);
        rdThang.addActionListener(this);
        rdNam.addActionListener(this);
        btnTimKiem.addActionListener(this);
	}
	
	private void hienThiDoanhThuTheoNgay() {
	    LocalDate selectedDate = calendar.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
	    List<Map<String, Object>> dailyRevenue = dao.Revenue.getDailyRevenue(Date.valueOf(selectedDate));
	    for (Map<String, Object> revenue : dailyRevenue) {
	        Object salesDateObject = revenue.get("SalesDate");
	        if (salesDateObject instanceof java.sql.Date) {
	            java.sql.Date sqlSalesDate = (java.sql.Date) salesDateObject;
	            LocalDate localSalesDate = sqlSalesDate.toLocalDate();
	            tblmodel.addRow(new Object[]{
	                    DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localSalesDate),
	                    revenue.get("TicketRevenue"),
	                    revenue.get("PopcornRevenue"),
	                    revenue.get("DrinkRevenue"),
	                    revenue.get("TotalRevenue")
	            });
	        } else {
	            // Xử lý trường hợp dữ liệu không phải là java.sql.Date (nếu có)
	            System.err.println("Lỗi: Kiểu dữ liệu của SalesDate không phải là java.sql.Date");
	        }
	    }
	}

    private void hienThiDoanhThuTheoThang() {
        int selectedMonth = (int) spnThang.getValue();
        int selectedYear = (int) spnNam.getValue();
        List<Map<String, Object>> monthlyRevenue = null;
		try {
			monthlyRevenue = dao.Revenue.getMonthlyRevenue(selectedMonth, selectedYear);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (Map<String, Object> revenue : monthlyRevenue) {
            tblmodel.addRow(new Object[]{
                    revenue.get("SalesMonthYear"),
                    revenue.get("TicketRevenue"),
                    revenue.get("PopcornRevenue"),
                    revenue.get("DrinkRevenue"),
                    revenue.get("TotalRevenue")
            });
        }
    }

    private void hienThiDoanhThuTheoNam() {
        int selectedYear = (int) spnNam.getValue();
        List<Map<String, Object>> yearlyRevenue = null;
		try {
			yearlyRevenue = dao.Revenue.getYearlyRevenue(selectedYear);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (Map<String, Object> revenue : yearlyRevenue) {
            tblmodel.addRow(new Object[]{
                    revenue.get("SalesYear"),
                    revenue.get("TicketRevenue"),
                    revenue.get("PopcornRevenue"),
                    revenue.get("DrinkRevenue"),
                    revenue.get("TotalRevenue")
            });
        }
    }

	
	public static void main(String[] args) {
		new ThongKeDT();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTimKiem) {
            tblmodel.setRowCount(0); // Xóa dữ liệu cũ trên bảng
            if (rdNgay.isSelected()) {
                hienThiDoanhThuTheoNgay();
            } else if (rdThang.isSelected()) {
                hienThiDoanhThuTheoThang();
            } else if (rdNam.isSelected()) {
                hienThiDoanhThuTheoNam();
            }
        }	
	}
}
