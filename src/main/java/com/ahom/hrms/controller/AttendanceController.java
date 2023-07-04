package com.ahom.hrms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahom.hrms.Helper.Excel;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.AttendanceDto;
import com.ahom.hrms.serviceimpl.AttendanceServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    AttendanceServiceImpl attendanceService;


    @PostMapping("/save")
    public ResponseEntity<Object> saveEmp(@Valid @RequestBody AttendanceDto attendancedto) {
        attendancedto.setDate(new Date());
        return ResponseHandler.responseBuilder("Attendance uploaded", HttpStatus.OK,
                attendanceService.saveEmplAttendance(attendancedto));


    }


    @PostMapping("/upload")
    public ResponseEntity<Object> save(@Valid @RequestParam("file") MultipartFile file) throws IOException {

        if (Excel.checkFormat(file)) {
            try {
                Workbook workbook = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);

                // Identify the header row based on its content
                Row headerRow = sheet.getRow(0);
                if (headerRow == null) {
                    throw new RuntimeException("Header Row not found. Please upload Valid file");
                }

                String[] expectedColumns = {"selectEmployee", "date", "inTime", "outTime", "status", "userName"};
                for (int i = 0; i < expectedColumns.length; i++) {
                    Cell cell = headerRow.getCell(i);
                    if (cell == null || !cell.getStringCellValue().equals(expectedColumns[i])) {
                        throw new RuntimeException("Header Row is not Valid. Please upload Valid file");
                    }
                }

                // Iterate over the remaining rows and perform processing/validation
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }

                    String originalFilename = file.getOriginalFilename();
                    return ResponseHandler.responseBuilder("File Uploaded successfully", HttpStatus.OK,
                            attendanceService.saveExcel(file));


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Only Excel file Accepted");

    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> getEmplAttendance() {
        List<AttendanceDto> allEmployee = attendanceService.getAllEmplAttendance();
        return ResponseHandler.responseBuilder("Data Fetched Successfully", HttpStatus.OK, allEmployee);
    }

    @DeleteMapping("/Delete/{employeeId}")
    public ResponseEntity<Object> deleteEmp(@PathVariable("employeeId") int id) {
        return ResponseHandler.responseBuilder("Attendance deleted for ID:" + id
                , HttpStatus.OK, attendanceService.deleteAttendance(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateEmplAttendance(@RequestBody AttendanceDto attendancedto) {
        attendancedto.setDate(new Date());
        return ResponseHandler.responseBuilder("Data Updated Successfully", HttpStatus.OK, attendanceService.updateEmployeeAttendance(attendancedto));
    }

    @PostMapping("/byDate")
    @ResponseBody
    public ResponseEntity<List<Attendance>> ot(@RequestParam String startdate,
                                               @RequestParam String enddate,
                                               @RequestParam String name) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date stdate = format.parse(startdate);
        Date endate = format.parse(enddate);
        List<Attendance> Emplfetch = attendanceService.gteOt(stdate, endate, name);
        return new ResponseEntity<>(Emplfetch, HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<List<Attendance>> statusOfAttendance(@RequestParam String startdate,
                                                               @RequestParam String enddate,
                                                               @RequestParam String name,
                                                               @RequestParam String status) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(startdate);
        Date endDate = format.parse(enddate);
        List<Attendance> statusFetch = attendanceService.getByStatus(startDate, endDate, name, status);
        return new ResponseEntity<>(statusFetch, HttpStatus.ACCEPTED);
    }

    @PostMapping("/count")
    public ResponseEntity<List<Integer>> countAttendance(@RequestParam List<String> name,
                                                   @RequestParam List<String> userName,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDates = format.parse(startDate);
        Date endDates = format.parse(endDate);

        List<Integer> attendance = attendanceService.countAttendance(name,userName,startDates,endDates);
        return new ResponseEntity<>(attendance, HttpStatus.ACCEPTED);
    }

    @GetMapping("/statusOf")
    public ResponseEntity<List<Attendance>> statusOf(@RequestParam String name,
                                                     @RequestParam String userName,
                                                     @RequestParam String month
    )
            throws ParseException {
        return new ResponseEntity<>(attendanceService.status(name, userName, month), HttpStatus.OK);
    }


}
