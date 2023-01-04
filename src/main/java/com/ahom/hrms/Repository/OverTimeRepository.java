package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.OverTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OverTimeRepository extends JpaRepository<OverTime, Integer> {

	

}
