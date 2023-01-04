package com.ahom.hrms.service;

import com.ahom.hrms.dto.VacancyDto;
import com.ahom.hrms.entities.Vacancy;

public interface VacancyService {

	void vacancySave(VacancyDto vacancyDto);

	VacancyDto vacancyToVacancyDto(Vacancy vacancy);

	Vacancy vacancyDtoTovacancy(VacancyDto vacancyDto);

}
