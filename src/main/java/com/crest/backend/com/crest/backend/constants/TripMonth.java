package com.crest.backend.com.crest.backend.constants;

/**
 * Created by Arun on 10/13/16.
 */
public enum TripMonth {
    JAN(1),
    FEB(2),
    MAR(3),
    APR(4),
    MAY(5),
    JUN(6),
    JUL(7),
    AUG(8),
    SEP(9),
    OCT(10),
    NOV(11),
    DEC(12);

    int monthSequence;

    TripMonth(int monthSequence) {
        this.monthSequence = monthSequence;
    }

    public static TripMonth toTripMonth(String value) {
        TripMonth[] values = values();
        for (TripMonth tripMonth : values) {
            String str = tripMonth.getMonthSequence() + "";
            if (str.equals(value)) {
                return tripMonth;
            }
        }
        return null;
    }

    public int getMonthSequence() {
        return monthSequence;
    }
}
