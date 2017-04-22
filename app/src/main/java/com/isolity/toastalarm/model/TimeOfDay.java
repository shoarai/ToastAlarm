package com.isolity.toastalarm.model;

/**
 * Created by shohei52a on 2017/04/22.
 */

public class TimeOfDay {
    private int hour;
    private int minute;

    public TimeOfDay(int h, int m) {
        hour = h;
        minute = m;
    }

    public String toString() {
        return   String.valueOf(hour) + ':' + String.valueOf(minute);
    }
}
