package com.example.otp_android;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by aritzbi on 16/05/13.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NICK = "nick";
    public static final String COLUMN_PASSPHRASE = "passphrase";
    public static final String COLUMN_COUNTER = "counter";

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NICK
            + " text not null, "+COLUMN_PASSPHRASE
            + " text not null, "+COLUMN_COUNTER
            + " integer);";

    public MySQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	if(newVersion > oldVersion){                    
    		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    	     onCreate(db);
    }
    }

}
