package com.poly.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.VerificationTokenDAO;
import com.poly.entity.Account;
import com.poly.entity.VerificationToken;
import com.poly.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

	@Autowired
    VerificationTokenDAO verificationTokenDAO;
	
	public String createVerificationTokenForUser(Account account) {
	    // Tạo mã xác thực gồm 6 chữ số
	    Random random = new Random();
	    int tokenInt = random.nextInt(1000000);
	    String token = String.format("%06d", tokenInt);
	    
	    // Lưu mã xác thực vào cơ sở dữ liệu
	    VerificationToken verificationToken = new VerificationToken();
	    verificationToken.setToken(token);
	    verificationToken.setAccount(account);
	    
	    // Thiết lập thời gian hết hạn cho mã xác thực
	    LocalDateTime expiry_date = LocalDateTime.now().plusMinutes(5);
	    verificationToken.setExpiry_date(expiry_date);
	    
	    verificationTokenDAO.save(verificationToken);
	    
	    return token;
	}
	@Override
    public VerificationToken findByToken(String token) {
        return verificationTokenDAO.findByToken(token);
    }
}
