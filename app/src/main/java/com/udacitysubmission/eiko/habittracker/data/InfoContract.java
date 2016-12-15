package com.udacitysubmission.eiko.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by eiko on 12/14/2016.
 */
public final class InfoContract {

    public static abstract class InfoEntry implements BaseColumns{

        public static final String TABLE_NAME ="info";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
    }
}
