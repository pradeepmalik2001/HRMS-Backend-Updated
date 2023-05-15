package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.ApproveRepository;
import com.ahom.hrms.entities.Approve;
import com.ahom.hrms.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveServiceImpl implements ApproveService
{
    @Autowired
    ApproveRepository approveRepository;

    @Override
    public Approve saveApprove(Approve approve)
    {
          return approveRepository.save(approve);
    }
}
