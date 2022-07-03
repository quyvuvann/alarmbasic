package com.example.alarm;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Alarm implements Serializable {
    EditTimeActivity editTimeActivity;
    int imageView;
    String mTitle;
    Boolean mSwitch = false;
    int hourStartTime, hourEndTime, minuteStartTime, minuteEndTime;
    String date;
    private int id;

    public Alarm() {
    }

    public Alarm(int id, int imageView, String mTitle, Boolean mSwitch, int hourStartTime, int hourEndTime, int minuteStartTime, int minuteEndTime, String date) {
        this.id = id;
        this.imageView = imageView;
        this.mTitle = mTitle;
        this.mSwitch = mSwitch;
        this.hourStartTime = hourStartTime;
        this.hourEndTime = hourEndTime;
        this.minuteStartTime = minuteStartTime;
        this.minuteEndTime = minuteEndTime;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Boolean getmSwitch() {
        return mSwitch;
    }

    public void setmSwitch(Boolean mSwitch) {
        this.mSwitch = mSwitch;
    }

    public int getHourStartTime() {
        return hourStartTime;
    }

    public void setHourStartTime(int hourStartTime) {
        this.hourStartTime = hourStartTime;
    }

    public int getHourEndTime() {
        return hourEndTime;
    }

    public void setHourEndTime(int hourEndTime) {
        this.hourEndTime = hourEndTime;
    }

    public int getMinuteStartTime() {
        return minuteStartTime;
    }

    public void setMinuteStartTime(int minuteStartTime) {
        this.minuteStartTime = minuteStartTime;
    }

    public int getMinuteEndTime() {
        return minuteEndTime;
    }

    public void setMinuteEndTime(int minuteEndTime) {
        this.minuteEndTime = minuteEndTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " " + imageView + " " + mTitle + " " + mSwitch + " " + hourStartTime + " " + hourEndTime + " " + minuteStartTime + " " + minuteEndTime + " " + date;
    }
}
