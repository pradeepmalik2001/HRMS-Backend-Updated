package com.ahom.hrms.controller;import com.ahom.hrms.Response.ResponseHandler;import com.ahom.hrms.entities.Termination;import com.ahom.hrms.service.TerminationService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;@RestController@RequestMapping("/termination")@CrossOriginpublic class TerminationController {    @Autowired    TerminationService terminationService;    @PostMapping("/save")    public ResponseEntity<Object>save(@Valid @RequestBody Termination termination){        return ResponseHandler.responseBuilder("Terminated", HttpStatus.OK,terminationService.save(termination));    }}