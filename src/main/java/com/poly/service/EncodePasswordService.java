package com.poly.service;

import org.springframework.context.annotation.Configuration;

@Configuration
public interface EncodePasswordService {
	
	String encodeBcrypt(String password);
	
	String toSHA1(String password);
	
}
