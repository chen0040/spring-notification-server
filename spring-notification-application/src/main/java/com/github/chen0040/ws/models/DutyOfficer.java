package com.github.chen0040.ws.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DutyOfficer {

    private long id;
    private String mobile = "";
    private String email = "";
    private String chatParameters = "";
    private String chatService = "";
    private int fallbackAlertUser = 0;
    private String note = "";
    private String title = "";
    private String firstName = "";
    private String lastName = "";
    private long activeDateTime = 0L;


}
