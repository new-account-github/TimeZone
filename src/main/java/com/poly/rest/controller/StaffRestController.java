package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Authority;
import com.poly.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/staff")
public class StaffRestController {
	@Autowired
	AuthorityService authorityService;

	@GetMapping()
	public List<Object[]> authority() {
		return authorityService.getStaff();
	}

	@PostMapping()
	public Authority create(@RequestBody Authority authority) {
		return authorityService.create(authority);
	}
}
