package com.ahom.hrms.Repository;

import java.util.Optional;

import com.ahom.hrms.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;





public interface ImageDataRepo extends JpaRepository<ImageData, Long>{
	  Optional<ImageData> findByName(String fileName);
}
