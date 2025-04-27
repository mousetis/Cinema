package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Employee;
import services.EmailUtils;
import services.EmployeeManager;

public class ThemNV extends JPanel implements ActionListener{
	private JLabel lbTenNV;
	private JTextField txtTenNV;
	private JLabel lbSDT;
	private JTextField txtSDT;
	private JLabel lbGioiTinh;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private ButtonGroup rdPhai;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JComboBox comboxQuyen;
	private JLabel lbMatKhau;
	private JTextField txtMatKhau;
	private JButton btnLayMK;
	private JButton btnThem;
	private JLabel lbQuyen;
	private JLabel lbTitle;
	private JButton btnXoaTrang;
	private services.EmployeeManager service;
	private dao.EmployeeManager dao;

	public ThemNV() {
		service = new EmployeeManager(dao);
		//font chữ
		Font font = new Font ("Arial",Font.BOLD,16);
		Font font2 = new Font ("Arial",Font.BOLD,23);
		
		//Phần khung nằm ngoài chứa tất cả các khung khác. Khung này dạng lưới có 2 dòng 1 cột
		JPanel pnlCent = new JPanel();
		pnlCent.setLayout(new BoxLayout(pnlCent, BoxLayout.Y_AXIS));
		
		//Phần khung chứa title
		JPanel pnlGird0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlGird0.add(Box.createVerticalStrut(100));
		pnlGird0.add(lbTitle = new JLabel("THÔNG TIN NHÂN VIÊN MỚI"));
		pnlGird0.add(Box.createVerticalStrut(100));
		lbTitle.setFont(font2);
		lbTitle.setForeground(Color.BLUE);
		
		//Phần khung thứ nhất chứa các field nhập liệu. Dạng 1 dòng 2 cột
		JPanel pnlGird1 = new JPanel(new GridLayout(1, 2));
		
		//Phần bên trái của khung thứ nhất
		JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxLeft = new Box(BoxLayout.Y_AXIS);
		boxLeft.add(Box.createVerticalStrut(5));
		
		//Field nhập tên nhân viên nằm trên phần bên trái của khung thứ nhất
		Box boxLeft1 = new Box(BoxLayout.X_AXIS);
		boxLeft1.add(lbTenNV = new JLabel("Tên nhân viên:"));
		lbTenNV.setFont(font);
		boxLeft1.add(Box.createHorizontalStrut(15));
		boxLeft1.add(txtTenNV = new JTextField(30));
		txtTenNV.setPreferredSize(new Dimension(25, 30));
		txtTenNV.requestFocus();
		boxLeft.add(boxLeft1);
		boxLeft.add(Box.createVerticalStrut(15));
		
		//Field nhập số điện thoại nằm trên phần bên trái của khung thứ nhất
		Box boxLeft2 = new Box(BoxLayout.X_AXIS);
		boxLeft2.add(lbSDT = new JLabel("SĐT:"));
		lbSDT.setFont(font);
		boxLeft2.add(Box.createHorizontalStrut(90));
		boxLeft2.add(txtSDT = new JTextField(30));
		txtSDT.setPreferredSize(new Dimension(25, 30));
		boxLeft.add(boxLeft2);
		boxLeft.add(Box.createVerticalStrut(15));
		
		//Field chọ giới tính nằm trên phần bên trái của khung thứ nhất
		Box boxLeft3 = new Box(BoxLayout.X_AXIS);
		boxLeft3.add(lbGioiTinh = new JLabel("Giới tính:"));
		lbGioiTinh.setFont(font);
		boxLeft3.add(Box.createHorizontalStrut(60));
		boxLeft3.add(rdNam = new JRadioButton("Nam"));
		rdNam.setFont(font);
		boxLeft3.add(Box.createHorizontalStrut(10));
		boxLeft3.add(rdNu = new JRadioButton("Nữ"));
		rdNu.setFont(font);
		rdPhai = new ButtonGroup();
		rdPhai.add(rdNam);
		rdPhai.add(rdNu);
		rdNam.setSelected(true);
		boxLeft3.add(Box.createHorizontalStrut(200));
		boxLeft.add(boxLeft3);
		boxLeft.add(Box.createVerticalStrut(15));
		
		//Field nhập Email nằm trên phần bên trái của khung thứ nhất
		Box boxLeft4 = new Box(BoxLayout.X_AXIS);
		boxLeft4.add(lbEmail = new JLabel("Email:"));
		lbEmail.setFont(font);
		boxLeft4.add(Box.createHorizontalStrut(80));
		boxLeft4.add(txtEmail = new JTextField(30));
		txtEmail.setPreferredSize(new Dimension(25, 30));
		boxLeft.add(boxLeft4);
		boxLeft.add(Box.createVerticalStrut(15));
		
		//Thêm boxLeft vào phần bên trái khung thứ nhất
		pnlLeft.add(boxLeft);
		
		//============================================================================
		//Phần bên phải của khung thứ nhất
		JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		boxRight.add(Box.createVerticalStrut(5));
		
		//Field chọn quyền nằm trên phần bên phải của khung thứ nhất
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight1.add(lbQuyen = new JLabel("Quyền:"));
		lbQuyen.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(45));
		comboxQuyen = new JComboBox<>();
		comboxQuyen.addItem("Nhân viên");
		comboxQuyen.addItem("Quản lý");
		comboxQuyen.setFont(font);
		boxRight1.add(comboxQuyen);
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(15));
		
		
		//Field nhập mật khẩu nằm trên phần bên phải của khung thứ nhất
		Box boxRight2 = new Box(BoxLayout.X_AXIS);
		boxRight2.add(lbMatKhau = new JLabel("Password:"));
		lbMatKhau.setFont(font);
		boxRight2.add(Box.createHorizontalStrut(20));
		boxRight2.add(txtMatKhau = new JTextField(30));
		txtMatKhau.setPreferredSize(new Dimension(25, 30));
		boxRight.add(boxRight2);
		boxRight.add(Box.createVerticalStrut(15));
		
		//Field nút lấy mật khẩu nằm trên phần bên phải của khung thứ nhất
		boxRight.add(btnLayMK = new JButton("Lấy mật khẩu"));
		btnLayMK.setFont(font);
		btnLayMK.setPreferredSize(new Dimension(25, 30));
		
		//Thêm boxRight vào phần bên phải khung thứ nhất
		pnlRight.add(boxRight);
		
		//Thêm phần bên trái và bên phải vào khung thứ nhất
		pnlGird1.add(pnlLeft);
		pnlGird1.add(pnlRight);
		
		
		//===========================================================
		//Phần khung thứ hai chứa các nút thêm nhân viên. Dạng 1 dòng 1 cột
		JPanel pnlGird2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlGird2.add(Box.createVerticalStrut(150));
		pnlGird2.add(btnThem = new JButton("Thêm"));
		btnThem.setFont(font);
		btnThem.setBackground(Color.CYAN);
		pnlGird2.add(Box.createHorizontalStrut(30));
		pnlGird2.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBackground(Color.GREEN);
		btnXoaTrang.setPreferredSize(new Dimension(150, 30));
		
		
		//Thêm khung thứ nhất và khung thứ hai vào khung tổng
		pnlCent.add(pnlGird0);
		pnlCent.add(pnlGird1);
		pnlCent.add(pnlGird2);
		
		//Thêm khung tổng vào JFrame
		add(pnlCent, BorderLayout.CENTER);
		
		
		btnLayMK.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		
	}
	
	
	private String sinhMatKhauNgauNhien(int doDai) {
		
	    String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
	    
	    StringBuilder matKhau = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < doDai; i++) {
	        matKhau.append(kyTu.charAt(random.nextInt(kyTu.length())));
	    }
	    return matKhau.toString();
	}
	
	public static void main(String[] args) {
		new ThemNV();
	}
	
	public void xoaTrang() {
		txtTenNV.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		rdNam.setSelected(true);
		comboxQuyen.setSelectedIndex(0);
		txtMatKhau.setText("");
		txtTenNV.requestFocus();
	}
	
	//check rỗng
	public boolean checkIsEmpty() {
		if (txtTenNV.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên nhân viên!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
	        txtTenNV.requestFocus();
	        return false;
	    }
	    if (txtSDT.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập Số điện thoại!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
	        txtSDT.requestFocus();
	        return false;
	    }
	    if (txtEmail.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập Email!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
	        txtEmail.requestFocus();
	        return false;
	    }
	    if (txtMatKhau.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhấn nút lấy Password!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
	        txtMatKhau.requestFocus();
	        return false;
	    }
	    return true;
	}
	
	//thêm nhân viên
	public void addEmployee() {
		Employee nv = new Employee();
		boolean res;
		if(checkIsEmpty()) {
			String name = txtTenNV.getText();
			String phone = txtSDT.getText();
			String email = txtEmail.getText();
			String password = txtMatKhau.getText();
			boolean gender;
			if(rdNam.isSelected()) {
				gender = true;
			} else {
				gender = false;
			}
			String role = (String) comboxQuyen.getSelectedItem();
			nv.setEmployeeName(name);
			nv.setGender(gender);
			nv.setPhone(phone);
			nv.setRole(role);
			nv.setEmail(email);
			
			res = service.addEmployee(nv, email, password);
			if(res) {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Thêm không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLayMK)) {
	        String email = txtEmail.getText().trim();
	        if (email.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập email!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String matKhauMoi = sinhMatKhauNgauNhien(8);
	        txtMatKhau.setText(matKhauMoi);

	        try {
	            EmailUtils.guiEmail(email, matKhauMoi);
	            JOptionPane.showMessageDialog(null, "Mật khẩu mới đã được gửi về email!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Lỗi gửi email: " + ex.getMessage());
	        }
	    
		} else if(o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if(o == btnThem) {
			addEmployee();
		}
	}
}
