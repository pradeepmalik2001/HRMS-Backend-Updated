package com.ahom.hrms.controller;


import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.serviceimpl.ForgotPasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/forgot-password")
@CrossOrigin
public class ForgotPasswordController {
    private ForgotPasswordService forgotPasswordService;

    public ForgotPasswordController (ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/email")
    public ResponseEntity<Object> sendPasswordResetEmail(@Valid @NotEmpty @RequestParam String userName) {

        return ResponseHandler.responseBuilder("Email Sent Successfully", HttpStatus.OK,forgotPasswordService.sendPasswordResetEmail(userName));
    }

    @PostMapping("/reset")
    public ResponseEntity<Object> resetPassword(@Valid @RequestParam String userName,@NotEmpty @RequestParam String otp, @NotEmpty @RequestParam String password, @NotEmpty @RequestParam String confirmPassword) {
        return ResponseHandler.responseBuilder("Password Reset Successfully",HttpStatus.OK,forgotPasswordService.resetPassword(userName, otp, password, confirmPassword));
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<Object> verifyOtp(@Valid @NotEmpty @RequestParam String userName, @NotEmpty @RequestParam String otp)
    {
        return ResponseHandler.responseBuilder("OTP Verified Successfully",HttpStatus.OK,forgotPasswordService.verifyOtp(userName, otp));
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<Object> resendOtp(@Valid @RequestParam String userName)
    {
        return ResponseHandler.responseBuilder("Otp Resend Successfully",HttpStatus.OK,forgotPasswordService.resendOTP(userName));
    }
}

