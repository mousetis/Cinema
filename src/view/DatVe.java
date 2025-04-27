package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.toedter.calendar.JDateChooser;

import connectDB.Connected;
import dao.Employee_Dao;
import model.Customer;
import model.Movies;
import model.OrderDetails;
import model.Orders;
import model.Room;
import model.Seat;
import model.Ticket;
import model.Products;
import model.Employee;
import model.Showtimes;

public class DatVe extends JPanel implements ActionListener{
	private static Movies phimDangChon; 
	private JLabel lbTitlePhim;
	private JDateChooser findDateChooser;
	private JLabel lbNgayChieu;
	private JLabel lbTenPhim;
	private JTextField txtTenPhim;
	private JLabel lbGioBatDau;
	private SpinnerDateModel modelBD;
	private JSpinner spnGioBD;
	private DateEditor editorBD;
	private JLabel lbTitleKH;
	private JLabel lbTenKH;
	private JTextField txtTenKH;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JLabel lbTenGhe;
	private JLabel lbGia;
	private JLabel lbTitleCN;
	private JLabel lbTitleDV;
	private JLabel lbBongNgo;
	private JSpinner spnBongNgo;
	private JLabel lbTienBN;
	private JLabel lbNuocNgot;
	private JSpinner spnNuocNgot;
	private JButton btnThanhToan;
	private JButton btnLamMoi;
	private JLabel lbViTri;
	private JLabel lbGiaGhe;
	private Box boxGhe;
	private ImageIcon iconPhim;
	private Image imgPhim;
	private JLabel lbPhim;
	private services.Employee_Services service;
	private dao.Employee_Dao dao;
	private JPanel pnlGhe;
	private JButton btn;
	private String label;


	private Map<String, JButton> seatButtons = new HashMap<>(); // Lưu các nút ghế
	private ArrayList<String> selectedSeats = new ArrayList<>(); // Ghế đã chọn
	private double totalSeatPrice = 0; // Tổng tiền ghế
	private final double STANDARD_SEAT_PRICE = 50000; // Giá ghế thường
	private final double VIP_SEAT_PRICE = 80000; // Giá ghế VIP
	private static final double POPCORN_PRICE = 35000; // Giá bỏng ngô
	private static final double DRINK_PRICE = 25000; // Giá nước ngọt
	private String roomID = "R001"; // ID phòng chiếu 
	private JLabel lbPhong;
	private JComboBox comboxPhong;
	
	private static Employee nvDangNhap; //================================Mới thêm===============================


