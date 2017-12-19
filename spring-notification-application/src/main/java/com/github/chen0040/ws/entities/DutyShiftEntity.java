package com.github.chen0040.ws.entities;


import com.github.chen0040.ws.consts.DutyShiftRepeatPattern;
import com.github.chen0040.ws.models.DutyShift;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;


@Getter
@Setter
@Entity
public class DutyShiftEntity {

    @Id
    @GeneratedValue
    private long id;

    private long startTime = 0L;
    private long endTime = 0L;
    private int repeatCount = -1;
    private long activeDateTime = 0L;
    private int repeatPattern = DutyShiftRepeatPattern.SameDaysOfEveryWeek;

    @ElementCollection
    private List<Long> logicalGroupsInScope = new ArrayList<>();
    @ElementCollection
    private List<Long> dutyOfficersInScope = new ArrayList<>();

    private String note = "";

    public DutyShift toDutyShift() {
        DutyShift result = new DutyShift();
        result.setId(id);
        result.setStartTime(startTime);
        result.setEndTime(endTime);
        result.setRepeatCount(repeatCount);
        result.setActiveDateTime(activeDateTime);
        result.setRepeatPattern(repeatPattern);
        result.setLogicalGroupsInScope(logicalGroupsInScope);
        result.setDutyOfficersInScope(dutyOfficersInScope);

        return result;
    }

    public void copy(DutyShift that){
        id = that.getId();
        startTime = that.getStartTime();
        endTime = that.getEndTime();
        repeatCount = that.getRepeatCount();
        activeDateTime = that.getActiveDateTime();
        repeatPattern = that.getRepeatPattern();
        logicalGroupsInScope = that.getLogicalGroupsInScope();
        dutyOfficersInScope = that.getDutyOfficersInScope();
    }




}
