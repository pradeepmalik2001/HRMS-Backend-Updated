package com.ahom.hrms.controller;import com.ahom.hrms.Response.ResponseHandler;import com.ahom.hrms.entities.SalarySlip;import com.ahom.hrms.service.SalarySlipService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.text.ParseException;import java.time.format.DateTimeFormatter;@RestController@RequestMapping("/salarySlip")@CrossOriginpublic class SalarySlipController {    @Autowired    SalarySlipService salarySlipService;    @PostMapping("/save")    public ResponseEntity<Object>save(@RequestParam String month) throws ParseException {        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM yyyy");        String format = String.format(month, formatter);        return ResponseHandler.responseBuilder("generated", HttpStatus.OK                ,salarySlipService.saveSalary(format));    }    @GetMapping("/export-to-excel/{month}")    public ResponseEntity<Object> exportToExcel(HttpServletResponse response,@PathVariable String month) throws IOException {        response.setContentType("application/octet-stream");        String headerKey = "Content-Disposition";        String headerValue = "attachment; filename=Salary_Information.xlsx";        response.setHeader(headerKey, headerValue);        return ResponseHandler.responseBuilder("Excel Created", HttpStatus.OK, salarySlipService.getAllSalary(response,month));    }    @GetMapping("/getAll")    public ResponseEntity<Object>getAll(){        return ResponseHandler.responseBuilder("fetch success",HttpStatus.OK,salarySlipService.getAll());    }    @PutMapping("/update/{id}")    public ResponseEntity<Object>updateSalary(@RequestBody SalarySlip salarySlip,@PathVariable int id){        return ResponseHandler.responseBuilder("update successfully",HttpStatus.OK,salarySlipService.                updateSalary(salarySlip,id));    }    @PostMapping("/getById")    public ResponseEntity<Object>getById(@RequestParam String employeeId,@RequestParam String month){        return ResponseHandler.responseBuilder("data fetch",HttpStatus.OK,salarySlipService.getDataForPdf(employeeId,month));    }}