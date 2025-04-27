package services;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.mail.smtp.SMTPTransport;

public class EmailUtils {
		public static void guiEmail(String emailNhan, String matKhauMoi) {
	       final String emailGui = "javaemailk19@gmail.com";
	       final String matKhauEmail = "zzkz digm npwv ylah"; // Mật khẩu ứng dụng
	       
	       Properties props = new Properties();
	       props.put("mail.smtp.auth", "true");  // Yêu cầu xác thực
	       props.put("mail.smtp.starttls.enable", "true");
	       props.put("mail.smtp.host", "smtp.gmail.com"); // Máy chủ SMTP của Gmail
	       props.put("mail.smtp.port", "587");  // Cổng SMTP Gmail
//	       props.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Phiên bản TLS
//	       props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      
	       
	       Authenticator auth = new Authenticator() {
	           @Override
	           protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(emailGui, matKhauEmail);
	           }
	       };
	       Session session = Session.getInstance(props, auth);
	       try {
	           MimeMessage msg = new MimeMessage(session);
	           
	           // Header nội dung
	           msg.addHeader("Content-type", "text/plain; charset=UTF-8");
	           
	           // Địa chỉ người gửi
	           msg.setFrom(new InternetAddress(emailGui));
	           
	           // Gửi tin nhắn đến người nhận 
	           msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailNhan));
	           
	           // Tạo tin nhắn ở chính email người gửi
	           msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(emailGui));
	           
	           //Tiều đề của email
	           msg.setSubject("Mật khẩu mới của bạn");
	           
	           //Nội dung của email
	           msg.setText("Mật khẩu mới được tạo: " + matKhauMoi, "UTF-8");
	       

	           // Thêm debug để kiểm tra quá trình gửi mail
	           session.setDebug(true);
	           // Gửi tin nhắn đến người nhận
	           Transport.send(msg);
	           
	           System.out.println("Email đã được gửi thành công tới: " + emailNhan);
	          
	       } catch (Exception e) {
	           System.err.println("Lỗi khi gửi email:");
	           e.printStackTrace();
	          
	           //email hoặc mật khẩu không chính xác
	           if (e instanceof AuthenticationFailedException) {
	               System.err.println("Lỗi xác thực - Kiểm tra lại email và mật khẩu ứng dụng");
	           } 
	           
	           else if (e instanceof MessagingException) {
	               System.err.println("Lỗi kết nối - Kiểm tra mạng hoặc cấu hình SMTP");
	           }
	       }
	   }
}
