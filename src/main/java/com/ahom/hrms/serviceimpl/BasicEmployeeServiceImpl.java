package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BasicEmployeeDto;

import com.ahom.hrms.service.BasicEmployeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BasicEmployeeServiceImpl implements BasicEmployeeService{

	@Autowired
	BasicEmployeeRepository basicEmployeeRepository;
	@Autowired
	SalarySetupRepository salarySetupRepository;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail; // Set from email address in application.properties file

	//    @Value("${mail.hr}")
	String hrEmail="malikpradeep2001@gmail.com"; // Set HR email address in application.properties file

	@Value("${mail.subject}")
	private String emailSubject;

	//save data
	public Object saveEmployee(BasicEmployeeDto basicEmployeeDto) throws ParseException {


		BasicEmployee basicEmployee = basicEmployeeRepository.
				findByAadhaarNumberAndPanNumberAndPfnumberAndMobileAndEmail(
						basicEmployeeDto.getAadhaarNumber(),
						basicEmployeeDto.getPanNumber(),
						basicEmployeeDto.getPfnumber(),
						basicEmployeeDto.getMobile(),
						basicEmployeeDto.getEmail());

		if (basicEmployee == null) {

			basicEmployeeRepository.save(basicEmployeeDtoToBasicEmployee(basicEmployeeDto));
		} else {
			throw
					new RuntimeException("Found duplicate entry");
		}
		return basicEmployeeDto;
	}

	//fetch data by employee id
	public BasicEmployeeDto employeeById(int employeeId){
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(employeeId).orElse(null);
		return basicEmployeeToBasicEmployeeDto(basicEmployee);
	}


	public List<BasicEmployee> getAllEmployee() {

		return this.basicEmployeeRepository.findAll();
	}


	//converting DTO
	public BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto) throws ParseException {
		BasicEmployee basicEmployee=new BasicEmployee();
		basicEmployeeDto.setEmployeeId(basicEmployee.getEmployeeId());
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=dateFormat.parse(basicEmployeeDto.getJoiningDate());
		basicEmployee.setJoiningDate(String.valueOf(date));
		return this.modelMapper.map(basicEmployeeDto, BasicEmployee.class);
	}

	public BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee) {
		return this.modelMapper.map(basicEmployee, BasicEmployeeDto.class);
	}

	public List<BasicEmployee>details(int id){
		List<BasicEmployee>list=basicEmployeeRepository.findAllByDetails(id);
		System.out.println(list);
		List<BasicEmployee>filterData=new ArrayList<>();

		for (BasicEmployee basicEmployee:list) {
			filterData.add(basicEmployee);
			System.out.println(basicEmployee);

		}
		return filterData;
	}

	@Override
	public Object deleteEmployee(int id) {
		BasicEmployee basicEmployee=basicEmployeeRepository.findById(id).orElse(null);
		if (basicEmployee!=null) {

			basicEmployeeRepository.deleteById(id);
//			throw new RuntimeException("Employee with ID"+" " +id + " " + "& name" + " " +
//					basicEmployee.getEmployeeName()+ " " + " Deleted Successfully");
		}
		else {
			throw new RuntimeException("Employee with ID:" + id + " " + "not found");
		}
		return basicEmployee;
	}
	@Scheduled(cron = "00 33 18 * * ?")
	public void checkBirthday()
	{
		List<BasicEmployee> basicEmployee=basicEmployeeRepository.findAll();

		LocalDate localDate=LocalDate.now();

		for (BasicEmployee basicEmployee1:basicEmployee)
		{
			if(basicEmployee1.getDob().getMonthValue()==localDate.getMonthValue()&& basicEmployee1.getDob().getDayOfMonth()== localDate.getDayOfMonth())
			{
				SimpleMailMessage mailMessage=new SimpleMailMessage();

				mailMessage.setFrom(fromEmail);
				mailMessage.setTo(basicEmployee1.getEmail());
				mailMessage.setSubject(emailSubject);
				mailMessage.setText("Today is Birthday of : "+basicEmployee1.getEmployeeName());
				mailSender.send(mailMessage);
				System.out.println(mailMessage);
			}
		}
	}

//	public void checkBirthdayDate()
//	{
//		LocalDate localDate=LocalDate.now();
//		int currentDay= localDate.getDayOfMonth();
//		int currentMonth=localDate.getMonthValue();
//		List<BasicEmployee> basicEmployees= (List<BasicEmployee>) basicEmployeeRepository.findByDob(localDate.getMonth().);
//
//
//	}


}
