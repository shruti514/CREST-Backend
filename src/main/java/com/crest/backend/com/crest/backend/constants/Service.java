package com.crest.backend.com.crest.backend.constants;

public enum Service {
    WEEKDAY(1),
    SATURDAY(2),
    SUNDAY_HOLIDAY(3),
    JULY(4);


    private int value;

    Service(int index) {
        value = index;
    }

    public int getValue() {
        return value;
    }

}
