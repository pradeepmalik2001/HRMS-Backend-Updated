package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.TrainingEmployeeReposatory;
import com.ahom.hrms.entities.EmployeeTraining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.EmployeeTrainingDto;

import com.ahom.hrms.service.TrainingToEmployeeService;

@Service
public class TrainingToEmployeeServiceImpl implements TrainingToEmployeeService {
	
	@Autowired
	TrainingEmployeeReposatory employeeReposatory;
	
	
	@Override
	public void saveEmployee(EmployeeTrainingDto trainingToEmployeeDto) {
		employeeReposatory.save(dtotoEmp(trainingToEmployeeDto));
	}
	
	//Fetch data from database
	@Override
	public List<EmployeeTrainingDto> getAll(){
		List<EmployeeTraining> employeeTraining=this.employeeReposatory.findAll();
		List<EmployeeTrainingDto> employeeTrainingDto = employeeTraining.stream().map(deduct ->this.EmptoDto(deduct)).collect(Collectors.toList());
		
		return employeeTrainingDto;
	}
	
	
	//Update data from database By Id
	@Override
	public void updateEmployeeTraining(EmployeeTrainingDto employeeTraining,int id) {
	 EmployeeTraining employeeTraining2 = this.employeeReposatory.findById(id).get();
	 
	 employeeTraining2.setEmployee(employeeTraining.getEmployee());
	 employeeTraining2.setEventName(employeeTraining.getEventName());
	 employeeTraining2.setId(employeeTraining.getId());
	 employeeTraining2.setTrainingName(employeeTraining.getTrainingName());

	 employeeReposatory.save(employeeTraining2);

//		employeeTraining2.stream().map( b->{
//			
//			if(b.getId()==id) {
//				b.setEmployee(employeeTraining.getEmployee());
//				b.setEventName(employeeTraining.getEventName());
//				b.setId(employeeTraining.getId());
//				b.setTrainingName(employeeTraining.getTrainingName());
//			}
//			return b;
//				
//			}).collect(Collectors.toList());
		}
		//emp

	
	
	//Delete data from database
	@Override
	public void deleteEmployeeTraining(int id) {
	employeeReposatory.deleteById(id);
	}
	
    @Override
	public EmployeeTraining  dtotoEmp( EmployeeTrainingDto dto) 
	{
		EmployeeTraining employee = new EmployeeTraining();
		
		employee.setId(dto.getId());
		employee.setEventName(dto.getEventName());
		employee.setTrainingName(dto.getTrainingName());
		employee.setEmployee(dto.getEmployee());
		return employee;
		
		
	}
	
	@Override
	public EmployeeTrainingDto EmptoDto(EmployeeTraining traningToEmployee) {
		
		EmployeeTrainingDto employeeDto = new EmployeeTrainingDto();
		
		employeeDto.setId(traningToEmployee.getId());
		employeeDto.setEventName(traningToEmployee.getEventName());
		employeeDto.setTrainingName(traningToEmployee.getTrainingName());
		employeeDto.setEmployee(traningToEmployee.getEmployee());
		return employeeDto;
	}
	
	

}
