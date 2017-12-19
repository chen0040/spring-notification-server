package com.github.chen0040.ws.services;


import com.github.chen0040.ws.models.DutyShift;

import java.util.List;

/**
 * Created by chen0 on 2/6/2016.
 */
public interface DutyShiftService {
    List<DutyShift> findAll();
    DutyShift save(DutyShift shift);
    void deleteAll();
    DutyShift findById(long shiftId);
    void delete(DutyShift shift);
}
