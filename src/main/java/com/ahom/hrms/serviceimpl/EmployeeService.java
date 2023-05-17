package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class EmployeeService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee){
       String encode= passwordEncoder.encode(employee.getPassword());
       employee.setPassword(encode);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
       return employeeRepository.findAll();
    }

    public Optional<Employee> findByUser(String userName){
        return employeeRepository.findByUserName(userName);
    }
}
