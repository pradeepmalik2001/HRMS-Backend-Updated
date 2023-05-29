package com.ahom.hrms.serviceimpl;
import com.ahom.hrms.Repository.BranchRepository;
import com.ahom.hrms.dto.BranchDto;
import com.ahom.hrms.entities.Branch;
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
    public BranchDto saveBranch(BranchDto branchDto)
    {
        Branch branch=branchRepository.findByName(branchDto.getName());
        if(branch==null)
        {
            branchRepository.save(branchDtoToBranch(branchDto));
        }else{
            throw new RuntimeException("Branch is Already Present");
        }
            return branchDto;
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

    @Override
    public Branch deleteBranch(String id)
    {
        Branch branch=branchRepository.findById(id).orElse(null);
        if(branch!=null)
        {
            branchRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Branch for Id : "+id+" Not Found ");
        }
         return branch;
    }
}
