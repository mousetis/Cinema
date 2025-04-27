package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import dao.Employee_Dao;
import dao.Login;
import model.Employee;
import model.Movies;
import services.Employee_Services;

public class HomeNV extends JFrame implements ActionListener{



	private JButton btnDatVe;
	private JScrollPane cuon;
	private JButton accountBtn;
	private JPanel headerPanel;
	private JLabel lbTimTenPhim;
	private JTextField txtTimTenPhim;
	private JLabel lbTimTheLoai;
	private JTextField txtTimTheLoai;
	private JButton btnTimKiem;
	private Box pnlCent;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton btnXoaTrang;
	private JPanel pnlKhuVucPhim;
	private ArrayList<Movies> danhSachPhim;
	private DatVe datVePanel;
	private services.Employee_Services service;
	private services.Login serviceEmployee;
	private dao.Employee_Dao dao;
	private static Employee nvDangNhap;
	

	public HomeNV(Employee nvDangNhap) {
		this.nvDangNhap = nvDangNhap;
		Font font = new Font ("Arial",Font.BOLD,18);
		
		headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setPreferredSize(new Dimension(0, 50));

        // Logo bên trái
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/img/CGV_4d6c9e8f1f.png"));
        Image imgLogo = iconLogo.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH); // width, height
        JLabel logoLabel = new JLabel(new ImageIcon(imgLogo));
        
        logoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        logoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Đổi con trỏ chuột

        // Xử lý khi nhấn vào logo để quay lại danh sách phim
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {         	
            	cardLayout.show(cardPanel, "HOME");
            }
        });
        
        
        
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Account bên phải
        ImageIcon iconAcc = new ImageIcon(getClass().getResource("/img/account-icon-33-removebg-preview.png"));
        Image imgAcc = iconAcc.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel lbAcc = new JLabel(new ImageIcon(imgAcc));
        
        lbAcc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Đổi con trỏ chuột

        
        // Xử lý khi nhấn vào logo account sẽ chuyển đến panel thông tin nhân viên
        lbAcc.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
				cardLayout.show(cardPanel, "THONG_TIN_NV");
            }
        });
        
        headerPanel.add(lbAcc, BorderLayout.EAST);
        

        add(headerPanel, BorderLayout.NORTH);
        
        //==========================================================================
   
        
        pnlCent = new Box(BoxLayout.Y_AXIS);
        pnlCent.add(Box.createVerticalStrut(15));
        
        JPanel pnlTK = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Box boxTimKiem = new Box(BoxLayout.X_AXIS);
        boxTimKiem.add(lbTimTenPhim = new JLabel("Tên phim:"));
        lbTimTenPhim.setFont(font);
        boxTimKiem.add(Box.createHorizontalStrut(20));
        boxTimKiem.add(txtTimTenPhim = new JTextField(25));
        boxTimKiem.add(Box.createHorizontalStrut(20));
        boxTimKiem.add(lbTimTheLoai = new JLabel("Thể loại:"));
        lbTimTheLoai.setFont(font);
        boxTimKiem.add(Box.createHorizontalStrut(20));
        boxTimKiem.add(txtTimTheLoai = new JTextField(25));
        boxTimKiem.add(Box.createHorizontalStrut(20));
        boxTimKiem.add(btnTimKiem = new JButton("Tìm kiếm"));
        btnTimKiem.setFont(font);
        btnTimKiem.setBackground(Color.CYAN);
        boxTimKiem.add(Box.createHorizontalStrut(20));
        boxTimKiem.add(btnXoaTrang = new JButton("Xóa trắng"));
        btnXoaTrang.setFont(font);
        btnXoaTrang.setBackground(Color.GREEN);
        pnlTK.add(boxTimKiem);
        
        pnlCent.add(pnlTK);
        pnlCent.add(Box.createVerticalStrut(15));
        
        pnlKhuVucPhim = new JPanel(new GridLayout(0, 4, 20, 20));
        pnlKhuVucPhim.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        cuon = new JScrollPane(pnlKhuVucPhim);
        cuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cuon.getVerticalScrollBar().setUnitIncrement(20);
        
        
        dao = new Employee_Dao(); 
        service = new Employee_Services(dao);
        
        
        // Tạo danh sách phim mẫu
        danhSachPhim = new ArrayList<Movies>();
        danhSachPhim = service.getListMovie();
       
        // Thêm từng thẻ phim vào panel
        for (Movies phim : danhSachPhim) {
            pnlKhuVucPhim.add(taoThePhim(phim));
        }
        
        pnlCent.add(cuon);
        
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        
        datVePanel = new DatVe(null, nvDangNhap);
        cardPanel.add(pnlCent, "HOME");
        cardPanel.add(datVePanel, "DAT_VE");
        cardPanel.add(new ThongTinNNV(this, nvDangNhap), "THONG_TIN_NV");
        
        // Thêm cardPanel vào JFrame
        add(cardPanel, BorderLayout.CENTER);
        
        
        
        
