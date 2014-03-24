package com.example.sportmix;
import com.example.sportmix.R;
import com.google.gwt.user.client.ui.Button;

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
	private View addPreferenceButton;
	
	
	private String[] preferenceSportString;
	static final String[] preferenceFootballString = new String[] {"FC Barcelona","Real Madrid","Manchester United"};
	private String[] dataSource;
	
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_preferences, container, false);
        
        preferenceSportList = (ListView) rootView.findViewById(R.id.preferenceListView);
        addPreferenceButton =  getActivity().findViewById(R.id.addPreferenceButton);
        
        preferenceSportString = getResources().getStringArray(R.array.prefrenceSports);
        dataSource = preferenceSportString;
        
        final ArrayAdapter<String> preferenceAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,dataSource);
        
        preferenceSportList.setAdapter(preferenceAdapter);
        
        preferenceSportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
		        dataSource = preferenceFootballString;
		        preferenceAdapter.notifyDataSetChanged();
		       Toast.makeText(getActivity(),preferenceSportString[position], Toast.LENGTH_SHORT).show();
			}
        	
        	
		});
        
        return rootView;
    }
}