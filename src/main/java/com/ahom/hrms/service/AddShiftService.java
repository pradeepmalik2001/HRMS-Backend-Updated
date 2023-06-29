package com.ahom.hrms.service;

import com.ahom.hrms.dto.AddShiftDto;
import com.ahom.hrms.entities.ShiftManagement;

import java.util.List;

public interface AddShiftService {
    AddShiftDto saveAddShift(AddShiftDto addShiftDto);
    List<AddShiftDto> getAllAddShift();
    ShiftManagement deleteAddShift(String id);
    ShiftManagement updateAddshift(ShiftManagement shiftManagement, String id);
    List<ShiftManagement> addShiftById(String userName);
}
