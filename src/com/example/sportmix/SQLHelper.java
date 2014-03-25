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

public class SQLHelper extends SQLiteOpenHelper {

  


  private static final String DATABASE_NAME = "sportmix.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String SCORE_TABLE = "create table score (id integer primary key autoincrement,"
  		+ "team1 text,score text, team2 text,foreign key (team1) references team(name), foreign key (team2) references team(name))";
  
  private static final String TEAM_TABLE = "create table team (id integer primary key autoincrement,"
	  		+ "name text unique,sportname text)";
  
  private static final String PREF_TABLE = "create table preference (id integer primary key autoincrement,"
	  		+ "teamname text unique,foreign key (teamname) references team(name))";

  public SQLHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
	
    db.execSQL(SCORE_TABLE);
    db.execSQL(TEAM_TABLE);
    db.execSQL(PREF_TABLE);
    clear("Preference",db);
    clear("team",db);
    clear("score",db);
    insertTeam(new Team("Arsenal","Football"),db);
    insertTeam(new Team("Chelsea","Football"),db);
    insertScore(new Score("Arsenal", "Chelsea","10-0"),db);        
    insertScore(new Score("Manu", "Manc","0-0"),db);
    insertPreference(new Preference("Arsenal"),db);
    insertPreference(new Preference("Chelsea"),db);
    insertTeam(new Team("FC Barcelona","Football"),db);
    insertPreference(new Preference("FC Barcelona"),db);
    /*
    clear("Preference");
    clear("team");
    clear("score");
    insertTeam(new Team("Arsenal","Football"));
    insertTeam(new Team("Chelsea","Football"));
    insertScore(new Score("Arsenal", "Chelsea","10-0"));        
    insertScore(new Score("Manu", "Manc","0-0"));
    insertPreference(new Preference("Arsenal"));
    insertPreference(new Preference("Chelsea"));
    insertTeam(new Team("FC Barcelona","Football"));
    insertPreference(new Preference("FC Barcelona"));
    */
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(SQLHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    /*db.execSQL("DROP TABLE IF EXISTS Preference");
    db.execSQL("DROP TABLE IF EXISTS Score");
    db.execSQL("DROP TABLE IF EXISTS team");
    onCreate(db);*/
    
    
  }
 
  public void closeDB() {
      SQLiteDatabase db = this.getReadableDatabase();
      if (db != null && db.isOpen())
          db.close();
  }

  public List<String> getSportslist()
  {
	  List<String> contactList = new ArrayList<String>();
	    // Select All Query
	    String selectQuery = "SELECT distinct sportname from team";
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Team contact = new Team();
	            
	            contact.setSportname(cursor.getString(0));
	          
	            contactList.add(contact.getSportname());
	        } while (cursor.moveToNext());
	    }
	    //db.delete("score",null,null);
	    db.close();
	    // return contact list
	    return contactList;
  }
  public void insertScore(Score s) {
	  SQLiteDatabase  database=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("team1", s.getTeam1());
    values.put("score", s.getScore());
    values.put("team2", s.getTeam2());
    
    
    Log.d("Data:",s.getId()+s.getTeam1()+" "+s.getScore()+" "+s.getTeam2());
    database.insert("Score",null,values);
    database.close();
  }
  private void insertScore(Score s,SQLiteDatabase database) {
	 
    ContentValues values = new ContentValues();
    values.put("team1", s.getTeam1());
    values.put("score", s.getScore());
    values.put("team2", s.getTeam2());
    
    
    Log.d("Data:",s.getId()+s.getTeam1()+" "+s.getScore()+" "+s.getTeam2());
    database.insert("Score",null,values);
    
  }
  public void deleteScore(Score s) {
	  SQLiteDatabase db = this.getWritableDatabase();
	    db.delete("Score", "id = ?",
	            new String[] { String.valueOf(s.getId()) });
	    db.close();
  }
  public void clear(String tname)
  {
	  SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(tname,null,null);
	    db.close();
  }
  private void clear(String tname,SQLiteDatabase db)
  {
	 
	    db.delete(tname,null,null);
	    
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
	            contact.setScore(cursor.getString(2));
	            contact.setTeam2(cursor.getString(3));
	           
	            // Adding contact to list
	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    //db.delete("score",null,null);
	    db.close();
	    // return contact list
	    return contactList;
  }

  public int updateScore(Score contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put("team1", contact.getTeam1());
	    values.put("team2", contact.getTeam2());
	    values.put("score", contact.getScore());
	   
	    
	    // updating row
	    return db.update("Score", values, "id = ?",
	            new String[] { String.valueOf(contact.getId()) });
	}
  private void insertPreference(Preference s,SQLiteDatabase db) {
	 
    ContentValues values = new ContentValues();
    
    values.put("teamname", s.getTeam());
    
    db.insert("Preference",null,values);
    

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
	            
	            contact.setTeam(cursor.getString(1));
	           
	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    //db.delete("preference",null,null);
	    // return contact list
	    db.close();
	    return contactList;
  }

  public int updatePreference(Preference contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    
	    values.put("teamname",contact.getTeam());
	    int a=db.update("Preference", values, "id = ?",
	            new String[] { String.valueOf(contact.getId()) });
	  db.close();
	  return a;
	}
  
  public void insertTeam(Team s) {
	SQLiteDatabase  database=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("name", s.getName());
    values.put("sportname", s.getSportname());
    database.insert("Team",null,values);
    
    database.close();
  }
  private void insertTeam(Team s,SQLiteDatabase database) {
		
	    ContentValues values = new ContentValues();
	    values.put("name", s.getName());
	    values.put("sportname", s.getSportname());
	    database.insert("Team",null,values);
	    
	    
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
	            

	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    //db.delete("team",null,null);
	    // return contact list
	    db.close();
	    return contactList;
  }

  public int updateTeam(Team contact) {
	    SQLiteDatabase db = this.getWritableDatabase();

	    ContentValues values = new ContentValues();
	    values.put("name", contact.getName());
	    
	    int a= db.update("Team", values, "id = ?",
	            new String[] { String.valueOf(contact.getId()) });
	    db.close();
	    return a;
	}
} 
