package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import dao.Employee_Dao;
import model.Movies;
import model.Room;
import model.Showtimes;

public class ThemLichChieu extends JPanel implements ActionListener{
	private JLabel lBTitle;
	private JDateChooser findDateChooser;
	private JLabel lbTimNgayChieu;
	private JLabel lbTimPhongChieu;
	private JComboBox cboPhongChieu;
	private JButton btnTimKiem;
	private DefaultTableModel tblmodel;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	private JLabel lbTitle2;
	private JLabel lbTenPhim;
	private JComboBox cboTenPhim;
	private JLabel lbPhongChieu;
	private JTextField txtPhongChieu;
	private JLabel lbNgayChieu;
	private JDateChooser dateChooser;
	private JComponent lbGioBatDau;
	private SpinnerDateModel modelBD;
	private JSpinner spnGioBD;
	private DateEditor editorBD;
	private JLabel lbGioKetThuc;
	private SpinnerDateModel modelKT;
	private JSpinner spnGioKT;
	private DateEditor editorKT;
	private JButton btnThemLichChieu;
	private JButton btnXoaTrang;
	private services.Employee_Services service;
	private services.Showtimes serviceST;
	private dao.ShowTimes daoST;
	private dao.Employee_Dao dao;

	public ThemLichChieu() {
		dao = new Employee_Dao();
		service = new services.Employee_Services(dao);
		serviceST = new services.Showtimes(daoST);
		
		Font font = new Font ("Arial",Font.BOLD,15);
		Font font2 = new Font ("Atial",Font.BOLD,23);
		Font font3 = new Font ("Arial",Font.BOLD,18);
		
		//Phần khung bên phải
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		pnlRight.setBorder(BorderFactory.createTitledBorder(""));
		pnlRight.setPreferredSize(new Dimension(600, 500));
		
		//Phần title của phần khung bên phải
		Box boxRight0 = new Box(BoxLayout.X_AXIS);
		boxRight.add(Box.createVerticalStrut(50));
		boxRight0.add(lbTitle2 = new JLabel("THÊM LỊCH CHIẾU PHIM"));
		lbTitle2.setFont(font3);
		lbTitle2.setForeground(Color.GREEN);
		boxRight.add(boxRight0);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field chọn tên phim nằm trên phần bên phải
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight1.add(lbTenPhim = new JLabel("Tên phim:"));
		lbTenPhim.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(40));
		cboTenPhim = new JComboBox<>();
		for(Movies p: service.getListMovie()) {
			cboTenPhim.addItem(p.getTitle());
		}
		cboTenPhim.setPreferredSize(new Dimension(230, 30));
		boxRight1.add(cboTenPhim);
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field phòng chiếu nằm trên phần bên phải
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(lbPhongChieu = new JLabel("Phòng chiếu:"));
		lbPhongChieu.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(15));
		cboPhongChieu = new JComboBox<>();
		for(Room p : service.getListRoom()) {
			cboPhongChieu.addItem(p.getRoomName());
		}
		boxRight2.add(cboPhongChieu);
		boxRight.add(boxRight2);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field chọn ngày chiếu nằm trên phần bên phải
		Box boxRight3 = new Box(BoxLayout.X_AXIS);
		boxRight3.add(lbNgayChieu = new JLabel("Ngày chiếu:"));
		lbNgayChieu.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(25));
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy"); // format ngày 
		dateChooser.setPreferredSize(new Dimension(150, 30));
		boxRight3.add(dateChooser);
		boxRight.add(boxRight3);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field chọn giờ bắt dầu nằm trên phần bên phải
		Box boxRight4 = new Box(BoxLayout.X_AXIS);
		boxRight4.add(lbGioBatDau = new JLabel("Giờ bắt đầu:"));
		lbGioBatDau.setFont(font);
		boxRight4.add(Box.createHorizontalStrut(20));
		 // Spinner model với thời gian
		modelBD = new SpinnerDateModel();  //model (mô hình dữ liệu) cho spinner kiểu ngày/giờ.Mặc định nó sẽ lấy thời gian hiện tại làm giá trị ban đầu.
		spnGioBD = new JSpinner(modelBD);  //Tạo một JSpinner mới với kiểu dữ liệu là ngày giờ từ SpinnerDateModel.
        // Định dạng hiển thị giờ và phút
		editorBD = new JSpinner.DateEditor(spnGioBD, "HH:mm");   //Tạo một editor (bộ hiển thị/định dạng) cho spinner có định dạng kiểu "giờ:phút"
		spnGioBD.setEditor(editorBD);  //Gán bộ hiển thị editorBD vừa tạo vào spinner spnGioBD, để nó dùng định dạng giờ phút
		spnGioBD.setValue(new Date());
		spnGioBD.setPreferredSize(new Dimension(230, 30));
		boxRight4.add(spnGioBD);
		boxRight.add(boxRight4);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field hiển thị giờ kết thúc nằm trên phần bên phải chỉ hiển thi giờ kết thúc được tính từ giờ bắt đầu + thời lượng phim
		Box boxRight5 = new Box(BoxLayout.X_AXIS);
		boxRight5.add(lbGioKetThuc = new JLabel("Giờ kết thúc:"));
		lbGioKetThuc.setFont(font);
		boxRight5.add(Box.createHorizontalStrut(15));
		modelKT = new SpinnerDateModel();
        spnGioKT = new JSpinner(modelKT);
        editorKT = new JSpinner.DateEditor(spnGioKT, "HH:mm");
        spnGioKT.setEditor(editorKT);
        spnGioKT.setPreferredSize(new Dimension(230, 30));
		boxRight5.add(spnGioKT);
		boxRight.add(boxRight5);
		boxRight.add(Box.createVerticalStrut(35));
		
		// Tính giờ kết thúc dựa trên giờ bắt đầu + 2 tiếng (ví dụ)
        Date gioBD = (Date) spnGioBD.getValue();
        spnGioKT.setValue(new Date());
        spnGioKT.setEnabled(true); //Để ôn giờ kết thúc không nhập được
		
        //Field các nút chức năng nằm bên phải
		Box boxRight6 = new Box(BoxLayout.X_AXIS);
		boxRight6.add(btnThemLichChieu = new JButton("Thêm lịch chiếu"));
		btnThemLichChieu.setFont(font);
		btnThemLichChieu.setBackground(Color.CYAN);
		btnThemLichChieu.setPreferredSize(new Dimension(25, 30));
		boxRight6.add(Box.createHorizontalStrut(20));
		boxRight6.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBackground(Color.GREEN);
		btnXoaTrang.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight6);
		boxRight.add(Box.createVerticalStrut(25));
		
		//Thêm phần bên phải vào phần khung chính
		pnlRight.add(boxRight);
		
		//Thêm khung chính vào JFrame
		add(pnlRight);
		btnXoaTrang.addActionListener(this);
		btnThemLichChieu.addActionListener(this);
		
	}
	
	//xoá trắng
	public void xoaTrang() {
		findDateChooser.setDate(new Date());
		cboPhongChieu.setSelectedIndex(0);
		cboTenPhim.setSelectedIndex(0);
		dateChooser.setDate(new Date());
		Date now = new Date();
	    spnGioBD.setValue(now);
		txtPhongChieu.requestFocus();
	}
	
	// Hàm để lấy giờ từ JSpinner và chuyển sang LocalTime
    public LocalTime getLocalTimeFromSpinner(JSpinner spinner) {
        Date date = (Date) spinner.getValue(); 
        return LocalTime.ofInstant(date.toInstant(), ZoneId.systemDefault());  
    }

    // Hàm để lấy ngày từ JDateChooser và chuyển sang LocalDate
    public LocalDate getLocalDateFromDateChooser(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();  
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    
	//thêm lịch chiếu
	public void addShowtimes() {
		String movieName = cboTenPhim.getSelectedItem().toString();
		String roomName = cboPhongChieu.getSelectedItem().toString();
		LocalDate showDateTime = getLocalDateFromDateChooser(dateChooser);
		LocalTime startTime = getLocalTimeFromSpinner(spnGioBD);
		LocalTime endTime = getLocalTimeFromSpinner(spnGioKT);
		
		model.Showtimes st = new Showtimes();
		model.Movies mv = new Movies();
		model.Room room = new Room();
		mv.setMovieID(serviceST.getMovieID(movieName));
		room.setRoomID(serviceST.getRoomID(roomName));
		st.setShowDateTime(showDateTime);
		st.setMovieID(mv);
		st.setRoomID(room);
		st.setStartedTime(startTime);
		st.setEndTime(endTime);
		
		if(serviceST.add(st)) {
			JOptionPane.showMessageDialog(this, "Thêm thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "Thêm không thành công","Lỗi!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new ThemLichChieu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}else if(o.equals(btnThemLichChieu)){
			addShowtimes();
		}
		
	}
}
