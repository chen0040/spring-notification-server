package com.github.chen0040.ws.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LogicalGroup {

    private String groupId = "";
    private String companyId = "";
    private String groupName = "";
    private String note = "";
    private List<String> instances = new ArrayList<>();
    private long activeDateTime;




}
