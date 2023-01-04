package com.ahom.hrms.service;

import com.ahom.hrms.dto.AddShiftDto;
import com.ahom.hrms.entities.ShiftManagement;

import java.util.List;

public interface AddShiftService {
    void saveAddShift(AddShiftDto addShiftDto);
    List<AddShiftDto> getAllAddShift();
    void deleteAddShift(int Id);
    void updateAddshift(ShiftManagement shiftManagement, int id);
    AddShiftDto addShiftById(Integer id);
}
