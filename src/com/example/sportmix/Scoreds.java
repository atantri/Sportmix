package com.example.sportmix;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Scoreds extends SQLiteOpenHelper {

  


  private static final String DATABASE_NAME = "sportmix.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table score (id integer primary key autoincrement,"
  		+ "team1 text,score1 integer, team2 text,score2 integer)";

  public Scoreds(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(Scoreds.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS score");
    onCreate(db);
  }
 



  public void insertScore(Score s) {
	  SQLiteDatabase  database=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("team1", s.getTeam1());
    values.put("score1", s.getScore());
    values.put("team2", s.getTeam2());
    
    values.put("score2", s.getScore2());
    Log.d("Data:",s.getId()+s.getTeam1()+" "+s.getScore()+"-"+s.getScore2()+" "+s.getTeam2());
    database.insert("Score",null,values);
    database.close();
  }

  public void deleteScore(Score s) {
	  SQLiteDatabase db = this.getWritableDatabase();
	    db.delete("Score", "id = ?",
	            new String[] { String.valueOf(s.getId()) });
	    db.close();
  }

  public List<Score> getAllScores() {
	  List<Score> contactList = new ArrayList<Score>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM Score";
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Score contact = new Score();
	            contact.setId(Integer.parseInt(cursor.getString(0)));
	            contact.setTeam1(cursor.getString(1));
	            contact.setScore(Integer.parseInt(cursor.getString(2)));
	            contact.setTeam2(cursor.getString(3));
	            contact.setScore2(Integer.parseInt(cursor.getString(4)));
	            // Adding contact to list
	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    db.delete("score",null,null);
	    // return contact list
	    return contactList;
  }

  public int updateScore(Score contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put("team1", contact.getTeam1());
	    values.put("team2", contact.getTeam2());
	    values.put("score1", contact.getScore());
	    values.put("score2", contact.getScore());
	    
	    // updating row
	    return db.update("Score", values, "id = ?",
	            new String[] { String.valueOf(contact.getId()) });
	}
} 