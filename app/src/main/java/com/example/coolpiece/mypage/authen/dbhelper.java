package com.example.coolpiece.mypage.authen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class dbhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;

    public dbhelper(Context context){
        super(context,"authenDB",null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String authenSql="create table tb_authen("+
                "email not null,"+
                "certificate_name not null,"+
                "certificate_serial_num,"+
                "birthday,"+
                "certificate_date,"+
                "certificate_institution)";

        db.execSQL(authenSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion==DATABASE_VERSION){
            db.execSQL("drop table tb_authen");
        }
        onCreate(db);
    }
}
