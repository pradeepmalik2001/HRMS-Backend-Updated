package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
import com.ahom.hrms.exception.CustomException;
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
    BasicEmployeeRepository basicEmployeeRepository;

    @Autowired
    ModelMapper modelMapper;

    //save data
    public void saveWorkInfo(WorkInformationDto workInformationDto) throws Exception {

            workInformationRepository.save(workInformationDtoToWorkInformation(workInformationDto));
           
    }

    //converting DTO
    public WorkInformation workInformationDtoToWorkInformation(WorkInformationDto workInformationDto) throws Exception {
        WorkInformation workInformation = this.modelMapper.map(workInformationDto, WorkInformation.class);
        BasicEmployee basicEmployee=basicEmployeeRepository.findById(workInformationDto.getWorkId()).orElse(null);
        WorkInformation workInformation1=workInformationRepository.findById(workInformationDto.getWorkId()).orElse(null);
        if(workInformation1==null)
        {
            if (basicEmployee!=null) {
                workInformation.setWorkId(workInformationDto.getWorkId());
                workInformation.setBasicEmployee(basicEmployee);
            }else {
                throw new CustomException("employee not found for particular Id" +" "+workInformation.getWorkId());
            }
        }else
        {
            throw new CustomException("Record for Id" + " " + workInformationDto.getWorkId()+" " + "is already present");
        }
        return workInformation;
    }

    public WorkInformationDto workInformationToWorkInformationDto(WorkInformation workInformation) {
        return this.modelMapper.map(workInformation, WorkInformationDto.class);
    }


    //fetch
    public List<WorkInformationDto> getAll() {
        List list=workInformationRepository.findAll();
        return list;
    }



}
