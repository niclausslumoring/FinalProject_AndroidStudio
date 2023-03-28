package com.example.signup;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.signup.DBHelper;

public class userHelper {
    private static String database_table = "users";
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase DB;

    public userHelper(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        dbHelper = new DBHelper(context);
        DB = dbHelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        dbHelper.close();
    }

    public User getUserByUsername(String username) {
        User user = null;
        Cursor cursor = null;
        try {
            cursor = DB.query(database_table, null, "username=?", new String[]{username}, null, null, null);
            if (cursor.moveToFirst()) {
                user = new User(
                        cursor.getInt(cursor.getColumnIndex("userID")),
                        cursor.getString(cursor.getColumnIndex("username")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("region")),
                        cursor.getString(cursor.getColumnIndex("phone_number")),
                        cursor.getString(cursor.getColumnIndex("password"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return user;
    }
}
