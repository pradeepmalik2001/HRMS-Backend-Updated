package com.ahom.hrms.serviceimpl;import com.ahom.hrms.Helper.ExportDataCsv;import com.ahom.hrms.Repository.BankingInfoRepository;import com.ahom.hrms.Repository.SalarySlipRepository;import com.ahom.hrms.dto.EmployeeLeaveCount;import com.ahom.hrms.entities.AdvanceSalary;import com.ahom.hrms.entities.BankingInfo;import com.ahom.hrms.entities.BasicEmployee;import com.ahom.hrms.entities.SalarySlip;import com.ahom.hrms.service.*;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.text.DecimalFormat;import java.text.ParseException;import java.text.SimpleDateFormat;import java.time.LocalDate;import java.time.YearMonth;import java.time.format.DateTimeFormatter;import java.util.*;@Servicepublic class SalarySlipServiceImpl implements SalarySlipService {    @Autowired    BankingInfoRepository bankingInfoRepository;    @Autowired    SalarySlipRepository salarySlipRepository;    @Autowired    LeaveRecordServiceImpl leaveRecordService;    @Autowired    AdvanceSalaryService advanceSalaryService;    @Autowired    OverTimeService overTimeService;    @Override    public List<SalarySlip> saveSalary(String month) throws ParseException {        List<BankingInfo> bankingInfo = bankingInfoRepository.findAll();        List<SalarySlip> listSalarySlip = new ArrayList<>();        DecimalFormat decimalFormat = new DecimalFormat("#.00");        YearMonth currentYearMonth = YearMonth.now();        int maxDayOfMonth = currentYearMonth.lengthOfMonth();        System.out.println("year"+ maxDayOfMonth);        Date date=new Date();        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        String newDate= dateFormat.format(date);        LocalDate currentDate = LocalDate.now();        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");        String currentMonth = currentDate.format(formatter);        currentMonth=currentMonth.toUpperCase();        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("MMMM");        String forMonth=currentDate.format(formatter1);        for (BankingInfo info : bankingInfo) {//            System.out.println("Email of employeeeeeee" + info.getBasicEmployee1().getEmail());            int byUserNameAndMonth = overTimeService.                    getByUserNameAndMonth(currentMonth,                            info.getBasicEmployee1().getEmail());            AdvanceSalary advance;            try {                advance = advanceSalaryService.findByEmployeeId(info.getId());            } catch (Exception e) {                throw new RuntimeException(e);            }            if (advance != null && advance.getRemainingAdvance() >= 0) {                advanceSalaryService.updateSalary(advance, info.getId());                EmployeeLeaveCount byId = leaveRecordService.getById(info.getId());                SalarySlip salarySlip = new SalarySlip();                salarySlip.setWorkingDays(maxDayOfMonth);                double luf = info.getGrossSalary() * 0.01;                double lopCount = byId.getLeaveCount();                double lossOfPay = Double.parseDouble(decimalFormat.format((info.getBasicSalary() / maxDayOfMonth) * lopCount));                double advanceDeductionPerMonth = Double.parseDouble(decimalFormat.format(advance.getAmountToPayPerMonth()));                double gross = Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18 + info.getGrossSalary() * 0.01 + lossOfPay + advanceDeductionPerMonth));                double overTimePay = Double.parseDouble(decimalFormat.format(((info.getGrossSalary()) / (8 * 60 * 30)) * byUserNameAndMonth));                System.out.println("overtime" + overTimePay);                salarySlip.setMonthFor(forMonth);                salarySlip.setLop(lopCount);                salarySlip.setEmployeeId(info.getId());                salarySlip.setName(info.getName());                salarySlip.setBankName(info.getBankName());                salarySlip.setBankAccountNumber(info.getBankAccountNo());                salarySlip.setDOB(String.valueOf(info.getBasicEmployee1().getDob()));                salarySlip.setEmailId(info.getBasicEmployee1().getEmail());                salarySlip.setPfNumber(info.getBasicEmployee1().getPfnumber());                salarySlip.setMobile(info.getBasicEmployee1().getMobile());                salarySlip.setBankBranch(info.getBankBranch());                salarySlip.setIfscCode(info.getIfscCode());                salarySlip.setPanNumber(info.getBasicEmployee1().getPanNumber());                salarySlip.setDepartment(info.getBasicEmployee1().getSelectDepartment());                salarySlip.setDesignation(info.getBasicEmployee1().getDesignation());                salarySlip.setDateOfJoining(info.getBasicEmployee1().getJoiningDate());                salarySlip.setCompany(info.getBasicEmployee1().getWhichCompany());                salarySlip.setCreatedOn(newDate);                salarySlip.setPaidDays(maxDayOfMonth - lopCount);                salarySlip.setOverTime((double) byUserNameAndMonth / 60);                double v = Double.parseDouble(decimalFormat.format(info.getGrossSalary()                        - (info.getBasicSalary() - info.getBasicSalary() * 0.40)));                double all = v / 2;                salarySlip.setBasicSalary(Double.parseDouble(decimalFormat.format(info.getBasicSalary())));                salarySlip.setHra(Double.parseDouble(decimalFormat.format(info.getBasicSalary() * 0.40)));                salarySlip.setConveyance(all);                salarySlip.setOtherAllowances(all);                // Calculation for deduction                salarySlip.setProvidentFund(Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18)));                salarySlip.setLuf(luf);                salarySlip.setLopPay(lossOfPay);                salarySlip.setAdvance(advance.getAmountToPayPerMonth());                salarySlip.setGrossDeduction(gross);                // Calculation for Gross Earnings                salarySlip.setGrossEarning(Double.parseDouble(decimalFormat                        .format(info.getGrossSalary())));                salarySlip.setOverTimePay(overTimePay);                salarySlip.setNetPay(Double.parseDouble(decimalFormat                        .format(info.getGrossSalary() - gross + overTimePay)));//                advance.setRemainingAdvance(advance.getRemainingAdvance()-advance.getAmountToPayPerMonth());                salarySlipRepository.save(salarySlip);                if (advance.getRemainingAdvance() < advance.getAmountToPayPerMonth()) {                    advance.setRemainingAdvance(advance.getRemainingAdvance());                    advance.setAmountToPayPerMonth(advance.getRemainingAdvance());                    advance.setStatus(true);                    advanceSalaryService.updateRemainingAdavnceMonth(advance);                }                listSalarySlip.add(salarySlip);            } else {                EmployeeLeaveCount byId = leaveRecordService.getById(info.getId());                SalarySlip salarySlip = new SalarySlip();                salarySlip.setWorkingDays(maxDayOfMonth);                double luf = info.getGrossSalary() * 0.01;                double lopCount = byId.getLeaveCount();                double lossOfPay = Double.parseDouble(decimalFormat.format((info.getBasicSalary() / maxDayOfMonth) * lopCount));                double gross = Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18 + info.getGrossSalary() * 0.01 + lossOfPay));                salarySlip.setMonthFor(forMonth);                salarySlip.setLop(lopCount);                salarySlip.setEmployeeId(info.getId());                salarySlip.setName(info.getName());                salarySlip.setBankName(info.getBankName());                salarySlip.setBankAccountNumber(info.getBankAccountNo());                salarySlip.setDOB(String.valueOf(info.getBasicEmployee1().getDob()));                salarySlip.setEmailId(info.getBasicEmployee1().getEmail());                salarySlip.setPfNumber(info.getBasicEmployee1().getPfnumber());                salarySlip.setMobile(info.getBasicEmployee1().getMobile());                salarySlip.setBankBranch(info.getBankBranch());                salarySlip.setIfscCode(info.getIfscCode());                salarySlip.setPanNumber(info.getBasicEmployee1().getPanNumber());                salarySlip.setDepartment(info.getBasicEmployee1().getSelectDepartment());                salarySlip.setDesignation(info.getBasicEmployee1().getDesignation());                salarySlip.setDateOfJoining(info.getBasicEmployee1().getJoiningDate());                salarySlip.setCompany(info.getBasicEmployee1().getWhichCompany());                salarySlip.setCreatedOn(newDate);                salarySlip.setPaidDays(maxDayOfMonth - lopCount);                salarySlip.setOverTime((double) byUserNameAndMonth / 60);                double v = Double.parseDouble(decimalFormat.format(info.getGrossSalary()                        - (info.getBasicSalary() - info.getBasicSalary() * 0.40)));                double all = v / 2;                double overTimePay = Double.parseDouble(decimalFormat.                        format(((info.getGrossSalary()) / (8 * 60 * 30)) * byUserNameAndMonth));                salarySlip.setBasicSalary(Double.parseDouble(decimalFormat.format(info.getBasicSalary())));                salarySlip.setHra(Double.parseDouble(decimalFormat.format(info.getBasicSalary() * 0.40)));                salarySlip.setConveyance(all);                salarySlip.setOtherAllowances(all);                // Calculation for deduction                salarySlip.setProvidentFund(Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18)));                salarySlip.setLuf(luf);                salarySlip.setLopPay(lossOfPay);                salarySlip.setGrossDeduction(gross);                // Calculation for Gross Earnings                salarySlip.setGrossEarning(Double.parseDouble(decimalFormat.format(info.getGrossSalary())));                salarySlip.setOverTimePay(overTimePay);                salarySlip.setNetPay(Double.parseDouble(decimalFormat                        .format(info.getGrossSalary() - gross + overTimePay)));                salarySlipRepository.save(salarySlip);                listSalarySlip.add(salarySlip);            }        }        return listSalarySlip;    }    @Override    public List<SalarySlip> UpdateSalary(SalarySlip salarySlip) {        return null;    }    @Override    public List<SalarySlip> getAllSalary(HttpServletResponse response,String month) throws IOException {        List<SalarySlip> all = salarySlipRepository.findByMonthFor(month);        ExportDataCsv exportDataCsv=new ExportDataCsv(all);        exportDataCsv.exportDataToExcel(response);        return all;    }    @Override    public List<SalarySlip> getAll() {        return salarySlipRepository.findAll();    }}