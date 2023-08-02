package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller

public class HomeAdminController {
	@Autowired
	ProductService productService;
	
	
	@RequestMapping("/admin/index")
	public String show() {
		return "admin/DashBoard";
	}


	@RequestMapping("/admin/Authority")
	public String EditProduct() {
		return "admin/Authority";
	}

	
	@GetMapping("/admin/index/{id}")
	public Product getOne(@PathVariable("id") Integer id) {	
		return productService.findById(id);
	}
	
	
}
