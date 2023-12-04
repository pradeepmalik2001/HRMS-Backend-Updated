package com.ahom.hrms.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.BranchDto;
import com.ahom.hrms.entities.Branch;
import com.ahom.hrms.serviceimpl.BranchServiceImpl;

@RestController
@RequestMapping("/branch")
@CrossOrigin
public class BranchController {

    @Autowired
    BranchServiceImpl branchService;


    @PostMapping("/save")
    public ResponseEntity<Object> saveBranch(@Valid @RequestBody BranchDto branchDto){
        return ResponseHandler.responseBuilder("Branch Saved Successfully",HttpStatus.OK,branchService.saveBranch(branchDto));
    }

    ResponseEntity<Object> entity(){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }


    @GetMapping("/fetchData")
    public ResponseEntity<Object> getBranch(){
        return ResponseHandler.responseBuilder("Fetched Successfully",HttpStatus.OK,branchService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBranch(@PathVariable String id)
    {
        return ResponseHandler.responseBuilder("Deleted Successfully",HttpStatus.OK,branchService.deleteBranch(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBranch(@Valid @RequestBody Branch branch,@PathVariable String id)
    {
        return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,branchService.updateBranch(branch, id));
    }
}
