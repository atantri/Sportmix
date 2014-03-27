package com.example.sportmix;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CricketPreference extends Activity {

	private ArrayList<String> dataSource;
	private ListView preferenceCricketList;
	//static final String[] preferenceCricketString = new String[] {"FC Barcelona","Real Madrid FC","Bayern Munich FC","Chelsea FC"};

	SQLHelper db=new SQLHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cricket_preference);
		preferenceCricketList = (ListView) findViewById(R.id.cricketPreferenceList);

		List <Team> tlist=db.getAllTeams("Cricket");
		dataSource=new ArrayList<String>();
		for(int i=0;i<tlist.size();i++)
		{
			dataSource.add(tlist.get(i).getName());
		}

		final ArrayAdapter<String> preferenceSportAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSource);
		preferenceCricketList.setAdapter(preferenceSportAdapter);
		preferenceCricketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {

				Toast.makeText(getApplicationContext(),dataSource.get(position), Toast.LENGTH_SHORT).show();
				db.insertPreference(new Preference(dataSource.get(position)));
				preferenceSportAdapter.remove(preferenceSportAdapter.getItem(position));
				preferenceSportAdapter.notifyDataSetChanged();
			}


		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.cricket_preference, menu);
		return true;
	}

}