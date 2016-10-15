package com.crest.backend.com.crest.backend.constants;

public enum TimeBucket {
    MORNING("Morning"),
    EARLY_MORNING("Early Morning"),
    NOON("Noon"),
    AFTERNOON("Afternoon"),
    EVENING("Evening"),
    NIGHT("Night");

    private String displayValue;

    TimeBucket(String displayValue) {
        this.displayValue = displayValue;
    }

    public static TimeBucket toTimeBucket(String reqParam) {
        int requestParam = Integer.parseInt(reqParam);
        if (requestParam < 9) {
            return EARLY_MORNING;
        } else if (requestParam < 11) {
            return MORNING;
        } else if (requestParam < 13) {
            return NOON;
        } else if (requestParam < 17) {
            return AFTERNOON;
        } else if (requestParam < 20) {
            return EVENING;
        } else {
            return NIGHT;
        }
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
