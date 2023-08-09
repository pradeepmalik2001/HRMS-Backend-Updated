package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.LeaveRecordRepository;
import com.ahom.hrms.dto.EmployeeLeaveCount;
import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.CreateLeaveRequest;
import com.ahom.hrms.entities.LeaveRecord;
import com.ahom.hrms.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class LeaveScenarioService
{
    @Autowired
    AttendanceServiceImpl attendanceService;

    @Autowired
    CreateLeaveRequestServiceImpl createLeaveRequestService;

    @Autowired
    LeaveRecordServiceImpl leaveRecordService;

    @Autowired
    LeaveRecordRepository leaveRecordRepository;



    public double LeaveApproved(String name,String userName,String month,String employeeId) throws ParseException
    {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("MMMM yyyy")
                .toFormatter(Locale.ENGLISH);
        YearMonth userYearMonth = YearMonth.parse(month, formatter);

        LocalDate firstDayOfMonth = userYearMonth.atDay(1);

        LocalDate lastDayOfMonth = userYearMonth.atEndOfMonth();

        int weekendCount = 0;
        double officialHoliday=0;

        LocalDate currentDay = firstDayOfMonth;
        while (!currentDay.isAfter(lastDayOfMonth)) {
            // Check if the current day is Saturday or Sunday
            if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY || currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekendCount++;
            }
            currentDay = currentDay.plusDays(1);
        }
        System.out.println("weekend Day : "+weekendCount);

        String status="Present";
        double present = attendanceService.countAttendance(name, userName, month, status);

        String status1="WFH";
        double wfh= attendanceService.countAttendance(name,userName,month,status1);

        String status2="Absent";
        double absent= attendanceService.countAttendance(name,userName,month,status2);

        double approvedLeave = createLeaveRequestService.countApprovedLeave(employeeId,month);

        EmployeeLeaveCount leaveCount = leaveRecordService.getById(employeeId, month);
        double leaveCount1 = leaveCount.getLeaveCount();
        int present1=0;
        LeaveRecord byEmployeeId = leaveRecordRepository.findByEmployeeIdAndLeaveRecordOfMonth(employeeId,month);

        List<CreateLeaveRequest> createLeaveRequests = createLeaveRequestService.ApprovedLeave(employeeId, month);
        for (CreateLeaveRequest createLeaveRequest:createLeaveRequests)
        {
            String date = String.valueOf(createLeaveRequest.getDate());
            System.out.println("Dateee : "+date);
            List<Attendance> status3 = attendanceService.dataFor(name, userName, month);

            for (Attendance attendance:status3)
            {
                String date1 = String.valueOf(attendance.getDate());
                if(date1.equals(date))
                {
                    if(attendance.getStatus().equals("Present"))
                    {
                        ++present1;
                    }
                }
            }
            System.out.println("Value of Present : "+present1);
            System.out.println("status 3 : "+status3.size());

        }
        double v = byEmployeeId.getLop() - present1;

        System.out.println("Present : "+present);
        System.out.println("WeekendCount : "+weekendCount);
        System.out.println("ApprovedLeave : "+approvedLeave);
        System.out.println("LeaveCount1 : "+leaveCount1);
        System.out.println("vvvvvvv : "+v);
//        double case1=present+weekendCount+approvedLeave-leaveCount1;
//        if (present1>=approvedLeave) {
//            double case2 = present + weekendCount + approvedLeave - present1;
//            return case2;
//        }
        double case2 = present + weekendCount + approvedLeave - present1 - v+wfh;
//        double case3=present+weekendCount+approvedLeave-present1-leaveCount1;


        return case2;
    }
}
