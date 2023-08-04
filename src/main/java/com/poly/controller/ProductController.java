package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/home/shop")
	public String shop(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "/product/shop";
	}
	
	@RequestMapping("/home/shop/findByName")
	public String product(Model model,@RequestParam("keyword") Optional<String> kw) {
		List<Product> list = productService.findByName("%" + kw + "%");
		model.addAttribute("items", list);
		return "/product/shop";
	}
}
