package com.example.sportmix;
import java.util.ArrayList;
import java.util.List;

import com.example.sportmix.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PreferencesFragment extends Fragment {
	
	private ListView preferenceSportList;
	
	
	
	
	private ArrayList<String> dataSource;
	 
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_preferences, container, false);
        
        preferenceSportList = (ListView) rootView.findViewById(R.id.preferenceListView);
       
        //preferenceSportString = getResources().getStringArray(R.array.prefrenceSports);
        //dataSource = preferenceSportString;
        final SQLHelper  db = new SQLHelper(getActivity());
        /*db.insertTeam(new Team("Arsenal","Football"));
        db.insertTeam(new Team("Chelsea","Football"));
        db.insertScore(new Score("Arsenal", "Chelsea","10-0"));        
        db.insertScore(new Score("Manu", "Manc","0-0"));
        db.insertPreference(new Preference("Arsenal"));
        db.insertPreference(new Preference("Chelsea"));
        db.insertTeam(new Team("FC Barcelona","Football"));
        db.insertPreference(new Preference("FC Barcelona"));*/
        List<Preference> preflist=db.getAllPreferences();
        int size=preflist.size();
        //int size=1;
        dataSource=new ArrayList<String>();
        
        for(int i=0;i<size;i++)
        {
        	dataSource.add(preflist.get(i).getTeam());
        	//dataSource[i]="a";
        }
        final ArrayAdapter<String> preferenceAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,dataSource);        
        preferenceSportList.setAdapter(preferenceAdapter);
        
        preferenceSportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
		        
		        preferenceAdapter.notifyDataSetChanged();
		       Toast.makeText(getActivity(),dataSource.get(position), Toast.LENGTH_SHORT).show();
			}
        	
        	
		});
        
        android.widget.Button addPreferenceButton = (android.widget.Button) rootView.findViewById(R.id.addPreferenceButton);
        
        addPreferenceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Should open a new activity", Toast.LENGTH_LONG).show();
				
				switch (v.getId())
				{
				case R.id.addPreferenceButton:
					startActivity(new Intent("com.example.sportmix.PreferenceActivity"));
					
				}
			}
		});
        
  preferenceSportList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Toast.makeText(getActivity(),"LONGCLICK", Toast.LENGTH_SHORT).show();
				db.deletePreference(new Preference(dataSource.get(position)));
				preferenceAdapter.remove(preferenceAdapter.getItem(position));
				preferenceAdapter.notifyDataSetChanged();
				return true;
			
			}
		});
        //db.closeDB();
        return rootView;
    }
}