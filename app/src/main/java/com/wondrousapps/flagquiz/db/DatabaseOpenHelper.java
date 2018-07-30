package com.wondrousapps.flagquiz.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import com.wondrousapps.flagquiz.model.FlagModel;

/**
 * Created by ntf-42 on 22/2/18.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "flagdb";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
//de_DE Deuttche
    //it_IT Espanol
    //hi_IN hindi
    //fr_FR Francais
    //en english
    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public ArrayList<FlagModel> getPoses(){
        SQLiteDatabase db=getWritableDatabase();
        //select * from flag where data_lang ="hi" ORDER BY RANDOM();
        Cursor cursor = db.rawQuery("SELECT * FROM flag ORDER BY RANDOM()", null);
        ArrayList<FlagModel> questionsArrayList=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                FlagModel quest = new FlagModel();

                quest.setImg(cursor.getString(0));
                quest.setOptA(cursor.getString(1));
                quest.setOptB(cursor.getString(2));
                quest.setOptC(cursor.getString(3));
                quest.setOptD(cursor.getString(4));
                quest.setAns(cursor.getString(5));
                questionsArrayList.add(quest);
            } while (cursor.moveToNext());
        }
        return questionsArrayList;
    }
    public ArrayList<FlagModel> getPoses(String lang){
        SQLiteDatabase db=getWritableDatabase();
        //select * from flag where data_lang ="hi" ORDER BY RANDOM();
        Cursor cursor = db.rawQuery("select * from flag where data_lang =\""+lang+"\" ORDER BY RANDOM()", null);
        ArrayList<FlagModel> questionsArrayList=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                FlagModel quest = new FlagModel();

                quest.setImg(cursor.getString(0));
                quest.setOptA(cursor.getString(1));
                quest.setOptB(cursor.getString(2));
                quest.setOptC(cursor.getString(3));
                quest.setOptD(cursor.getString(4));
                quest.setAns(cursor.getString(5));
                quest.setLang(cursor.getString(6));
                questionsArrayList.add(quest);
            } while (cursor.moveToNext());
        }
        return questionsArrayList;
    }
    public String getflagname(){

        return "";
    }
    public ArrayList<FlagModel> getPose2(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM flag", null);
        ArrayList<FlagModel> questionsArrayList=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                FlagModel quest = new FlagModel();
                quest.setImg(cursor.getString(0));
                quest.setOptA(cursor.getString(1));
                quest.setOptB(cursor.getString(2));
                quest.setOptC(cursor.getString(3));
                quest.setOptD(cursor.getString(4));
                quest.setAns(cursor.getString(5));
                questionsArrayList.add(quest);
            } while (cursor.moveToNext());
        }
        return questionsArrayList;
    }
    public String checkFlag(String flagimg,String lang){
        SQLiteDatabase db=getWritableDatabase();
        String t1 = null;
        Cursor cursor = db.rawQuery("select ans from flag where flagimg=\""+flagimg+"\" and data_lang=\""+lang+"\";", null);
        //Cursor cursor = db.rawQuery("select ans from flag where flagimg=\""+flagimg+"\";", null);
        ArrayList<FlagModel> questionsArrayList=new ArrayList<>();
if (cursor.moveToFirst()){
    FlagModel quest = new FlagModel();
   t1=cursor.getString(0);
}
        return t1;
    }
    public String checkFlag2(String ans){
        SQLiteDatabase db=getWritableDatabase();
        String t1 = null;
        Cursor cursor = db.rawQuery("select flagimg from flag where ans=\""+ans+"\";", null);
        ArrayList<FlagModel> questionsArrayList=new ArrayList<>();
        if (cursor.moveToFirst()){
            FlagModel quest = new FlagModel();
            t1=cursor.getString(0);
        }
        return t1;
    }
}