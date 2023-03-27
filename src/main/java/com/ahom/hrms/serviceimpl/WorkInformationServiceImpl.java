package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.service.WorkInformationService;

import java.util.List;


@Service
public class WorkInformationServiceImpl implements WorkInformationService {

    @Autowired
    WorkInformationRepository workInformationRepository;
    
    @Autowired
    private BasicEmployeeRepository basicEmployeeRepository;

    @Autowired
    ModelMapper modelMapper;

    //save data
    public void saveWorkInfo(WorkInformationDto workInformationDto) throws Exception {
        workInformationRepository.save(workInformationDtoToWorkInformation(workInformationDto));
           
    }

    //converting DTO
    public WorkInformation workInformationDtoToWorkInformation(WorkInformationDto workInformationDto) throws Exception {
        WorkInformation workInformation = this.modelMapper.map(workInformationDto, WorkInformation.class);
        BasicEmployee basicEmployee=basicEmployeeRepository.findByEmployeeName(workInformationDto.getEmployeeName());
        if(basicEmployee!=null)
        {
        	workInformation.setBasicEmployee(basicEmployee);
        }else
        {
        	throw new Exception("employee not found!!");
        }
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
