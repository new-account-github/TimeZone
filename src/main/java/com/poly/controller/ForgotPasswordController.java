package com.poly.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Account;
import com.poly.entity.VerificationToken;
import com.poly.service.AccountService;
import com.poly.service.EmailService;
import com.poly.service.VerificationTokenService;

@Controller
public class ForgotPasswordController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/security/forgot")
    public String showForgotPasswordForm() {
        return "/security/forgot";
    }

    @PostMapping("/security/forgot")
    public String processForgotPasswordForm(@RequestParam("username") String username, @RequestParam("email") String email, Model model) {
        Account account = accountService.findByUsernameAndEmail(username, email);
        if (account == null) {
            model.addAttribute("message", "Invalid username or email");
            return "/security/forgot";
        }
        String token = verificationTokenService.createVerificationTokenForUser(account);
        emailService.sendEmail(email, token,account.getFirstname(),account.getLastname());
        model.addAttribute("message", "An email with a reset link has been sent to your email address");
        return "/security/verifi";
    }

    @GetMapping("/security/verifi")
    public String showVerifiForm() {
        return "/security/verifi";
    }

    @PostMapping("/security/verifi")
    public String processVerifiForm(@RequestParam("token") String token, Model model) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null || verificationToken.getExpiry_date().isBefore(LocalDateTime.now())) {
            model.addAttribute("message", "Invalid or expired token");
            return "/security/verifi";
        }
        model.addAttribute("token", token);
        return "/security/confirmPass";
    }

    @PostMapping("/security/confirmPass")
    public String processResetPassword(@RequestParam("password") String password, @RequestParam("password1") String password1, @RequestParam("token") String token, Model model) {
        if (!password.equals(password1)) {
            model.addAttribute("message", "Passwords do not match");
            return "/security/confirmPass";
        }
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null || verificationToken.getExpiry_date().isBefore(LocalDateTime.now())) {
            model.addAttribute("message", "Invalid or expired token");
            return "/security/confirmPass";
        }
        Account account = verificationToken.getAccount();
        account.setPassword(password);
        accountService.create(account);
        model.addAttribute("message", "Your password has been reset successfully");
        return "/security/login";
    }
}
