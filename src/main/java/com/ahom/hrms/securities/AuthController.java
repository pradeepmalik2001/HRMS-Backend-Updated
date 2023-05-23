package com.ahom.hrms.securities;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.serviceimpl.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
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

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

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

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
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


//            SimpleMailMessage messageToEmployee = new SimpleMailMessage();
//            messageToEmployee.setFrom(fromEmail);
//            messageToEmployee.setTo(UserDTO.getUsername());
//            messageToEmployee.setSubject("Login Credentials");
//            messageToEmployee.setText("Login Credentials for : " + UserDTO.getEmployeeName()+ " "
//                    + "\n"
//                    +"\n"
//                    +"User Name = "
//                    + UserDTO.getUsername() +" " +
//                    " "+
//                    "\n" +
//                    "Password :" +
//                    " " + UserDTO.getConfirmPassword());
//            mailSender.send(messageToEmployee);
//            System.out.println(messageToEmployee);

                Employee createUser = userService.saveEmployee(UserDTO);
                return ResponseHandler.responseBuilder("Employee registered successfully",
                        HttpStatus.OK, createUser);
            }
            else {
                throw new RuntimeException("Role is Mandatory");
            }
        }else {
            throw new RuntimeException("UserName already existed");
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