	public DatVe(Movies phim, Employee nvDangNhap) {
		this.nvDangNhap = nvDangNhap;//================================Mới thêm===============================
		
		Font font = new Font ("Arial",Font.BOLD,16);
		Font font2 = new Font ("Arial",Font.BOLD,20);
		Font font3 = new Font ("Arial",Font.BOLD,18);
		Font font4 = new Font ("Arial",Font.ITALIC,9);
		
		dao = new Employee_Dao(); 
		service = new services.Employee_Services(dao);

		this.phimDangChon = phim;

		Box boxCent = new Box(BoxLayout.X_AXIS);

		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLeft = new Box(BoxLayout.Y_AXIS);
		boxLeft.add(Box.createHorizontalStrut(35));

		Box boxPhim = new Box(BoxLayout.Y_AXIS);
		boxPhim.add(Box.createHorizontalStrut(15));
		boxPhim.setBorder(BorderFactory.createTitledBorder(""));

		JPanel boxPhim1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		boxPhim1.add(lbTitlePhim = new JLabel("THÔNG TIN PHIM"));
		lbTitlePhim.setFont(font2);
		boxPhim.add(boxPhim1);
		boxPhim.add(Box.createVerticalStrut(10));

		Box boxPhimTrong = new Box(BoxLayout.X_AXIS);

		Box boxPhimLeft = new Box(BoxLayout.Y_AXIS);
		boxPhimLeft.setPreferredSize(new Dimension(270, 150));


		Box boxPhim2 = new Box(BoxLayout.X_AXIS);
		boxPhim2.add(Box.createHorizontalStrut(20));
		boxPhim2.add(lbTenPhim = new JLabel("Tên phim:"));
		lbTenPhim.setFont(font);
		boxPhim2.add(Box.createHorizontalStrut(10));
		boxPhim2.add(txtTenPhim = new JTextField());
		txtTenPhim.setPreferredSize(new Dimension(50, 30));
		txtTenPhim.setEnabled(false);
		txtTenPhim.setFont(font4);
		txtTenPhim.setForeground(Color.BLACK);
		boxPhim2.add(Box.createHorizontalStrut(10));
		boxPhimLeft.add(boxPhim2);
		boxPhimLeft.add(Box.createVerticalStrut(10));

		Box boxPhim3 = new Box(BoxLayout.X_AXIS);
		findDateChooser = new JDateChooser();
		findDateChooser.setDateFormatString("dd-MM-yyyy"); // format ngày
		findDateChooser.setPreferredSize(new Dimension(50, 30)); // chỉnh kích thước
		boxPhim3.add(Box.createHorizontalStrut(20));
		boxPhim3.add(lbNgayChieu = new JLabel("Ngày chiếu:"));
		lbNgayChieu.setFont(font);
		boxPhim3.add(Box.createHorizontalStrut(25));
		boxPhim3.add(findDateChooser);
		boxPhim3.add(Box.createHorizontalStrut(10));
		boxPhimLeft.add(boxPhim3);
		boxPhimLeft.add(Box.createVerticalStrut(10));

		//Field chọn giờ bắt dầu nằm trên phần bên phải
		Box boxPhim4 = new Box(BoxLayout.X_AXIS);
		boxPhim4.add(Box.createHorizontalStrut(20));
		boxPhim4.add(lbGioBatDau = new JLabel("Giờ bắt đầu:"));
		lbGioBatDau.setFont(font);
		boxPhim4.add(Box.createHorizontalStrut(20));
		// Spinner model với thời gian
		modelBD = new SpinnerDateModel(); //model (mô hình dữ liệu) cho spinner kiểu ngày/giờ.Mặc định nó sẽ lấy thời gian hiện tại làm giá trị ban đầu.
		spnGioBD = new JSpinner(modelBD); //Tạo một JSpinner mới với kiểu dữ liệu là ngày giờ từ SpinnerDateModel.
		// Định dạng hiển thị giờ và phút
		editorBD = new JSpinner.DateEditor(spnGioBD, "HH:mm");  //Tạo một editor (bộ hiển thị/định dạng) cho spinner có định dạng kiểu "giờ:phút"
		spnGioBD.setEditor(editorBD);  //Gán bộ hiển thị editorBD vừa tạo vào spinner spnGioBD, để nó dùng định dạng giờ phút
		spnGioBD.setPreferredSize(new Dimension(230, 30));
		boxPhim4.add(spnGioBD);
		boxPhim4.add(Box.createHorizontalStrut(10));
		boxPhimLeft.add(boxPhim4);
		boxPhimLeft.add(Box.createVerticalStrut(10));

		boxPhimTrong.add(boxPhimLeft);
		boxPhimTrong.add(Box.createHorizontalStrut(10));

		Box boxPhimRight = new Box(BoxLayout.Y_AXIS);
		boxPhimRight.setBorder(BorderFactory.createTitledBorder(""));
		boxPhimRight.setPreferredSize(new Dimension(90, 130));

		lbPhim = new JLabel();

		boxPhimRight.add(lbPhim);

		boxPhimTrong.add(boxPhimRight);
		boxPhimTrong.add(Box.createHorizontalStrut(10));

		boxPhim.add(boxPhimTrong);
		boxPhim.add(Box.createHorizontalStrut(15));

		boxLeft.add(boxPhim);
		boxLeft.add(Box.createVerticalStrut(15));

		//===============================================================
		Box boxKhachHang = new Box(BoxLayout.Y_AXIS);

		Box boxKH1 = new Box(BoxLayout.X_AXIS);
		boxKH1.add(lbTitleKH = new JLabel("KHÁCH HÀNG"));
		lbTitleKH.setFont(font2);
		boxKhachHang.add(boxKH1);
		boxKhachHang.add(Box.createVerticalStrut(10));

		Box boxKH2 = new Box(BoxLayout.X_AXIS);
		boxKH2.add(Box.createHorizontalStrut(25));
		boxKH2.add(lbTenKH = new JLabel("Tên khách hàng:"));
		lbTenKH.setFont(font);
		boxKH2.add(Box.createHorizontalStrut(15));
		boxKH2.add(txtTenKH  = new JTextField());
		txtTenKH.setPreferredSize(new Dimension(150, 30));
		boxKH2.add(Box.createHorizontalStrut(30));
		boxKhachHang.add(boxKH2);
		boxKhachHang.add(Box.createVerticalStrut(10));

		Box boxKH3 = new Box(BoxLayout.X_AXIS);
		boxKH3.add(Box.createHorizontalStrut(25));
		boxKH3.add(lbEmail = new JLabel("Email:"));
		lbEmail.setFont(font);
		boxKH3.add(Box.createHorizontalStrut(95));
		boxKH3.add(txtEmail  = new JTextField());
		txtEmail.setPreferredSize(new Dimension(150, 30));
		boxKH3.add(Box.createHorizontalStrut(30));
		boxKhachHang.add(boxKH3);

		boxLeft.add(boxKhachHang);
		boxLeft.add(Box.createVerticalStrut(15));

		//================================================================
		Box boxChoNgoi = new Box(BoxLayout.Y_AXIS);

		Box boxCN1 = new Box(BoxLayout.X_AXIS);
		boxCN1.add(lbTitleCN = new JLabel("CHỖ NGỒI"));
		lbTitleCN.setFont(font2);
		boxChoNgoi.add(boxCN1);
		boxChoNgoi.add(Box.createVerticalStrut(10));
		
		JPanel boxCN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxCN2.add(Box.createHorizontalStrut(15));
		boxCN2.add(lbPhong = new JLabel("Phòng:"));
		lbPhong.setFont(font);
		boxCN2.add(Box.createHorizontalStrut(5));
		comboxPhong = new JComboBox<>();
		ArrayList<Room> listRoom = dao.getListRoom();
		for(Room phong : listRoom) {
			comboxPhong.addItem(phong.getRoomName());
		}
		boxCN2.add(comboxPhong);
		boxCN2.add(Box.createHorizontalStrut(25));
		boxChoNgoi.add(boxCN2);
		boxChoNgoi.add(Box.createVerticalStrut(10));

		JPanel boxCN3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxCN3.add(Box.createHorizontalStrut(15));
		boxCN3.add(lbViTri = new JLabel("Vị trí ghế:"));
		lbViTri.setFont(font);
		boxCN3.add(Box.createHorizontalStrut(5));
		boxCN3.add(lbTenGhe = new JLabel(""));
		boxCN3.add(Box.createHorizontalStrut(25));
		boxChoNgoi.add(boxCN3);
		boxChoNgoi.add(Box.createVerticalStrut(10));

		JPanel boxCN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxCN4.add(Box.createHorizontalStrut(15));
		boxCN4.add(lbGiaGhe = new JLabel("Giá:"));
		lbGiaGhe.setFont(font);
		boxCN4.add(Box.createHorizontalStrut(5));
		boxCN4.add(lbGia  = new JLabel(""));
		boxCN4.add(Box.createHorizontalStrut(25));
		boxChoNgoi.add(boxCN4);

		boxLeft.add(boxChoNgoi);
		boxLeft.add(Box.createVerticalStrut(15));

		//=================================================================
		Box boxDichVu = new Box(BoxLayout.Y_AXIS);

		Box boxDV1 = new Box(BoxLayout.X_AXIS);
		boxDV1.add(lbTitleDV = new JLabel("DỊCH VỤ KHÁC"));
		lbTitleDV.setFont(font2);
		boxDichVu.add(boxDV1);
		boxDichVu.add(Box.createVerticalStrut(10));

		Box boxDV2 = new Box(BoxLayout.X_AXIS);
		boxDV2.add(Box.createHorizontalStrut(25));
		boxDV2.add(lbBongNgo = new JLabel("Bỏng ngô:"));
		lbBongNgo.setFont(font);
		SpinnerNumberModel monthModel = new SpinnerNumberModel(0, 0, 100, 1);  // giúp có thể chọn một giá trị số bằng cách bấm nút tăng/giảm. Giá trị ban đầu là 0, giá trị nhỏ nhất là 0, giá trị lớn nhất là 200, bước nhảy là 1 
		spnBongNgo = new JSpinner(monthModel);
		spnBongNgo.setPreferredSize(new Dimension(5, 30));
		boxDV2.add(Box.createHorizontalStrut(25));
		boxDV2.add(spnBongNgo);
		boxDV2.add(Box.createHorizontalStrut(15));
		boxDV2.add(lbTienBN = new JLabel("0 VNĐ"));
		boxDV2.add(Box.createHorizontalStrut(150));
		boxDichVu.add(boxDV2);
		boxDichVu.add(Box.createVerticalStrut(10));

		Box boxDV3 = new Box(BoxLayout.X_AXIS);
		boxDV3.add(Box.createHorizontalStrut(25));
		boxDV3.add(lbNuocNgot = new JLabel("Nước ngọt:"));
		lbNuocNgot.setFont(font);
		SpinnerNumberModel monthModel1 = new SpinnerNumberModel(0, 0, 100, 1);  // giúp có thể chọn một giá trị số bằng cách bấm nút tăng/giảm. Giá trị ban đầu là 0, giá trị nhỏ nhất là 0, giá trị lớn nhất là 200, bước nhảy là 1
		spnNuocNgot = new JSpinner(monthModel1);
		spnNuocNgot.setPreferredSize(new Dimension(5, 30));
		boxDV3.add(Box.createHorizontalStrut(15));
		boxDV3.add(spnNuocNgot);
		boxDV3.add(Box.createHorizontalStrut(15));
		boxDV3.add(lbTienBN = new JLabel("0 VNĐ"));
		boxDV3.add(Box.createHorizontalStrut(150));
		boxDichVu.add(boxDV3);
		boxDichVu.add(Box.createVerticalStrut(10));

		boxLeft.add(boxDichVu);
		boxLeft.add(Box.createVerticalStrut(15));

		//===============================================================
		Box boxThanhToan = new Box(BoxLayout.X_AXIS);
		boxThanhToan.add(btnThanhToan = new JButton("Thanh toán"));
		btnThanhToan.setPreferredSize(new Dimension(50, 30));
		btnThanhToan.setFont(font);
		btnThanhToan.setBackground(Color.CYAN);
		boxThanhToan.add(Box.createHorizontalStrut(30));
		boxThanhToan.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.setPreferredSize(new Dimension(50, 30));
		btnLamMoi.setFont(font);
		btnLamMoi.setBackground(Color.ORANGE);
		boxLeft.add(boxThanhToan);

		boxLeft.add(Box.createHorizontalStrut(15));
		pnlLeft.add(boxLeft);

		//================================================================
		//================================================================

		JPanel pnlRight = new JPanel(new BorderLayout(10, 10));
		pnlRight.setPreferredSize(new Dimension(650, 650));
		pnlRight.setBorder(BorderFactory.createTitledBorder(""));

		//---------------------------------------------------------------
		JLabel manHinh = new JLabel("Màn hình", SwingConstants.CENTER);
		manHinh.setOpaque(true);
		manHinh.setBackground(Color.DARK_GRAY);
		manHinh.setForeground(Color.WHITE);
		manHinh.setFont(new Font("Arial", Font.BOLD, 16));
		manHinh.setPreferredSize(new Dimension(100, 40));
		pnlRight.add(manHinh, BorderLayout.NORTH);

		//---------------------------------------------------------------
		pnlGhe = new JPanel(new GridLayout(8, 10, 5, 5));
		char rowChar = 'A';
		for (int i = 0; i < 8; i++) {

			for (int j = 1; j <= 10; j++) {
				label = rowChar + String.valueOf(j);
				btn = new JButton(label);
				btn.setBackground(Color.GREEN); // Ghế trống
				btn.setOpaque(true);
				btn.setForeground(Color.BLACK);
				btn.setFont(new Font("Arial", Font.BOLD, 12));
				btn.setPreferredSize(new Dimension(5, 2));

				pnlGhe.add(btn);
			}
			rowChar++;
		}

		pnlRight.add(pnlGhe, BorderLayout.CENTER);

		//---------------------------------------------------------------
		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(new FlowLayout());

		pnlTrangThai.add(taoMauGiaiThich("Đã mua", Color.RED));
		pnlTrangThai.add(taoMauGiaiThich("Đang chọn", Color.BLUE));
		pnlTrangThai.add(taoMauGiaiThich("Ghế trống", Color.GREEN));
		pnlRight.add(pnlTrangThai, BorderLayout.SOUTH);

		//---------------------------------------------------------------
		boxCent.add(Box.createHorizontalStrut(15));
		boxCent.add(pnlLeft);
		boxCent.add(Box.createHorizontalStrut(15));
		boxCent.add(pnlRight);
		boxCent.add(Box.createHorizontalStrut(15));

		add(boxCent);



		hienThiThongTinPhim();
		loadSeatsFromDatabase();

		btnThanhToan.addActionListener(this);
		btnLamMoi.addActionListener(this);

	}

