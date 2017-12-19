package com.github.chen0040.ws.services;


import com.github.chen0040.ws.models.LogicalGroup;

import java.util.List;


public interface LogicalGroupService {
    List<LogicalGroup> findAll();
    LogicalGroup save(LogicalGroup group);
    void deleteAll();
    LogicalGroup findById(long groupId);
    void delete(LogicalGroup group);
}
