package com.varasccatalina.myapplication;

public class ScheduleItem {
    private String time;
    private String className;

    public ScheduleItem(String time, String className) {
        this.time = time;
        this.className = className;
    }

    public String getTime() {
        return time;
    }

    public String getClassName() {
        return className;
    }
}
