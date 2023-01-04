package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.service.Payroll_itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.PayrollItemRepository;

import com.ahom.hrms.dto.Payroll_itemDto;
import com.ahom.hrms.entities.Payroll_Item;
@Service

public class Payroll_itemServicesimpl implements Payroll_itemService {

	
@Autowired
	PayrollItemRepository payrollItemRepository;
	@Override
	public void savePayrollitem(Payroll_itemDto payroll_Itemdto)
	{
		payrollItemRepository.save(payroll_Itemdtotopayroll_Item(payroll_Itemdto));
	}
	@Override
	 public List<Payroll_itemDto> getAllpayrollitem(){

	        List<Payroll_Item> listpayrollitem= this.payrollItemRepository.findAll();
	        
	        List<Payroll_itemDto> payrolldto=listpayrollitem.stream().map(pay ->this.payroll_Itemtopayroll_Itemdto(pay)).collect(Collectors.toList());
	         return payrolldto;
	 }


	
	@Override
	 public void deletePayrollitem(int empId){

	     payrollItemRepository.deleteById(empId);

	    }
		@Override
	    public Payroll_itemDto updatePayrollitem(Payroll_itemDto payroll_Itemdto)
	    {
	    payrollItemRepository.save(payroll_Itemdtotopayroll_Item(payroll_Itemdto));
	    return payroll_Itemdto;

	    }


	 public Payroll_Item payroll_Itemdtotopayroll_Item(Payroll_itemDto payroll_Itemdto)
	    {
	          Payroll_Item Item =new Payroll_Item();
	          
	          Item.setId(payroll_Itemdto.getId());;
		      Item.setPayrollId(payroll_Itemdto.getPayrollId());
		      Item.setEmployeeId(payroll_Itemdto.getEmployeeId());
		      Item.setPresent(payroll_Itemdto.getPresent());
		      Item.setAbsent(payroll_Itemdto.getAbsent());
		      Item.setLate(payroll_Itemdto.getLate());
		      Item.setSalary(payroll_Itemdto.getSalary());
		      Item.setAllowanceAmount(payroll_Itemdto.getAllowanceAmount());
		      Item.setAllowancesType(payroll_Itemdto.getAllowancesType());
		      Item.setDeductionAmount(payroll_Itemdto.getDeductionAmount());
		      Item.setDeductions(payroll_Itemdto.getDeductions());
		      Item.setGrossNet(payroll_Itemdto.getGrossNet());
			  Item.setDateCreated(payroll_Itemdto.getDateCreated());
		      

	       
	        return Item;
	    }

	    public Payroll_itemDto payroll_Itemtopayroll_Itemdto(Payroll_Item payroll_Item)
	    {
	        Payroll_itemDto payrollItem= new Payroll_itemDto();

	      payrollItem.setId(payroll_Item.getId());;
	      payrollItem.setPayrollId(payroll_Item .getPayrollId());
	      payrollItem.setEmployeeId(payroll_Item.getEmployeeId());
	      payrollItem.setPresent(payroll_Item.getPresent());
	      payrollItem.setAbsent(payroll_Item.getAbsent());
	      payrollItem.setLate(payroll_Item.getLate());
	      payrollItem.setSalary(payrollItem.getSalary());
	      payrollItem.setAllowanceAmount(payroll_Item.getAllowanceAmount());
	      payrollItem.setAllowancesType(payroll_Item.getAllowancesType());
	      payrollItem.setDeductionAmount(payroll_Item.getDeductionAmount());
	      payrollItem.setDeductions(payroll_Item.getDeductions());
	      payrollItem.setGrossNet(payroll_Item.getGrossNet());
		  payrollItem.setDateCreated(payroll_Item.getDateCreated());
	     
	        
	      return payrollItem;
	        
	    }
	

}
