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

public class Teamds extends SQLiteOpenHelper {

  


  private static final String DATABASE_NAME = "sportmix.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table team (id integer primary key autoincrement,"
  		+ "name text,sportname text)";

  public Teamds(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(Teamds.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS team");
    onCreate(db);
  }
 



  public void insertTeam(Team s) {
	  SQLiteDatabase  database=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("name", s.getName());
    String selectQuery = "SELECT  id FROM Sport where id="+s.getSport();
    
	 
    
            
        
    
    values.put("sportname",s.getSport());	
   
    database.insert("Team",null,values);
    
    database.close();
  }

  public void deleteTeam(Team s) {
	  SQLiteDatabase db = this.getWritableDatabase();
	    db.delete("Team", "id = ?",
	            new String[] { String.valueOf(s.getId()) });
	    db.close();
  }

  public List<Team> getAllTeams() {
	  List<Team> contactList = new ArrayList<Team>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM Team";
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Team contact = new Team();
	            contact.setId(Integer.parseInt(cursor.getString(0)));
	            contact.setName(cursor.getString(1));
	            contact.setSport(cursor.getString(2));
	           
	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    db.delete("team",null,null);
	    // return contact list
	    return contactList;
  }

  public int updateTeam(Team contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put("name", contact.getName());
	    values.put("sportname",contact.getSport());
	    return db.update("Team", values, "id = ?",
	            new String[] { String.valueOf(contact.getId()) });
	}
} 