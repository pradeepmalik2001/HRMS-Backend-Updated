package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Allowances {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int Id;
	private String allowancesDescription;
	@ManyToOne(fetch =FetchType.LAZY)
	private PayRoll payRoll;





}