	private void hienThiThongTinPhim() {
	    if (phimDangChon != null) {
	        txtTenPhim.setText(phimDangChon.getTitle());

	        // Lấy thông tin suất chiếu
	        Showtimes showtime = dao.getShowTime(phimDangChon.getMovieID());
	        if (showtime != null) {
	            // Set ngày chiếu
	            LocalDate showDate = showtime.getShowDateTime();
	            findDateChooser.setDate(java.sql.Date.valueOf(showDate));

	            // Set giờ bắt đầu
	            LocalTime startTime = showtime.getStartedTime();
	            // Convert LocalTime to Date
	            LocalDateTime dateTime = LocalDateTime.of(showDate, startTime);
	            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	            spnGioBD.setValue(date);
	        }

	        try {
	            ImageIcon icon = new ImageIcon(getClass().getResource(phimDangChon.getImg()));
	            Image img = icon.getImage().getScaledInstance(90, 130, Image.SCALE_SMOOTH);
	            lbPhim.setIcon(new ImageIcon(img));
	        } catch (Exception e) {
	            System.err.println("Không thể load hình ảnh: " + phimDangChon.getImg());
	        }
	    }
	}


	public void capNhatThongTinPhim(Movies phim) {
		this.phimDangChon = phim;
		hienThiThongTinPhim();
	}

