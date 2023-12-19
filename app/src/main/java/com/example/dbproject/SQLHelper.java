package com.example.dbproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class SQLHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "uneBase.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + PersonneContrat.Personne.TABLE_NAME;

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + PersonneContrat.Personne.TABLE_NAME + " (" +
                    PersonneContrat.Personne._ID + " INTEGER PRIMARY KEY," +
                    PersonneContrat.Personne.TABLE_COLUM_NAME_NOM + TEXT_TYPE + COMMA_SEP +
                    PersonneContrat.Personne.TABLE_COLUM_NAME_AGE + INT_TYPE + COMMA_SEP +
                    PersonneContrat.Personne.TABLE_COLUM_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    PersonneContrat.Personne.TABLE_COLUM_NAME_POSTE + TEXT_TYPE +
                    ")";



    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
    }
}
