package com.example.samim.to_do_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;
import android.widget.AdapterView;

import static com.example.samim.to_do_application.DataBaseInformation.DATABASE_NAME;
import static com.example.samim.to_do_application.DataBaseInformation.TABLE_NAME;
import static com.example.samim.to_do_application.R.attr.title;

/**
 * Created by SAMIM on 10/10/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String QUERY = "Create Table " + DataBaseInformation.TABLE_NAME + "(" + DataBaseInformation.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataBaseInformation.KEY_TITLE + " TEXT," + DataBaseInformation.KEY_DESCRIPTION + " TEXT," + DataBaseInformation.DATE + " TEXT," + DataBaseInformation.KEY_STATUS + " TEXT" + ");";

    public DataBase(Context context) {
        super(context, DataBaseInformation.DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public int AddInformation(SQLiteDatabase db, String date, String description, String title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseInformation.KEY_TITLE, title);
        contentValues.put(DataBaseInformation.DATE, date);
        contentValues.put(DataBaseInformation.KEY_DESCRIPTION, description);
        String selection = DataBaseInformation.KEY_TITLE + " LIKE ?";
        String[] selection_arg = {title};
        int count = db.update(DataBaseInformation.TABLE_NAME, contentValues, selection, selection_arg);
        return count;
    }

    public Cursor GetInformation(SQLiteDatabase db) {
        String[] projections = {DataBaseInformation.KEY_TITLE, DataBaseInformation.KEY_DESCRIPTION, DataBaseInformation.DATE};
        Cursor cursor = db.query(DataBaseInformation.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public void DeleteIndormation(SQLiteDatabase db, String keyTitle) {
        String selection = DataBaseInformation.KEY_TITLE + " LIKE ?";
        String[] selection_arg = {keyTitle};
        db.delete(DataBaseInformation.TABLE_NAME, selection, selection_arg);
    }

    public int UpdateInformation(SQLiteDatabase database, String date, String title, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseInformation.DATE, date);
        contentValues.put(DataBaseInformation.KEY_DESCRIPTION, description);
        String selection = DataBaseInformation.KEY_TITLE + " LIKE ?";
        String[] selection_args = {title};
        int count = database.update(DataBaseInformation.TABLE_NAME, contentValues, selection, selection_args);
        return count;

    }
}

