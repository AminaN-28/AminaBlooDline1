
package com.example.blood_line;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BL_DATABASE extends SQLiteOpenHelper {



    public void BL_DATABASE(){
    }



    public BL_DATABASE(Context context) {
        super(context, "bloodlines.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String s="create table utilisateur (nom Text, age INTEGER, region Text, groupeS Text, number INTEGER, password Text);" ;
        db.execSQL(s);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  }



}
