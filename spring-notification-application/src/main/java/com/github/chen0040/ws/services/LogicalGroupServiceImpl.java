package com.github.chen0040.ws.services;



import com.github.chen0040.ws.entities.LogicalGroupEntity;
import com.github.chen0040.ws.models.LogicalGroup;
import com.github.chen0040.ws.repositories.LogicalGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogicalGroupServiceImpl implements LogicalGroupService {


    @Autowired
    private LogicalGroupRepository repository;


    @Override
    public List<LogicalGroup> findAll() {
        List<LogicalGroup> result = new ArrayList<>();

        for(LogicalGroupEntity entity : repository.findAll()){
            result.add(entity.toLogicalGroup());
        }

        return result;
    }

    @Transactional
    @Override
    public LogicalGroup save(LogicalGroup group) {
        LogicalGroupEntity entity = repository.findOne(group.getId());
        if(entity == null) {
            entity = new LogicalGroupEntity();
            entity.copy(group);
            entity.setId(0L);
        } else {
            entity.copy(group);
        }
        return repository.save(entity).toLogicalGroup();
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    @Override
    public void delete(LogicalGroup group) {
        repository.delete(group.getId());
    }

    @Override
    public LogicalGroup findById(long groupId) {
        LogicalGroupEntity entity = repository.findOne(groupId);
        if(entity == null ) {
            return LogicalGroup.createAlert("Failed to find group with id " + groupId);
        }
        return entity.toLogicalGroup();
    }
}
