package com.ahom.hrms.Repository;import com.ahom.hrms.entities.SalarySlip;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;public interface SalarySlipRepository extends JpaRepository<SalarySlip,Integer> {    List<SalarySlip> findByMonthFor(String month);    SalarySlip findByEmployeeIdAndMonthFor(String employeeId, String month);}