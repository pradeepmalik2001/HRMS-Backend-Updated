package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

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

import com.ahom.hrms.dto.RaiseGrievancesDto;
import com.ahom.hrms.serviceimpl.RaiseGrievancesServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/RaiseGrievances")
public class RaiseGrievancesController {
	
	
	@Autowired
	RaiseGrievancesServiceImpl raiseGrievancesService;
	
	 @PostMapping("/post")
	    public ResponseEntity<RaiseGrievancesDto> saveEmp(@RequestBody RaiseGrievancesDto raiseGrievancesDto)
	    {
	        raiseGrievancesService.saveRaiseGrievances(raiseGrievancesDto);

	        return new ResponseEntity<>(raiseGrievancesDto, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/get")
	    public ResponseEntity<List<RaiseGrievancesDto>> getRaiseGrievances()
	    {
	        List<RaiseGrievancesDto> allRaiseGrievances = raiseGrievancesService.getAllRaiseGrievances();
	       
	        return ResponseEntity.of(Optional.of(allRaiseGrievances));

	    }
	 
//	 @DeleteMapping("/raiseGrievances/{raiseGrievancesId}")
//	    public void deleteEmp(@PathVariable("raiseGrievancesId") int id){
//	        raiseGrievancesService.deleteRaiseGrievances(id);
//	    }
	 
	 @PutMapping("/put")
	    public ResponseEntity<RaiseGrievancesDto> updateEmp(@RequestBody RaiseGrievancesDto raiseGrievancesDto)
	    {
		 raiseGrievancesService.updateRaiseGrievances(raiseGrievancesDto);
	        return new ResponseEntity<>(raiseGrievancesDto, HttpStatus.ACCEPTED);
	    }
	

}
