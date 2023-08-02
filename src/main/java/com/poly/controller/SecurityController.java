package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String form(Model model) {
		model.addAttribute("message", "Login");
		return "/security/login";
	}

	@RequestMapping("/security/login/success")
	public String success() {
		return "redirect:/home/index";
	}

	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Username or password incorrect");
		return "/security/login";
	}

	@RequestMapping("/security/login/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("messsage", "Your don't have role to get url");
		return "/security/login";
	}

	@RequestMapping("/security/logoff/success")
	public String logoff() {
		return "redirect:/home/index";
	}
}
