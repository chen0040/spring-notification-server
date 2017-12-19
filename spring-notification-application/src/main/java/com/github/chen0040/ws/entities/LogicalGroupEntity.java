package com.github.chen0040.ws.entities;

import lombok.Getter;
import lombok.Setter;

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
    private List<String> instances = new ArrayList<>();
    private long activeDateTime;
}
