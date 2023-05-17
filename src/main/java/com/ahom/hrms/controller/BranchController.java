package com.ahom.hrms.controller;
import com.ahom.hrms.dto.BranchDto;
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
    public ResponseEntity<BranchDto> saveBranch(@Valid @RequestBody BranchDto branchDto){
        branchService.saveBranch(branchDto);
        return new ResponseEntity<>(branchDto, HttpStatus.ACCEPTED);
    }


    @GetMapping("/fetchData")
    public List<BranchDto> getBranch(){
        List<BranchDto> allBranchDto=branchService.getAll();
        return allBranchDto;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBranch(@PathVariable int id)
    {
        branchService.deleteBranch(id);
    }
}
