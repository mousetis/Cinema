package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

import model.Employee;
import services.Movies;

public class ListPhim extends JPanel implements ActionListener, MouseListener{

	private JLabel lbTimMaPhim;
	private JTextField txtTimMaPhim;
	private JLabel lbTimTenPhim;
	private JTextField txtTimTenPhim;
	private JButton btnTimKiem;
	private JButton btnDangPhatHanh;
	private JButton btnDaDung;
	private DefaultTableModel tblmodel;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	private JLabel lbMaPhim;
	private JTextField txtMaPhim;
	private JLabel lbTenPhim;
	private JTextField txtTenPhim;
	private JLabel lbDoTuoi;
	private JRadioButton rdTreEm;
	private JRadioButton rdTren16;
	private JRadioButton rdTren18;
	private ButtonGroup rdTuoi;
	private JLabel lbThoiLuong;
	private JSpinner spinnerThoiLuong;
	private JLabel lbThoiGianTao;
	private JLabel lbThoiGian;
	private JButton btnSua;
	private JButton btnDungChieu;
	private JLabel lbTitle2;
	private JButton btnXoaTrang;
	private services.Movies service;
	private dao.Movies dao;
	ArrayList<model.Movies> list = new ArrayList<model.Movies>();

	public ListPhim() {
		service = new Movies(dao);
		Font font = new Font ("Arial",Font.BOLD,16);
		Font font2 = new Font ("Arial",Font.BOLD,20);
		
		//Phần khung tổng chứa tất cả
		JPanel pnlCent = new JPanel(new GridLayout(1,2));
		
		//Phần khung bên trái
		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLeft = new Box(BoxLayout.Y_AXIS);
		boxLeft.add(Box.createVerticalStrut(20));
		
		//Field nhập mã phim cần tìm nằm trên phần khung bên trái
		Box boxLeft1 = new Box(BoxLayout.X_AXIS);
		boxLeft1.add(lbTimMaPhim = new JLabel("Mã phim:"));
		lbTimMaPhim.setFont(font);
		boxLeft1.add(Box.createHorizontalStrut(20));
		boxLeft1.add(txtTimMaPhim = new JTextField(20));
		txtTimMaPhim.setPreferredSize(new Dimension(25, 30));
		txtTimMaPhim.requestFocus();
		boxLeft1.add(Box.createHorizontalStrut(115));
		boxLeft.add(boxLeft1);
		boxLeft.add(Box.createVerticalStrut(15));
		
		//Field nhập tên phim cần tìm nằm trên phần khung bên trái
		Box boxLeft2 = new Box(BoxLayout.X_AXIS);
		boxLeft2.add(lbTimTenPhim = new JLabel("Tên phim:"));
		lbTimTenPhim.setFont(font);
		boxLeft2.add(Box.createHorizontalStrut(15));
		boxLeft2.add(txtTimTenPhim = new JTextField(20));
		boxLeft2.add(Box.createHorizontalStrut(15));
		boxLeft2.add(btnTimKiem = new JButton("Tìm kiếm")); 
		btnTimKiem.setFont(font);
		btnTimKiem.setBackground(Color.ORANGE);
		txtTimTenPhim.setPreferredSize(new Dimension(25, 30));
		boxLeft.add(boxLeft2);
		boxLeft.add(Box.createVerticalStrut(15));
		
		//Field các nút thay đổi loại bảng đang phát hàng hoặc đã dừng chiếu nằm trên phần khung bên trái
//		Box boxLeft3 = new Box(BoxLayout.X_AXIS);
//		boxLeft3.add(btnDangPhatHanh = new JButton("Đang phát hành"));
//		btnDangPhatHanh.setFont(font);
//		btnDangPhatHanh.setBackground(Color.CYAN);
//		boxLeft3.add(Box.createHorizontalStrut(25));
//		boxLeft3.add(btnDaDung = new JButton("Đã dừng"));
//		btnDaDung.setFont(font);
//		btnDaDung.setBackground(Color.RED);
//		boxLeft.add(boxLeft3);
//		boxLeft.add(Box.createVerticalStrut(15));
		
		//Field table nằm trên phần khung bên trái
		Box boxTable = new Box(BoxLayout.Y_AXIS);
		String[] colNames = {"Mã phim", "Tên phim", "Độ tuổi", "Ngày phát hành", "Thời lượng"};
		tblmodel = new DefaultTableModel(colNames, 0);
		table = new JTable(tblmodel);
		boxTable.add(new JScrollPane(table));
		sorter = new TableRowSorter<TableModel>(tblmodel);
		table.setFont(font);
		table.setRowSorter(sorter);
		table.setFont(font);
		boxLeft.add(boxTable);
		
		//Thêm boxLeft vào phần khung bên trái
		pnlLeft.add(boxLeft);
		
		//============================================================================
		//Phần khung bên phải chứa cách field nhập để cập nhật phim nào đó
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		boxRight.add(Box.createVerticalStrut(90));
		
		Box boxRight0 = new Box(BoxLayout.X_AXIS);
		boxRight0.add(lbTitle2 = new JLabel("THÔNG TIN PHIM"));
		lbTitle2.setFont(font2);
		lbTitle2.setForeground(Color.GREEN);
		boxRight.add(boxRight0);
		boxRight.add(Box.createVerticalStrut(25));
		
		//Field nhập mã phim nằm trên phần khung bên phải
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight1.add(lbMaPhim = new JLabel("Mã phim:"));
		lbMaPhim.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(20));
		boxRight1.add(txtMaPhim = new JTextField(30));
		txtMaPhim.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(20));
		
		//Field nhập tên phim nằm trên phần khung bên phải
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(lbTenPhim = new JLabel("Tên phim:"));
		lbTenPhim.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(15));
		boxRight2.add(txtTenPhim = new JTextField(30));
		txtTenPhim.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight2);
		boxRight.add(Box.createVerticalStrut(20));
		
		//Field chọn độ tuổi nằm trên phần khung bên phải
		Box boxRight3 = new Box(BoxLayout.X_AXIS);
		boxRight3.add(lbDoTuoi = new JLabel("Độ tuổi:"));
		lbDoTuoi.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(30));
		boxRight3.add(rdTreEm = new JRadioButton("Trẻ em"));
		rdTreEm.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(15));
		boxRight3.add(rdTren16 = new JRadioButton("16+"));
		rdTren16.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(15));
		boxRight3.add(rdTren18 = new JRadioButton("18+"));
		rdTren18.setFont(font);
		rdTuoi = new ButtonGroup();
		rdTuoi.add(rdTreEm);
		rdTuoi.add(rdTren16);
		rdTuoi.add(rdTren18);
		rdTren18.setSelected(true);
		boxRight.add(boxRight3);
		boxRight3.add(Box.createHorizontalStrut(95));
		boxRight.add(Box.createVerticalStrut(20));
		
		
		//Field nhập thời lượng phim nằm trên phần khung bên phải
		Box boxRight5 = new Box(BoxLayout.X_AXIS);
		boxRight5.add(lbThoiLuong = new JLabel("Thời lượng (phút):"));
		lbThoiLuong.setFont(font);
		boxRight5.add(Box.createHorizontalStrut(15));
		spinnerThoiLuong = new JSpinner(new SpinnerNumberModel(0, 0, 500, 1)); //Ô nhập giá trị bằng cách chọn một giá trị số bằng cách bấm nút tăng/giảm
		spinnerThoiLuong.setPreferredSize(new Dimension(25, 30));
		boxRight5.add(spinnerThoiLuong);
		boxRight.add(boxRight5);
		boxRight.add(Box.createVerticalStrut(20));
		
