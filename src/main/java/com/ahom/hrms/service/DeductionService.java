package com.ahom.hrms.service;

import com.ahom.hrms.dto.DeductionDto;
import com.ahom.hrms.entities.Deduction;

import java.util.List;

public interface DeductionService {
    void saveDeduction(DeductionDto deductionDto);
    public void deleteDeduction(int id );
    public List<DeductionDto> getAllDeduction();
    public DeductionDto updateDeduction(DeductionDto deductionDto);

}
