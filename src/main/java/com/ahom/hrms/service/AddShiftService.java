package com.ahom.hrms.service;

import com.ahom.hrms.dto.AddShiftDto;
import com.ahom.hrms.entities.ShiftManagement;

import java.util.List;

public interface AddShiftService {
    AddShiftDto saveAddShift(AddShiftDto addShiftDto);
    List<AddShiftDto> getAllAddShift();
    ShiftManagement deleteAddShift(int id);
    ShiftManagement updateAddshift(ShiftManagement shiftManagement, int id);
    AddShiftDto addShiftById(Integer id);
}
