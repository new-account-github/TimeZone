package com.poly.rest.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Authority;
import com.poly.entity.Product;
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
