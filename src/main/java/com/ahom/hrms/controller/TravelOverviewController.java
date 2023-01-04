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

import com.ahom.hrms.dto.TravelOverviewDto;
import com.ahom.hrms.serviceimpl.TravelOverviewServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/TravelOverview")
public class TravelOverviewController {
	
	@Autowired
	TravelOverviewServiceImpl travelOverviewService;
	
	@PostMapping("/post")
    public ResponseEntity<TravelOverviewDto> saveEmp(@RequestBody TravelOverviewDto travelOverviewDto)
    {
		travelOverviewService.saveTravelOverview(travelOverviewDto);

        return new ResponseEntity<>(travelOverviewDto, HttpStatus.CREATED);
    }
	
	 @GetMapping("/get")
	    public ResponseEntity<List<TravelOverviewDto>> getTravelOverview()
	    {
	        List<TravelOverviewDto> allTravelOverview = travelOverviewService.getAllTravelOverview();

	        return ResponseEntity.of(Optional.of(allTravelOverview));

	    }
	 
//	 @DeleteMapping("/travelOverview/{travelOverviewId}")
//	    public void deleteEmp(@PathVariable("travelOverviewId") int id){
//	        travelOverviewService.deleteTravelOverview(id);
//	    }
	 
	 @PutMapping("/put")
	    public ResponseEntity<TravelOverviewDto> updateEmp(@RequestBody TravelOverviewDto travelOverviewDto)
	    {
		 travelOverviewService.updateTravelOverview(travelOverviewDto);
	        return new ResponseEntity<>(travelOverviewDto, HttpStatus.ACCEPTED);
	    }


}
