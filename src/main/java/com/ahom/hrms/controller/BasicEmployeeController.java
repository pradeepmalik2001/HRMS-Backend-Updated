package com.ahom.hrms.controller;

import com.ahom.hrms.serviceimpl.ReportXmlServiceImpl;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.service.BasicEmployeeService;
import com.ahom.hrms.util.PdfGenerator;
import com.lowagie.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
//@CrossOrigin
@RequestMapping("/basic")
public class BasicEmployeeController {

	@Autowired
	BasicEmployeeService basicEmployeeService;

	@Autowired
	ReportXmlServiceImpl reportxmlService;

	//save data
	@PostMapping("/saveemployee")
	public ResponseEntity<BasicEmployeeDto> saveEmployees(@RequestBody BasicEmployeeDto basicEmployeeDto){
		basicEmployeeService.saveEmployee(basicEmployeeDto);
		return new ResponseEntity<>(basicEmployeeDto, HttpStatus.CREATED);
	}

	//fetch data by id
	@GetMapping("/fetchemployee/{employeeid}")
	public ResponseEntity<BasicEmployeeDto> getEmployee(@PathVariable("employeeid") Integer employeeId){
		BasicEmployeeDto basicEmployeeDto = basicEmployeeService.employeeById(employeeId);
		ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(basicEmployeeDto);
	}
	@GetMapping("/export-to-pdf")
	public void getAll(HttpServletResponse response) throws DocumentException, IOException 
	{
		 response.setContentType("application/pdf");
		    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		    String currentDateTime = dateFormat.format(new Date());
		    String headerkey = "Content-Disposition";
		    String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
		    response.setHeader(headerkey, headervalue);
	        List<BasicEmployee> allEmployee = this.basicEmployeeService.getAllEmployee();
	        PdfGenerator generator = new PdfGenerator();
		    generator.generate(allEmployee, response);
	}
//	@PostMapping("/hra/{id}")
//	public double  hra(@PathVariable int id)
//	{
//		return basicEmployeeService.
//
//	}
@GetMapping("/formatof/{format}")
public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
	return reportxmlService.exportReport(format);
}
}
