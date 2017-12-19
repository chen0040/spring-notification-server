package com.github.chen0040.ws.services;


import com.github.chen0040.ws.entities.DutyShiftEntity;
import com.github.chen0040.ws.models.DutyShift;
import com.github.chen0040.ws.repositories.DutyShiftRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DutyShiftServiceImpl implements DutyShiftService {


    private DutyShiftRepository repository;

    public DutyShiftServiceImpl(DutyShiftRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DutyShift> findAll() {
        List<DutyShift> result = new ArrayList<>();
        for(DutyShiftEntity entity : repository.findAll()){
            result.add(entity.toDutyShift());
        }
        return result;
    }

    @Transactional
    @Override
    public DutyShift save(DutyShift shift) {
        DutyShiftEntity entity = repository.findOne(shift.getId());
        if(entity == null) {
            entity = new DutyShiftEntity();
            entity.copy(shift);
            entity.setId(0L);
        } else {
            entity.copy(shift);
        }
        return repository.save(entity).toDutyShift();
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    @Override
    public void delete(DutyShift shift) {
        repository.delete(shift.getId());
    }

    @Override
    public DutyShift findById(long shiftId) {
        DutyShiftEntity entity = repository.findOne(shiftId);
        if(entity == null) {
            return DutyShift.createAlert("Failed to find duty shift with id " + shiftId);
        }
        return entity.toDutyShift();
    }
}