//		//Field hiển thị thời gian đã tạo phim hoặc cập nhật phim nằm trên phần khung bên phải
//		JPanel pnlRight6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		Box boxRight6 = new Box(BoxLayout.X_AXIS);
//		boxRight6.add(lbThoiGianTao= new JLabel("Thời gian tạo:"));  //lbThoiGianTao là label chỉ nhãn "Thời gian tạo:"
//		lbThoiGianTao.setFont(font);
//		boxRight6.add(Box.createHorizontalStrut(20));
//		boxRight6.add(lbThoiGian = new JLabel(""));	// lbThoiGian là dùng để lấy giá trị thời gian "HH:mm:ss dd-MM-yyyy" tại thời điểm tạo hoặc lưu thay đổi
//		lbThoiGian.setFont(font);
//		pnlRight6.add(boxRight6);
//		boxRight.add(pnlRight6);
//		boxRight.add(Box.createVerticalStrut(20));
		
		//Field chức năng sửa hoặc xóa phim nằm trên phần khung bên phải
		Box boxRight7 = new Box(BoxLayout.X_AXIS);
		boxRight7.add(btnSua = new JButton("Sửa"));
		btnSua.setFont(font);
		btnSua.setBackground(Color.CYAN);
		boxRight7.add(Box.createHorizontalStrut(15));
		boxRight7.add(btnDungChieu = new JButton("Dừng chiếu"));
		btnDungChieu.setFont(font);
		btnDungChieu.setBackground(Color.RED);
		boxRight7.add(Box.createHorizontalStrut(15));
		boxRight7.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBackground(Color.GREEN);
		boxRight.add(boxRight7);
		boxRight.add(Box.createVerticalStrut(20));
		
		//Thêm boxRight phần khung bên phải
		pnlRight.add(boxRight);
		
		//Thêm khung bên trái và khung bên phải vào khung tổng
		pnlCent.add(pnlLeft);
		pnlCent.add(pnlRight);
		
		//Thêm khung tổng vào JFrame
		add(pnlCent);
		
		
		btnXoaTrang.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnSua.addActionListener(this);
		btnDungChieu.addActionListener(this);
		table.addMouseListener(this);
		loadDataToTable();
		
	}
	
	//load dữ liệu lên bảng
	public void loadDataToTable() {
		tblmodel.setRowCount(0);
		list = service.getList();
		for(model.Movies movie : list) {
			tblmodel.addRow(new Object[] {
					movie.getMovieID(),
					movie.getTitle(),
					movie.getAgeRating(),
					movie.getReleaseDate(),
					movie.getDuration()
			});
		}
	}
	
	//load dữ liệu lên textField
	public void loadToTextField() {
		int rowSelected = table.convertRowIndexToModel(table.getSelectedRow());
		String maMV = tblmodel.getValueAt(rowSelected, 0).toString();
		model.Movies mv = new model.Movies();
		mv = service.getMovie(maMV);
		txtMaPhim.setText(maMV);
		txtTenPhim.setText(mv.getTitle());
		int ageRating = mv.getAgeRating();
		if(ageRating < 16) {
			rdTreEm.setSelected(true);
		}else if(ageRating >= 16 && ageRating < 18) {
			rdTren16.setSelected(true);
		}else {
			rdTren18.setSelected(true);
		}
		int time = mv.getDuration();
		double price = service.getPriceByMovieID(maMV);
		spinnerThoiLuong.setValue(time);
	}
	
	//cập nhật bảng
	public void updateTable(ArrayList<model.Movies> mv){
        if(!mv.isEmpty()) {
        	tblmodel.setRowCount(0);
	        for (model.Movies movie : list) {
	            tblmodel.addRow(new Object[]{
	        		movie.getMovieID(),
					movie.getTitle(),
					movie.getAgeRating(),
					movie.getReleaseDate(),
					movie.getDuration()
            	});
        	}
        }
        else{
            JOptionPane.showMessageDialog(this,"Không tìm thông tin phù hợp","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
	//xoá trắng
	public void xoaTrang() {
		txtTimMaPhim.setText("");
		txtTimTenPhim.setText("");
		txtMaPhim.setText("");
		txtTenPhim.setText("");
		rdTren18.setSelected(true);
		spinnerThoiLuong.setValue(0);
		txtMaPhim.requestFocus();
		loadDataToTable();
	}
	
	//tìm phim
	public void findMovie() {
		String movieID = txtTimMaPhim.getText();
		String movieName = txtTimTenPhim.getText();
		list = service.findMovie(movieID, movieName);
		updateTable(list);
	}
	
	//chuyển từ chuỗi sang localdate
	public LocalDate convertStringToLocalDate(String dateStr) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return LocalDate.parse(dateStr, formatter);
	}
	
	//cập nhật phim
	public void updateMovie() {
		int rowSelected = table.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(this, "Không có thông tin nào cập nhật!", "Lỗi",JOptionPane.ERROR_MESSAGE);
		}else {
			rowSelected = table.convertRowIndexToModel(rowSelected);
			String movieID = tblmodel.getValueAt(rowSelected, 0).toString();
			String currentID = txtMaPhim.getText();
			if(!currentID.equalsIgnoreCase(movieID)) {
				JOptionPane.showMessageDialog(this, "Không được sửa mã phim!","Lỗi",JOptionPane.ERROR_MESSAGE);
				txtMaPhim.setText(movieID);
				txtMaPhim.requestFocus();
				return;
			}
			model.Movies mv = new model.Movies();
			mv.setMovieID(movieID);
			mv.setTitle(txtTenPhim.getText());
			mv.setAgeRating(Integer.parseInt(tblmodel.getValueAt(rowSelected, 2).toString()));
			Number numberDur = (Number) spinnerThoiLuong.getValue();
			mv.setDuration(numberDur.intValue());
			mv.setReleaseDate(convertStringToLocalDate(tblmodel.getValueAt(rowSelected,3).toString()));
			String ticketID = service.getTicketIDByMovieID(movieID);
			boolean res = service.update(movieID, mv);
			if(res) {
				JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "Cập nhật không thành công!", "Lỗi",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//xoá phim
	public void deleteMovie() {
		int rowSelected = table.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phim cần xoá!", "Lỗi",JOptionPane.ERROR_MESSAGE);
		}else {
			rowSelected = table.convertRowIndexToModel(rowSelected);
			String movieID = tblmodel.getValueAt(rowSelected, 0).toString();
			if(service.delete(movieID)) {
				JOptionPane.showMessageDialog(this, "Xoá thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "Xoá không thàng công!","Lỗi!",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		new ListPhim();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if(o.equals(btnTimKiem)) {
			findMovie();
		} else if(o.equals(btnSua)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thay đổi?","Xác nhận",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				updateMovie();
			}
		} else if(o.equals(btnDungChieu)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá?","Xác nhận",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				deleteMovie();
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
