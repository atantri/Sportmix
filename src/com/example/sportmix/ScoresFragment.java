package com.example.sportmix;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;

import com.example.sportmix.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
public class ScoresFragment extends Fragment {
	ListView listv;
	ArrayList<HashMap<String,String>> contactList;
    
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
         
        SQLHelper db = new SQLHelper(getActivity());
        
        int i=0;
    /*    db.insertTeam(new Team("Arsenal","football"));
        db.insertTeam(new Team("Chelsea","football"));
        db.insertScore(new Score("Arsenal", "Chelsea","10-0"));        
        db.insertScore(new Score("Manu", "Manc","0-0"));
    */
         
      

   listv=(ListView)rootView.findViewById(android.R.id.list);
   
        List<Score> contacts = db.getAllScores();    
        ArrayList<String> scores = new ArrayList<String>(); 
        String str="";
       Iterator<Score> it=contacts.iterator();
for(Score c:contacts)
{
	str="\n "+c.getId()+"     "+c.getTeam1()+"     "+c.getScore()+"     "+c.getTeam2()+"     ";
	scores.add(str);
        
           i++;
                // Writing Contacts to log
        }


      //  TextView t=(TextView)rootView.findViewById(R.id.textView1);
       // t.setText("Working");
        ArrayAdapter<String> myListAdapter = 
        	    new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.item, scores);

        listv.setAdapter(myListAdapter);
        
        
        
       // db.clear("Preference");
        
        
        return rootView;
    }
}