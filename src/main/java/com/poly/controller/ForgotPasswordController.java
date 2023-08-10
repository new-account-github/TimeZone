package com.poly.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import com.poly.service.EmailService;
import com.poly.service.VerificationTokenService;

@Controller
public class ForgotPasswordController {
	@Autowired
	AccountService accountService;

	@Autowired
	VerificationTokenService verificationTokenService;

	@Autowired
	EmailService emailService;

	@Autowired
	BCryptPasswordEncoder pe;
	
	@RequestMapping(value = "/security/forgot", method = RequestMethod.GET)
	public String forgotForm() {
		return "/security/forgot";
	}

	@RequestMapping(value = "/security/forgot", method = RequestMethod.POST)
	public String forgot(@RequestParam("username") String username, @RequestParam("email") String email, Model model,
			HttpSession session) {
		Optional<Account> accountOptional = accountService.findByUsernameAndEmail(username, email);
		if (!accountOptional.isPresent()) {
			model.addAttribute("message", "Username or email does not exist");
			return "/security/forgot";
		}

		Account account = accountOptional.get();

		// Lưu giá trị của username vào HttpSession
		session.setAttribute("username", username);

		model.addAttribute("message", "Please check your email to reset your password");

		// Tạo mã xác thực và lưu vào cơ sở dữ liệu
		String token = verificationTokenService.createVerificationTokenForUser(account);
		session.setAttribute("token", token);
		// Gửi email
		emailService.sendEmail(account.getEmail(), token, account.getFirstname(),account.getLastname());

		return "redirect:/security/verifi";
	}

	@RequestMapping(value = "/security/verifi", method = RequestMethod.GET)
	public String verifiForm() {
		return "/security/verifi";
	}

	@RequestMapping(value = "/security/verifi", method = RequestMethod.POST)
	public String verifi(@RequestParam("token") String tokenveri, Model model, HttpSession session) {
		// Lấy giá trị của username từ HttpSession
		String username = (String) session.getAttribute("username");

		Optional<Account> accountOptional = accountService.findByUsername(username);
		Account account = accountOptional.get();
		// Lấy giá trị của token từ HttpSession
		String token = (String) session.getAttribute("token");
		// System.out.println(token);
		// System.out.println(tokenveri);
		if (tokenveri.equals(token)) {
			return "redirect:/security/confirmPass";
		} else {
			model.addAttribute("message", "Sai mã xác thực");
			return "/security/verifi";
		}
	}

	@RequestMapping(value = "/security/confirmPass", method = RequestMethod.GET)
	public String confirmPassForm() {
		return "/security/confirmPass";
	}

	@RequestMapping(value = "/security/confirmPass", method = RequestMethod.POST)
	public String confirmPass(Model model, @RequestParam("PassWord1") String PassWord1,
			@RequestParam("PassWord2") String PassWord2, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (PassWord1.equals(PassWord2)) {
			// Đổi mật khẩu
			Account account = accountService.findById(username);
			if (account != null) {
				// Đổi mật khẩu
				account.setPassword(pe.encode(PassWord1));
				// Lưu lại thông tin tài khoản
				accountService.create(account);

			} else {
				model.addAttribute("message", "Wrong Password");
				return "/security/confirmPass";
			}

		}
		return "/security/Login";
	}
}
