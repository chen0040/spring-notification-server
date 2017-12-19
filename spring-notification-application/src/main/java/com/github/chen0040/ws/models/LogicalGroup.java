package com.github.chen0040.ws.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LogicalGroup {

    private long id;
    private String groupName = "";
    private String note = "";
    private List<String> instances = new ArrayList<>();
    private long activeDateTime;
    private String error;

    public static LogicalGroup createAlert(String error) {
        return new LogicalGroup().alert(error);
    }

    public LogicalGroup alert(String error) {
        this.error = error;
        return this;
    }

    private boolean matchExpression(String regex, String value){
        return value != null && value.equals(regex);
    }

    public boolean match(AuditEvent alarm) {
        boolean matched1 = false;
        for(String key_value_pairs: instances) {

            String[] key_values = key_value_pairs.split("&");

            boolean matched2 = true;
            for (int k = 0; k < key_values.length; ++k) {
                String key_value_pair = key_values[k];
                String[] key_value = key_value_pair.split("=");
                String key = key_value[0];
                String value = key_value[1];
                Map<String, String> alarmDetail = alarm.getImpacts();
                if (alarmDetail.containsKey(key)) {
                    if (!matchExpression(value, alarmDetail.get(key))) {
                        matched2 = false;
                    }
                } else {
                    matched2 = false;
                }

                if (!matched2) {
                    break;
                }
            }

            if (matched2) {
                matched1 = true;
                break;
            }
        }

        return matched1;
    }
}
