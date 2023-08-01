package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {
	@RequestMapping("/admin/authority")
	public String show() {
		return "admin/authority";
	}

}
