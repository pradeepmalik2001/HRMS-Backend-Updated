package com.ahom.hrms.controller;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.BranchDto;
import com.ahom.hrms.entities.Branch;
import com.ahom.hrms.serviceimpl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
