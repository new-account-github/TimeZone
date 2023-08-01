package com.poly.service.impl;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountsDAO;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountsDAO accountsDAO;

	@Override
	public Account findById(String username) {
		return accountsDAO.findById(username).get();
	}

	@Override
	public void create(Account account) {
		accountsDAO.save(account);
	}
	@Override
    public Optional<Account> findByUsernameAndEmail(String username, String email) {
        return accountsDAO.findByUsernameAndEmail(username, email);
    }

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountsDAO.findByUsername(username);
	}

	@Override
	 public boolean checkUsernameExists(String username) {
        return accountsDAO.findByUsername(username).isPresent();
    }
	public List<Account> getAdmin() {
		return accountsDAO.getAdmin();
	}

	@Override
	public List<Account> findALL() {
		return accountsDAO.findAll();
	}
}
