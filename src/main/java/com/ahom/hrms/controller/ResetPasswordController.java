package com.ahom.hrms.controller;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.securities.AuthController;
import com.ahom.hrms.securities.JwtAuthRequest;
import com.ahom.hrms.securities.JwtAuthResponse;
import com.ahom.hrms.serviceimpl.EmployeeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/reset")
@CrossOrigin
public class ResetPasswordController
{

    @Autowired
    HttpSession session;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${mail.from}")
    private String fromEmail;

    private int otp1;

    @Autowired
    EmployeeService employeeService;

    private LocalDateTime otpExpirationTime;

    JwtAuthResponse jwtAuthResponse;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("userName") String userName)
    {
        Employee employee = employeeRepository.getByUserName(userName);
        this.session.setAttribute("otp",otp1);
        this.session.setAttribute("email",userName);
        System.out.println(employee);
        if (employee.getUsername().isEmpty())
        {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        Random random = new Random();
        otp1 = 10000 + random.nextInt(90000);
        System.out.println("OTP: " + otp1);
        otpExpirationTime = LocalDateTime.now().plusMinutes(1);

            SimpleMailMessage messageToEmployee = new SimpleMailMessage();
                    messageToEmployee.setFrom(fromEmail);
                    messageToEmployee.setTo(employee.getUsername());
                    messageToEmployee.setSubject("OTP for Reset Password");
                    messageToEmployee.setText("Otp : "+otp1);
                    javaMailSender.send(messageToEmployee);
                    System.out.println(messageToEmployee);

        return ResponseEntity.ok("OTP sent successfully");

    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestParam("userName") String userName, @RequestParam("otp") int otp)
    {
        Employee employee = employeeRepository.getByUserName(userName);
        if (employee == null)
        {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        if (otp1==otp)
        {
            LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.isAfter(otpExpirationTime)) {
                return ResponseEntity.badRequest().body("OTP has expired");
            }
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        return ResponseEntity.ok("OTP verified successfully");
    }

    @PostMapping(value = "/reset-password", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> resetPassword(@RequestPart("password") String password,
                                                @RequestPart("confirmPassword") String confirmPassword,
                                                @RequestParam("token") String token)
    {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("jwtTokenKey")
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();

            Optional<Employee> optionalEmployee = employeeRepository.findByUserName(username);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                if(password.equals(confirmPassword))
                {
                    employee.setPassword(bCryptPasswordEncoder.encode(password));
                    employeeRepository.save(employee);
                }

                return ResponseEntity.ok("Password reset successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to reset password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while resetting the password");
    }
}




