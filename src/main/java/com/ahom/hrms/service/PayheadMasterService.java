package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.PayheadMasterDto;

public interface PayheadMasterService {

	void SavePayheadMaster(PayheadMasterDto payheadMasterDto);

	List<PayheadMasterDto> getpayheadMasterDetail();

	void deletepayheadmasterDetail(int i);

	void updatpayheadMaster(PayheadMasterDto payheadMasterDto);

}
