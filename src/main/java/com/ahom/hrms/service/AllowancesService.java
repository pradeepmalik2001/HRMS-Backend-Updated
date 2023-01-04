package com.ahom.hrms.service;

import com.ahom.hrms.dto.AllowancesDto;

import java.util.List;

public interface AllowancesService {

    void saveAllowances(AllowancesDto allowancesDto);
    void deleteAllowances(int id);
    void updateAllowances(AllowancesDto allowancesDto);
    List<AllowancesDto> getAllAllowances();
}
