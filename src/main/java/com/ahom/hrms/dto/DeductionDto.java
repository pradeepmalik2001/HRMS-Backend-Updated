package com.ahom.hrms.dto;

import com.ahom.hrms.entities.PayRoll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeductionDto {

	private int Id;
	@NotNull(message="plssss enter deduction")
	private double Deduction;
	private String Description;



}
