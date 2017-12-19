package com.github.chen0040.ws.services;

import com.github.chen0040.ws.models.DutyOfficer;

import java.util.List;

/**
 * Created by chen0 on 2/6/2016.
 */
public interface DutyOfficerService {
    List<DutyOfficer> findAll();
    DutyOfficer save(DutyOfficer officer);
    void deleteAll();
    DutyOfficer findById(long officerId);
    void delete(DutyOfficer officer);
}
