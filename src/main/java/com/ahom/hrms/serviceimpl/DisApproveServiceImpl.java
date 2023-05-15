package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.DisApproveRepo;
import com.ahom.hrms.entities.DisApprove;
import com.ahom.hrms.service.DisApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisApproveServiceImpl implements DisApproveService
{
    @Autowired
    DisApproveRepo disApproveRepo;

    @Override
    public DisApprove saveDisapprove(DisApprove disApprove) {
        return disApproveRepo.save(disApprove);
    }
}
