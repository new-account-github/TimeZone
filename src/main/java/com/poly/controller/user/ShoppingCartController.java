package com.poly.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {
	@RequestMapping("/home/cart")
	public String cart() {
		return "/cart/cart";
	}
}	
