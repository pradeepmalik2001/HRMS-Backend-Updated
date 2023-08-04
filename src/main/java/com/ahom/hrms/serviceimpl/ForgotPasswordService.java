package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.entities.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ForgotPasswordService
{
    private static final int OTP_EXPIRATION_TIME_SECONDS = 300;

    private Map<String, Map<String, Long>> otpMap;

    private final EmployeeRepository employeeRepository;
    private final JavaMailSender javaMailSender;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${mail.from}")
    private String fromEmail;

    Employee employee;

    public ForgotPasswordService(EmployeeRepository employeeRepository, JavaMailSender javaMailSender) {
        this.employeeRepository = employeeRepository;
        this.javaMailSender = javaMailSender;
        this.otpMap=new ConcurrentHashMap<>();
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String sendPasswordResetEmail(String userName) {
        Employee employee = employeeRepository.getByUserName(userName);
        if (employee == null) {
            throw new IllegalArgumentException("User not found");
        } else {
            String otp = generateOTP();
            System.out.println("otpppp : " + otp);
            long currentTime = System.currentTimeMillis();
            Map<String, Long> otpInfo = new HashMap<>();
            otpInfo.put(otp, currentTime);
            otpMap.put(userName, otpInfo);
            System.out.println("otpMappp : " + otpMap);
            sendEmail(userName, otp);
        }
        return "Email Sent Successfully";
    }

    public String resendOTP(String userName) {
        Employee employee = employeeRepository.getByUserName(userName);
        if (employee == null) {
            throw new IllegalArgumentException("UserName not found");
        }

        Map<String, Long> otpInfo = otpMap.getOrDefault(userName, null);
        if (otpInfo == null) {
            throw new IllegalArgumentException("OTP not found");
        }

        // Check if the previous OTP has expired
        long currentTime = System.currentTimeMillis();
        long otpCreationTime = otpInfo.values().iterator().next();
        long otpAgeInSeconds = (currentTime - otpCreationTime) / 1000;
        if (otpAgeInSeconds <= OTP_EXPIRATION_TIME_SECONDS) {
            throw new IllegalArgumentException("Cannot resend OTP, the previous OTP is still valid.");
        }

        // Generate a new OTP and update the OTP map
        String newOTP = generateOTP();
        otpInfo.clear();
        otpInfo.put(newOTP, currentTime);
        otpMap.put(userName, otpInfo);

        // Send the new OTP to the user's email
        sendEmail(userName, newOTP);

        return "New OTP Sent Successfully";
    }


    public String verifyOtp(String userName, String otp) {
        Employee employee1 = employeeRepository.getByUserName(userName);
        Map<String, Long> otpInfo = otpMap.getOrDefault(userName, null);

        if (employee1 == null || otpInfo == null) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        long currentTime = System.currentTimeMillis();
        long otpCreationTime = otpInfo.values().iterator().next();
        long otpAgeInSeconds = (currentTime - otpCreationTime) / 1000;

        if (otpAgeInSeconds > OTP_EXPIRATION_TIME_SECONDS) {
            otpMap.remove(userName);
            throw new IllegalArgumentException("OTP has expired");
        }

        String storedOtp = otpInfo.keySet().iterator().next();
        if (!storedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        return "Otp Verified Successfully";
    }


    public String resetPassword(String userName, String otp, String password, String confirmPassword) {
        Employee employee = employeeRepository.getByUserName(userName);
        Map<String, Long> otpInfo = otpMap.getOrDefault(userName, null);

        if (otpInfo == null || otpInfo.isEmpty()) {
            throw new IllegalArgumentException("OTP not found");
        }

        String storedOtp = otpInfo.keySet().iterator().next();

        if (!storedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        long currentTime = System.currentTimeMillis();
        long otpCreationTime = otpInfo.values().iterator().next();
        long otpAgeInSeconds = (currentTime - otpCreationTime) / 1000;

        if (otpAgeInSeconds > OTP_EXPIRATION_TIME_SECONDS) {
            otpMap.remove(userName);
            throw new IllegalArgumentException("OTP has expired");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm Password do not match");
        }

        employee.setPassword(bCryptPasswordEncoder.encode(password));
        otpMap.remove(userName);
        employeeRepository.save(employee);

        return "Password Reset Successfully";
    }


    private String generateOTP()
    {
        Random random = new Random();
        int otp1 = 10000 + random.nextInt(90000);
        String otpString = Integer.toString(otp1);
        System.out.println("OTP: " + otp1);
        return otpString;
    }

    private String sendEmail(String userName, String otp)
    {
        SimpleMailMessage messageToEmployee = new SimpleMailMessage();
        messageToEmployee.setFrom(fromEmail);
        messageToEmployee.setTo(userName);
        messageToEmployee.setSubject("OTP for Reset Password");
        messageToEmployee.setText("Otp : "+otp);
        javaMailSender.send(messageToEmployee);
        System.out.println(messageToEmployee);

        return "Otp Sent Successfully";
    }

}

