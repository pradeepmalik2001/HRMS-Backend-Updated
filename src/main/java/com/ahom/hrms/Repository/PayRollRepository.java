package com.ahom.hrms.Repository;



import com.ahom.hrms.dto.PayRollDto;
import com.ahom.hrms.entities.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRollRepository extends JpaRepository<PayRoll,Integer> {

//	PayRollDto findOneByIgnoreCaseIdAndrefNoAndtype(Integer Id, String refNo,String type);
}
