package com.example.samim.to_do_application;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.samim.to_do_application.DataBaseInformation.TABLE_NAME;
import static com.example.samim.to_do_application.R.id.item;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    EditText title, description;
    DatePicker datePicker;
    Button save, cancel;
    ListView listView;
    Item_Adapter item_adapter;
    View view1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.ShowList);
        String method = "get_info";
        Background_AsyncTask background_asyncTask = new Background_AsyncTask(this);
        background_asyncTask.execute(method);
        item_adapter = new Item_Adapter(this, R.layout.dilog);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Toast.makeText(getApplicationContext(), "Click On Position : " + item_adapter.getItemId(i), Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                view1 = getLayoutInflater().inflate(R.layout.dilog, null);
                title = (EditText) view1.findViewById(R.id.txtTitel);
                description = (EditText) view1.findViewById(R.id.txtDescription);
                datePicker = (DatePicker) view1.findViewById(R.id.datePicker);
                save = (Button) view1.findViewById(R.id.btnSave);
                cancel = (Button) view1.findViewById(R.id.btnCancel);
                builder.setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String TITLE, DESCRIPTION, DATE;
                        TITLE = title.getText().toString();
                        DESCRIPTION = description.getText().toString();
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth();
                        int year = datePicker.getYear();
                        DATE = day + " / " + month + " / " + year;
                        if (!TITLE.isEmpty() && !DESCRIPTION.isEmpty() && !DATE.isEmpty()) {
                            DataBase dataBase = new DataBase((Context) getApplicationContext());
                            SQLiteDatabase database = dataBase.getWritableDatabase();
                            dataBase.UpdateInformation(database, DATE, TITLE, DESCRIPTION);

                            Toast.makeText(getApplicationContext(), "Update Success...!!!" + "\n" + TITLE + "\n" + DESCRIPTION + "\n" + DATE, Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter The All Details....!!!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Dialog is Dissmiss....!!!!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dilog);
                dialog.show();

                Button save = (Button) dialog.findViewById(R.id.btnSave);
                Button cancel = (Button) dialog.findViewById(R.id.btnCancel);

                final EditText Description = (EditText) dialog.findViewById(R.id.edDescription);
                final EditText Title = (EditText) dialog.findViewById(R.id.edTitel);
                final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String KEY_DESCRIPTION, KEY_TITLE, DATE;
                        KEY_DESCRIPTION = Description.getText().toString();
                        KEY_TITLE = Title.getText().toString();
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth();
                        int year = datePicker.getYear();
                        DATE = day + " / " + month + " / " + year;

                        if (!KEY_TITLE.isEmpty() && !KEY_DESCRIPTION.isEmpty() && !DATE.isEmpty()) {
                            DataBase dataBase = new DataBase((Context) getApplicationContext());
                            SQLiteDatabase db = dataBase.getWritableDatabase();
                            dataBase.AddInformation(db, DATE, KEY_TITLE, KEY_DESCRIPTION);

                            Toast.makeText(getApplicationContext(), "Item Successfully Added...!!!" + "\n" + KEY_TITLE + "\n" + KEY_DESCRIPTION + "\n" + DATE, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter The All Details....!!!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Dialog is Dissmiss", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.Delete:
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                View view2;
                view2 = getLayoutInflater().inflate(R.layout.delete_dialog, null, false);
                builder1.setView(view2);
                Button delbutton = (Button) view2.findViewById(R.id.Delete);
                Button cancelbutton = (Button) view2.findViewById(R.id.btNo);
                final EditText delete = (EditText) view2.findViewById(R.id.DltSearch);
                final AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                delbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        builder2.setTitle("Delete The Detials...!!!");
                        builder2.setMessage("Are You Sure to delete..?????");
                        builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String del = delete.getText().toString();
                                DataBase dbOperation = new DataBase(MainActivity.this);
                                SQLiteDatabase database = dbOperation.getWritableDatabase();
                                dbOperation.DeleteIndormation(database, del);
                                AlertDialog dialog = builder2.create();
                                Toast.makeText(MainActivity.this, "Information Successfully Deleted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog dialog1 = builder2.create();
                                dialog1.dismiss();
                                Toast.makeText(MainActivity.this, "Dialog Dismiss", Toast.LENGTH_SHORT).show();

                            }
                        });
                        AlertDialog alertDialog2 = builder2.create();
                        alertDialog2.show();
                    }

                });
                cancelbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog1.dismiss();
                    }
                });

                break;
            case R.id.Like_logo:
                imageView = (ImageView) findViewById(R.id.Like_logo);
                imageView.setColorFilter(Color.parseColor("#03a9f4"));
            }
            return super.onOptionsItemSelected(item);
        }

}

