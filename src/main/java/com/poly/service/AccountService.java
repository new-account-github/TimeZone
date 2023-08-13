package com.poly.service;

import java.util.Optional;
import java.util.List;
import com.poly.entity.Account;

public interface AccountService {
	Account findById(String username);

	Account update(Account account);

	void delete(String username);

	
	Account findByUsernameAndEmail(String username, String email);

	Optional<Account> findByUsername(String username);

	void create(Account account);

	public boolean checkUsernameExists(String username);

	List<Account> getAdmin();

	List<Account> findALL();

}
