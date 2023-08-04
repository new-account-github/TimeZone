package com.poly.service.impl;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.service.EncodePasswordService;

@Service
public class EncodePasswordImpl implements EncodePasswordService{
	
	@Autowired
	BCryptPasswordEncoder encode;
	
	@Override
	public String encodeBcrypt(String password) {
		return encode.encode(password);
	}

	@Override
	public String toSHA1(String password) {
		String moreChar = "sadfqo*ewi&uchvkaj131s1&6#87dfui@343";
		String result = null;
		password = password + moreChar;
		
		try {
			 byte[] dataBytes = password.getBytes("UTF-8");
		     MessageDigest md = MessageDigest.getInstance("SHA-1");
		     result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
		
	}
}
