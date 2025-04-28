package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	public ThemLichChieu() {
		Font font = new Font ("Arial",Font.BOLD,15);
		Font font2 = new Font ("Atial",Font.BOLD,23);
		Font font3 = new Font ("Arial",Font.BOLD,18);
		
		//Phần khung chính chứa khung bên trái và khung bên phải
		Box boxCent = new Box(BoxLayout.X_AXIS);
		
		//Phần khung bên trái
		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLefft = new Box(BoxLayout.Y_AXIS);
		
		//Field Title của phần bên trái
		Box boxLeft1 = new Box(BoxLayout.X_AXIS);
		boxLeft1.add(lBTitle = new JLabel("DANH SÁCH LỊCH CHIẾU"));
		lBTitle.setFont(font2);
		lBTitle.setForeground(Color.BLUE);
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
//		for(Phong p : ds.layDSPhong()) {
//			cboPhongChieu.addItem(p.getTenPhong());
//		}
		cboPhongChieu.addItem("Phòng 1");
		cboPhongChieu.addItem("Phòng 2");
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
		String[] colNames = {"Tên phim", "Phòng chiếu", "Ngày chiếu", "Giờ bắt đầu", "Giờ kết thúc"};
		tblmodel = new DefaultTableModel(colNames, 0);
		table = new JTable(tblmodel);
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
//		for(Phim p: ds.layPhim()) {
//			cboTenPhim.addItem(p.getTenPhim());
//		}
		cboTenPhim.addItem("Phim1");
		cboTenPhim.addItem("Phim2");
		cboTenPhim.setPreferredSize(new Dimension(230, 30));
		boxRight1.add(cboTenPhim);
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(35));
		
		//Field phòng chiếu nằm trên phần bên phải
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(lbPhongChieu = new JLabel("Phòng chiếu:"));
		lbPhongChieu.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(15));
		boxRight2.add(txtPhongChieu = new JTextField());
		txtPhongChieu.setPreferredSize(new Dimension(230, 30));
		txtPhongChieu.setText(cboPhongChieu.getSelectedItem().toString());
		txtPhongChieu.setEnabled(false);   // ẩn không cho nhập phòng chiếu
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
        spnGioKT.setValue(new Date(gioBD.getTime() + 2 * 60 * 60 * 1000)); // +2h
        spnGioKT.setEnabled(false); //Để ôn giờ kết thúc không nhập được
		
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
		boxCent.add(pnlRight);
		
		//Thêm khung chính vào JFrame
		add(boxCent);
		
		
		cboPhongChieu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (cboPhongChieu.getSelectedItem() != null) {
		            txtPhongChieu.setText(cboPhongChieu.getSelectedItem().toString());
		        }
		    }
		});
		
		spnGioBD.addChangeListener(e -> {
		    Date gioBDMoi = (Date) spnGioBD.getValue();
		    Date gioKTMoi = new Date(gioBDMoi.getTime() + 2 * 60 * 60 * 1000); // +2 tiếng
		    spnGioKT.setValue(gioKTMoi);
		});
		
		btnXoaTrang.addActionListener(this);
		
	}
	
	public void xoaTrang() {
		findDateChooser.setDate(new Date());
		cboPhongChieu.setSelectedIndex(0);
		cboTenPhim.setSelectedIndex(0);
		dateChooser.setDate(new Date());
		Date now = new Date();
	    spnGioBD.setValue(now);
		txtPhongChieu.requestFocus();
	}
	
	public static void main(String[] args) {
		new ThemLichChieu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		
	}
}
