package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Employee;


public class ThongTinNNV extends JPanel implements ActionListener{
	private JLabel lbTitle;
	private JLabel lbTenNV;
	private JTextField txtTenNV;
	private JLabel lbSDT;
	private JTextField txtSDT;
	private JLabel lbGioiTinh;
	private JTextField txtGioiTinh;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JLabel lbDaiChi;
	private JTextField txtDaiChi;
	private JLabel lbNgaySinh;
	private JTextField txtNgaySinh;
	private JLabel lbChucVu;
	private JTextField txtChucVu;
	private JButton btnSua;
	private JButton btnDangXuat;
	private JLabel lbMaNV;
	private JLabel lbMaNhanVien;
	private JButton btnXoaTrang;
	private JLabel lbImg;
	private static JFrame parentFrame;

	private static Employee nhanVien;

	public ThongTinNNV(JFrame parentFrame, Employee nv) {
		ThongTinNNV.parentFrame = parentFrame;
		this.nhanVien = nv;

		//font chữ
		Font font = new Font ("Arial",Font.BOLD,16);
		Font font2 = new Font ("Arial",Font.BOLD,23);

		//Phần khung nằm ngoài chứa tất cả các khung khác. Khung này dạng lưới có 2 dòng 1 cột
		JPanel pnlCent = new JPanel();
		pnlCent.setLayout(new BoxLayout(pnlCent, BoxLayout.Y_AXIS));

		//Phần khung chứa title
		JPanel pnlGird0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlGird0.add(Box.createVerticalStrut(100));
		pnlGird0.add(lbTitle = new JLabel("THÔNG TIN NHÂN VIÊN"));
		pnlGird0.add(Box.createVerticalStrut(80));
		lbTitle.setFont(font2);
		lbTitle.setForeground(Color.BLUE);

		//Phần khung thứ nhất chứa các field nhập liệu. Dạng 1 dòng 2 cột
		JPanel pnlGird1 = new JPanel(new GridLayout(1, 2));

		//Phần bên trái của khung thứ nhất
		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLeft = new Box(BoxLayout.Y_AXIS);
		boxLeft.add(Box.createVerticalStrut(5));

		//Phần chứa ảnh của nhân viên
		lbImg = new JLabel();
		JPanel pnlImg = new JPanel();
		pnlImg.setBorder(BorderFactory.createTitledBorder(""));
		pnlImg.setPreferredSize(new Dimension(250, 300));
		pnlImg.add(lbImg);
		boxLeft.add(pnlImg);
		boxLeft.add(Box.createVerticalStrut(30));

		JPanel pnlMaNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Box boxMaNV = new Box(BoxLayout.X_AXIS);
		boxMaNV.add(lbMaNhanVien = new JLabel("Mã nhân viên:"));
		lbMaNhanVien.setFont(font);
		boxMaNV.add(Box.createHorizontalStrut(35));
		boxMaNV.add(lbMaNV = new JLabel(""));
		lbMaNV.setFont(font);
		pnlMaNV.add(boxMaNV);
		boxLeft.add(pnlMaNV);


		//Thêm boxLeft vào phần bên trái khung thứ nhất
		pnlLeft.add(boxLeft);

		//============================================================================
		//Phần bên phải của khung thứ nhất
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		boxRight.add(Box.createVerticalStrut(5));

		//Field họ tên nhân viên trên phần bên phải của khung thứ nhất
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight1.add(lbTenNV = new JLabel("Họ tên:"));
		lbTenNV.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(65));
		boxRight1.add(txtTenNV = new JTextField(30));
		txtTenNV.setEditable(false);
		txtTenNV.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(15));


		//Field số điện thoại nằm trên phần bên phải của khung thứ nhất
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(lbSDT = new JLabel("Số điện thoại:"));
		lbSDT.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(15));
		boxRight2.add(txtSDT = new JTextField(30));
		txtSDT.setPreferredSize(new Dimension(25, 30));
		txtSDT.requestFocus();
		boxRight.add(boxRight2);
		boxRight.add(Box.createVerticalStrut(15));


		//Field giới tính nằm trên phần bên phải của khung thứ nhất
		Box boxRight3 = new Box(BoxLayout.X_AXIS);
		boxRight3.add(lbGioiTinh = new JLabel("Giới tính:"));
		lbGioiTinh.setFont(font);
		boxRight3.add(Box.createHorizontalStrut(50));
		boxRight3.add(txtGioiTinh = new JTextField(30));
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight3);
		boxRight.add(Box.createVerticalStrut(15));

		//Field email nằm trên phần bên phải của khung thứ nhất
		Box boxRight4 = new Box(BoxLayout.X_AXIS);
		boxRight4.add(lbEmail = new JLabel("Email:"));
		lbEmail.setFont(font);
		boxRight4.add(Box.createHorizontalStrut(75));
		boxRight4.add(txtEmail = new JTextField(30));
		txtEmail.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight4);
		boxRight.add(Box.createVerticalStrut(15));

		//Field địa chỉ nằm trên phần bên phải của khung thứ nhất
		Box boxRight5 = new Box(BoxLayout.X_AXIS);
		boxRight5.add(lbDaiChi = new JLabel("Địa chỉ:"));
		lbDaiChi.setFont(font);
		boxRight5.add(Box.createHorizontalStrut(65));
		boxRight5.add(txtDaiChi = new JTextField(30));
		txtDaiChi.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight5);
		boxRight.add(Box.createVerticalStrut(15));

		//Field ngày sinh nằm trên phần bên phải của khung thứ nhất
		Box boxRight6 = new Box(BoxLayout.X_AXIS);
		boxRight6.add(lbNgaySinh = new JLabel("Ngày sinh:"));
		lbNgaySinh.setFont(font);
		boxRight6.add(Box.createHorizontalStrut(40));
		boxRight6.add(txtNgaySinh = new JTextField(30));
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight6);
		boxRight.add(Box.createVerticalStrut(15));

		//Field chức vụ nằm trên phần bên phải của khung thứ nhất
		Box boxRight7 = new Box(BoxLayout.X_AXIS);
		boxRight7.add(lbChucVu = new JLabel("Chức vụ:"));
		lbChucVu.setFont(font);
		boxRight7.add(Box.createHorizontalStrut(50));
		boxRight7.add(txtChucVu = new JTextField(30));
		txtChucVu.setEditable(false);
		txtChucVu.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight7);
		boxRight.add(Box.createVerticalStrut(15));


		//Thêm boxRight vào phần bên phải khung thứ nhất
		pnlRight.add(boxRight);

		//Thêm phần bên trái và bên phải vào khung thứ nhất
		pnlGird1.add(pnlLeft);
		pnlGird1.add(pnlRight);


		//===========================================================
		//Phần khung thứ hai chứa các nút. Dạng 1 dòng 1 cột
		JPanel pnlGird2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlGird2.add(Box.createVerticalStrut(100));
		pnlGird2.add(btnSua = new JButton("Sửa thông tin"));
		btnSua.setFont(font);
		btnSua.setBackground(Color.CYAN);
		btnSua.setPreferredSize(new Dimension(150, 30));
		pnlGird2.add(Box.createHorizontalStrut(30));
		pnlGird2.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBackground(Color.GREEN);
		btnXoaTrang.setPreferredSize(new Dimension(150, 30));
		pnlGird2.add(Box.createHorizontalStrut(30));
		pnlGird2.add(btnDangXuat = new JButton("Đăng xuất"));
		btnDangXuat.setFont(font);
		btnDangXuat.setBackground(Color.RED);
		btnDangXuat.setPreferredSize(new Dimension(150, 30));


		//Thêm khung thứ nhất và khung thứ hai vào khung tổng
		pnlCent.add(pnlGird0);
		pnlCent.add(pnlGird1);
		pnlCent.add(pnlGird2);

		//Thêm khung tổng vào JFrame
		add(pnlCent, BorderLayout.CENTER);
		
		hienThiThongTinNV();

		btnDangXuat.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	public void xoaTrang() {
		txtSDT.setText("");
		txtEmail.setText("");
		txtDaiChi.setText("");
		txtSDT.requestFocus();
	}

	public void hienThiThongTinNV() {
        if (nhanVien != null) {
            lbMaNV.setText(nhanVien.getEmployeeID());
            txtTenNV.setText(nhanVien.getEmployeeName());
            txtSDT.setText(nhanVien.getPhone());
            String gioitinh;
            if(nhanVien.isGender() == true)
            	gioitinh = "Nam";
            else
            	gioitinh = "Nữ";
            txtGioiTinh.setText(gioitinh);
            txtEmail.setText(nhanVien.getEmail());
            txtDaiChi.setText(nhanVien.getAddress());
            txtNgaySinh.setText(nhanVien.getHireDate().toString());
            txtChucVu.setText(nhanVien.getRole());
            try {
				ImageIcon icon = new ImageIcon(getClass().getResource(nhanVien.getImg()));
				Image img = icon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
				JLabel lbAnh = new JLabel(new ImageIcon(img));
				lbAnh.setAlignmentX(CENTER_ALIGNMENT);
				lbImg.setIcon(new ImageIcon(img));

			} catch (Exception e) {
				System.err.println("Không thể load hình ảnh: " + nhanVien.getImg());
			}
        }
    }

	public static void main(String[] args) {
		new ThongTinNNV(parentFrame, nhanVien);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDangXuat)) {
			if (parentFrame != null) {
				parentFrame.dispose(); // Đóng frame cha
			}
			new Login();

		}	
		else if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
	}
}
