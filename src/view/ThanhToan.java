package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class ThanhToan extends JFrame implements ActionListener{
	private JLabel lbTitle;
	private JLabel lbTitleTT;
	private JLabel lbTenKH;
	public JLabel TenKH;
	private JLabel lbEmail;
	public JLabel Email;
	private JLabel lbTenPhim;
	public JLabel TenPhim;
	private JLabel lbThoiGian;
	public JLabel ThoiGian;
	private JLabel lbMaGhe;
	public JLabel MaGhe;
	private JLabel lbBongNgo;
	public JLabel BongNgo;
	private JLabel lbNuoc;
	public JLabel Nuoc;
	public JLabel lbGiaGhe;
	public JLabel lbGiaBongNgo;
	public JLabel lbGiaNuoc;
	private JLabel lbTongTien;
	public JLabel TongTien;
	private JButton btnKetThuc;
	private JLabel lbTitleDG;
    public JPanel pnlQR;


	public ThanhToan() throws UnsupportedEncodingException {
		Font font = new Font ("Arial",Font.BOLD,18);
		Font font2 = new Font ("Atial",Font.BOLD,23);
		Font font3 = new Font ("Arial",Font.BOLD,20);
		Font font4 = new Font ("Arial",Font.PLAIN,15);
		
		JPanel pnlCent = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JPanel pnlNor = new JPanel();
		pnlNor.add(lbTitle = new JLabel("Xác nhận thông tin thanh toán"));
		pnlNor.add(Box.createVerticalStrut(80));
		lbTitle.setFont(font2);
		lbTitle.setForeground(Color.BLUE);
		add(pnlNor, BorderLayout.NORTH);
		
		//================================================================
		Box boxCent = new Box(BoxLayout.X_AXIS);
		
		Box boxLeft = new Box(BoxLayout.X_AXIS);
		boxLeft.setBorder(BorderFactory.createTitledBorder(""));
		
		Box boxLeftTT = new Box(BoxLayout.Y_AXIS);
		boxLeftTT.setBorder(BorderFactory.createTitledBorder(""));
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT1 = new Box(BoxLayout.X_AXIS);
		boxLeftTT1.add(Box.createHorizontalStrut(130));
		boxLeftTT1.add(lbTitleTT = new JLabel("Thông tin"));
		lbTitleTT.setFont(font3);
		lbTitleTT.setForeground(Color.GREEN);
		boxLeftTT1.add(Box.createHorizontalStrut(130));
		boxLeftTT.add(boxLeftTT1);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT2 = new Box(BoxLayout.X_AXIS);
		boxLeftTT2.add(lbTenKH = new JLabel("Tên khách hàng:"));
		lbTenKH.setFont(font);
		boxLeftTT2.add(Box.createHorizontalStrut(15));
		boxLeftTT2.add(TenKH = new JLabel("              "));
		TenKH.setFont(font4);
		boxLeftTT.add(boxLeftTT2);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT3 = new Box(BoxLayout.X_AXIS);
		boxLeftTT3.add(lbEmail = new JLabel("Email:"));
		lbEmail.setFont(font);
		boxLeftTT3.add(Box.createHorizontalStrut(15));
		boxLeftTT3.add(Email = new JLabel("              "));
		Email.setFont(font4);
		boxLeftTT.add(boxLeftTT3);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT4 = new Box(BoxLayout.X_AXIS);
		boxLeftTT4.add(lbTenPhim = new JLabel("Tên phim:"));
		lbTenPhim.setFont(font);
		boxLeftTT4.add(Box.createHorizontalStrut(15));
		boxLeftTT4.add(TenPhim = new JLabel("              "));
		TenPhim.setFont(font4);
		boxLeftTT.add(boxLeftTT4);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT5 = new Box(BoxLayout.X_AXIS);
		boxLeftTT5.add(lbThoiGian = new JLabel("Thời gian:"));
		lbThoiGian.setFont(font);
		boxLeftTT5.add(Box.createHorizontalStrut(15));
		boxLeftTT5.add(ThoiGian = new JLabel("              "));
		ThoiGian.setFont(font4);
		boxLeftTT.add(boxLeftTT5);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT6 = new Box(BoxLayout.X_AXIS);
		boxLeftTT6.add(lbMaGhe = new JLabel("Mã ghế:"));
		lbMaGhe.setFont(font);
		boxLeftTT6.add(Box.createHorizontalStrut(15));
		boxLeftTT6.add(MaGhe = new JLabel("              "));
		MaGhe.setFont(font4);
		boxLeftTT.add(boxLeftTT6);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT7 = new Box(BoxLayout.X_AXIS);
		boxLeftTT7.add(lbBongNgo = new JLabel("Bỏng ngô:"));
		lbBongNgo.setFont(font);
		boxLeftTT7.add(Box.createHorizontalStrut(15));
		boxLeftTT7.add(BongNgo = new JLabel("              "));
		BongNgo.setFont(font4);
		boxLeftTT.add(boxLeftTT7);
		boxLeftTT.add(Box.createVerticalStrut(20));
		
		Box boxLeftTT8 = new Box(BoxLayout.X_AXIS);
		boxLeftTT8.add(lbNuoc = new JLabel("Nước:"));
		lbNuoc.setFont(font);
		boxLeftTT8.add(Box.createHorizontalStrut(15));
		boxLeftTT8.add(Nuoc = new JLabel("              "));
		Nuoc.setFont(font4);
		boxLeftTT.add(boxLeftTT8);
		boxLeftTT.add(Box.createVerticalStrut(20));
		boxLeftTT.add(Box.createHorizontalStrut(20));
		
		//--------------------------------------------------------------------
		Box boxLeftDG = new Box(BoxLayout.Y_AXIS);
		boxLeftDG.setBorder(BorderFactory.createTitledBorder(""));
		boxLeftDG.add(Box.createVerticalStrut(15));
		
		JPanel pnlLeftDG1 = new JPanel();
		pnlLeftDG1.add(Box.createHorizontalStrut(20));
		pnlLeftDG1.add(lbTitleDG = new JLabel("Đơn giá"));
		lbTitleDG.setFont(font3);
		lbTitleDG.setForeground(Color.GREEN);
		pnlLeftDG1.add(Box.createHorizontalStrut(20));
		boxLeftDG.add(pnlLeftDG1);
		boxLeftDG.add(Box.createVerticalStrut(170));
		
		JPanel pnlLeftDG2 = new JPanel();
		pnlLeftDG2.add(lbGiaGhe = new JLabel("0 VNĐ"));
		lbGiaGhe.setFont(font4);
		boxLeftDG.add(pnlLeftDG2);
		
		JPanel pnlLeftDG3 = new JPanel();
		pnlLeftDG3.add(lbGiaBongNgo = new JLabel("0 VNĐ"));
		lbGiaBongNgo.setFont(font4);
		boxLeftDG.add(pnlLeftDG3);
		
		JPanel pnlLeftDG4 = new JPanel();
		pnlLeftDG4.add(lbGiaNuoc = new JLabel("0 VNĐ"));
		lbGiaNuoc.setFont(font4);
		boxLeftDG.add(pnlLeftDG4);
		
		//-------------------------------------------------------------------
		boxLeft.add(boxLeftTT);
		boxLeft.add(boxLeftDG);
		
		//-------------------------------------------------------------------
		Box boxRight = new Box(BoxLayout.Y_AXIS);
		boxRight.add(Box.createVerticalStrut(40));
		
		
		//=====================================================================
		pnlQR = new JPanel(); // Khởi tạo pnlQR
        String noiDungQR = "Vui lòng chờ...";
        BufferedImage anhQR = taoAnhMaQR(noiDungQR, 200, 200);

        if (anhQR != null) {
            JLabel nhanAnhQR = new JLabel(new ImageIcon(anhQR));
            pnlQR.add(nhanAnhQR);
        }
        boxRight.add(pnlQR);
        //======================================================================
		
		boxRight.add(Box.createVerticalStrut(40));
		
		Box boxRight1 = new Box(BoxLayout.X_AXIS);
		boxRight1.add(lbTongTien = new JLabel("Tổng tiền:"));
		lbTongTien.setFont(font);
		boxRight1.add(Box.createHorizontalStrut(15));
		boxRight1.add(TongTien = new JLabel(""));
		TongTien.setFont(font4);
		boxRight.add(boxRight1);
		boxRight.add(Box.createVerticalStrut(10));
		
		JPanel pnlRight2 = new JPanel();
		pnlRight2.add(btnKetThuc = new JButton("Kết thúc"));
		btnKetThuc.setFont(font);
		btnKetThuc.setBackground(Color.CYAN);
		boxRight.add(pnlRight2);
		
		//-------------------------------------------------------------------
		boxCent.add(Box.createHorizontalStrut(20));
		boxCent.add(boxLeft);
		boxCent.add(Box.createHorizontalStrut(20));
		boxCent.add(boxRight);
		boxCent.add(Box.createHorizontalStrut(20));
		
		
		pnlCent.add(boxCent, BorderLayout.CENTER);
		
		
		add(pnlCent, BorderLayout.CENTER);
		
		
		btnKetThuc.addActionListener(this);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public BufferedImage taoAnhMaQR(String noiDung, int chieuRong, int chieuCao) throws UnsupportedEncodingException {
	    try {
	    	// Đảm bảo mã hóa UTF-8 để không lỗi font tiếng Việt. Chuyển chuổi UTF-8 sang theo chuẩn ISO-8859-1
	        String noiDungUTF8 = new String(noiDung.getBytes("UTF-8"), "ISO-8859-1"); //ZXing xử lý chuỗi đầu vào theo chuẩn ISO-8859-1
	    	
	        QRCodeWriter maQR = new QRCodeWriter(); // Tạo đối tượng mã hóa QR
	        BitMatrix maTran = maQR.encode(noiDungUTF8, BarcodeFormat.QR_CODE, chieuRong, chieuCao); // Mã hóa văn bản thành ma trận
	        return MatrixToImageWriter.toBufferedImage(maTran); // Chuyển ma trận thành ảnh
	    } catch (WriterException loi) {
	        loi.printStackTrace(); // Ghi log nếu có lỗi
	        return null;
	    }
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		new ThanhToan();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnKetThuc)) {
			this.dispose();
		}
		
	}
}
