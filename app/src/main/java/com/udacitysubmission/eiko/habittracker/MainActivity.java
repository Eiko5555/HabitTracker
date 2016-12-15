package com.udacitysubmission.eiko.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacitysubmission.eiko.habittracker.data.InfoContract;
import com.udacitysubmission.eiko.habittracker.data.SqliteDB;

public class MainActivity extends AppCompatActivity {

    private SqliteDB mDhHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDhHelper = new SqliteDB(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)
                findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        EditorActivity.class);
                startActivity(intent);
            }
        });
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo(){
        SQLiteDatabase db = mDhHelper.getReadableDatabase();
        String[] projection = {InfoContract.InfoEntry.COLUMN_NAME,
                InfoContract.InfoEntry.COLUMN_PHONE,
                InfoContract.InfoEntry._ID};
        Cursor cursor = db.query(InfoContract.InfoEntry.TABLE_NAME,
                projection, null, null, null, null, null);
        TextView displayview = (TextView)findViewById(R.id.textview);
        try{
            displayview.setText("Number of contact info saved:  "+
            cursor.getCount() + "\n\n");
            displayview.append(InfoContract.InfoEntry._ID + "  ,  "+
                    InfoContract.InfoEntry.COLUMN_NAME + "  ,  " +
                    InfoContract.InfoEntry.COLUMN_PHONE + "\n");
            int idColumIndex = cursor.getColumnIndex(InfoContract.InfoEntry._ID);
            int nameColumIndex = cursor.getColumnIndex(InfoContract.InfoEntry.COLUMN_NAME);
            int phoneColumIndex = cursor.getColumnIndex(InfoContract.InfoEntry.COLUMN_PHONE);

            while (cursor.moveToNext()){
                int curentID = cursor.getInt(idColumIndex);
                String currentName = cursor.getString(nameColumIndex);
                int currentPhone = cursor.getInt(phoneColumIndex);

                displayview.append("\n" + curentID + "  ,  " +
                currentName + "  ,  " + currentPhone);
            }
        }finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
