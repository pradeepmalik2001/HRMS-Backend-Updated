package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.LeaveRecordRepository;
import com.ahom.hrms.dto.EmployeeLeaveCount;
import com.ahom.hrms.entities.CreateLeaveRequest;
import com.ahom.hrms.entities.LeaveRecord;
import com.ahom.hrms.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveRecordServiceImpl implements LeaveRecordService
{
    @Autowired
    LeaveRecordRepository leaveRecordRepository;

    CreateLeaveRequest createLeaveRequest;

    @Override
    public LeaveRecord saveLeave(LeaveRecord leaveRecord)
    {

        return leaveRecordRepository.save(leaveRecord);
    }

    @Override
    public LeaveRecord updateLeaveRecord(LeaveRecord leaveRecord, int id) {
        leaveRecordRepository.save(leaveRecord);
        return null;
    }

    @Scheduled(cron = "0 40 17 6 * *")
    public void myScheduledMethod() {
        List<LeaveRecord> leaveRecords = leaveRecordRepository.findAll();

        LocalDate localDate=LocalDate.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("MMMM yyyy");
        String format = localDate.format(dateTimeFormatter);

        int currentMonth = localDate.getMonthValue();
        int currentYear = localDate.getYear();

        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);

        String startDate1= createLeaveRequest.getStartDate();
        String lastDayofMonth= String.valueOf(yearMonth.lengthOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDate1, formatter);
        LocalDate endDate = LocalDate.parse(lastDayofMonth, formatter);

        long daysInBetween= ChronoUnit.DAYS.between(startDate,endDate);
        long difference = daysInBetween + 1;

        for (LeaveRecord leaveRecord : leaveRecords)
        {
               if(leaveRecord.getCarryForward()<=0)
               {
                   LeaveRecord leaveRecord1=new LeaveRecord();
                   double totalLeaveRecord= leaveRecord.getLeaveLeft()+1.5;
                   leaveRecord1.setTotalLeave(totalLeaveRecord);
                   leaveRecord1.setLeaveTaken(0);
                   leaveRecord1.setLeaveLeft(leaveRecord.getLeaveLeft()+1.5);
                   leaveRecord1.setLeaveRecordOfMonth(format);
                   leaveRecord1.setLop(0);
                   leaveRecord1.setCarryForward(0);
                   leaveRecord1.setCL(leaveRecord.getCL()+1);
                   leaveRecord1.setPL(leaveRecord.getPL()+0.5);
                   leaveRecordRepository.save(leaveRecord1);
               }
           else
           {
               LeaveRecord leaveRecord1=new LeaveRecord();
               double totalLeaveRecord= leaveRecord.getLeaveLeft()+1.5;
               leaveRecord1.setTotalLeave(totalLeaveRecord);
               leaveRecord1.setLop(leaveRecord.getCarryForward());
               leaveRecord1.setCarryForward(0);
//               leaveRecord1.setLeaveTaken();
               leaveRecord1.setLeaveLeft(leaveRecord.getLeaveLeft()+1.5);
               leaveRecord1.setLeaveRecordOfMonth(format);
               leaveRecordRepository.save(leaveRecord1);
           }
        }
        System.out.println("Method called on the 1st of every month.");
    }
    public List<EmployeeLeaveCount> getAllEmployeesLeaveCount() {
        List<LeaveRecord> leaveRecords = leaveRecordRepository.findAll();
        Map<String, Double> leaveCountMap = new HashMap<>();

        for (LeaveRecord leaveRecord : leaveRecords) {
            String employeeName = leaveRecord.getEmployeeName();
            double leaveTaken = leaveRecord.getLop();

            if (leaveCountMap.containsKey(employeeName)) {
                double existingLeaveCount = leaveCountMap.get(employeeName);
                double updatedLeaveCount = existingLeaveCount + leaveTaken;
                leaveCountMap.put(employeeName, updatedLeaveCount);
            } else {
                leaveCountMap.put(employeeName, leaveTaken);
            }
        }

        List<EmployeeLeaveCount> employeesLeaveCount = new ArrayList<>();

        for (Map.Entry<String, Double> entry : leaveCountMap.entrySet()) {
            String employeeName = entry.getKey();
            double leaveCount = entry.getValue();
            employeesLeaveCount.add(new EmployeeLeaveCount(employeeName, leaveCount));
        }

        return employeesLeaveCount;
    }

    public EmployeeLeaveCount getById(String employeeId,String leaveRecordOfMonth) {

        LeaveRecord byId = leaveRecordRepository.findByEmployeeIdAndLeaveRecordOfMonth(employeeId, leaveRecordOfMonth);
        if (byId != null) {
            EmployeeLeaveCount employeeLeaveCount = new EmployeeLeaveCount();
            employeeLeaveCount.setEmployeeName(byId.getEmployeeName());
            employeeLeaveCount.setLeaveCount(byId.getLop());
            return employeeLeaveCount;
        }
        else throw new RuntimeException("Record for EmployeeId: "+employeeId + " for Month:"+leaveRecordOfMonth+" "+"not available");
    }

}
