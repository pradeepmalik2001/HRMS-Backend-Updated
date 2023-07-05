package com.ahom.hrms.controller;


import com.ahom.hrms.serviceimpl.ForgotPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {
    private ForgotPasswordService forgotPasswordService;

    public ForgotPasswordController (ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendPasswordResetEmail(@RequestParam String userName) {
        forgotPasswordService.sendPasswordResetEmail(userName);
        return ResponseEntity.ok("Password reset email sent successfully");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String userName, @RequestParam String otp, @RequestParam String newPassword) {
        forgotPasswordService.resetPassword(userName, otp, newPassword);
        return ResponseEntity.ok("Password reset successful");
    }
}

