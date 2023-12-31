package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.serviceimpl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/employees")
@CrossOrigin

public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee>save(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Employee>getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/byUserName")
    public ResponseEntity<Optional<Employee>>getByName(String userName){
        return new ResponseEntity<>(employeeService.findByUser(userName),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") String id) {
        return ResponseHandler.responseBuilder("Employee Record for ID:" + " " + id + " " + "is deleted successfully",
                HttpStatus.OK, employeeService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee,@PathVariable String id)
    {
        return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,employeeService.updateEmployee(employee,id));
    }
}
