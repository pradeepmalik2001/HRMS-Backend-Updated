package com.ahom.hrms.serviceimpl;
import com.ahom.hrms.Repository.BranchRepository;
import com.ahom.hrms.dto.BranchDto;
import com.ahom.hrms.entities.Branch;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public void saveBranch(BranchDto branchDto)
    {
        Branch branch=branchRepository.findByName(branchDto.getName());
        if(branch==null)
        {
            branchRepository.save(branchDtoToBranch(branchDto));
        }else{
            throw new CustomException("Branch is Already Present");
        }

    }

    @Override
    public Branch branchDtoToBranch(BranchDto branchDto){
        Branch branch= this.modelMapper.map(branchDto,Branch.class);
        return branch;
    }

    @Override
    public BranchDto branchToBranchDto(Branch branch){
        BranchDto branchDto=this.modelMapper.map(branch,BranchDto.class);
        return branchDto;
    }

    //get data
    @Override
    public List<BranchDto> getAll() {
        List list=branchRepository.findAll();
        return list;
    }
}
