package com.ahom.hrms.serviceimpl;
import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.service.AllowancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahom.hrms.Repository.AllowancesRepository;
import com.ahom.hrms.dto.AllowancesDto;

import com.ahom.hrms.entities.Allowances;

@Service
public class AllowancesServicesimpl implements AllowancesService {
	
	@Autowired
	private AllowancesRepository allowancesRepository;
	@Override
	public void saveAllowances(AllowancesDto allowancesDto)
	{
	 
		allowancesRepository.save(allowancesDtoToAllowances(allowancesDto));
	 
	 	 		
	}
	@Override
	public void deleteAllowances(int id) {
		
		allowancesRepository.deleteById(id);
	}
	@Override
	public void updateAllowances(AllowancesDto allowancesDto) {
		
		allowancesRepository.save(allowancesDtoToAllowances(allowancesDto));
	}
	@Override
	public List<AllowancesDto> getAllAllowances(){
		   List<Allowances> listAllowance= this.allowancesRepository.findAll();
		
		   List<AllowancesDto> allowancesdto  = listAllowance.stream().map(allow ->this.allowancesToAllowancesDto(allow)).collect(Collectors.toList());
		
		return allowancesdto;
		
	}
	
	  public Allowances allowancesDtoToAllowances(AllowancesDto allowancesDto)
	    {
	        Allowances allowances=new Allowances();

	       allowances.setId(allowancesDto.getId());
	       allowances.setAllowancesDescription(allowancesDto.getAllowancesDescription());
	        return allowances;
	    }

	    public AllowancesDto allowancesToAllowancesDto(Allowances allowances)
	    {
	        AllowancesDto allowancesDto= new AllowancesDto();

	        allowancesDto.setId(allowancesDto.getId());

	        allowancesDto.setAllowancesDescription(allowancesDto.getAllowancesDescription());
	        	     
	        return allowancesDto;
	    }



	    



	
	

}
