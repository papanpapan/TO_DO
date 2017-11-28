package com.example.samim.to_do_application;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by SAMIM on 11/15/2017.
 */

public class Background_AsyncTask extends AsyncTask<String,Item,String> {
    Context context;
    Activity activity;
    Item_Adapter item_adapter;
    ListView listView;

    public Background_AsyncTask(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("save_info")) {
            String title = params[1];
            String description = params[2];
            String date = params[3];
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase database = dataBase.getWritableDatabase();
            dataBase.AddInformation(database, title, description, date);
            return "data Successfully Saved";

        }else if (method.equals("get_info")) {
            item_adapter = new Item_Adapter(context, R.layout.show_item_in_a_listview);
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase database = dataBase.getReadableDatabase();
            Cursor cursor = dataBase.GetInformation(database);
            while (cursor.moveToNext()) {
                listView = (ListView) activity.findViewById(R.id.ShowList);
                String date, title, description, ststus;
                date = cursor.getString(cursor.getColumnIndex(DataBaseInformation.DATE));
                title = cursor.getString(cursor.getColumnIndex(DataBaseInformation.KEY_TITLE));
                description = cursor.getString(cursor.getColumnIndex(DataBaseInformation.KEY_DESCRIPTION));
                ststus = cursor.getString(cursor.getColumnIndex(DataBaseInformation.KEY_STATUS));
                Item product = new Item(title, description, date, ststus);
                publishProgress(product);
            }
            return "get_info";
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Item... values) {
        item_adapter.add(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_info")) {
            listView.setAdapter(item_adapter);
        } else {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
    }

}
