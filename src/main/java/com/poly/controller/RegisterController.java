package com.poly.controller;

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
import com.poly.service.EncodePasswordService;

@Controller
public class RegisterController {
    @Autowired
    AccountService accountService;
    
    @Autowired
    BCryptPasswordEncoder pe;
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    EncodePasswordService encoder;
    
    @RequestMapping(value = "/security/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("account", new Account());
        return "/security/register";
    }
    @RequestMapping(value = "/security/register", method = RequestMethod.POST)
    public String register(Account account, Model model) {
    	if (accountService.checkUsernameExists(account.getUsername())) {
            model.addAttribute("message", "Username already exists");
            return "/security/register";
        }
    	
    	account.setPhone(account.getPhone());
    	account.setPassword(encoder.encodeBcrypt(account.getPassword()));
        accountService.create(account);
        emailService.sendWelcomeEmail(account.getEmail(), account.getFullname());
        model.addAttribute("message", "Đăng ký thành công");
        return "/security/login";
    }
    
    
}

