package com.example.coolpiece.mypage.authen;

public  class Authentificate_user {
    public String email;
    public String certificate_name;
    public String certificate_serial_num;
    public String birthday;
    public String certificate_date;
    public String certificate_institution;

    public Authentificate_user(String name,String num,String birth,String date,String institution){
        //this.email = email;
        this.certificate_name=name;
        this.certificate_serial_num=num;
        this.birthday=birth;
        this.certificate_date = date;
        this.certificate_institution=institution;

    };
}
