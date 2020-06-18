package com.example.coolpiece.mypage.challenge;

import java.util.ArrayList;

public class Challenge {
    String certification;
    String day;
    String point;
    String attend;
    String startdate;
    ArrayList<String> day_check;

    public Challenge() {
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public ArrayList<String> getDay_check() {
        return day_check;
    }

    public void setDay_check(ArrayList<String> day_check) {
        this.day_check = day_check;
    }
}
