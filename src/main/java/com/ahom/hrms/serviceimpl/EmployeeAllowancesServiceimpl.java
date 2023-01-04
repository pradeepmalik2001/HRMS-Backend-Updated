package com.ahom.hrms.serviceimpl;




import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.service.EmployeeAllowancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.EmployeeAllowancesRepository;

import com.ahom.hrms.dto.EmployeeAllowancesDto;

import com.ahom.hrms.entities.EmployeeAllowances;

@Service
public class EmployeeAllowancesServiceimpl implements EmployeeAllowancesService {
	
	@Autowired
	EmployeeAllowancesRepository employeeAllowancesRepository;
	
	@Override
	public void saveEmployeeAllowances(EmployeeAllowancesDto employeeAllowancesDto) {
		employeeAllowancesRepository.save(employeeAllowancesDtoToEmployeeAllowances(employeeAllowancesDto));
	
	}
	@Override
	public List<EmployeeAllowancesDto> getAllEmployeeAllowances (){
		List<EmployeeAllowances> listEmployeeAllowances = this.employeeAllowancesRepository.findAll();
		List<EmployeeAllowancesDto> employeeAllowanceDtoList = listEmployeeAllowances.stream().map(emp -> this.employeeAllowancesToEmployeeAllowancesDto(emp)).collect(Collectors.toList());
		
		return employeeAllowanceDtoList;
		
	}
	@Override
	public void deleteEmployeeAllowance(int Id) {
		
			employeeAllowancesRepository.deleteById(Id);
	}
		
		public EmployeeAllowances saveEmployeeAllowances(EmployeeAllowances employeeAllowances) {
			return employeeAllowancesRepository.save(employeeAllowances);
		}
		@Override
		 public EmployeeAllowancesDto employeeAllowancesById(Integer Id)
		    {
		        EmployeeAllowances employeeAllowances = this.employeeAllowancesRepository.findById(Id).get();
		        // Optional<Employee> byId = employeeReposatory.findById(employeeId);
		        return this.employeeAllowancesToEmployeeAllowancesDto(employeeAllowances);

		    }


	
	
	
	
	
	public EmployeeAllowances employeeAllowancesDtoToEmployeeAllowances(EmployeeAllowancesDto employeeAllowancesDto) {
		
		EmployeeAllowances employeeAllowances = new EmployeeAllowances();
		employeeAllowances.setId(employeeAllowancesDto.getId());
		employeeAllowances.setAmmount(employeeAllowancesDto.getAmmount());
		employeeAllowances.setDateCreated(employeeAllowancesDto.getDateCreated());
		employeeAllowances.setEffectiveDate(employeeAllowancesDto.getEffectiveDate());
		employeeAllowances.setEmployeeId(employeeAllowancesDto.getEmployeeId());
		employeeAllowances.setType(employeeAllowancesDto.getType());
		
		return employeeAllowances;
		
	}
	
	
	public EmployeeAllowancesDto employeeAllowancesToEmployeeAllowancesDto(EmployeeAllowances employeeAllowances) {
		
		EmployeeAllowancesDto employeeAllowancesDto = new EmployeeAllowancesDto();
		employeeAllowancesDto.setId(employeeAllowances.getId());
		employeeAllowancesDto.setAmmount(employeeAllowances.getAmmount());
		employeeAllowancesDto.setDateCreated(employeeAllowances.getDateCreated());
		employeeAllowancesDto.setEffectiveDate(employeeAllowances.getEffectiveDate());
		employeeAllowancesDto.setEmployeeId(employeeAllowances.getEmployeeId());
		employeeAllowancesDto.setType(employeeAllowances.getType());
		
		return employeeAllowancesDto;
		
	}

}
