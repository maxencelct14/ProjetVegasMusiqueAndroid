package com.example.projetvegasmusique.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projetvegasmusique.db";
    private static final int DATABASE_VERSION = 1;

    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req1 = "create table musique (name text, artiste_id number, date_création date)"; // ajout de la colonne artiste_id
        db.execSQL(req1);
        String req2 = "create table artiste (id number, name text, description text, dateCrea date, dateUpdate date)";
        db.execSQL(req2);
        String req3 = "create table playlist (id text, titreplay text)";
        db.execSQL(req3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS musique");
        db.execSQL("DROP TABLE IF EXISTS artiste");
        db.execSQL("DROP TABLE IF EXISTS playlist");
        onCreate(db);
    }

    public Cursor getData(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"id", "name", "description", "dateCrea", "dateUpdate"};
        String sortOrder = "name ASC";
        Cursor cursor = db.query(tableName, projection, null, null, null, null, sortOrder);
        return cursor;
    }
}
