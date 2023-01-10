package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.entities.WorkInformation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.service.WorkInformationService;

import java.util.List;


@Service
public class WorkInformationServiceImpl implements WorkInformationService {

    @Autowired
    WorkInformationRepository workInformationRepository;

    @Autowired
    ModelMapper modelMapper;

    //save data
    public void saveWorkInfo(WorkInformationDto workInformationDto) {
        workInformationRepository.save(workInformationDtoToWorkInformation(workInformationDto));
    }

    //converting DTO
    public WorkInformation workInformationDtoToWorkInformation(WorkInformationDto workInformationDto) {
        WorkInformation workInformation = this.modelMapper.map(workInformationDto, WorkInformation.class);
        return workInformation;
    }

    public WorkInformationDto workInformationToWorkInformationDto(WorkInformation workInformation) {
        WorkInformationDto workInformationDto = this.modelMapper.map(workInformation, WorkInformationDto.class);
        return workInformationDto;
    }


    //fetch
    public List<WorkInformationDto> getAll() {
        List list=workInformationRepository.findAll();
        return list;
    }



}
