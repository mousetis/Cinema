package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import services.Movies;

public class ThemPhim extends JPanel implements ActionListener{
	private JLabel lbTitle;
	private DefaultTableModel tblmodel;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	private JButton btnThemTatCa;
	private JButton btnXoa;
	private JButton btnXoaTatCa;
	private JLabel lbNgayPhatHanh;
	private JTextField txtNgayPhatHanh;
	private JLabel lbTenPhim;
	private JTextField txtTenPhim;
	private JLabel lbDoTuoi;
	private JRadioButton rdTreEm;
	private JRadioButton rdTren16;
	private JRadioButton rdTren18;
	private ButtonGroup rdTuoi;
	private JLabel lbGiaVe;
	private JSpinner spinnerGiaVe;
	private JLabel lbThoiLuong;
	private JSpinner spinnerThoiLuong;
	private JButton btnThemPhim;
	private JButton btnXoaTrang;
	private JLabel lbTitle2;
	private JButton btnThem;
	private services.Movies service;
	private dao.Movies dao;
	ArrayList<model.Movies> list = new ArrayList<model.Movies>();
	private JDateChooser findDateChooser;
	private JLabel lbTimNgayChieu;
	
	public ThemPhim() {
		Font font = new Font ("Arial",Font.BOLD,15);
		Font font1 = new Font ("Arial",Font.BOLD,23);
		Font font2 = new Font ("Arial",Font.BOLD,18);
		
		service = new Movies(dao);
		//Phần khung tổng chứa tất cả
		Box pnlCent = new Box(BoxLayout.X_AXIS);
		
		//Phần khung bên trái
		Box pnlLeft = new Box(BoxLayout.Y_AXIS);
		pnlLeft.setPreferredSize(new Dimension(650, 600)); // khối trái lớn hơn
		pnlLeft.add(Box.createVerticalStrut(10)); 

		//Phần khung boxLeft nằm bên trong khung pnlLeft
		Box boxLeft = new Box(BoxLayout.X_AXIS);
		boxLeft.add(Box.createVerticalStrut(20));
		
		//Field title nằm trên phía trên phần khung bên trái
		Box boxLeft1 = new Box(BoxLayout.X_AXIS);
		boxLeft1.add(Box.createHorizontalGlue());
		boxLeft1.add(lbTitle = new JLabel("PHIM ĐANG CHIẾU"));
		boxLeft1.add(Box.createHorizontalGlue());
		lbTitle.setFont(font1);
		lbTitle.setForeground(Color.BLUE);
		pnlLeft.add(boxLeft1, BorderLayout.NORTH);
		pnlLeft.add(Box.createVerticalStrut(10));
		
		//Phần table nằm trên phần ở chính giữa phần khung bên trái
		Box boxTable = new Box(BoxLayout.Y_AXIS);
		boxTable.setPreferredSize(new Dimension(450, 600)); //set kích thước cho phần table so với khung
		String[] colNames = {"Ngày Phát Hành", "Tên phim", "Độ tuổi", "Thời lượng"};
		tblmodel = new DefaultTableModel(colNames, 0);
		table = new JTable(tblmodel);
		boxTable.add(new JScrollPane(table));
		sorter = new TableRowSorter<TableModel>(tblmodel);
		table.setFont(font);
		table.setRowSorter(sorter);
		boxLeft.add(boxTable, BorderLayout.CENTER);

		//Thêm khoảng cách giữa bảng và các nút chức năng
		boxLeft.add(Box.createHorizontalStrut(10));
		
		//Phần các nút chức năng để thao tác dữ liệu trên bảng 
		Box boxLeft3 = new Box(BoxLayout.Y_AXIS);	

		Dimension buttonSize = new Dimension(120, 30); // kích thước chung
		boxLeft3.setPreferredSize(new Dimension(100, 600)); //set kích thước cho phần chứa các nút so với khung
		
		//Nút thêm tất cả
		boxLeft3.add(btnThemTatCa = new JButton("Thêm tất cả"));
		btnThemTatCa.setFont(font);
		btnThemTatCa.setBackground(Color.CYAN);
		btnThemTatCa.setPreferredSize(buttonSize);
		btnThemTatCa.setMaximumSize(buttonSize);
		btnThemTatCa.setMinimumSize(buttonSize);
		boxLeft3.add(Box.createVerticalStrut(20));
		
		//Nút thêm
		boxLeft3.add(btnThem = new JButton("Thêm"));
		btnThem.setFont(font);
		btnThem.setBackground(Color.GRAY);
		btnThem.setPreferredSize(buttonSize);
		btnThem.setMaximumSize(buttonSize);
		btnThem.setMinimumSize(buttonSize);
		boxLeft3.add(Box.createVerticalStrut(20));
		
		//Nút xóa
		boxLeft3.add(btnXoa = new JButton("Xóa"));
		btnXoa.setFont(font);
		btnXoa.setBackground(Color.ORANGE);
		btnXoa.setPreferredSize(buttonSize);
		btnXoa.setMaximumSize(buttonSize);
		btnXoa.setMinimumSize(buttonSize);
		boxLeft3.add(Box.createVerticalStrut(20));
		
		//Nút xóa tất cả
		boxLeft3.add(btnXoaTatCa = new JButton("Xóa tất cả"));
		btnXoaTatCa.setFont(font);
		btnXoaTatCa.setBackground(Color.RED);
		btnXoaTatCa.setPreferredSize(buttonSize);
		btnXoaTatCa.setMaximumSize(buttonSize);
		btnXoaTatCa.setMinimumSize(buttonSize);

		//Thêm phần chứa các nút chức năng vào phía bên phải của boxLeft
		boxLeft.add(boxLeft3, BorderLayout.EAST);
		
		//Thêm boxLeft vào phần khung bên trái
		pnlLeft.add(boxLeft);
		
		pnlCent.add(Box.createVerticalStrut(50));
		
		//============================================================================
		//Phần khung bên phải chứa cách field nhập để thêm phim
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));		
		pnlRight.setPreferredSize(new Dimension(350, 600)); // khối phải nhỏ hơn so với khối bên trái
		pnlRight.setBorder(BorderFactory.createTitledBorder(""));
		
		//Phần boxRight
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		boxRight.add(Box.createVerticalStrut(90));
		
		Box boxRight0 = new Box(BoxLayout.X_AXIS);
		boxRight0.add(lbTitle2 = new JLabel("THÔNG TIN PHIM MỚI"));
		lbTitle2.setFont(font2);
		lbTitle2.setForeground(Color.GREEN);
		boxRight.add(boxRight0);
		boxRight.add(Box.createVerticalStrut(25));
		
		//Field nhập ngày phát hành nằm trên phần khung bên phải
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
//		findDateChooser = new JDateChooser();
//		findDateChooser.setDateFormatString("yyyy-MM-dd"); // format ngày
//		findDateChooser.setPreferredSize(new Dimension(50, 30)); // chỉnh kích thước
//		boxRight1.add(Box.createHorizontalStrut(100));
//		boxRight1.add(lbNgayPhatHanh = new JLabel("Ngày phát hành:"));
//		lbNgayPhatHanh.setFont(font);
//		boxRight1.add(Box.createHorizontalStrut(20));
//		boxRight1.add(txtNgayPhatHanh = new JTextField(10));
//		txtNgayPhatHanh.setPreferredSize(new Dimension(10, 30));
//		txtNgayPhatHanh.requestFocus();
//		boxRight1.add(Box.createHorizontalStrut(10));
//		boxRight1.add(findDateChooser);
//		boxRight.add(boxRight1);
//		boxRight.add(Box.createVerticalStrut(25));

		boxRight1.add(Box.createHorizontalStrut(100));
		boxRight1.add(lbNgayPhatHanh = new JLabel("Ngày Phát Hành:"));
		lbNgayPhatHanh.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(20));
		findDateChooser = new JDateChooser();
		findDateChooser.setDateFormatString("yyyy-MM-dd"); // format ngày 
		findDateChooser.setPreferredSize(new Dimension(150, 30));
		boxRight1.add(findDateChooser);
		boxRight1.add(Box.createHorizontalStrut(100));
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(25));
		
		//Field nhập tên phim nằm trên phần khung bên phải
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(Box.createHorizontalStrut(100));
		boxRight2.add(lbTenPhim = new JLabel("Tên phim:"));
		lbTenPhim.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(15));
		boxRight2.add(txtTenPhim = new JTextField(10));
		txtTenPhim.setPreferredSize(new Dimension(10, 30));
		boxRight2.add(Box.createHorizontalStrut(100));
		boxRight.add(boxRight2);
		boxRight.add(Box.createVerticalStrut(25));
		
		//Field chọn độ tuổi nằm trên phần khung bên phải
		Box boxRight3 = new Box(BoxLayout.X_AXIS);
		boxRight3.add(Box.createHorizontalStrut(100));
		boxRight3.add(lbDoTuoi = new JLabel("Độ tuổi:"));
		lbDoTuoi.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(30));
		boxRight3.add(rdTreEm = new JRadioButton("Trẻ em"));
		rdTreEm.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(5));
		boxRight3.add(rdTren16 = new JRadioButton("16+"));
		rdTren16.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(5));
		boxRight3.add(rdTren18 = new JRadioButton("18+"));
		rdTren18.setFont(font);
		rdTuoi = new ButtonGroup();
		rdTuoi.add(rdTreEm);
		rdTuoi.add(rdTren16);
		rdTuoi.add(rdTren18);
		rdTren18.setSelected(true);
		boxRight.add(boxRight3);
		boxRight3.add(Box.createHorizontalStrut(95));
		boxRight.add(Box.createVerticalStrut(25));
		
		//Field nhập thời lượng phim nằm trên phần khung bên phải
		Box boxRight5 = new Box(BoxLayout.X_AXIS);
		boxRight5.add(Box.createHorizontalStrut(100));
		boxRight5.add(lbThoiLuong = new JLabel("Thời lượng (phút):"));
		lbThoiLuong.setFont(font);
		boxRight5.add(Box.createHorizontalStrut(15));
		spinnerThoiLuong = new JSpinner(new SpinnerNumberModel(0, 0, 500, 1));  //Ô nhập giá trị bằng cách chọn một giá trị số bằng cách bấm nút tăng/giảm
		spinnerThoiLuong.setPreferredSize(new Dimension(10, 30));
		boxRight5.add(spinnerThoiLuong);
		boxRight5.add(Box.createHorizontalStrut(100));
		boxRight.add(boxRight5);
		boxRight.add(Box.createVerticalStrut(25));
		
		
		//Field chức năng sửa hoặc xóa phim nằm trên phần khung bên phải
		Box boxRight7 = new Box(BoxLayout.X_AXIS);
		boxRight7.add(btnThemPhim = new JButton("Thêm phim"));
		btnThemPhim.setFont(font);
		btnThemPhim.setBackground(Color.CYAN);
		boxRight7.add(Box.createHorizontalStrut(15));
		boxRight7.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBackground(Color.GREEN);
		boxRight.add(boxRight7);
		boxRight.add(Box.createVerticalStrut(25));
		
		
		//Thêm boxRight phần khung bên phải
		pnlRight.add(boxRight);
		
		//Thêm khung bên trái và khung bên phải vào khung tổng
		pnlCent.add(pnlLeft);
		pnlCent.add(pnlRight);
		
		//Thêm khung tổng vào JFrame
		add(pnlCent);
		
		btnXoaTrang.addActionListener(this);
		btnThemPhim.addActionListener(this);
		btnThem.addActionListener(this);
		btnThemTatCa.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTatCa.addActionListener(this);
	}
	
	//xoá trắng
	public void xoaTrang() {
		findDateChooser.setDate(null);
		txtTenPhim.setText("");
		rdTren18.setSelected(true);
		spinnerThoiLuong.setValue(0);
	}
	
	//thêm phim vào bảng
	public void addToTable() {
		try {
			Date releaseDate = findDateChooser.getDate();
			LocalDate local = releaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String title = txtTenPhim.getText();
			int age;
			if(rdTreEm.isSelected()) {
				age = 13;
			}else if(rdTren16.isSelected()) {
				age = 16;
			}else {
				age = 18;
			}
			Number numberDur = (Number) spinnerThoiLuong.getValue();
			int duration = numberDur.intValue();
			
			model.Movies mv = new model.Movies();
			mv.setAgeRating(age);
			mv.setDuration(duration);
			mv.setTitle(title);
			mv.setReleaseDate(local);
			tblmodel.addRow(new Object[] {
					mv.getReleaseDate(),
					mv.getTitle(),
					mv.getAgeRating(),
					mv.getDuration()
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Không thể thêm phim", "Lỗi", JOptionPane.ERROR_MESSAGE);
			e.getStackTrace();
		}
	}
	//lấy phim
	public model.Movies getMovie(int row) {
		model.Movies mv = new model.Movies();
		String releaseDate = tblmodel.getValueAt(row, 0).toString();
		String title = tblmodel.getValueAt(row, 1).toString();
		int ageRating = Integer.parseInt(tblmodel.getValueAt(row, 2).toString());
		int duration = Integer.parseInt(tblmodel.getValueAt(row, 3).toString());
		
		LocalDate local = LocalDate.parse(releaseDate);
		
		mv.setAgeRating(ageRating);
		mv.setDuration(duration);
		mv.setReleaseDate(local);
		mv.setTitle(title);
		
		return mv;
	}
	
	//thêm lần lượt
	public void addOne() {
		int rowSelected = table.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần thêm", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else {
			rowSelected = table.convertRowIndexToModel(rowSelected);
			model.Movies mv = getMovie(rowSelected);
			if(mv != null) {
				service.add(mv);
				tblmodel.removeRow(rowSelected);
				JOptionPane.showMessageDialog(this, "Lưu thành công!","Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "Lưu thất bại!","Lỗi",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//thêm tất cả
	public void addAll() {
		for(int i = 0; i < table.getRowCount(); i++) {
			model.Movies mv = getMovie(i);
			if(mv != null) {
				service.add(mv);
			}
		}
		for(int i = table.getRowCount() - 1; i >= 0; i--) {
			tblmodel.removeRow(i);
		}
		JOptionPane.showMessageDialog(this, "Đã lưu tất cả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//xoá 1
	public void deleteOne() {
		int rowSelected = table.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else {
			tblmodel.removeRow(rowSelected);
			JOptionPane.showMessageDialog(this, "Xoá thành công!","Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//xoá tất cả
	public void deleteAll() {
		for(int i = table.getRowCount() - 1; i >= 0; i--) {
			tblmodel.removeRow(i);
			JOptionPane.showMessageDialog(this, "Đã xoá tất cả","Thông báo",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new ThemPhim();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}else if(o.equals(btnThemPhim)) {
			addToTable();
			xoaTrang();
		}else if(o.equals(btnThem)) {
			addOne();
		}else if(o.equals(btnThemTatCa)) {
			addAll();
		}else if(o.equals(btnXoa)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xoá phim này?","Xác nhận", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				deleteOne();
			}
		}else if(o.equals(btnXoaTatCa)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xoá tất cả?","Xác nhận", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				deleteAll();
			}
		}
		
	}
}
