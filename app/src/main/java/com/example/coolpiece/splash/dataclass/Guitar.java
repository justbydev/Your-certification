package com.example.coolpiece.splash.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Guitar implements Parcelable {
    String name;
    String intro;
    String exam;
    String cut_line;
    String enrollment;
    String association;
    String training_center;
    ArrayList<String> academy_name;
    ArrayList<String> academy_address;
    ArrayList<String> academy_phone;
    ArrayList<String> academy_connect;
    public Guitar(){}

    protected Guitar(Parcel in) {
        name = in.readString();
        intro = in.readString();
        exam = in.readString();
        cut_line = in.readString();
        enrollment = in.readString();
        association = in.readString();
        training_center = in.readString();
        academy_name = in.createStringArrayList();
        academy_address = in.createStringArrayList();
        academy_phone = in.createStringArrayList();
        academy_connect = in.createStringArrayList();
    }

    public static final Creator<Guitar> CREATOR = new Creator<Guitar>() {
        @Override
        public Guitar createFromParcel(Parcel in) {
            return new Guitar(in);
        }

        @Override
        public Guitar[] newArray(int size) {
            return new Guitar[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getCut_line() {
        return cut_line;
    }

    public void setCut_line(String cut_line) {
        this.cut_line = cut_line;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getTraining_center() {
        return training_center;
    }

    public void setTraining_center(String training_center) {
        this.training_center = training_center;
    }

    public ArrayList<String> getAcademy_name() {
        return academy_name;
    }

    public void setAcademy_name(ArrayList<String> academy_name) {
        this.academy_name = academy_name;
    }

    public ArrayList<String> getAcademy_address() {
        return academy_address;
    }

    public void setAcademy_address(ArrayList<String> academy_address) {
        this.academy_address = academy_address;
    }

    public ArrayList<String> getAcademy_phone() {
        return academy_phone;
    }

    public void setAcademy_phone(ArrayList<String> academy_phone) {
        this.academy_phone = academy_phone;
    }

    public ArrayList<String> getAcademy_connect() {
        return academy_connect;
    }

    public void setAcademy_connect(ArrayList<String> academy_connect) {
        this.academy_connect = academy_connect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(intro);
        dest.writeString(exam);
        dest.writeString(cut_line);
        dest.writeString(enrollment);
        dest.writeString(association);
        dest.writeString(training_center);
        dest.writeStringList(academy_name);
        dest.writeStringList(academy_address);
        dest.writeStringList(academy_phone);
        dest.writeStringList(academy_connect);
    }
}
