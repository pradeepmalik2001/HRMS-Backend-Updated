package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.entities.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ForgotPasswordService
{
    private  Map<String, String> otpMap;
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
        Employee employee=employeeRepository.getByUserName(userName);
        if (employee == null) {
            throw new IllegalArgumentException("User not found");
        }
        else
        {
            String otp=generateOTP();
            System.out.println("otpppp : "+otp);
            otpMap.put(userName,otp);
            System.out.println("otpMappp : "+otpMap);
            sendEmail(userName,otp);
        }
        return "Email Sent Successfully";
    }

    public String verifyOtp(String userName,String otp)
    {
        Employee employee1 =employeeRepository.getByUserName(userName);
        String storedOtp1 = otpMap.getOrDefault(userName, "");

        if(employee1!=null && (storedOtp1.isEmpty() || !storedOtp1.equals(otp)))
        {
            throw new IllegalArgumentException("Invalid OTP");
        }
        return "Otp Verified Successfully";
    }

    public String resetPassword(String userName, String otp, String password, String confirmPassword) {
        Employee employee=employeeRepository.getByUserName(userName);
        String storedOtp = otpMap.getOrDefault(userName, "");

        if (storedOtp.isEmpty() || !storedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        // Update the password and clear OTP
        if (otpMap.containsKey(userName))
        {
            if(password.equals(confirmPassword))
            {
                employee.setPassword(bCryptPasswordEncoder.encode(password));
                otpMap.remove(userName);
                employeeRepository.save(employee);
            }
            else {
                throw new IllegalArgumentException("Password and confirm Password do not matched");
            }
        } else {
            throw new IllegalArgumentException("OTP not found");
        }

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

