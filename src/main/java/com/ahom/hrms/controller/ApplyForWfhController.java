package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.ApplyForWfh;
import com.ahom.hrms.service.ApplyForWfhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/WFH")
@CrossOrigin
public class ApplyForWfhController {
    @Autowired
    ApplyForWfhService applyForWfhService;

    @PostMapping("/save")
    public ResponseEntity<Object>save(@Valid @RequestBody ApplyForWfh applyForWfh){
        applyForWfh.setStatus("3");
        return ResponseHandler.responseBuilder
                ("Applied Successfully", HttpStatus.OK,applyForWfhService.save(applyForWfh));
    }

    @GetMapping("/getById")
    public ResponseEntity<Object>getById(@RequestParam String employeeId){
        return ResponseHandler.responseBuilder
                ("fetched",HttpStatus.OK,applyForWfhService.getByEmployeeId(employeeId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable int id){
        return ResponseHandler.responseBuilder("deleted",HttpStatus.OK,applyForWfhService.delete(id));
    }
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Object>update(@RequestBody ApplyForWfh applyForWfh,@PathVariable String employeeId){
        return ResponseHandler.responseBuilder
                ("updated",HttpStatus.OK,applyForWfhService.update(applyForWfh,employeeId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll()
    {
        return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,applyForWfhService.getAll());
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Object> updateStatus(@RequestBody ApplyForWfh applyForWfh,@PathVariable int id)
    {
        return ResponseHandler.responseBuilder("Data Updated Succesfully",HttpStatus.OK,applyForWfhService.updateStatus(applyForWfh,id));
    }
}
