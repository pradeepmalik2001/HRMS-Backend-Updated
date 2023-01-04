package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.EmployeeTrainingDto;
import com.ahom.hrms.entities.EmployeeTraining;


public interface TrainingToEmployeeService {

	void saveEmployee(EmployeeTrainingDto trainingToEmployeeDto);

	List<EmployeeTrainingDto> getAll();

	void updateEmployeeTraining(EmployeeTrainingDto employeeTraining, int id);

	void deleteEmployeeTraining(int id);

	EmployeeTraining dtotoEmp(EmployeeTrainingDto dto);

	EmployeeTrainingDto EmptoDto(EmployeeTraining traningToEmployee);

}
