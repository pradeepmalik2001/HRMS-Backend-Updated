package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.TravelOverviewDto;

public interface TravelOverviewService {

	void saveTravelOverview(TravelOverviewDto travelOverviewDto);

	List<TravelOverviewDto> getAllTravelOverview();

	TravelOverviewDto updateTravelOverview(TravelOverviewDto travelOverviewDto);

}
