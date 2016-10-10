package com.lesliedahlberg.weatherclothes;

import android.provider.BaseColumns;

/**
 * Created by lesliedahlberg on 2016-10-10.
 */
public class ClothingContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ClothingContract() {}

    /* Inner class that defines the table contents */
    public static class ClothingEntry implements BaseColumns {
        public static final String TABLE_NAME = "clothing";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ILLUSTARTION = "illustration";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_RAIN_FROM = "rain_from";
        public static final String COLUMN_NAME_RAIN_TO = "rain_to";
        public static final String COLUMN_NAME_TEMPERATURE_FROM = "temperature_from";
        public static final String COLUMN_NAME_TEMPERATURE_TO = "temperature_to";
        public static final String COLUMN_NAME_WIND_FROM = "wind_from";
        public static final String COLUMN_NAME_WIND_TO = "wind_to";
        public static final String COLUMN_NAME_HUMIDITY_FROM = "humidity_from";
        public static final String COLUMN_NAME_HUMIDITY_TO = "humidity_to";
        public static final String COLUMN_NAME_CLOUDINESS_FROM = "cloudiness_from";
        public static final String COLUMN_NAME_CLOUDINESS_TO = "cloudiness_to";
    }
}


