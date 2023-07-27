package com.ahom.hrms.controller;import com.ahom.hrms.Response.ResponseHandler;import com.ahom.hrms.entities.Deduction;import com.ahom.hrms.service.DeductionService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;@RestController@RequestMapping("/deduction")public class DeductionController {    @Autowired    DeductionService deductionService;    @PostMapping("/save")    public ResponseEntity<Object>save(@RequestBody Deduction deduction){        return ResponseHandler.responseBuilder("Saved", HttpStatus.OK,deductionService.save(deduction));    }    @GetMapping("/getAll")    public ResponseEntity<Object>getAll(){        return ResponseHandler.responseBuilder("data",HttpStatus.OK,deductionService.getAll());    }    @PutMapping("/update/{id}")    public ResponseEntity<Object>update(@RequestBody Deduction deduction,@PathVariable int id){        return ResponseHandler.responseBuilder("Completed",HttpStatus.OK,deductionService.update(deduction, id));    }    @DeleteMapping("/delete/{id}")    public ResponseEntity<Object>delete(@PathVariable int id){        return ResponseHandler.responseBuilder("deleted",HttpStatus.OK,deductionService.delete(id));    }}