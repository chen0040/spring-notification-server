package com.github.chen0040.ws.enums;

public enum DutyShiftRepeatPattern {

    SameTimeIntervalOfEveryHour(1),
    SameTimeIntervalOfEveryDay(2),
    SameDaysOfEveryWeek(3),
    SameDaysOfEveryMonth(4),
    SameDaysOfEveryYear(5);

    private final int value;

    DutyShiftRepeatPattern(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
