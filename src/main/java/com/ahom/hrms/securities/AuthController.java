package com.ahom.hrms.securities;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.entities.LeaveRecord;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.LeaveRecordService;
import com.ahom.hrms.serviceimpl.EmployeeService;

@RestController

public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JavaMailSender mailSender;
    @Value("${mail.from}")
    private String fromEmail; // Set from email address in application.properties file

    @Autowired
    private EmployeeService userService;

    @Autowired
    private LeaveRecordService leaveRecordService;

    @PostMapping("/login")
    public ResponseEntity<Object> createToken(@Valid @RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
       Employee user = userService.user(request.getUsername());
        if (user!=null) {
            response.setUserName(user.getEmployeeName());
            response.setRoles(user.getRoles());
            response.setId(user.getId());
            response.setEmail(user.getUsername());
            return ResponseHandler.responseBuilder("Login Successful", HttpStatus.OK,
                    response);
        }else
            throw new RuntimeException();
//        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        try {

            this.authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details !!");
            throw new RuntimeException("Invalid password !!");
        }

    }

     // register new user api

    @PostMapping("/signup")
        public ResponseEntity<Object> registerUser(@Valid @RequestBody Employee UserDTO) {
        Optional<Employee> employee=employeeRepository.findByUserName(UserDTO.getUsername());
        if (employee.isEmpty()) {
            if (UserDTO.getRoles()!=null) {

                if (UserDTO.getConfirmPassword().equals(UserDTO.getPassword())) {

//                    SimpleMailMessage messageToEmployee = new SimpleMailMessage();
//                    messageToEmployee.setFrom(fromEmail);
//                    messageToEmployee.setTo(UserDTO.getUsername());
//                    messageToEmployee.setSubject("Login Credentials");
//                    messageToEmployee.setText("Login Credentials for : " + UserDTO.getEmployeeName() + " "
//                            + "\n"
//                            + "\n"
//                            + "User Name = "
//                            + UserDTO.getUsername() + " " +
//                            " " +
//                            "\n" +
//                            "Password :" +
//                            " " + UserDTO.getConfirmPassword());
//                    mailSender.send(messageToEmployee);
//                    System.out.println(messageToEmployee);

                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
                    String currentMonth = currentDate.format(formatter);
                    currentMonth=currentMonth.toUpperCase();

                    Employee createUser = userService.saveEmployee(UserDTO);
                    LeaveRecord leaveRecord=new LeaveRecord();
                    leaveRecord.setCarryForward(0);
                    leaveRecord.setTotalLeave(0);
                    leaveRecord.setLeaveLeft(leaveRecord.getTotalLeave());
                    leaveRecord.setEmployeeId(UserDTO.getId());
                    leaveRecord.setEmployeeName(UserDTO.getEmployeeName());
                    leaveRecord.setLeaveRecordOfMonth(currentMonth);
                    leaveRecordService.saveLeave(leaveRecord);
                    return ResponseHandler.responseBuilder("Employee registered successfully",
                            HttpStatus.OK, createUser);
                }else
                    throw new RuntimeException("Password validation Failed");

            }
            else {
                throw new RuntimeException("Role is Mandatory");
            }
        }else {
            throw new CustomException("UserName already present");
        }
    }

// get logged user data
    @Autowired
    private EmployeeRepository userRepo;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/current-user/")
    public ResponseEntity<Employee> getUser(Principal principal) {
        Employee user = this.userRepo.findByUserName(principal.getName()).get();
        return new ResponseEntity<>(this.mapper.map(user, Employee.class), HttpStatus.OK);
    }

}

