package com.example.coolpiece;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Gisul implements Parcelable {
    String name;
    String intro;
    String association;
    String major;
    String training_center;
    String test_subject;
    String test_method;
    String cut_line;
    ArrayList<String> pilgi_apply;
    ArrayList<String> pilgi_test;
    ArrayList<String> pilgi_balpyo;
    ArrayList<String> pilgi_balpyo_final;
    ArrayList<String> silgi_apply;
    ArrayList<String> silgi_test;
    ArrayList<String> final_balpyo;
    ArrayList<String> academy_name;
    ArrayList<String> academy_address;
    ArrayList<String> academy_phone;

    protected Gisul(Parcel in) {
        name = in.readString();
        intro = in.readString();
        association = in.readString();
        major = in.readString();
        training_center = in.readString();
        test_subject = in.readString();
        test_method = in.readString();
        cut_line = in.readString();
        pilgi_apply = in.createStringArrayList();
        pilgi_test = in.createStringArrayList();
        pilgi_balpyo = in.createStringArrayList();
        pilgi_balpyo_final = in.createStringArrayList();
        silgi_apply = in.createStringArrayList();
        silgi_test = in.createStringArrayList();
        final_balpyo = in.createStringArrayList();
        academy_name = in.createStringArrayList();
        academy_address = in.createStringArrayList();
        academy_phone = in.createStringArrayList();
    }

    public static final Creator<Gisul> CREATOR = new Creator<Gisul>() {
        @Override
        public Gisul createFromParcel(Parcel in) {
            return new Gisul(in);
        }

        @Override
        public Gisul[] newArray(int size) {
            return new Gisul[size];
        }
    };

    public Gisul() {

    }

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

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTraining_center() {
        return training_center;
    }

    public void setTraining_center(String training_center) {
        this.training_center = training_center;
    }

    public String getTest_subject() {
        return test_subject;
    }

    public void setTest_subject(String test_subject) {
        this.test_subject = test_subject;
    }

    public String getTest_method() {
        return test_method;
    }

    public void setTest_method(String test_method) {
        this.test_method = test_method;
    }

    public String getCut_line() {
        return cut_line;
    }

    public void setCut_line(String cut_line) {
        this.cut_line = cut_line;
    }

    public ArrayList<String> getPilgi_apply() {
        return pilgi_apply;
    }

    public void setPilgi_apply(ArrayList<String> pilgi_apply) {
        this.pilgi_apply = pilgi_apply;
    }

    public ArrayList<String> getPilgi_test() {
        return pilgi_test;
    }

    public void setPilgi_test(ArrayList<String> pilgi_test) {
        this.pilgi_test = pilgi_test;
    }

    public ArrayList<String> getPilgi_balpyo() {
        return pilgi_balpyo;
    }

    public void setPilgi_balpyo(ArrayList<String> pilgi_balpyo) {
        this.pilgi_balpyo = pilgi_balpyo;
    }

    public ArrayList<String> getPilgi_balpyo_final() {
        return pilgi_balpyo_final;
    }

    public void setPilgi_balpyo_final(ArrayList<String> pilgi_balpyo_final) {
        this.pilgi_balpyo_final = pilgi_balpyo_final;
    }

    public ArrayList<String> getSilgi_apply() {
        return silgi_apply;
    }

    public void setSilgi_apply(ArrayList<String> silgi_apply) {
        this.silgi_apply = silgi_apply;
    }

    public ArrayList<String> getSilgi_test() {
        return silgi_test;
    }

    public void setSilgi_test(ArrayList<String> silgi_test) {
        this.silgi_test = silgi_test;
    }

    public ArrayList<String> getFinal_balpyo() {
        return final_balpyo;
    }

    public void setFinal_balpyo(ArrayList<String> final_balpyo) {
        this.final_balpyo = final_balpyo;
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
    public void printall(){
        System.out.println("======================================");
        System.out.println(this.name);
        System.out.println(this.intro);
        System.out.println(this.association);
        System.out.println(this.major);
        System.out.println(this.training_center);
        System.out.println(this.test_subject);
        System.out.println(this.test_method);
        System.out.println(this.cut_line);
        System.out.println(pilgi_apply.size());
        for(int i=0; i<pilgi_apply.size(); i++){
            System.out.println(this.pilgi_apply.get(i));
            System.out.println(this.pilgi_test.get(i));
            System.out.println(this.pilgi_balpyo.get(i));
            System.out.println(this.pilgi_balpyo_final.get(i));
            System.out.println(this.silgi_apply.get(i));
            System.out.println(this.silgi_test.get(i));
            System.out.println(this.final_balpyo.get(i));
        }
        System.out.println("=====================================");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(intro);
        dest.writeString(association);
        dest.writeString(major);
        dest.writeString(training_center);
        dest.writeString(test_subject);
        dest.writeString(test_method);
        dest.writeString(cut_line);
        dest.writeStringList(pilgi_apply);
        dest.writeStringList(pilgi_test);
        dest.writeStringList(pilgi_balpyo);
        dest.writeStringList(pilgi_balpyo_final);
        dest.writeStringList(silgi_apply);
        dest.writeStringList(silgi_test);
        dest.writeStringList(final_balpyo);
        dest.writeStringList(academy_name);
        dest.writeStringList(academy_address);
        dest.writeStringList(academy_phone);
    }
}
