package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.ResignationRepository;
import com.ahom.hrms.entities.Resignation;
import com.ahom.hrms.service.ResignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResignationServiceImpl implements ResignationService
{
    @Autowired
    ResignationRepository resignationRepository;

    @Override
    public Resignation postResignation(Resignation resignation)
    {
        return resignationRepository.save(resignation);
    }

    @Override
    public List<Resignation> fetchResignation()
    {
        List<Resignation> resignations=resignationRepository.findAll();
        return resignations;
    }
}
