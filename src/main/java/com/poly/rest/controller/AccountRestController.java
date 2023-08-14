package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/account")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@Autowired
	HttpServletRequest request;

	@GetMapping()
	public List<Account> getAll() {
		return accountService.findALL();
	}

	@GetMapping("/admin")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getStaff();
		} else {
			return accountService.findALL();
		}
	}

	@PostMapping("/add")
	public Account createStaff(@RequestBody JsonNode account, Model model) {
		String username = account.get("username").asText();
		return accountService.checkUsernameExists(username) ? null : accountService.create(account);
	}

	@PutMapping("/update/{username}")
	public Account updateStaff(@RequestBody JsonNode staffToUpdate, @PathVariable("username") String username) {
		return accountService.create(staffToUpdate);
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
