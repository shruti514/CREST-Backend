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

    public String getDisplayValue() {
        return displayValue;
    }
}
