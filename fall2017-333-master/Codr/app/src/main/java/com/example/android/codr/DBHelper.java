package com.example.android.codr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.codr.R.id.username;

/**
 * Created by kimb7669 on 9/27/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="codr.db";

    public DBHelper(Context activityContext) {
        super(activityContext, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqlIteDatabase) {
        sqlIteDatabase.execSQL(
                "CREATE TABLE users (" +
                        "id INT PRIMARY KEY, " +
                        "username TEXT," +
                        "password TEXT)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }
    public void insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        db.insert("users", null, contentValues); // this has what you want to insert into the user
    }
    public User getUserByUsername(String username) {
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM users WHERE TRIM(username) = '"+username.trim()+"'", null);


        // Move the cursor to the first row
        result.moveToFirst();
        String name = result.getString(result.getColumnIndex("username"));
        String password = result.getString(result.getColumnIndex("password"));

        return new User(name, password);
    }
}
