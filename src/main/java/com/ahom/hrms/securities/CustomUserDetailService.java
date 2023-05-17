package com.ahom.hrms.securities;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.employeeRepository.findByUserName(username)
                .orElseThrow(() -> new CustomException("employee not found"));
    }
}
