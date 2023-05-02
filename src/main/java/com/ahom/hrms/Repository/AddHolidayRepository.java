package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.AddHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddHolidayRepository extends JpaRepository<AddHoliday, Integer>
{
    AddHoliday findByHolidayNameAndFromDateAndToDate(String holidayName, String fromDate, String toDate);
}
