package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.RaiseGrievancesRepository;
import com.ahom.hrms.entities.RaiseGrievances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.RaiseGrievancesDto;

import com.ahom.hrms.service.RaiseGrievancesService;

@Service
public class RaiseGrievancesServiceImpl implements RaiseGrievancesService{
	
	@Autowired
	RaiseGrievancesRepository raiseGrievancesRepository;
	
	
	@Override
	public void saveRaiseGrievances(RaiseGrievancesDto raiseGrievancesDto)
    {
		raiseGrievancesRepository.save(raiseGrievancesdtotoraiseGrievances(raiseGrievancesDto));

    }
	 
	
	@Override
	 public List<RaiseGrievancesDto> getAllRaiseGrievances(){

	        List<RaiseGrievances> listRaiseGrievances= this.raiseGrievancesRepository.findAll();

	        List<RaiseGrievancesDto> userDtoList = listRaiseGrievances.stream().map(emp -> this.raiseGrievancestoraiseGrievancesdto(emp)).collect(Collectors.toList());

	        return userDtoList;
	    }
	 
//	 public void deleteRaiseGrievances(int empId){
//
//	        raiseGrievancesRepository.deleteById(empId);
//
//	    }
//	 
	@Override
	 public RaiseGrievancesDto updateRaiseGrievances(RaiseGrievancesDto raiseGrievancesDto)
	    {
	    raiseGrievancesRepository.save(raiseGrievancesdtotoraiseGrievances(raiseGrievancesDto));
	    return raiseGrievancesDto;

	    }

	
	
	
	 public RaiseGrievances raiseGrievancesdtotoraiseGrievances(RaiseGrievancesDto raiseGrievancesDto) {
		 
		 RaiseGrievances raiseGrievances = new RaiseGrievances();
		 
		 raiseGrievances.setDescription(raiseGrievancesDto.getDescription());
		 raiseGrievances.setGrievanceType(raiseGrievancesDto.getGrievanceType());
		 raiseGrievances.setTitle(raiseGrievancesDto.getTitle());
		 raiseGrievances.setUploadAttechment(raiseGrievancesDto.getUploadAttechment());
		 
		return raiseGrievances;
		 
	 }
	 
	 public RaiseGrievancesDto raiseGrievancestoraiseGrievancesdto(RaiseGrievances raiseGrievances) {
		 
		 RaiseGrievancesDto raiseGrievancesDto = new RaiseGrievancesDto();
		 
		 raiseGrievancesDto.setDescription(raiseGrievances.getDescription());
		 raiseGrievancesDto.setGrievanceType(raiseGrievances.getGrievanceType());
		 raiseGrievancesDto.setTitle(raiseGrievances.getTitle());
		 raiseGrievancesDto.setUploadAttechment(raiseGrievances.getUploadAttechment());
		 
		return raiseGrievancesDto;
		 
		 
	 }

}
