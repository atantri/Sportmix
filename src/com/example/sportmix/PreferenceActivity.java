package com.example.sportmix;

import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PreferenceActivity extends Activity {
	
	private ListView preferenceSportList;
	private String [] dataSource;
	static final String[] preferenceSportString = new String[] {"Football","Cricket","Tennis"};
	static final String[] preferenceFootballString = new String[] {"FC Barcelona","Real Madrid FC","Bayern Munich FC","Chelsea FC"};
	static final String[] preferenceCricketString = new String[] {"India","Pakistan","Sri Lanka","Australia","England","South Africa","West Indies",
		"New Zealand"};
	static final String[] preferenceTennisString = new String[] {"Roger Federer","Novak Djokovic","Rafael Nadal","J Del Potro"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference);
		
		
		preferenceSportList = (ListView) findViewById(R.id.sportPreferenceList);
		dataSource = preferenceSportString;
		final ArrayAdapter<String> preferenceSportAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSource);
		preferenceSportList.setAdapter(preferenceSportAdapter);
		preferenceSportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				switch(position)
				{
				case 0:
					startActivity(new Intent("com.example.sportmix.FootBallPreference"));
//					preferenceSportList.invalidate();
//					dataSource = preferenceFootballString;
//					preferenceSportAdapter.notifyDataSetChanged();
					Toast.makeText(getApplicationContext(),"Case 0"+preferenceSportString[position], Toast.LENGTH_SHORT).show();
					break;
				case 1:
					dataSource = preferenceCricketString;
					preferenceSportAdapter.notifyDataSetChanged();
					Toast.makeText(getApplicationContext(),"Case 1"+preferenceSportString[position], Toast.LENGTH_SHORT).show();
					break;
				case 2:
					dataSource = preferenceTennisString;
					preferenceSportAdapter.notifyDataSetChanged();
					Toast.makeText(getApplicationContext(),"Case 2"+preferenceSportString[position], Toast.LENGTH_SHORT).show();
					break;
				}
				
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

}
