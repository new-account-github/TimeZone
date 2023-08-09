package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return accountService.findById(request.getRemoteUser());
	}

	@GetMapping("/admin")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdmin();
		} else {
			return accountService.findALL();
		}
	}

	@PutMapping("/{username}")
	public Account update(@RequestBody Account account, @PathVariable("username") String username) {
		return accountService.update(account);
	}

	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
}
