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
	ArrayAdapter<String> aa;
	
	
	
	private ArrayList<String> dataSource;
	 public void setData()
	 {
		 SQLHelper  db = new SQLHelper(getActivity());
	       
	        List<Preference> preflist=db.getAllPreferences();
	        int size=preflist.size();
	        
	        dataSource=new ArrayList<String>();
	        
	        for(int i=0;i<size;i++)
	        {
	        	dataSource.add(preflist.get(i).getTeam());
	        	//dataSource[i]="a";
	        }
	        db.closeDB();
	 }
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_preferences, container, false);
        
        preferenceSportList = (ListView) rootView.findViewById(R.id.preferenceListView);
       
        
        final SQLHelper  db = new SQLHelper(getActivity());
       
        List<Preference> preflist=db.getAllPreferences();
        int size=preflist.size();
        
        dataSource=new ArrayList<String>();
        
        for(int i=0;i<size;i++)
        {
        	dataSource.add(preflist.get(i).getTeam());
        	//dataSource[i]="a";
        }
        aa= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,dataSource);  
        
        preferenceSportList.setAdapter(aa);
        
        preferenceSportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
		        
		        aa.notifyDataSetChanged();
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
				aa.remove(aa.getItem(position));
				aa.notifyDataSetChanged();
				
				return true;
			
			}
		});
 
        //db.closeDB();
        return rootView;
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	SQLHelper  db = new SQLHelper(getActivity());
        
        List<Preference> preflist=db.getAllPreferences();
        int size=preflist.size();
        
        dataSource=new ArrayList<String>();
        
        for(int i=0;i<size;i++)
        {
        	dataSource.add(preflist.get(i).getTeam());
        	//dataSource[i]="a";
        }
        aa=new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,dataSource);
        preferenceSportList.setAdapter(aa);
  	  aa.notifyDataSetChanged();
    }
}