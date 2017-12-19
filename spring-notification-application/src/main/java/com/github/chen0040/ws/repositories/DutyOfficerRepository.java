package com.github.chen0040.ws.repositories;

import com.github.chen0040.ws.entities.DutyOfficerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyOfficerRepository extends CrudRepository<DutyOfficerEntity, Long> {
}
