package com.github.chen0040.ws.entities;

import com.github.chen0040.ws.models.DutyOfficer;
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

    public DutyOfficer toDutyOfficer(){
        DutyOfficer result = new DutyOfficer();
        result.setId(id);
        result.setMobile(mobile);
        result.setChatParameters(chatParameters);
        result.setChatService(chatService);
        result.setFallbackAlertUser(fallbackAlertUser);
        result.setNote(note);
        result.setTitle(title);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setActiveDateTime(activeDateTime);
        return result;
    }

    public void copy(DutyOfficer that){
        id = that.getId();
        mobile = that.getMobile();
        chatParameters = that.getChatParameters();
        chatService = that.getChatService();
        fallbackAlertUser = that.getFallbackAlertUser();
        note = that.getNote();
        title = that.getTitle();
        firstName = that.getFirstName();
        lastName = that.getLastName();
        activeDateTime = that.getActiveDateTime();

    }


}
