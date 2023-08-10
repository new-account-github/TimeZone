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

    public void sendWelcomeEmail(String to, String firstname, String lastname) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Welcome to TimeZone");
        msg.setText("Welcome " + firstname +" "+lastname+ " to TimeZone");
        javaMailSender.send(msg);
    }
    public void sendEmail(String to, String token,String firstname, String lastname) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
         msg.setSubject("Reset Password");
        msg.setText("Hi "  + firstname +" "+lastname+  ",\n\n"
                + "We have received a request to reset the password for your account. \n\n"
                + "Your authentication code is: " + token + "\n\n"
                + "If you did not request a password reset, please ignore this email.\n\n"
                + "Regards,\n"
                + "TimeZone");
        javaMailSender.send(msg);

	}
}

