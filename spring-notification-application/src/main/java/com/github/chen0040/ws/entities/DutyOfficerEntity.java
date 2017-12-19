package com.github.chen0040.ws.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class DutyOfficerEntity {

    @Id
    @GeneratedValue
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
