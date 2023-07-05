package com.ahom.hrms.serviceimpl;import com.ahom.hrms.Helper.ExportDataCsv;import com.ahom.hrms.Repository.BankingInfoRepository;import com.ahom.hrms.Repository.BasicEmployeeRepository;import com.ahom.hrms.Repository.SalarySlipRepository;import com.ahom.hrms.dto.EmployeeLeaveCount;import com.ahom.hrms.entities.BankingInfo;import com.ahom.hrms.entities.SalarySlip;import com.ahom.hrms.service.BankingInfoService;import com.ahom.hrms.service.BasicEmployeeService;import com.ahom.hrms.service.SalarySlipService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.text.DecimalFormat;import java.text.ParseException;import java.util.ArrayList;import java.util.Calendar;import java.util.Date;import java.util.List;@Servicepublic class SalarySlipServiceImpl implements SalarySlipService {    @Autowired    BasicEmployeeService employeeService;    @Autowired    BankingInfoService bankingInfoService;    @Autowired    BasicEmployeeRepository employeeRepository;    @Autowired    BankingInfoRepository bankingInfoRepository;    @Autowired    SalarySlipRepository salarySlipRepository;    @Autowired    LeaveRecordServiceImpl leaveRecordService;    @Override    public List<SalarySlip> saveSalary(String month) throws ParseException {        List<BankingInfo> bankingInfo = bankingInfoRepository.findAll();        List<SalarySlip> listSalarySlip = new ArrayList<>();        Calendar calendar = Calendar.getInstance();        int currentYear = calendar.get(Calendar.YEAR);        int currentMonth = calendar.get(Calendar.MONTH);        DecimalFormat decimalFormat = new DecimalFormat("#.00");        Date date=new Date();        String newDate= String.valueOf(date);        for (BankingInfo info : bankingInfo) {            EmployeeLeaveCount byId = leaveRecordService.getById(info.getId());            SalarySlip salarySlip = new SalarySlip();            int workingDays = getWorkingDays(currentYear, month);            salarySlip.setWorkingDays(workingDays);            double luf = info.getGrossSalary() * 0.01;            double lopCount = byId.getLeaveCount();            double lossOfPay= Double.parseDouble(decimalFormat.format((info.getBasicSalary()/workingDays)* lopCount));            double gross= Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18+info.getGrossSalary() * 0.01+lossOfPay));            salarySlip.setMonthFor(month);            salarySlip.setLop(lopCount);            salarySlip.setEmployeeId(info.getId());            salarySlip.setName(info.getName());            salarySlip.setBankName(info.getBankName());            salarySlip.setBankAccountNumber(info.getBankAccountNo());            salarySlip.setDOB(info.getBasicEmployee1().getDob());            salarySlip.setEmailId(info.getBasicEmployee1().getEmail());            salarySlip.setPfNumber(info.getBasicEmployee1().getPfnumber());            salarySlip.setMobile(info.getBasicEmployee1().getMobile());            salarySlip.setBankBranch(info.getBankBranch());            salarySlip.setIfscCode(info.getIfscCode());            salarySlip.setPanNumber(info.getBasicEmployee1().getPanNumber());            salarySlip.setDepartment(info.getBasicEmployee1().getSelectDepartment());            salarySlip.setDesignation(info.getBasicEmployee1().getDesignation());            salarySlip.setDateOfJoining(info.getBasicEmployee1().getJoiningDate());            salarySlip.setCompany(info.getBasicEmployee1().getWhichCompany());            salarySlip.setCreatedOn(new Date());            salarySlip.setPaidDays(workingDays- lopCount);            double v = Double.parseDouble(decimalFormat.format(info.getGrossSalary()                    - (info.getBasicSalary() - info.getBasicSalary() * 0.40)));            double all = v / 2;            salarySlip.setBasicSalary(Double.parseDouble(decimalFormat.format(info.getBasicSalary())));            salarySlip.setHra(Double.parseDouble(decimalFormat.format(info.getBasicSalary() * 0.40)));            salarySlip.setConveyance(all);            salarySlip.setOtherAllowances(all);            // Calculation for deduction            salarySlip.setProvidentFund(Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18)));            salarySlip.setLuf(luf);            salarySlip.setLopPay(lossOfPay);            salarySlip.setGrossDeduction(gross);            // Calculation for Gross Earnings            salarySlip.setGrossEarning(Double.parseDouble(decimalFormat.format(info.getGrossSalary())));            salarySlip.setNetPay(Double.parseDouble(decimalFormat.format(info.getGrossSalary()-gross)));            salarySlipRepository.save(salarySlip);            listSalarySlip.add(salarySlip);        }        return listSalarySlip;    }    @Override    public List<SalarySlip> UpdateSalary(SalarySlip salarySlip) {        return null;    }    @Override    public List<SalarySlip> getAllSalary(HttpServletResponse response,String month) throws IOException {        List<SalarySlip> all = salarySlipRepository.findByMonthFor(month);        ExportDataCsv exportDataCsv=new ExportDataCsv(all);        exportDataCsv.exportDataToExcel(response);        return all;    }    @Override    public int getWorkingDays(int year, String month) {        // Convert the month string to the corresponding month value        int monthValue = parseMonth(month);        // Create a calendar object for the specified year and month        Calendar calendar = Calendar.getInstance();        calendar.set(Calendar.YEAR, year);        calendar.set(Calendar.MONTH, monthValue - 1);  // Note: Month is zero-based in Calendar class        // Get the maximum number of days for the specified month        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);        // Set the first day of the month        calendar.set(Calendar.DAY_OF_MONTH, 1);        // Iterate over the days of the month and count the working days        int workingDays = 0;//        for (int day = 1; day <= maxDays; day++) {//            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);//            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {//                workingDays++;//            }            calendar.add(Calendar.DAY_OF_MONTH, 1);  // Move to the next day//        }        return maxDays;    }    @Override    public int parseMonth(String month) {        switch (month.toLowerCase()) {            case "jan":                return 1;            case "feb":                return 2;            case "mar":                return 3;            case "apr":                return 4;            case "may":                return 5;            case "jun":                return 6;            case "jul":                return 7;            case "aug":                return 8;            case "sep":                return 9;            case "oct":                return 10;            case "nov":                return 11;            case "dec":                return 12;            default:                throw new IllegalArgumentException("Invalid month: " + month);        }    }    @Override    public List<SalarySlip> getAll() {        return salarySlipRepository.findAll();    }}