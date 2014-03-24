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
public class Sportds extends SQLiteOpenHelper {
	 private static final String DATABASE_NAME = "sportmix.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table sport (id integer primary key autoincrement,"
	  		+ "teamname text)";

	  public Sportds(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(Sportds.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS sport");
	    onCreate(db);
	  }
	 



	  public void insertsport(Sport s) {
		  SQLiteDatabase  database=this.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put("teamname", s.getName());
	    
	    
	   
	    //Log.d("Data:",s.getId()+s.getTeam1()+" "+s.getsport()+"-"+s.getsport2()+" "+s.getTeam2());
	    database.insert("Sport",null,values);
	    database.close();
	  }

	  public void deletesport(Sport s) {
		  SQLiteDatabase db = this.getWritableDatabase();
		    db.delete("Sport", "id = ?",
		            new String[] { String.valueOf(s.getId()) });
		    db.close();
	  }

	  public List<Sport> getAllSports() {
		  List<Sport> contactList = new ArrayList<Sport>();
		    // Select All Query
		    String selectQuery = "SELECT  * FROM sport";
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		            Sport contact = new Sport();
		            contact.setId(Integer.parseInt(cursor.getString(0)));
		            contact.setName(cursor.getString(1));
		           
		            contactList.add(contact);
		        } while (cursor.moveToNext());
		    }
		    db.delete("sport",null,null);
		    // return contact list
		    return contactList;
	  }

	  public int updatesport(Sport contact) {
		    SQLiteDatabase db = this.getWritableDatabase();
		 
		    ContentValues values = new ContentValues();
		    values.put("teamname", contact.getName());
		 
		    
		    // updating row
		    return db.update("sport", values, "id = ?",
		            new String[] { String.valueOf(contact.getId()) });
		}

}
