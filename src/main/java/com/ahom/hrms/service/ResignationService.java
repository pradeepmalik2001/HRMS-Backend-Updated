package com.ahom.hrms.service;

import com.ahom.hrms.entities.Resignation;

import java.util.List;

public interface ResignationService
{
    public Resignation postResignation(Resignation resignation);

    public List<Resignation> fetchResignation();
}
