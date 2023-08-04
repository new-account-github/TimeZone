package com.poly.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import com.poly.service.EncodePasswordService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/account")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@Autowired
	EncodePasswordService encodePasswordService;

	@Autowired
	HttpServletRequest request;
	
	
	@GetMapping()
	public Account account(HttpServletRequest request) {
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		if (username != null && account != null) {
			return account;
		} else {
			return null;
		}
	}

	@PutMapping("/{username}")
	public Account update(@RequestBody Account account, @PathVariable("username") String username) {
			account.setPhone("(+84) " + account.getPhone());
			return accountService.update(account);
	}

	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
}
