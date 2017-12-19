package com.github.chen0040.ws.repositories;

import com.github.chen0040.ws.entities.LogicalGroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogicalGroupRepository extends CrudRepository<LogicalGroupEntity, Long> {

}
