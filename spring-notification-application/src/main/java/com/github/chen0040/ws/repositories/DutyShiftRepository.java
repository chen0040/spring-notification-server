package com.github.chen0040.ws.repositories;

import com.github.chen0040.ws.entities.DutyShiftEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyShiftRepository extends CrudRepository<DutyShiftEntity, Long> {

}
