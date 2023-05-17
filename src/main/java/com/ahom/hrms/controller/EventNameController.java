package com.ahom.hrms.controller;

import com.ahom.hrms.dto.EventNameDto;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.serviceimpl.EventNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventNameController {

    @Autowired
    EventNameServiceImpl eventNameService;


    @PostMapping("/saveData")
    public ResponseEntity<EventNameDto> saveEvent(@Valid @RequestBody EventNameDto eventNameDto){
        eventNameService.saveEventName(eventNameDto);
        return new ResponseEntity<>(eventNameDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/fetchData")
    public List<EventNameDto> getEventName(){
        List<EventNameDto> allEventNameDto=eventNameService.getAll();
        return allEventNameDto;

    }
}
