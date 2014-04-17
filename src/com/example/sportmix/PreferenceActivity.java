package com.example.sportmix;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PreferenceActivity extends Activity implements OnKeyListener{
	Boolean addteam=false;
	private ListView preferenceSportList;
	private ArrayList<String> dataSource;
	//static final String[] preferenceSportString = new String[] {"Football","Cricket","Tennis"};
	static final String[] preferenceFootballString = new String[] {"FC Barcelona","Real Madrid FC","Bayern Munich FC","Chelsea FC"};
	static final String[] preferenceCricketString = new String[] {"India","Pakistan","Sri Lanka","Australia","England","South Africa","West Indies",
		"New Zealand"};
	static final String[] preferenceTennisString = new String[] {"Roger Federer","Novak Djokovic","Rafael Nadal","J Del Potro"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference);
		
		
		preferenceSportList = (ListView) findViewById(R.id.sportPreferenceList);
		SQLHelper db=new SQLHelper(this);
		List<String> sportlist=db.getSportslist();
		dataSource=new ArrayList<String>();
		for(int i=0;i<sportlist.size();i++)
		{
			dataSource.add(sportlist.get(i));
		}
		final ArrayAdapter<String> preferenceSportAdapter = new ArrayAdapter<String>(this,R.layout.customlist,dataSource);
		preferenceSportList.setAdapter(preferenceSportAdapter);
		preferenceSportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				String text=(String) parent.getItemAtPosition(position);
				//Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
				SQLHelper db=new SQLHelper(parent.getContext());
				
				if(text.equals("Football")||text.equals("Cricket")||text.equals("Tennis"))
				{
					List <Team> tlist=db.getAllTeams(text);
					dataSource=new ArrayList<String>();
					for(int i=0;i<tlist.size();i++)
					{
						dataSource.add(tlist.get(i).getName());
					}
					final ArrayAdapter<String> preferenceSportAdapter1 = new ArrayAdapter<String>(parent.getContext(),R.layout.customlist,dataSource);
					
					preferenceSportList.setAdapter(preferenceSportAdapter1);
					addteam=true;
				}
				else
				{
					db.insertPreference(new Preference(dataSource.get(position)));
					final ArrayAdapter<String> preferenceSportAdapter1=(ArrayAdapter<String>)parent.getAdapter();
						preferenceSportAdapter1.remove(preferenceSportAdapter1.getItem(position));
					preferenceSportAdapter1.notifyDataSetChanged();
					
				}
				/*
				
				switch(position)
				{
				case 0:
					/*List <Team> tlist=db.getAllTeams("Football");
					dataSource=new ArrayList<String>();
					for(int i=0;i<tlist.size();i++)
					{
						dataSource.add(tlist.get(i).getName());
					}
					startActivity(new Intent("com.example.sportmix.FootBallPreference"));
					
//					preferenceSportList.invalidate();
//					dataSource = preferenceFootballString;
//					preferenceSportAdapter.notifyDataSetChanged();
					//Toast.makeText(getApplicationContext(),"Case 0"+sportlist.get(position), Toast.LENGTH_SHORT).show();
					break;
				case 1:
					startActivity(new Intent("com.example.sportmix.CricketPreference"));
					//dataSource = preferenceCricketString;
					//preferenceSportAdapter.notifyDataSetChanged();
					//Toast.makeText(getApplicationContext(),"Case 1"+sportlist.get(position), Toast.LENGTH_SHORT).show();
					break;
				case 2:
					startActivity(new Intent("com.example.sportmix.TennisPreference"));
					//dataSource = preferenceTennisString;
					//preferenceSportAdapter.notifyDataSetChanged();
					//Toast.makeText(getApplicationContext(),"Case 2"+sportlist.get(position), Toast.LENGTH_SHORT).show();
					break;
				}*/
				
//		       Toast.makeText(getApplicationContext(),preferenceSportString[position], Toast.LENGTH_SHORT).show();
  
 
			}
        	
        	
		});
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preference, menu);
		return true;
	}

	@Override
	public boolean onKeyDown( int keyCode, KeyEvent event) {
		if(addteam&&keyCode == KeyEvent.KEYCODE_BACK)
		{
			SQLHelper db=new SQLHelper(this);
			List<String> sportlist=db.getSportslist();
			dataSource=new ArrayList<String>();
			for(int i=0;i<sportlist.size();i++)
			{
				dataSource.add(sportlist.get(i));
			}
			final ArrayAdapter<String> preferenceSportAdapter = new ArrayAdapter<String>(this,R.layout.customlist,dataSource);
			preferenceSportList.setAdapter(preferenceSportAdapter);
			addteam=false;
			return true;
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
