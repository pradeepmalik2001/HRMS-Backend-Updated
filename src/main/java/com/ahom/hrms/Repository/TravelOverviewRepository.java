package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahom.hrms.entities.TravelOverview;

@EnableJpaRepositories
public interface TravelOverviewRepository extends JpaRepository<TravelOverview, String> {

}
