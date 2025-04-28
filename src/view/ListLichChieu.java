package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import model.Movies;
import model.Room;
import services.Showtimes;

public class ListLichChieu extends JPanel implements ActionListener, MouseListener{
	private JLabel lbTimNgayChieu;
	private JLabel lbTimPhongChieu;
	private JComboBox cboPhongChieu;
	private JButton btnTimKiem;
	private JLabel lBTitle;
	private DefaultTableModel tblmodel;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	private JLabel lbTenPhim;
	private JComboBox cboTenPhim;
	private JLabel lbPhongChieu;
	private JTextField txtPhongChieu;
	private JDateChooser findDateChooser;
	private JLabel lbNgayChieu;
	private JDateChooser dateChooser;
	private JLabel lbGioBatDau;
	private SpinnerDateModel modelBD;
	private JSpinner spnGioBD;
	private JComponent editorBD;
	private JLabel lbGioKetThuc;
	private SpinnerDateModel modelKT;
	private JSpinner spnGioKT;
	private DateEditor editorKT;
	private JButton btnSua;
	private JButton btnHuy;
	private JLabel lbTitle2;
	private JButton btnXoaTrang;
	private services.Showtimes service;
	private services.Movies serviceMV;
	private dao.ShowTimes dao;
	ArrayList<model.Showtimes> list = new ArrayList<model.Showtimes>();
	private JComboBox cbPhong;

