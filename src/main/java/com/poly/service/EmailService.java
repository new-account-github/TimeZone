package com.poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    VerificationTokenService verificationTokenService;

    public void sendWelcomeEmail(String to, String fullName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Chào mừng đến với TimeZone");
        msg.setText("Chào mừng bạn " + fullName + " đã đến với TimeZone");
        javaMailSender.send(msg);
    }
    public void sendEmail(String to, String token,String fullName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Đặt lại mật khẩu");
        msg.setText("Chào " + fullName + ",\n\n"
                + "Chúng tôi nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn. "
                + "Mã xác thực của bạn là: " + token + "\n\n"
                + "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.\n\n"
                + "Trân trọng,\n"
                + "TimeZone");
        javaMailSender.send(msg);

	}
    
    public void sendPaymentSuccess(String to, String token,String fullName, String transactionId) {
    	SimpleMailMessage msg = new SimpleMailMessage();
    	msg.setTo(to);
    	msg.setSubject("Thanh toán thành công");
    	msg.setText("Chào " + fullName + ",\n\n"
    			+ "Chúng tôi đã xác nhận bạn đã thanh toán thành công cho đơn hàng " + transactionId + "\n\n "
    			+ "Mã xác thực của bạn là: " + token + " vui lòng giữ lại thông tin này để xác nhận với nhân viên của chúng tôi \n\n" 
    			+ "Trân trọng,\n"
    			+ "TimeZone");
    	javaMailSender.send(msg);
    }
    
    public void sendPaymentStatus(String to, String token, String transactionId) {
    	SimpleMailMessage msg = new SimpleMailMessage();
    	msg.setTo(to);
    	msg.setSubject("Thanh toán thành công");
    	msg.setText("Thông báo " + ",\n\n"
    			+ "Đã có đơn hàng thanh toán thành công mã đơn hàng là:" + transactionId + "\n\n" 
    			+ "Mã xác thực là: " + token + "\n\n"
    			+ "Yêu cầu kiểm tra lại thông tin đơn hàng và mã xác thực đơn hàng.\n\n"
    			+ "Trân trọng,\n"
    			+ "TimeZone");
    	javaMailSender.send(msg);
    }
    
    
}

