package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Employee user(String userName){
        return employeeRepository.getByUserName(userName);
    }

    public Object delete(int id){

        Employee employee=employeeRepository.findById(id).orElse(null);
        if (employee==null)
        {throw new RuntimeException("No employee for found ID:"+" "+id);
        }else {
            employeeRepository.deleteById(id);
        }
        return employee;
    }
}