	public ListLichChieu() {
		service = new Showtimes(dao);
		Font font = new Font ("Arial",Font.BOLD,15);
		Font font2 = new Font ("Arial",Font.BOLD,23);
		Font font3 = new Font ("Arial",Font.BOLD,18);
		
		//Phần khung chính chứa khung bên trái và khung bên phải
		Box boxCent = new Box(BoxLayout.X_AXIS);
		
		//Phần khung bên trái
		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLefft = new Box(BoxLayout.Y_AXIS);
		
		//Field Title của phần bên trái
		Box boxLeft1 = new Box(BoxLayout.X_AXIS);
		boxLeft1.add(lBTitle = new JLabel("DANH SÁCH LỊCH CHIẾU"));
		lBTitle.setForeground(Color.BLUE);
		lBTitle.setFont(font2);
		boxLefft.add(boxLeft1);
		boxLefft.add(Box.createVerticalStrut(15));
		
		//Field chọn ngày chiếu phim nằm trên khung bên trái
		Box boxLeft2 = new Box(BoxLayout.X_AXIS);
		findDateChooser = new JDateChooser();
		findDateChooser.setDateFormatString("dd-MM-yyyy"); // format ngày
		findDateChooser.setPreferredSize(new Dimension(50, 30)); // chỉnh kích thước
		boxLeft2.add(lbTimNgayChieu = new JLabel("Ngày chiếu:"));
		lbTimNgayChieu.setFont(font);
		boxLeft2.add(Box.createHorizontalStrut(25));
		boxLeft2.add(findDateChooser);
		boxLefft.add(boxLeft2);
		boxLefft.add(Box.createVerticalStrut(15));
		
		//Field chọn phòng chiếu nằm trên khung bên trái
		Box boxLeft3 = new Box(BoxLayout.X_AXIS);
		boxLeft3.add(lbTimPhongChieu = new JLabel("Phòng chiếu:"));
		lbTimPhongChieu.setFont(font);
		boxLeft3.add(Box.createHorizontalStrut(15));
		cboPhongChieu = new JComboBox<>();
		boxLeft3.add(cboPhongChieu);
		boxLeft3.add(Box.createHorizontalStrut(15));
		boxLeft3.add(btnTimKiem = new JButton("Tìm kiếm"));
		btnTimKiem.setFont(font);
		btnTimKiem.setBackground(Color.GREEN);
		boxLefft.add(boxLeft3);
		boxLefft.add(Box.createVerticalStrut(15));
		
		//Phần table nằm trên phần bên trái
		Box boxTable = new Box(BoxLayout.Y_AXIS);
		boxTable.setPreferredSize(new Dimension(450, 600)); //set kích thước cho phần table so với khung
		String[] colNames = {"ID","Tên phim", "Phòng chiếu", "Ngày chiếu", "Giờ bắt đầu", "Giờ kết thúc"};
		tblmodel = new DefaultTableModel(colNames, 0);
		table = new JTable(tblmodel);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		boxTable.add(new JScrollPane(table));
		sorter = new TableRowSorter<TableModel>(tblmodel);
		table.setFont(font);
		table.setRowSorter(sorter);
		boxLefft.add(boxTable);
		boxLefft.add(Box.createVerticalStrut(15));
		
		//Thêm phần khung bên trái vào khung chính
		pnlLeft.add(boxLefft);
		boxCent.add(pnlLeft);
		boxCent.add(Box.createHorizontalStrut(50));
		
		//============================================================================
		//Phần khung bên phải
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		pnlRight.setBorder(BorderFactory.createTitledBorder(""));
		
		//Phần title của phần khung bên phải
		Box boxRight0 = new Box(BoxLayout.X_AXIS);
		boxRight.add(Box.createVerticalStrut(50));
		boxRight0.add(lbTitle2 = new JLabel("THÔNG TIN LỊCH CHIẾU"));
		lbTitle2.setForeground(Color.GREEN);
		lbTitle2.setFont(font3);
		boxRight.add(boxRight0);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field chọn tên phim nằm trên phần bên phải
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight1.add(lbTenPhim = new JLabel("Tên phim:"));
		lbTenPhim.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(40));
		cboTenPhim = new JComboBox<>();
		cboTenPhim.setPreferredSize(new Dimension(230, 30));
		boxRight1.add(cboTenPhim);
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field phòng chiếu nằm trên phần bên phải
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(lbPhongChieu = new JLabel("Phòng chiếu:"));
		lbPhongChieu.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(15));
		cbPhong = new JComboBox<>(); 
		boxRight2.add(cbPhong);
		cbPhong.setPreferredSize(new Dimension(230, 30));
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
		modelBD = new SpinnerDateModel(); //model (mô hình dữ liệu) cho spinner kiểu ngày/giờ.Mặc định nó sẽ lấy thời gian hiện tại làm giá trị ban đầu.
		spnGioBD = new JSpinner(modelBD); //Tạo một JSpinner mới với kiểu dữ liệu là ngày giờ từ SpinnerDateModel.
        // Định dạng hiển thị giờ và phút
		editorBD = new JSpinner.DateEditor(spnGioBD, "HH:mm");  //Tạo một editor (bộ hiển thị/định dạng) cho spinner có định dạng kiểu "giờ:phút"
		spnGioBD.setEditor(editorBD);  //Gán bộ hiển thị editorBD vừa tạo vào spinner spnGioBD, để nó dùng định dạng giờ phút
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
        spnGioBD.setValue(new Date());
		boxRight5.add(spnGioKT);
		boxRight.add(boxRight5);
		boxRight.add(Box.createVerticalStrut(35));
		
        Date gioBD = (Date) spnGioBD.getValue();
        spnGioKT.setValue(new Date());
        spnGioKT.setEnabled(true); //Để ôn giờ kết thúc không nhập được
		
        //Field các nút chức năng nằm bên phải
		Box boxRight6 = new Box(BoxLayout.X_AXIS);
		boxRight6.add(btnSua = new JButton("Sửa"));
		btnSua.setFont(font);
		btnSua.setBackground(Color.CYAN);
		btnSua.setPreferredSize(new Dimension(25, 30));
		boxRight6.add(Box.createHorizontalStrut(20));
		boxRight6.add(btnHuy = new JButton("Hủy"));
		btnHuy.setFont(font);
		btnHuy.setBackground(Color.RED);
		btnHuy.setPreferredSize(new Dimension(25, 30));
		boxRight6.add(Box.createHorizontalStrut(20));
		boxRight6.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBackground(Color.GREEN);
		btnXoaTrang.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight6);
		boxRight.add(Box.createVerticalStrut(25));
		
		//Thêm phần bên phải vào phần khung chính
		pnlRight.add(boxRight);
		boxCent.add(pnlRight);
		
		//Thêm khung chính vào JFrame
		add(boxCent);
		loadDataToTable();
		table.addMouseListener(this);
		
		btnXoaTrang.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);
	}
	
	//đổi từ string sang date
	public static Date stringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; 
        }
    }
	
	public static Date stringToDateTime(String timeString) {
	    try {
	        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	        return formatter.parse(timeString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//xoá trắng
	public void xoaTrang() {
		tblmodel.setRowCount(0);
		findDateChooser.setDate(new Date());
		cboPhongChieu.setSelectedIndex(0);
		cboTenPhim.setSelectedIndex(0);
		cbPhong.setSelectedIndex(0);
		dateChooser.setDate(new Date());
		Date now = new Date();
	    spnGioBD.setValue(now);
	    
	    list = service.getList();
		for(model.Showtimes st : list) {
			tblmodel.addRow(new Object[] {
				st.getShowtimeID(),
				st.getMovieID().getTitle(),
				st.getRoomID().getRoomName(),
				st.getShowDateTime(),
				st.getStartedTime(),
				st.getEndTime()
			});
		}
	}
	
	//load lịch chiếu lên bảng
	public void loadDataToTable() {
		list = service.getList();
		Set<String> roomNames = new HashSet<>();
		Set<String> mvNames = new HashSet<String>();
		for(model.Showtimes st : list) {
			tblmodel.addRow(new Object[] {
				st.getShowtimeID(),
				st.getMovieID().getTitle(),
				st.getRoomID().getRoomName(),
				st.getShowDateTime(),
				st.getStartedTime(),
				st.getEndTime()
			});
			String room = st.getRoomID().getRoomName();
			String mv = st.getMovieID().getTitle();
			if(!roomNames.contains(room)) {
				cboPhongChieu.addItem(st.getRoomID().getRoomName());
				cbPhong.addItem(st.getRoomID().getRoomName());
				roomNames.add(room);
			}
			if(!mvNames.contains(mv)) {
				cboTenPhim.addItem(st.getMovieID().getTitle());
				mvNames.add(mv);
			}
		}
	}
	
	//cập nhật bảng
	public void updateTable(ArrayList<model.Showtimes> st) {
		if(!st.isEmpty()) {
			tblmodel.setRowCount(0);
			for(model.Showtimes stime : list) {
				tblmodel.addRow(new Object[] {
					stime.getMovieID().getTitle(),
					stime.getRoomID().getRoomName(),
					stime.getShowDateTime(),
					stime.getStartedTime(),
					stime.getEndTime()
				});
			}
		}
	}
	
	//load thông tin lên textField
	public void loadToTextField() {
		int rowSelected = table.convertRowIndexToModel(table.getSelectedRow());
		String mvName = tblmodel.getValueAt(rowSelected, 1).toString();
		String room = tblmodel.getValueAt(rowSelected, 2).toString();
		String showtime = tblmodel.getValueAt(rowSelected, 3).toString();
		String startTime = tblmodel.getValueAt(rowSelected, 4).toString();
		String endTime = tblmodel.getValueAt(rowSelected, 5).toString();
		
		cboTenPhim.setSelectedItem(mvName);
		cbPhong.setSelectedItem(room);
		dateChooser.setDate(stringToDate(showtime));
		spnGioBD.setValue(stringToDateTime(startTime));
		spnGioKT.setValue(stringToDateTime(endTime));
	}
	
	//tìm kiếm suất chiếu
	public void  findShowtime() {
		Date showtime = findDateChooser.getDate();
		java.sql.Date sqlDate = new java.sql.Date(showtime.getTime());
		String room = cboPhongChieu.getSelectedItem().toString();
		list = service.findShowtime(room, sqlDate);
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
    
	//sửa suất chiếu
	public void updateShowtime() {
		int rowSelected = table.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(this, "Chọn suất chiếu cần chỉnh sửa","Lỗi",JOptionPane.ERROR_MESSAGE);
		}else {
			rowSelected = table.convertRowIndexToModel(rowSelected);
			String movieName = cboTenPhim.getSelectedItem().toString();
		    String roomName = cbPhong.getSelectedItem().toString();
		    LocalDate showtimeDate = getLocalDateFromDateChooser(dateChooser);
		    LocalTime startTime = getLocalTimeFromSpinner(spnGioBD);
		    LocalTime endTime = getLocalTimeFromSpinner(spnGioKT);
		    
		    model.Showtimes st = new model.Showtimes();
		    model.Movies mv = new Movies();
		    model.Room room = new Room();
		    mv.setMovieID(service.getMovieID(movieName));
		    room.setRoomID(service.getRoomID(roomName));
		    String stID = tblmodel.getValueAt(rowSelected, 0).toString();
		    st.setShowtimeID(stID);
		    st.setShowDateTime(showtimeDate);
		    st.setStartedTime(startTime);
		    st.setEndTime(endTime);
		    st.setMovieID(mv);
		    st.setRoomID(room);

		    // Gọi hàm cập nhật từ service
		    boolean isUpdated = service.update(stID, st);

		    // Kiểm tra kết quả và hiển thị thông báo
		    if (isUpdated) {
		        JOptionPane.showMessageDialog(this, "Cập nhật lịch chiếu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    } else {
		        JOptionPane.showMessageDialog(this, "Cập nhật lịch chiếu thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}
	
	//xoá lịch chiếu
	public void deleteShowtime() {
		int rowSelected = table.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phim cần xoá!", "Lỗi",JOptionPane.ERROR_MESSAGE);
		}else {
			rowSelected = table.convertRowIndexToModel(rowSelected);
			String showtimeID = tblmodel.getValueAt(rowSelected, 0).toString();
			if(service.delete(showtimeID)) {
				JOptionPane.showMessageDialog(this, "Xoá thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "Xoá không thàng công!","Lỗi!",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		new ListLichChieu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}else if(o.equals(btnTimKiem)) {
			findShowtime();
		}else if(o.equals(btnSua)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật?","Xác nhận",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				updateShowtime();
			}
		}else if(o.equals(btnHuy)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá?","Xác nhận",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				deleteShowtime();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		loadToTextField();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
