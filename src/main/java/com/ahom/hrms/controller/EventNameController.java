package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.EventNameDto;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.entities.EventName;
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
    public ResponseEntity<Object> saveEvent(@Valid @RequestBody EventNameDto eventNameDto){
        return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,eventNameService.saveEventName(eventNameDto));
    }

    @GetMapping("/fetchData")
    public ResponseEntity<Object> getEventName(){
        return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,eventNameService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable int id)
    {
        return ResponseHandler.responseBuilder("Data Deleted Successfully",HttpStatus.OK,eventNameService.deleteEvent(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEvent(@Valid @RequestBody EventName eventName,@PathVariable int id)
    {
        return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,eventNameService.updateEvent(eventName, id));
    }

}
