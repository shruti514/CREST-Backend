package com.crest.backend.com.crest.backend.constants;

/**
 * Created by Arun on 10/13/16.
 */
public enum TimeBucket {
    MORNING("Morning"),
    EARLY_MORNING("Early Morning"),
    NOON("Noon"),
    AFTERNOON("Afternoon"),
    EVENING("Evening"),
    NIGHT("Night");

    private String displayValue;

    TimeBucket(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
