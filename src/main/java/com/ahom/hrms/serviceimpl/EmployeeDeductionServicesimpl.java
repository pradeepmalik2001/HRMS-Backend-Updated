package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.service.EmployeeAllowancesService;
import com.ahom.hrms.service.EmployeeDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.EmployeeDeductionRepository;
import com.ahom.hrms.dto.EmployeeDeductionDto;
import com.ahom.hrms.entities.EmployeeDeduction;

@Service
public class EmployeeDeductionServicesimpl implements EmployeeDeductionService {
	
	
		
		
		@Autowired
		 EmployeeDeductionRepository employeeDeductionRepository;

		@Override
		public void saveEmp(EmployeeDeductionDto empDeductiondto) {
		      employeeDeductionRepository.save(employeeDeductionDtoToEmployeeDeduction(empDeductiondto));
		
		}
		@Override
		public List<EmployeeDeductionDto> getAllempdeduction (){
			List<EmployeeDeduction> listPayroll = this.employeeDeductionRepository.findAll();
			List<EmployeeDeductionDto> empDeductions = listPayroll.stream().map(emp -> this.employeeDeductionToemployeeDeductiondto(emp)).collect(Collectors.toList());
			
			return empDeductions;
			
		}
		    @Override
			public void deleteempdeduction(int Id) {
			
				employeeDeductionRepository.deleteById(Id);
		}
			
			public EmployeeDeduction savePayroll(EmployeeDeduction employeeDeduction) {
				return employeeDeductionRepository.save(employeeDeduction);
			}
			
			 public EmployeeDeductionDto payrollById(Integer Id)
			    {
			        EmployeeDeduction employeeDeduction = this.employeeDeductionRepository.findById(Id).get();
			        // Optional<Employee> byId = employeeReposatory.findById(employeeId);
			        return this.employeeDeductionToemployeeDeductiondto(employeeDeduction);


}
			 public EmployeeDeduction employeeDeductionDtoToEmployeeDeduction (EmployeeDeductionDto employeeDeductiodto) {
				 
				 
				 EmployeeDeduction emp=new EmployeeDeduction();
				 emp.setAmmount(employeeDeductiodto.getAmmount());
				 emp.setDateCreated(employeeDeductiodto.getDateCreated());
				 emp.setDeductionId(employeeDeductiodto.getDeductionId());
				 emp.setEffectiveDate(employeeDeductiodto.getEffectiveDate());

				 emp.setId(employeeDeductiodto.getId());
				 emp.setType(employeeDeductiodto.getType());
				 
				 return emp;				 
			 }

			 public EmployeeDeductionDto employeeDeductionToemployeeDeductiondto(EmployeeDeduction employeeDeduction) {
				 
				 EmployeeDeductionDto dedu=new EmployeeDeductionDto();
				 dedu.setAmmount(employeeDeduction.getAmmount());
				 dedu.setDateCreated(employeeDeduction.getDateCreated());
				 dedu.setDeductionId(employeeDeduction.getDeductionId());
				 dedu.setEffectiveDate(employeeDeduction.getEffectiveDate());
				 dedu.setId(employeeDeduction.getId());
				 dedu.setType(employeeDeduction.getType());
				 
				 
				 return dedu;
				 
			 }




}
