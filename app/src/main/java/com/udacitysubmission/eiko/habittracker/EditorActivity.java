package com.udacitysubmission.eiko.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.udacitysubmission.eiko.habittracker.data.InfoContract;
import com.udacitysubmission.eiko.habittracker.data.SqliteDB;

/**
 * Created by eiko on 12/14/2016.
 */
public class EditorActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        mName = (EditText) findViewById(R.id.edit_name);
        mPhoneNumber = (EditText)findViewById(R.id.edit_phonenumber);
    }
    private void insertInfo(){
        String nameString = mName.getText().toString().trim();
        String phoneString = mPhoneNumber.getText().toString();
        int phonenum = Integer.parseInt(phoneString);

        SqliteDB mDbHelper = new SqliteDB(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(InfoContract.InfoEntry.COLUMN_NAME, nameString);
        value.put(InfoContract.InfoEntry.COLUMN_PHONE, phonenum);
        long newRowID = db.insert(InfoContract.InfoEntry.TABLE_NAME,
                null, value);
        if (newRowID == -1){
            Toast.makeText(this, "Please type somethig.", Toast.LENGTH_LONG);
        }else {
            Toast.makeText(this,"New contact ID: " + newRowID, Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertInfo();
                finish();
                return true;
            case R.id.action_delete:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
