package com.example.signup;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class gameHelper {
    private static String database_table = "games";
    private Context context;
    private DBHelper dbHelper;

    private SQLiteDatabase DB;

    public gameHelper(Context context){
        this.context = context;
    }
    public void open() throws SQLException{
        dbHelper = new DBHelper(context);
        DB = dbHelper.getWritableDatabase();
    }
    public void close() throws SQLException{
        dbHelper.close();
    }
    public ArrayList<Game> viewGame(){
        ArrayList<Game> gameList = new ArrayList<>();
        String query = "SELECT * FROM games";

        Cursor cursor = DB.rawQuery(query, null);

        cursor.moveToFirst();

        Game game;
        Integer gameID, gameRating;
        String gameImage, gameName, gameGenre, gameDesc, gamePrice;

        if(cursor.getCount() > 0){
            do{
                gameID = cursor.getInt(cursor.getColumnIndexOrThrow("gameID"));
                gameImage = cursor.getString(cursor.getColumnIndexOrThrow("gameImage"));
                gameName = cursor.getString(cursor.getColumnIndexOrThrow("gameName"));
                gameGenre = cursor.getString(cursor.getColumnIndexOrThrow("gameGenre"));
                gameRating = cursor.getInt(cursor.getColumnIndexOrThrow("gameRating"));
                gameDesc = cursor.getString(cursor.getColumnIndexOrThrow("gameDesc"));
                gamePrice = cursor.getString(cursor.getColumnIndexOrThrow("gamePrice"));

                game = new Game(gameID, gameImage, gameName, gameGenre, gameRating, gameDesc, gamePrice);
                gameList.add(game);

                cursor.moveToNext() ;
            }while (!cursor.isAfterLast());
        }
        cursor.close();

        return gameList;
    }

}