//        add(pnlCent, BorderLayout.CENTER);
        pnlKhuVucPhim.setBackground(Color.WHITE);
        cuon.getViewport().setBackground(Color.WHITE);
        
        btnXoaTrang.addActionListener(this);
        btnTimKiem.addActionListener(this);
        
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel taoThePhim(Movies phim) {
	    Font font = new Font("Arial", Font.BOLD, 18);
	    JPanel pnlThePhim = new JPanel();
	    pnlThePhim.setLayout(new BoxLayout(pnlThePhim, BoxLayout.Y_AXIS));
	    pnlThePhim.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    pnlThePhim.setBackground(new Color(255, 255, 200));

	    ImageIcon iconGoc = new ImageIcon(getClass().getResource(phim.getImg()));
	    Image anhThayDoi = iconGoc.getImage().getScaledInstance(180, 260, Image.SCALE_SMOOTH);
	    JLabel lbAnh = new JLabel(new ImageIcon(anhThayDoi));
	    lbAnh.setAlignmentX(CENTER_ALIGNMENT);

	    JLabel lbTenPhim = new JLabel("<html><div style='text-align: center; width:180px;'>" + phim.getTitle() + "</div></html>");
	    lbTenPhim.setFont(font);
	    lbTenPhim.setAlignmentX(CENTER_ALIGNMENT);

	    JLabel lbTheLoai = new JLabel("Thể loại: " + phim.getMovieCategory().getCategoryName());
	    lbTheLoai.setAlignmentX(CENTER_ALIGNMENT);
	    JLabel lbDoTuoi = new JLabel("Độ tuổi: " + phim.getAgeRating() + "+");
	    lbDoTuoi.setAlignmentX(CENTER_ALIGNMENT);
	    JLabel lbThoiLuong = new JLabel("Thời lượng: " + phim.getDuration() + " phút");
	    lbThoiLuong.setAlignmentX(CENTER_ALIGNMENT);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String ngayKhoiChieu = phim.getReleaseDate().format(formatter);

	    JLabel lbKhoiChieu = new JLabel("Khởi chiếu: " + ngayKhoiChieu);
	    lbKhoiChieu.setAlignmentX(CENTER_ALIGNMENT);

	    JButton btnDatVe = new JButton("Đặt vé");
	    btnDatVe.setBackground(Color.CYAN);
	    btnDatVe.setAlignmentX(CENTER_ALIGNMENT);

	    pnlThePhim.add(Box.createVerticalStrut(10));
	    pnlThePhim.add(lbAnh);
	    pnlThePhim.add(Box.createVerticalStrut(5));
	    pnlThePhim.add(lbTenPhim);
	    pnlThePhim.add(Box.createVerticalStrut(5));
	    pnlThePhim.add(lbTheLoai);
	    pnlThePhim.add(Box.createVerticalStrut(5));
	    pnlThePhim.add(lbDoTuoi);
	    pnlThePhim.add(Box.createVerticalStrut(5));
	    pnlThePhim.add(lbThoiLuong);
	    pnlThePhim.add(Box.createVerticalStrut(5));
	    pnlThePhim.add(lbKhoiChieu);
	    pnlThePhim.add(Box.createVerticalStrut(5));
	    pnlThePhim.add(btnDatVe);
	    pnlThePhim.add(Box.createVerticalStrut(10));

	    btnDatVe.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            datVePanel.capNhatThongTinPhim(phim);
	            cardLayout.show(cardPanel, "DAT_VE");
	        }
	    });

	    return pnlThePhim;
	}

	
	private void timKiemPhim() {
	    String tenPhim = txtTimTenPhim.getText().trim().toLowerCase();
	    String theLoai = txtTimTheLoai.getText().trim().toLowerCase();

	    pnlKhuVucPhim.removeAll();

	    for (Movies phim : danhSachPhim) {
	        boolean matchTen = phim.getTitle().toLowerCase().contains(tenPhim);

	        // Kiểm tra thể loại
	        String categoryName = phim.getMovieCategory().getCategoryName().toLowerCase();
	        boolean matchTheLoai = categoryName.contains(theLoai);

	        // Tìm kiếm nếu tên phim hoặc thể loại khớp
	        if ((tenPhim.isEmpty() || matchTen) && (theLoai.isEmpty() || matchTheLoai)) {
	            pnlKhuVucPhim.add(taoThePhim(phim));
	        }
	    }

	    pnlKhuVucPhim.revalidate(); //xác nhận lại các thẻ phim sẽ được hiển thị sau khi thay đổi.
	    pnlKhuVucPhim.repaint(); //vẽ lại (cập nhật) giao diện khi có thay đổi 
	}

	
	public void closeWindow() {
        this.dispose();
    }
	
	public void xoaTrang() {
		txtTimTenPhim.setText("");
		txtTimTheLoai.setText("");
		txtTimTenPhim.requestFocus();
		pnlKhuVucPhim.removeAll(); // thêm dòng này
	    for (Movies phim : danhSachPhim) {
	        pnlKhuVucPhim.add(taoThePhim(phim));
	    }
	    pnlKhuVucPhim.revalidate();
	    pnlKhuVucPhim.repaint();
	}
	
	public static void main(String[] args) {
		new HomeNV(nvDangNhap);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if(o.equals(btnTimKiem)) {
			timKiemPhim();
		}
	}
}
