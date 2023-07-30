package com.poly.service;

import org.springframework.stereotype.Service;

import com.poly.entity.Account;

public interface VerificationTokenService {
	public String createVerificationTokenForUser(Account account);
}
