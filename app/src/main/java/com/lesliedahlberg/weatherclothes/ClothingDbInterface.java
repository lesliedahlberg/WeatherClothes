package com.lesliedahlberg.weatherclothes;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by lesliedahlberg on 2016-10-10.
 */
public class ClothingDbInterface {

    Context context;
    ClothingDbHelper clothingDbHelper;

    public ClothingDbInterface(Context context){
        this.context = context;
        clothingDbHelper = new ClothingDbHelper(context);
    }

    public void readDatabase(){
        SQLiteDatabase db = clothingDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ClothingContract.ClothingEntry._ID,
                ClothingContract.ClothingEntry.COLUMN_NAME_TITLE,
                ClothingContract.ClothingEntry.COLUMN_NAME_DESCRIPTION
        };

        //Filter
        String selection = null;
        String[] selectionArgs = null;

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ClothingContract.ClothingEntry._ID + " ASC";

        Cursor c = db.query(
                ClothingContract.ClothingEntry.TABLE_NAME,// The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();


        Log.d("DBRead", "Starting reading.");
        while(true){
            Log.d("DBRead", String.valueOf(c.getInt(c.getColumnIndex(ClothingContract.ClothingEntry._ID)))+c.getString(c.getColumnIndex(ClothingContract.ClothingEntry.COLUMN_NAME_TITLE)));

            if(c.isLast()){
                break;
            }
            c.moveToNext();
        }


    }

    public void insertDefaultData(){

        //Open .CSV file with default data for database
        String mCSVfile = "clothing.csv";
        AssetManager manager = context.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(mCSVfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write data to database
        SQLiteDatabase db = clothingDbHelper.getWritableDatabase();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(";");
                if (colums.length != 14) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_TITLE, colums[0].trim().equals("NULL")?null:colums[0].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_DESCRIPTION, colums[1].trim().equals("NULL")?null:colums[1].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_ILLUSTARTION, colums[2].trim().equals("NULL")?null:colums[2].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_GENDER, colums[3].trim().equals("NULL")?null:colums[3].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_RAIN_FROM, colums[4].trim().equals("NULL")?null:colums[4].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_RAIN_TO, colums[5].trim().equals("NULL")?null:colums[5].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_TEMPERATURE_FROM, colums[6].trim().equals("NULL")?null:colums[6].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_TEMPERATURE_TO, colums[7].trim().equals("NULL")?null:colums[7].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_WIND_FROM, colums[8].trim().equals("NULL")?null:colums[8].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_WIND_TO, colums[9].trim().equals("NULL")?null:colums[9].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_HUMIDITY_FROM, colums[10].trim().equals("NULL")?null:colums[10].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_HUMIDITY_TO, colums[11].trim().equals("NULL")?null:colums[11].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_CLOUDINESS_FROM, colums[12].trim().equals("NULL")?null:colums[12].trim());
                cv.put(ClothingContract.ClothingEntry.COLUMN_NAME_CLOUDINESS_TO, colums[13].trim().equals("NULL")?null:colums[13].trim());
                db.insert(ClothingContract.ClothingEntry.TABLE_NAME, null, cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
