package com.example.signup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "JSteam.db";

    public DBHelper(Context context) {
        super(context, "JSteam.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(userID INTEGER PRIMARY KEY, username TEXT, email TEXT, region TEXT, phone_number TEXT, password TEXT)");
        MyDB.execSQL("INSERT INTO users (username, email, region, phone_number, password) VALUES ('admin', 'admin@gmail.com', 'Jakarta', '085156883552', '12345678')");

        MyDB.execSQL("create Table games(gameID INTEGER PRIMARY KEY, gameImage TEXT, gameName TEXT, gameGenre TEXT, gameRating INTEGER, gameDesc TEXT, gamePrice TEXT)");
        MyDB.execSQL("INSERT INTO games(gameImage, gameName, gameGenre, gameRating, gameDesc, gamePrice) VALUES ('valo', 'Valorant', 'FPS', 10, 'valorant adalah game FPS terbaik di dunia', '120000')");
        MyDB.execSQL("INSERT INTO games(gameImage, gameName, gameGenre, gameRating, gameDesc, gamePrice) VALUES ('ml', 'Mobile Legend', 'Moba', 6, 'Moba kok analog', '160000')");

        MyDB.execSQL("create Table reviews(reviewID INTEGER PRIMARY KEY, gameID INTEGER, userID INTEGER, userComment TEXT, FOREIGN KEY(gameID) REFERENCES games(gameID), FOREIGN KEY(userID) REFERENCES users(userID))");
        MyDB.execSQL("INSERT INTO reviews(gameID, userID, userComment) VALUES(1, 1, 'Bagus banget')");
        MyDB.execSQL("INSERT INTO reviews(gameID, userID, userComment) VALUES(2, 1, 'Jelek banget')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists games");
        MyDB.execSQL("drop Table if exists reviews");
        onCreate(MyDB);
    }

    public boolean insertData(String username, String email, String region, String phone_number, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", username);
        contentValues.put("region", username);
        contentValues.put("phone_number", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

}
