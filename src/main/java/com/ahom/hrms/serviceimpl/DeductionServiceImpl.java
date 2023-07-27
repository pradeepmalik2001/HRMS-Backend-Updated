package com.ahom.hrms.serviceimpl;import com.ahom.hrms.Repository.DeductionRepository;import com.ahom.hrms.entities.Deduction;import com.ahom.hrms.service.DeductionService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class DeductionServiceImpl implements DeductionService {    @Autowired    DeductionRepository deductionRepository;    @Override    public Deduction save(Deduction deduction) {        return deductionRepository.save(deduction) ;    }    @Override    public List<Deduction> getAll() {        return deductionRepository.findAll();    }    @Override    public Deduction update(Deduction deduction, int id) {        Deduction deduction2=deductionRepository.findById(id).orElse(null);        if (deduction2==null){            throw new RuntimeException("Empty");        }        else {            Deduction deduction1=new Deduction();            deduction1.setId(id);            deduction1.setGratuity(deduction.getGratuity());            deduction1.setProvidentFund(deduction.getProvidentFund());            deduction1.setLuf(deduction.getLuf());            deductionRepository.save(deduction1);        }        return deduction;    }    @Override    public Deduction delete(int id) {        deductionRepository.deleteById(id);        return null;    }}