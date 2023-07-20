package com.ahom.hrms.serviceimpl;import com.ahom.hrms.Helper.ExportDataCsv;import com.ahom.hrms.Repository.BankingInfoRepository;import com.ahom.hrms.Repository.SalarySlipRepository;import com.ahom.hrms.dto.EmployeeLeaveCount;import com.ahom.hrms.entities.AdvanceSalary;import com.ahom.hrms.entities.BankingInfo;import com.ahom.hrms.entities.SalarySlip;import com.ahom.hrms.service.*;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.text.DecimalFormat;import java.text.ParseException;import java.text.SimpleDateFormat;import java.time.YearMonth;import java.util.*;@Servicepublic class SalarySlipServiceImpl implements SalarySlipService {    @Autowired    BankingInfoRepository bankingInfoRepository;    @Autowired    SalarySlipRepository salarySlipRepository;    @Autowired    LeaveRecordServiceImpl leaveRecordService;    @Autowired    AdvanceSalaryService advanceSalaryService;    @Autowired    OverTimeService overTimeService;    @Override    public List<SalarySlip> saveSalary(String month) throws ParseException {        List<BankingInfo> bankingInfo = bankingInfoRepository.findAll();        List<SalarySlip> listSalarySlip = new ArrayList<>();        DecimalFormat decimalFormat = new DecimalFormat("#.00");        YearMonth currentYearMonth = YearMonth.now();        int maxDayOfMonth = currentYearMonth.lengthOfMonth();        System.out.println("year"+ maxDayOfMonth);        Date date=new Date();        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        String newDate= dateFormat.format(date);        month = month.toUpperCase();        for (BankingInfo info : bankingInfo) {            Optional<SalarySlip> slip = Optional.ofNullable(salarySlipRepository.findByEmployeeIdAndMonthFor(info.getId(), month));            if (slip.isPresent()) {                throw new RuntimeException("Salary slip is already generated of EmployeeID :" + info.getId() + " for mont :" + month);            }            int byUserNameAndMonth = overTimeService.getByUserNameAndMonth(month, info.getBasicEmployee1().getEmail());            AdvanceSalary advance;            try {                advance = advanceSalaryService.findByEmployeeId(info.getId());            } catch (Exception e) {                throw new RuntimeException(e);            }            double gratuity = 0;            if (advance != null && advance.getRemainingAdvance() >= 0) {                advanceSalaryService.updateSalary(advance, info.getId());                EmployeeLeaveCount byId = leaveRecordService.getById(info.getId(), month);                SalarySlip salarySlip = new SalarySlip();                salarySlip.setWorkingDays(maxDayOfMonth);                double luf = info.getGrossSalary() * 0.01;                double lopCount = byId.getLeaveCount();                double lossOfPay = Double.parseDouble(decimalFormat.format((info.getBasicSalary() / maxDayOfMonth) * lopCount));                double advanceDeductionPerMonth = Double.parseDouble(decimalFormat.format(advance.getAmountToPayPerMonth()));                double gross = Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18 + info.getGrossSalary() * 0.01 + lossOfPay + advanceDeductionPerMonth));                gratuity = Double.parseDouble(decimalFormat.format((info.getBasicEmployee1().getCtc() * 0.0481) / 12));                double overTimePay = Double.parseDouble(decimalFormat.format(((info.getGrossSalary()) / (8 * 60 * 30)) * byUserNameAndMonth));                salarySlip.setMonthFor(month);                salarySlip.setLop(lopCount);                salarySlip.setEmployeeId(info.getId());                salarySlip.setName(info.getName());                salarySlip.setBankName(info.getBankName());                salarySlip.setBankAccountNumber(info.getBankAccountNo());                salarySlip.setDOB(String.valueOf(info.getBasicEmployee1().getDob()));                salarySlip.setEmailId(info.getBasicEmployee1().getEmail());                salarySlip.setPfNumber(info.getBasicEmployee1().getPfnumber());                salarySlip.setMobile(info.getBasicEmployee1().getMobile());                salarySlip.setBankBranch(info.getBankBranch());                salarySlip.setIfscCode(info.getIfscCode());                salarySlip.setPanNumber(info.getBasicEmployee1().getPanNumber());                salarySlip.setDepartment(info.getBasicEmployee1().getSelectDepartment());                salarySlip.setDesignation(info.getBasicEmployee1().getDesignation());                salarySlip.setDateOfJoining(info.getBasicEmployee1().getJoiningDate());                salarySlip.setCompany(info.getBasicEmployee1().getWhichCompany());                salarySlip.setCreatedOn(newDate);                salarySlip.setPaidDays(maxDayOfMonth - lopCount);                salarySlip.setOverTime((double) byUserNameAndMonth / 60);                double v = Double.parseDouble(decimalFormat.format((info.getGrossSalary() - info.getBasicSalary()) * 0.40));                double all = v / 2;                salarySlip.setBasicSalary(Double.parseDouble(decimalFormat.format(info.getBasicSalary())));                salarySlip.setHra(Double.parseDouble(decimalFormat.format((info.getGrossSalary() - info.getBasicSalary())                        * 0.60)));                salarySlip.setConveyance(all);                salarySlip.setOtherAllowances(all);                // Calculation for deduction                salarySlip.setProvidentFund(Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18)));                salarySlip.setLuf(luf);                salarySlip.setLopPay(lossOfPay);                salarySlip.setGratuity(gratuity);                salarySlip.setAdvance(Double.parseDouble(decimalFormat.format(advance.getAmountToPayPerMonth())));                salarySlip.setGrossDeduction(gross+gratuity);                // Calculation for Gross Earnings                salarySlip.setGrossEarning(Double.parseDouble(decimalFormat                        .format(info.getGrossSalary())));                salarySlip.setOverTimePay(overTimePay);                salarySlip.setNetPay(Double.parseDouble(decimalFormat                        .format(info.getGrossSalary() - gross + overTimePay-gratuity)));                salarySlipRepository.save(salarySlip);                if (advance.getRemainingAdvance() < advance.getAmountToPayPerMonth()) {                    advance.setRemainingAdvance(advance.getRemainingAdvance());                    advance.setAmountToPayPerMonth(advance.getRemainingAdvance());                    advance.setStatus(true);                    advanceSalaryService.updateRemainingAdavnceMonth(advance);                }                listSalarySlip.add(salarySlip);            } else {                EmployeeLeaveCount byId = leaveRecordService.getById(info.getId(), month);                SalarySlip salarySlip = new SalarySlip();                salarySlip.setWorkingDays(maxDayOfMonth);                double luf = info.getGrossSalary() * 0.01;                double lopCount = byId.getLeaveCount();                double lossOfPay = Double.parseDouble(decimalFormat.format((info.getBasicSalary() / maxDayOfMonth) * lopCount));                double gross = Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18 + info.getGrossSalary() * 0.01 + lossOfPay));                salarySlip.setMonthFor(month);                salarySlip.setLop(lopCount);                salarySlip.setEmployeeId(info.getId());                salarySlip.setName(info.getName());                salarySlip.setBankName(info.getBankName());                salarySlip.setBankAccountNumber(info.getBankAccountNo());                salarySlip.setDOB(String.valueOf(info.getBasicEmployee1().getDob()));                salarySlip.setEmailId(info.getBasicEmployee1().getEmail());                salarySlip.setPfNumber(info.getBasicEmployee1().getPfnumber());                salarySlip.setMobile(info.getBasicEmployee1().getMobile());                salarySlip.setBankBranch(info.getBankBranch());                salarySlip.setIfscCode(info.getIfscCode());                salarySlip.setPanNumber(info.getBasicEmployee1().getPanNumber());                salarySlip.setDepartment(info.getBasicEmployee1().getSelectDepartment());                salarySlip.setDesignation(info.getBasicEmployee1().getDesignation());                salarySlip.setDateOfJoining(info.getBasicEmployee1().getJoiningDate());                salarySlip.setCompany(info.getBasicEmployee1().getWhichCompany());                salarySlip.setCreatedOn(newDate);                salarySlip.setPaidDays(maxDayOfMonth - lopCount);                salarySlip.setOverTime((double) byUserNameAndMonth / 60);                double v = Double.parseDouble(decimalFormat.format((info.getGrossSalary() - info.getBasicSalary()) * 0.40));                double all = v / 2;                double overTimePay = Double.parseDouble(decimalFormat.                        format(((info.getGrossSalary()) / (8 * 60 * 30)) * byUserNameAndMonth));                salarySlip.setBasicSalary(Double.parseDouble(decimalFormat.format(info.getBasicSalary())));                salarySlip.setHra(Double.parseDouble(decimalFormat.format(info.getBasicSalary() * 0.60)));                salarySlip.setConveyance(all);                salarySlip.setOtherAllowances(all);                // Calculation for deduction                salarySlip.setProvidentFund(Double.parseDouble(decimalFormat.format(info.getGrossSalary() * 0.18)));                salarySlip.setLuf(luf);                salarySlip.setLopPay(lossOfPay);                salarySlip.setGratuity(gratuity);                salarySlip.setGrossDeduction(gross+gratuity);                // Calculation for Gross Earnings                salarySlip.setGrossEarning(Double.parseDouble(decimalFormat.format(info.getGrossSalary())));                salarySlip.setOverTimePay(overTimePay);                salarySlip.setNetPay(Double.parseDouble(decimalFormat                        .format(info.getGrossSalary() - gross + overTimePay-gratuity)));                salarySlipRepository.save(salarySlip);                listSalarySlip.add(salarySlip);            }        }        return listSalarySlip;    }    @Override    public List<SalarySlip> getAllSalary(HttpServletResponse response,String month) throws IOException {        List<SalarySlip> all = salarySlipRepository.findByMonthFor(month);        if (all.isEmpty())        {            throw new RuntimeException("Record For the Month :"+month + " is not present");        }        ExportDataCsv exportDataCsv=new ExportDataCsv(all);        exportDataCsv.exportDataToExcel(response);        return all;    }    @Override    public SalarySlip getDataForPdf(String employeeId, String month) {        Optional<SalarySlip> slip = Optional.ofNullable(salarySlipRepository.findByEmployeeIdAndMonthFor(employeeId, month));        if (slip.isPresent()) {            return salarySlipRepository.findByEmployeeIdAndMonthFor(employeeId, month);        }else            throw new RuntimeException("No record available for this input");    }    @Override    public List<SalarySlip> getAll() {        return salarySlipRepository.findAll();    }    @Override    public Object delete(int id) {        salarySlipRepository.deleteById(id);        return null;    }    @Override    public SalarySlip updateSalary(SalarySlip salarySlip, int id) {        SalarySlip sa=salarySlipRepository.findById(id).orElse(null);        if (sa==null){            throw new RuntimeException("");        }        else {            double grossSalary = salarySlip.getHra() + salarySlip.getBasicSalary() + sa.getConveyance()                    + salarySlip.getOtherAllowances() + salarySlip.getOverTimePay();            double grossDeduction=salarySlip.getLopPay()+salarySlip.getAdvance()+salarySlip.getLuf()+salarySlip.getProvidentFund();            sa.setSlipId(salarySlip.getSlipId());            sa.setEmployeeId(salarySlip.getEmployeeId());            sa.setName(salarySlip.getName());            sa.setDOB(salarySlip.getDOB());            sa.setAdvance(salarySlip.getAdvance());            sa.setBankName(salarySlip.getBankName());            sa.setEmailId(salarySlip.getEmailId());            sa.setPfNumber(salarySlip.getPfNumber());            sa.setMobile(salarySlip.getMobile());            sa.setBankBranch(salarySlip.getBankBranch());            sa.setIfscCode(salarySlip.getIfscCode());            sa.setPanNumber(salarySlip.getPanNumber());            sa.setDepartment(salarySlip.getDepartment());            sa.setDesignation(salarySlip.getDesignation());            sa.setDateOfJoining(salarySlip.getDateOfJoining());            sa.setCompany(salarySlip.getCompany());            sa.setProvidentFund(salarySlip.getProvidentFund());            sa.setBankAccountNumber(salarySlip.getBankAccountNumber());            sa.setCreatedOn(salarySlip.getCreatedOn());            sa.setMonthFor(salarySlip.getMonthFor());            sa.setPaidDays(salarySlip.getPaidDays());            sa.setOverTime(salarySlip.getOverTime());            sa.setBasicSalary(salarySlip.getBasicSalary());            sa.setWorkingDays(salarySlip.getWorkingDays());            sa.setGrossDeduction(grossDeduction);            sa.setLop(salarySlip.getLop());            sa.setLuf(salarySlip.getLuf());            sa.setGratuity(salarySlip.getGratuity());            sa.setLopPay(salarySlip.getLopPay());            sa.setOtherAllowances(salarySlip.getOtherAllowances());            sa.setHra(salarySlip.getHra());            sa.setConveyance(salarySlip.getConveyance());            sa.setOverTimePay(salarySlip.getOverTimePay());            sa.setGrossEarning(grossSalary);            sa.setNetPay(grossSalary-grossDeduction);            salarySlipRepository.save(sa);        }        return sa;    }}