package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

@Controller
public class HomeController {
	@RequestMapping("/home/index")
	public String index() {
		return "/home/index";
	}
	@RequestMapping("/home/login")
	public String login() {
		return "/home/login";
	}
	@RequestMapping("/home/cart")
	public String cart() {
		return "/home/cart";
	}
	@RequestMapping("/home/shop")
	public String shop() {
		return "/home/shop";
	}
	@RequestMapping("/home/checkout")
	public String checkout() {
		return "/home/checkout";
	}
	@RequestMapping("/home/detail")
	public String detail() {
		return "/home/detail";
	}
}
