package com.ahom.hrms.service;
import com.ahom.hrms.dto.BranchDto;
import com.ahom.hrms.entities.Branch;
import java.util.List;

public interface BranchService {
    void saveBranch(BranchDto branchDto);

    Branch branchDtoToBranch(BranchDto branchDto);

    BranchDto branchToBranchDto(Branch branch);

    //get data
    List<BranchDto> getAll();
}
