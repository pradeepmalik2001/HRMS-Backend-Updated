package com.ahom.hrms.serviceimpl;





import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.PayRollRepository;

import com.ahom.hrms.dto.PayRollDto;

import com.ahom.hrms.entities.PayRoll;


@Service
public class PayrollServiceimpl implements PayrollService {
	
	
	@Autowired
	 PayRollRepository payrollRepository;

	@Override
	public void savePayroll(PayRollDto payrollDto) {
		payrollRepository.save(payrollDtoToPayroll(payrollDto));

	}
	@Override
	public List<PayRollDto> getAllPayroll (){
		List<PayRoll> listPayroll = this.payrollRepository.findAll();
		List<PayRollDto> payrollDtoList = listPayroll.stream().map(emp -> this.payrollToPayrollDto(emp)).collect(Collectors.toList());

		return payrollDtoList;

	}
	  @Override
		public void deletePayroll(int Id) {

			payrollRepository.deleteById(Id);
	}

	@Override
		public PayRollDto savePayroll(PayRoll payroll)
		{
			PayRoll payRoll = payrollRepository.save(payroll);
			return  payrollToPayrollDto(payroll);

		}
		@Override
		 public PayRollDto payrollById(Integer Id)
		    {
		        PayRoll payroll = this.payrollRepository.findById(Id).get();
		        // Optional<Employee> byId = employeeReposatory.findById(employeeId);
		        return this.payrollToPayrollDto(payroll);

		    }





			@Override
			public PayRoll payrollDtoToPayroll(PayRollDto payrollDto) {

		   PayRoll payroll = new PayRoll();
		   payroll.setId(payrollDto.getId());
		   payroll.setDateFrom(payrollDto.getDateFrom());
		   payroll.setDateTo(payrollDto.getDateTo());
		   payroll.setDateCreated(payrollDto.getDateCreated());
		   payroll.setRefNo(payrollDto.getRefNo());
		   payroll.setStatus(payrollDto.getStatus());
		   payroll.setType(payrollDto.getType());

		  return payroll;

	}

	@Override
	public PayRollDto payrollToPayrollDto(PayRoll payroll) {

		PayRollDto payrollDto = new PayRollDto();
		payroll.setId(payroll.getId());
		payroll.setDateFrom(payroll.getDateFrom());
		payroll.setDateTo(payroll.getDateTo());
		payroll.setDateCreated(payroll.getDateCreated());
		payroll.setStatus(payroll.getStatus());
		payroll.setType(payroll.getType());
		payroll.setRefNo(payroll.getRefNo());

		return payrollDto;

	}

}
