package com.github.chen0040.ws.entities;

import com.github.chen0040.ws.models.LogicalGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class LogicalGroupEntity {

    @Id
    @GeneratedValue
    private long id;

    private String groupName = "";
    private String note = "";

    @ElementCollection
    private List<String> instances = new ArrayList<>();

    private long activeDateTime;

    public LogicalGroup toLogicalGroup() {
        LogicalGroup result = new LogicalGroup();
        result.setId(id);
        result.setGroupName(groupName);
        result.setNote(note);
        result.setInstances(instances);
        result.setActiveDateTime(activeDateTime);
        return result;
    }

    public void copy(LogicalGroup that) {
        id = that.getId();
        groupName = that.getGroupName();
        note = that.getNote();
        instances = that.getInstances();
        activeDateTime = that.getActiveDateTime();
    }
}
