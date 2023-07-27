package com.poly.service;

import java.util.Optional;

import com.poly.entity.Account;

public interface AccountService {

	Account findById(String username);
	Optional<Account> findByUsernameAndEmail(String username, String email);
	Optional<Account> findByUsername(String username);
	void create(Account account);
	
}
