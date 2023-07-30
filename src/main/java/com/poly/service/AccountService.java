package com.poly.service;

import java.util.List;

import com.poly.entity.Account;

public interface AccountService {

	Account findById(String username);

	List<Account> getAdmin();


	List<Account> findALL();

}
