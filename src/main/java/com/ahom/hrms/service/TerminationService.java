package com.ahom.hrms.service;import com.ahom.hrms.entities.Termination;import java.util.List;public interface TerminationService {    Termination save(Termination termination);    List<Termination> getAll();    Termination update(Termination termination,int id);    Object delete(int id);}