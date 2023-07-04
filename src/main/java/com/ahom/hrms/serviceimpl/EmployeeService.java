package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Employee;
import com.google.firebase.auth.hash.Bcrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public Object delete(String id){

        Employee employee=employeeRepository.findById(id).orElse(null);
        if (employee==null)
        {throw new RuntimeException("No employee for found ID:"+" "+id);
        }else {
            employeeRepository.deleteById(id);
        }
        return employee;
    }

    public Employee updateEmployee(Employee employee,String id)
    {
        Employee employee1=employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found with "+employee.getId()));

        if(employee1!=null)
        {
            employee1.setEmployeeName(employee.getEmployeeName());
            employee1.setDepartmentName(employee.getDepartmentName());
            employee1.setUserName(employee.getUsername());
            employee1.setRoles(employee.getRoles());
            employee1.setPassword(employee.getPassword());
            employee1.setConfirmPassword(employee.getConfirmPassword());

            employeeRepository.save(employee1);
        }
        return employee;
    }

    public Employee updatePassword(String password)
    {
        Employee employee=new Employee();

        employee.setPassword(bCryptPasswordEncoder.encode(password));
//        employee.setConfirmPassword(confirmPassword);
        return employeeRepository.save(employee);
    }
}
