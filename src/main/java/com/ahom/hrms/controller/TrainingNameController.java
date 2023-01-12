package com.ahom.hrms.controller;

import com.ahom.hrms.dto.TrainingNameDto;
import com.ahom.hrms.service.TrainingNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/training")
@CrossOrigin
public class TrainingNameController {

    @Autowired
    TrainingNameService trainingNameService;




    @PostMapping("/save")
    public ResponseEntity<TrainingNameDto> saveTrainingName(@RequestBody TrainingNameDto trainingNameDto){
        trainingNameService.saveTrainingName(trainingNameDto);
        return new ResponseEntity<>(trainingNameDto, HttpStatus.ACCEPTED);
    }



    @GetMapping("/fetchdata")
    public List<TrainingNameDto> getTrainingName(){
        List<TrainingNameDto> allTrainingNameDto= trainingNameService.getAll();
        return allTrainingNameDto;
    }

}
