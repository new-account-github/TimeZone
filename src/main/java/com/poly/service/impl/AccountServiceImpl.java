package com.poly.service.impl;

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
	public List<Account> getAdmin() {
		return accountsDAO.getAdmin();
	}

	@Override
	public List<Account> findALL() {
		return accountsDAO.findAll();
	}
}
