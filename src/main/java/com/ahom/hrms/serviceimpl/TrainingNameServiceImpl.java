package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.TrainingNameRepository;
import com.ahom.hrms.dto.TrainingNameDto;
import com.ahom.hrms.entities.TrainingName;
import com.ahom.hrms.service.TrainingNameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingNameServiceImpl implements TrainingNameService {

    @Autowired
    TrainingNameRepository nameRepository;

   @Autowired
    ModelMapper modelMapper;



   @Override
   public TrainingName trainingNameDtoToTrainingName(TrainingNameDto trainingNameDto){

       TrainingName trainingName=this.modelMapper.map(trainingNameDto,TrainingName.class);
       return trainingName;
   }


   @Override
   public TrainingNameDto trainingNameToTrainingNameDto(TrainingName trainingName){

       TrainingNameDto trainingNameDto= this.modelMapper.map(trainingName,TrainingNameDto.class);
       return trainingNameDto;
   }


    //save data
    @Override
    public void saveTrainingName(TrainingNameDto trainingNameDto){
        nameRepository.save(trainingNameDtoToTrainingName(trainingNameDto));
    }



   //fetch data
    @Override
   public List<TrainingNameDto> getAll(){
       List list = nameRepository.findAll();
       return list;
   }


}
