package com.github.chen0040.ws.services;


import com.github.chen0040.ws.entities.DutyOfficerEntity;
import com.github.chen0040.ws.models.DutyOfficer;
import com.github.chen0040.ws.repositories.DutyOfficerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DutyOfficerServiceImpl implements DutyOfficerService {

    private DutyOfficerRepository repository;

    @Override
    public List<DutyOfficer> findAll() {
        List<DutyOfficer> result = new ArrayList<>();
        for(DutyOfficerEntity entity : repository.findAll()){
            result.add(entity.toDutyOfficer());
        }
        return result;
    }

    @Transactional
    @Override
    public DutyOfficer save(DutyOfficer officer) {
        DutyOfficerEntity entity = repository.findOne(officer.getId());
        if(entity == null) {
            entity = new DutyOfficerEntity();
            entity.copy(officer);
            entity.setId(0L);
        } else {
            entity.copy(officer);
        }
        entity = repository.save(entity);
        return entity.toDutyOfficer();
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    @Override
    public void delete(DutyOfficer officer) {
        repository.delete(officer.getId());
    }

    @Override
    public DutyOfficer findById(long officerId) {
        DutyOfficerEntity entity = repository.findOne(officerId);
        if(entity == null) {
            return DutyOfficer.createAlert("Failed to find officer with id " + officerId);
        }
        return entity.toDutyOfficer();
    }
}