	private void loadSeatsFromDatabase() {
		try {
			ArrayList<Seat> listSeat = service.getListSeat(); // Sử dụng service để lấy danh sách ghế

			pnlGhe.removeAll(); // Xóa tất cả ghế hiện có
			seatButtons.clear(); // Xóa danh sách nút ghế
			selectedSeats.clear(); // Xóa danh sách ghế đã chọn
			totalSeatPrice = 0; // Reset tổng tiền

			// Sắp xếp ghế theo thứ tự (A1, A2,... B1, B2,...)
			listSeat.sort((s1, s2) -> {
				int rowCompare = s1.getSeatNUmber().substring(0, 1).compareTo(s2.getSeatNUmber().substring(0, 1));
				if (rowCompare != 0) return rowCompare;
				return Integer.compare(
						Integer.parseInt(s1.getSeatNUmber().substring(1)),
						Integer.parseInt(s2.getSeatNUmber().substring(1))
						);
			});

			for (Seat seat : listSeat) {
				String seatNumber = seat.getSeatNUmber();
				String seatType = seat.getSeatType();
				String status = seat.getStatus();

				JButton btn = new JButton(seatNumber);
				btn.setOpaque(true);
				btn.setFont(new Font("Arial", Font.BOLD, 12));

				// Đặt màu theo trạng thái và loại ghế
				if ("Đã đặt".equals(status)) {
					btn.setBackground(Color.RED);
					btn.setEnabled(false);
				} else {
					// Ghế VIP màu vàng, ghế thường màu xanh lá
					btn.setBackground(seatType.equals("VIP") ? Color.YELLOW : Color.GREEN);
					btn.setEnabled(true);

					// Thêm sự kiện click
					btn.addActionListener(e -> handleSeatSelection(btn, seatNumber, seatType));
				}

				pnlGhe.add(btn);
				seatButtons.put(seatNumber, btn);
			}

			pnlGhe.revalidate();
			pnlGhe.repaint();
			updateSelectedSeatsInfo(); // Cập nhật thông tin ghế
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách ghế: " + e.getMessage(), 
					"Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void handleSeatSelection(JButton btn, String seatNumber, String seatType) {
		if (btn.getBackground() == Color.BLUE) {
			// Bỏ chọn ghế
			btn.setBackground(seatType.equals("VIP") ? Color.YELLOW : Color.GREEN);
			selectedSeats.remove(seatNumber);
			totalSeatPrice -= seatType.equals("VIP") ? VIP_SEAT_PRICE : STANDARD_SEAT_PRICE;
		} else {
			// Chọn ghế
			btn.setBackground(Color.BLUE);
			selectedSeats.add(seatNumber);
			totalSeatPrice += seatType.equals("VIP") ? VIP_SEAT_PRICE : STANDARD_SEAT_PRICE;
		}

		// Cập nhật thông tin ghế đã chọn
		updateSelectedSeatsInfo();
	}

	private void updateSelectedSeatsInfo() {
		// Hiển thị danh sách ghế đã chọn
		lbTenGhe.setText(String.join(", ", selectedSeats));

		// Hiển thị tổng tiền
		lbGia.setText(String.format("%,.0f VNĐ", totalSeatPrice));
	}

	private JPanel taoMauGiaiThich(String text, Color color) {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton b = new JButton();
		b.setEnabled(false);
		b.setBackground(color);
		b.setPreferredSize(new Dimension(30, 20));
		p.add(b);
		p.add(new JLabel(text));
		return p;
	}

	public void xoaTrang() {
		findDateChooser.setDate(new Date());
		Date now = new Date();
		spnGioBD.setValue(now);
		txtTenKH.setText("");
		txtEmail.setText("");
		lbTenGhe.setText("");
		lbGia.setText("");
		spnBongNgo.setValue(0);
		spnNuocNgot.setValue(0);
	}


	
	
	
	//======================================================================
	// Thêm vào lớp DatVe
	private String generateCustomerId() {
	    return "CUS" + System.currentTimeMillis();
	}

	private String generateOrderId() {
	    return "ORD" + System.currentTimeMillis();
	}

	private String generateTicketId() {
	    return "TKT" + System.currentTimeMillis();
	}

	private double calculateServicePrice() {
	    int popcornQty = (int) spnBongNgo.getValue();
	    int drinkQty = (int) spnNuocNgot.getValue();
	    return (popcornQty * POPCORN_PRICE) + (drinkQty * DRINK_PRICE);
	}

	private Seat getSeatByNumber(String seatNumber) {
	    ArrayList<Seat> allSeats = service.getListSeat();
	    for (Seat seat : allSeats) {
	        if (seat.getSeatNUmber().equals(seatNumber)) {
	            return seat;
	        }
	    }
	    return null;
	}
	//======================================================================
	


	public static void main(String[] args) {
		new DatVe(phimDangChon, nvDangNhap);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btnThanhToan) {
	        try {
	            // Validate input
	            if (txtTenKH.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            if (selectedSeats.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một ghế", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            //=======================================================================================
	         // Tính tổng tiền dịch vụ
	            int qtyPopcorn = (int) spnBongNgo.getValue();
	            int qtyDrink = (int) spnNuocNgot.getValue();
	            double servicePrice = calculateServicePrice();
	            double totalPrice = totalSeatPrice + servicePrice;

	            // Lấy thông tin ngày giờ chiếu
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

	            // Định dạng thời gian
	            String formattedDateTime = LocalDateTime.now().format(formatter);

	            // Tạo và hiển thị trang thanh toán
	            try {
	                ThanhToan thanhToan = new ThanhToan();
	                thanhToan.setVisible(true);
	                
	                // Truyền thông tin vào trang thanh toán
	                thanhToan.TenKH.setText(txtTenKH.getText());
	                thanhToan.Email.setText(txtEmail.getText());
	                thanhToan.TenPhim.setText(txtTenPhim.getText());
	                thanhToan.ThoiGian.setText(formattedDateTime);
	                thanhToan.MaGhe.setText(String.join(", ", selectedSeats));
	                thanhToan.BongNgo.setText(qtyPopcorn + " x " + POPCORN_PRICE + " VNĐ");
	                thanhToan.Nuoc.setText(qtyDrink + " x " + DRINK_PRICE + " VNĐ");
	                thanhToan.lbGiaGhe.setText(String.format("%,.0f VNĐ", totalSeatPrice));
	                thanhToan.lbGiaBongNgo.setText(String.format("%,.0f VNĐ", qtyPopcorn * POPCORN_PRICE));
	                thanhToan.lbGiaNuoc.setText(String.format("%,.0f VNĐ", qtyDrink * DRINK_PRICE));
	                thanhToan.TongTien.setText(String.format("%,.0f VNĐ", totalPrice));
	                
	                // Tạo nội dung QR code
	                String qrContent = "Khách hàng: " + txtTenKH.getText() + "\n" +
	                                  "Phim: " + txtTenPhim.getText() + "\n" +
	                                  "Ghế: " + String.join(", ", selectedSeats) + "\n" +
	                                  "Tổng tiền: " + String.format("%,.0f VNĐ", totalPrice);
	                
	                // Tạo ảnh QR code mới
	                BufferedImage qrImage = thanhToan.taoAnhMaQR(qrContent, 200, 200);
	                if (qrImage != null) {
	                    JLabel qrLabel = new JLabel(new ImageIcon(qrImage));
	                    // Xóa QR code cũ (nếu có)
	                    thanhToan.pnlQR.removeAll();
	                    thanhToan.pnlQR.add(qrLabel);
	                    thanhToan.pnlQR.revalidate();
	                    thanhToan.pnlQR.repaint();
	                }
	                
	            } catch (UnsupportedEncodingException e1) {
	                e1.printStackTrace();
	            }
	            //=======================================================================================
	            // Tạo khách hàng
	            Customer customer = new Customer(
	                generateCustomerId(),
	                txtTenKH.getText(),
	                "", // Số điện thoại
	                txtEmail.getText()
	            );

	         // Chèn khách hàng vào database
	            boolean insertedCustomer = dao.insertCustomer(customer);
	            if (!insertedCustomer) {
	                JOptionPane.showMessageDialog(this, 
	                    "Không thể thêm khách hàng vào hệ thống", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            

	            //tạo hóa đơn
	            Orders order = new Orders(
	                generateOrderId(),                    
	                nvDangNhap,
	                customer,
	                LocalDateTime.now(), // Ngày và giờ hiện tại
	                totalSeatPrice + calculateServicePrice()
	            );
	            

	            // Tạo danh sách chi tiết hóa đơn
	            List<OrderDetails> orderDetails = new ArrayList<>();
	            int popcornQty = (int) spnBongNgo.getValue();
	            int drinkQty = (int) spnNuocNgot.getValue();
	            
	            if (popcornQty > 0) {
	                Products popcorn = dao.getProductByName("Bỏng ngô");
	                if (popcorn != null) {
	                    orderDetails.add(new OrderDetails(
	                        order,
	                        popcorn,
	                        popcornQty,
	                        popcorn.getPrice(),
	                        0
	                    ));
	                }
	            }

	            if (drinkQty > 0) {
	                Products drink = dao.getProductByName("Nước ngọt");
	                if (drink != null) {
	                    orderDetails.add(new OrderDetails(
	                        order,
	                        drink,
	                        drinkQty,
	                        drink.getPrice(),
	                        0
	                    ));
	                }
	            }

	            // Tạo vé và cập nhật ghế
	            List<Ticket> tickets = new ArrayList<>();
	            List<Seat> seatsToUpdate = new ArrayList<>();
	            
	            for (String seatNumber : selectedSeats) {
	                Seat seat = getSeatByNumber(seatNumber);
	                Showtimes showTime = dao.getShowTime(phimDangChon.getMovieID());
	                if (seat != null) {
	                    tickets.add(new Ticket(
	                        generateTicketId(),
	                        customer,
	                        showTime,
	                        seat,
	                        seat.getSeatType().equals("VIP") ? VIP_SEAT_PRICE : STANDARD_SEAT_PRICE
	                    ));
	                    seatsToUpdate.add(seat);
	                }
	            }

	            // Thực hiện thanh toán
	            boolean success = dao.processPayment(order, orderDetails, tickets, seatsToUpdate);
	            
	            if (success) {
	                JOptionPane.showMessageDialog(this, 
	                    "Thanh toán thành công!\nMã hóa đơn: " + order.getOrderID(),
	                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                xoaTrang();
	                loadSeatsFromDatabase();
	            } else {
	                JOptionPane.showMessageDialog(this, 
	                    "Thanh toán không thành công", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, 
	                "Lỗi khi thanh toán: " + ex.getMessage(),
	                "Lỗi", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	    } else if (e.getSource() == btnLamMoi) {
	        xoaTrang();
	    }
	}
}
