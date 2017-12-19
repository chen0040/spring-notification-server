package com.github.chen0040.ws.models;


import com.github.chen0040.ws.enums.DutyShiftRepeatPattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class DutyShift {
    private String companyId = "";
    private String shiftId = "";
    private long startTime = 0L;
    private long endTime = 0L;
    private int repeatCount = -1;
    private long activeDateTime = 0L;
    private DutyShiftRepeatPattern repeatPattern = DutyShiftRepeatPattern.SameDaysOfEveryWeek;

    private Set<String> logicalGroupsInScope = new HashSet<>();
    private Set<String> dutyOfficersInScope = new HashSet<>();

    private String note = "";


    public boolean isServicingLogicalGroup(String groupId){
        return logicalGroupsInScope.contains(groupId);
    }

    public boolean inSchedule(long timestamp) {

        if(repeatCount == 0){
            return false;
        }

        if(repeatCount == 1){
            return timestamp >= startTime && timestamp <= endTime;
        }

        Date date = new Date(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        Date startDate = new Date(startTime);
        calendar.setTime(startDate);
        int startHour = calendar.get(Calendar.HOUR_OF_DAY);
        int startMinute = calendar.get(Calendar.MINUTE);
        int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int startDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int startDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        Date endDate = new Date(endTime);
        calendar.setTime(endDate);
        int endHour = calendar.get(Calendar.HOUR_OF_DAY);
        int endMinute = calendar.get(Calendar.MINUTE);
        int endDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int endDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int endDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);


        if(repeatPattern == DutyShiftRepeatPattern.SameTimeIntervalOfEveryHour){
            if(endMinute < startMinute){
                return !(minute >= endMinute && minute <= startMinute);
            } else {
                return minute >= startMinute && minute <= endMinute;
            }
        } else if(repeatPattern == DutyShiftRepeatPattern.SameTimeIntervalOfEveryDay){
            int startMinuteTotal = startHour * 60 + startMinute;
            int endMinuteTotal = endHour * 60 + endMinute;
            int minuteTotal = hour * 60 + minute;
            if(endMinuteTotal < startMinuteTotal){
                return !(minuteTotal >= endMinuteTotal && minuteTotal <= startMinuteTotal);
            } else {
                return minuteTotal >= startMinuteTotal && minuteTotal <= endMinuteTotal;
            }
        } else if(repeatPattern == DutyShiftRepeatPattern.SameDaysOfEveryWeek){
            int startMinuteTotal = (startDayOfWeek * 24 + startHour) * 60 + startMinute;
            int endMinuteTotal = (endDayOfWeek * 24 + endHour) * 60 + endMinute;
            int minuteTotal = (dayOfWeek * 24 + hour) * 60 + minute;
            if(endMinuteTotal < startMinuteTotal){
                return !(minuteTotal >= endMinuteTotal && minuteTotal <= startMinuteTotal);
            } else {
                return minuteTotal >= startMinuteTotal && minuteTotal <= endMinuteTotal;
            }
        } else if(repeatPattern == DutyShiftRepeatPattern.SameDaysOfEveryMonth) {
            int startMinuteTotal = (startDayOfMonth * 24 + startHour) * 60 + startMinute;
            int endMinuteTotal = (endDayOfMonth * 24 + endHour) * 60 + endMinute;
            int minuteTotal = (dayOfMonth * 24 + hour) * 60 + minute;
            if(endMinuteTotal < startMinuteTotal){
                return !(minuteTotal >= endMinuteTotal && minuteTotal <= startMinuteTotal);
            } else {
                return minuteTotal >= startMinuteTotal && minuteTotal <= endMinuteTotal;
            }
        } else if(repeatPattern == DutyShiftRepeatPattern.SameDaysOfEveryYear) {
            int startMinuteTotal = (startDayOfYear * 24 + startHour) * 60 + startMinute;
            int endMinuteTotal = (endDayOfYear * 24 + endHour) * 60 + endMinute;
            int minuteTotal = (dayOfYear * 24 + hour) * 60 + minute;
            if(endMinuteTotal < startMinuteTotal){
                return !(minuteTotal >= endMinuteTotal && minuteTotal <= startMinuteTotal);
            } else {
                return minuteTotal >= startMinuteTotal && minuteTotal <= endMinuteTotal;
            }
        }

        return false;
    }
}
