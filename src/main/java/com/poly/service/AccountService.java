package com.poly.service;

import com.poly.entity.Account;

public interface AccountService {

	Account findById(String username);


	Account update(Account account);


	void delete(String username);

	
}
