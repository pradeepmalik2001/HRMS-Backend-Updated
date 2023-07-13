package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.TrainingNameDto;
import com.ahom.hrms.entities.TrainingName;
import com.ahom.hrms.service.TrainingNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainingName")
@CrossOrigin
public class TrainingNameController {

    @Autowired
    TrainingNameService trainingNameService;




    @PostMapping("/save")
    public ResponseEntity<Object> saveTrainingName(@Valid @RequestBody TrainingNameDto trainingNameDto){
        return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,trainingNameService.saveTrainingName(trainingNameDto));
    }



    @GetMapping("/fetchdata")
    public ResponseEntity<Object> getTrainingName(){
        return ResponseHandler.responseBuilder("Data Fecthed Successfully",HttpStatus.OK,trainingNameService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTraining(@PathVariable int id)
    {
        return ResponseHandler.responseBuilder("Data Deleted Successfully",HttpStatus.OK,trainingNameService.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTraining(@Valid @RequestBody TrainingName trainingName,@PathVariable int id)
    {
        return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,trainingNameService.updateTraining(trainingName, id));
    }

}
