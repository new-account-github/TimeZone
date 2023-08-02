package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.OrderDetailDAO;

@CrossOrigin("*")
@RestController("/admin/statical")
public class StaticalRestController {
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@GetMapping("/revenue")
	public List<Object[]> getProductRevenue(){
		return orderDetailDAO.getProductRevenue();
	} 
}
