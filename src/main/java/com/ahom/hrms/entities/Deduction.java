package com.ahom.hrms.entities;import lombok.AllArgsConstructor;import lombok.Getter;import lombok.NoArgsConstructor;import lombok.Setter;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.validation.constraints.Max;@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Entitypublic class Deduction {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private int id;    @Max(100)    private double gratuity;    @Max(100)    private double luf;    @Max(100)    private double providentFund;}