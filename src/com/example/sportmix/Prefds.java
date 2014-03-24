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

public class Prefds extends SQLiteOpenHelper {

  


  private static final String DATABASE_NAME = "sportmix.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table preference (id integer primary key autoincrement,"
  		+ "sport text,team text)";

  public Prefds(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(Prefds.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS preference");
    onCreate(db);
  }
 



  public void insertPreference(Preference s) {
	  SQLiteDatabase  database=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("sport", s.getSport());
    values.put("team", s.getTeam());
    
    
	 
    
            
        
   	
   
    database.insert("Preference",null,values);
    
    database.close();
  }

  public void deletePreference(Preference s) {
	  SQLiteDatabase db = this.getWritableDatabase();
	    db.delete("Preference", "id = ?",
	            new String[] { String.valueOf(s.getId()) });
	    db.close();
  }

  public List<Preference> getAllPreferences() {
	  List<Preference> contactList = new ArrayList<Preference>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM Preference";
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Preference contact = new Preference();
	            contact.setId(Integer.parseInt(cursor.getString(0)));
	            contact.setSport(cursor.getString(1));
	            contact.setTeam(cursor.getString(2));
	           
	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    db.delete("preference",null,null);
	    // return contact list
	    return contactList;
  }

  public int updatePreference(Preference contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put("Sport", contact.getSport());
	    values.put("team",contact.getTeam());
	    return db.update("Preference", values, "id = ?",
	            new String[] { String.valueOf(contact.getId()) });
	}
} 