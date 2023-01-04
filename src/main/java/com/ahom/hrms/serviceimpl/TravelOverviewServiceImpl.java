package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.TravelOverviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.TravelOverviewDto;
import com.ahom.hrms.entities.TravelOverview;
import com.ahom.hrms.service.TravelOverviewService;

@Service
public class TravelOverviewServiceImpl implements TravelOverviewService  {
	
	@Autowired
	TravelOverviewRepository travelOverviewRepository;
	
	@Override
	public void saveTravelOverview(TravelOverviewDto travelOverviewDto)
    {
        travelOverviewRepository.save(travelOverviewdtototravelOverview(travelOverviewDto));

    }
	
	@Override
	 public List<TravelOverviewDto> getAllTravelOverview(){

	        List<TravelOverview> listTravelOverview= this.travelOverviewRepository.findAll();

	        List<TravelOverviewDto> userDtoList = listTravelOverview.stream().map(emp -> this.travelOverviewtotravelOverviewdto(emp)).collect(Collectors.toList());

	        return userDtoList;
	    }
	 
//	    public void deleteTravelOverview(int empId){
//
//	        travelOverviewRepository.deleteById(empId);
//
//	    }
	    
	@Override
	    public TravelOverviewDto updateTravelOverview(TravelOverviewDto travelOverviewDto)
	    {
	    travelOverviewRepository.save(travelOverviewdtototravelOverview(travelOverviewDto));
	    return travelOverviewDto;

	    }

	
	
	public TravelOverview travelOverviewdtototravelOverview(TravelOverviewDto travelOverviewDto) {
		
		TravelOverview travelOverview = new TravelOverview();
		
		travelOverview.setBranchMaster(travelOverviewDto.getBranchMaster());
		travelOverview.setSearchBranchName(travelOverviewDto.getSearchBranchName());
		
		return travelOverview;
		
	}
	
	public  TravelOverviewDto travelOverviewtotravelOverviewdto(TravelOverview travelOverview) {
		
		TravelOverviewDto travelOverviewDto = new TravelOverviewDto();
		
		travelOverviewDto.setBranchMaster(travelOverview.getBranchMaster());
		travelOverviewDto.setSearchBranchName(travelOverview.getSearchBranchName());
		
		return travelOverviewDto;
		
		
	}

}
