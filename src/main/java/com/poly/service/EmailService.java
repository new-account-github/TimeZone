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
                + "Đội ngũ hỗ trợ");
        javaMailSender.send(msg);

	}
}

