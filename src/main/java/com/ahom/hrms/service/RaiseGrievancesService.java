package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.RaiseGrievancesDto;

public interface RaiseGrievancesService {

	void saveRaiseGrievances(RaiseGrievancesDto raiseGrievancesDto );

	List<RaiseGrievancesDto> getAllRaiseGrievances();

	RaiseGrievancesDto updateRaiseGrievances(RaiseGrievancesDto raiseGrievancesDto);

}
