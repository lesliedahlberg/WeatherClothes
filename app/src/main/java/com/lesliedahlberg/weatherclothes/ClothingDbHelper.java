package com.lesliedahlberg.weatherclothes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lesliedahlberg on 2016-10-10.
 */
public class ClothingDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Clothing.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String NUM_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ClothingContract.ClothingEntry.TABLE_NAME + " (" +
                    ClothingContract.ClothingEntry._ID + " INTEGER PRIMARY KEY," +
                    ClothingContract.ClothingEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_ILLUSTARTION + TEXT_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_GENDER + TEXT_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_RAIN_FROM + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_RAIN_TO + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_TEMPERATURE_FROM + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_TEMPERATURE_TO + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_WIND_FROM + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_WIND_TO + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_HUMIDITY_FROM + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_HUMIDITY_TO + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_CLOUDINESS_FROM + NUM_TYPE + COMMA_SEP +
                    ClothingContract.ClothingEntry.COLUMN_NAME_CLOUDINESS_TO + NUM_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ClothingContract.ClothingEntry.TABLE_NAME;

    public ClothingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}