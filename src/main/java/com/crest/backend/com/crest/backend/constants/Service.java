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

    public static Service toService(String reqParam) {
        Service[] values = values();
        for (Service service : values) {
            if (reqParam.equals("1")) {
                return SUNDAY_HOLIDAY;
            }
            if (reqParam.equals("7")) {
                return SATURDAY;
            } else {
                return WEEKDAY;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

}
