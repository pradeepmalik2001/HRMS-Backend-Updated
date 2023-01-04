package com.ahom.hrms.serviceimpl;
import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.service.DeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahom.hrms.Repository.DeductionRepository;
import com.ahom.hrms.dto.DeductionDto;
import com.ahom.hrms.entities.Deduction;

@Service

public class DeductionServicesimpl implements DeductionService {
	@Autowired
	DeductionRepository deductionRepository;
	
	@Override
	public void saveDeduction(DeductionDto deductionDto) {
		
		deductionRepository.save(deductionDtoToDeduction(deductionDto));
	}
	@Override
	public void deleteDeduction(int id ) {
		
		deductionRepository.deleteById(id);
	}
	@Override
	public List<DeductionDto>getAllDeduction(){
		List<Deduction> deduction= this.deductionRepository.findAll();
		  List<DeductionDto> deductionDto  = deduction.stream().map(deduct ->this.deductionToDeductionDto(deduct)).collect(Collectors.toList());
		
		return deductionDto;
		
		
	}

	
	
	 public Deduction deductionDtoToDeduction(DeductionDto deductionDto)
	    {
		 Deduction deduction=new Deduction();
		 
		 deduction.setId(deductionDto.getId());
		 deduction.setDeduction(deductionDto.getDeduction());
		 deduction.setDescription(deductionDto.getDescription());
		 
		 return deduction;
	    }
	
	  public DeductionDto deductionToDeductionDto(Deduction deduction)
	  
	    {
         
		  DeductionDto deductionDto=new DeductionDto();
		  deductionDto.setId(deduction.getId());
		  deductionDto.setDeduction(deduction.getDeduction());
		  deductionDto.setDescription(deduction.getDescription());
		  
		  return deductionDto;
		  
	    
	    
	    }

	@Override
	public DeductionDto updateDeduction(DeductionDto deductionDto) { 
		deductionRepository.save(deductionDtoToDeduction(deductionDto));
	    return deductionDto;

	    }


}
	  


