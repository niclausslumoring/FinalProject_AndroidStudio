package com.example.signup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class reviewHelper {
    private static String database_table = "reviews";

    private Context context;

    private DBHelper dbHelper;

    private SQLiteDatabase DB;

    public reviewHelper(Context context){
        this.context = context;
    }

    public void open() throws SQLException{
        dbHelper = new DBHelper(context);
        DB = dbHelper.getWritableDatabase();
    }

    public void close() throws SQLException{
        dbHelper.close();
    }

    public ArrayList<Review> viewReview() {
        ArrayList<Review> reviewList = new ArrayList<>();
        String query = "SELECT reviews.*, users.username, games.gameName, games.gameImage FROM reviews INNER JOIN users ON reviews.userID = users.userID INNER JOIN games ON reviews.gameID = games.gameID";

        Cursor cursor = DB.rawQuery(query, null);

        cursor.moveToFirst();

        Review review;
        Integer reviewID, userID, gameID;
        String gameImage, gameName, userComment, username;

        if (cursor.getCount() > 0) {
            do {
                reviewID = cursor.getInt(cursor.getColumnIndexOrThrow("reviewID"));
                userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"));
                gameID = cursor.getInt(cursor.getColumnIndexOrThrow("gameID"));
                gameImage = cursor.getString(cursor.getColumnIndexOrThrow("gameImage"));
                gameName = cursor.getString(cursor.getColumnIndexOrThrow("gameName"));
                userComment = cursor.getString(cursor.getColumnIndexOrThrow("userComment"));
                username = cursor.getString(cursor.getColumnIndexOrThrow("username"));

                review = new Review(reviewID, userID, gameID, gameImage, gameName, userComment, username);
                reviewList.add(review);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();

        return reviewList;
    }
    public void updateReview(Review review) {
        ContentValues values = new ContentValues();
        values.put("userComment", review.getUserComment());
        String whereClause = "reviewID = ?";
        String[] whereArgs = { String.valueOf(review.getReviewID()) };
        DB.update(database_table, values, whereClause, whereArgs);
    }
    public void deleteReview(Review review) {
        String whereClause = "reviewID=?";
        String[] whereArgs = { Integer.toString(review.getReviewID()) };
        DB.delete(database_table, whereClause, whereArgs);
    }

    public void insertReview(Integer gameId, Integer userId, String comment){
        ContentValues values = new ContentValues();
        values.put("userID", userId);
        values.put("gameID", gameId);
        values.put("userComment", comment);
        DB.insert(database_table, null, values);
    }

}
