package com.example.logicgates;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "gate.db";
    private static final String TABLE_NAME = "Players";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+" ( PID Integer PRIMARY KEY AUTOINCREMENT, NAME Text, SCORE Integer)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public void addPlayer(Player player) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("NAME", player.getName());
            cv.put("SCORE", player.getScore());
            db.insert(TABLE_NAME, null, cv);
            db.close();
        } catch (Exception e) {
            //error msg here
        }
    }

    public ArrayList<Player> viewAllScores() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery("Select * from "+ TABLE_NAME,null);
            ArrayList<Player> playerArrayList = new ArrayList<>();
            if (cr.moveToFirst()) {
                do {
                    playerArrayList.add(new Player(cr.getString(1), cr.getString(2)));
                } while (cr.moveToNext());
            }
            return playerArrayList;
        }
        catch (Exception e) {
            return null;
        }
    }
}
