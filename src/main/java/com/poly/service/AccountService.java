package com.poly.service;

import java.util.Optional;

import com.poly.entity.Account;

public interface AccountService {

	Account findById(String username);
<<<<<<< HEAD


	Account update(Account account);


	void delete(String username);

	
=======
	Optional<Account> findByUsernameAndEmail(String username, String email);
	Optional<Account> findByUsername(String username);
	void create(Account account);
    public boolean checkUsernameExists(String username);
>>>>>>> eafe7e65e3de44fceeedcc8501c3e71259729d92
}
