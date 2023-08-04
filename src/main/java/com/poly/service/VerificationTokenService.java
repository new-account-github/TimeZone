package com.poly.service;

import com.poly.entity.Account;

public interface VerificationTokenService {
	public String createVerificationTokenForUser(Account account);
}
