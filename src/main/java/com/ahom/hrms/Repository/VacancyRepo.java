package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VacancyRepo extends JpaRepository<Vacancy,Integer>{

}
