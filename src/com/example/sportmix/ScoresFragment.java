package com.example.sportmix;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;

import com.example.sportmix.R;

import android.content.Context;
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
import android.widget.Toast;
public class ScoresFragment extends Fragment implements FragmentLifeCycle{
	ListView listv;
	ArrayList<HashMap<String,String>> contactList;
    
	
	
	ArrayAdapter<String> myListAdapter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
    	
        	View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
        	SQLHelper db = new SQLHelper(getActivity());
        	listv=(ListView)rootView.findViewById(android.R.id.list);
   
	        List<Score> contacts = db.getAllScores();    
	        ArrayList<String> scores = new ArrayList<String>(); 
	        String str="";
	        Iterator<Score> it=contacts.iterator();
			for(Score c:contacts)
			{
				str="\n "+c.getId()+"     "+c.getTeam1()+"     "+c.getScore()+"     "+c.getTeam2()+"     ";
				scores.add(str);
		        
           
                // Writing Contacts to log
			}


 
			 myListAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.item, scores);

			listv.setAdapter(myListAdapter);  
			myListAdapter.notifyDataSetChanged();
			return rootView;
    }
	 @Override
	 public void refresh()
	 {
		 SQLHelper db = new SQLHelper(getActivity());
		    listv=(ListView)getView().findViewById(android.R.id.list);
	        List<Score> contacts = db.getAllScores();    
	        ArrayList<String> scores = new ArrayList<String>(); 
	        String str="";
	        Iterator<Score> it=contacts.iterator();
			for(Score c:contacts)
			{
				str="\n "+c.getId()+"     "+c.getTeam1()+"     "+c.getScore()+"     "+c.getTeam2()+"     ";
				scores.add(str);
	        }
	        myListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.item, scores);

	        listv.setAdapter(myListAdapter);
	        myListAdapter.notifyDataSetChanged();
	 }
	 
	/* 
   public void onActivityCreated(Bundle saved) 
   {
        super.onActivityCreated(saved);
        SQLHelper db = new SQLHelper(getActivity());
        listv=(ListView)getView().findViewById(android.R.id.list);
	   
	        List<Score> contacts = db.getAllScores();    
	        ArrayList<String> scores = new ArrayList<String>(); 
	        String str="";
	        Iterator<Score> it=contacts.iterator();
			for(Score c:contacts)
			{
				str="\n "+c.getId()+"     "+c.getTeam1()+"     "+c.getScore()+"     "+c.getTeam2()+"     ";
				scores.add(str);
			}
	        ArrayAdapter<String> myListAdapter =  new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.item, scores);

	        listv.setAdapter(myListAdapter);
	        
	        
	    }*/
	
	
	
	  @Override
	  public void onStart()
	  { 
	    super.onStart();
	    SQLHelper db = new SQLHelper(getActivity());
	    listv=(ListView)getView().findViewById(android.R.id.list);
        List<Score> contacts = db.getAllScores();    
        ArrayList<String> scores = new ArrayList<String>(); 
        String str="";
        Iterator<Score> it=contacts.iterator();
		for(Score c:contacts)
		{
			str="\n "+c.getId()+"     "+c.getTeam1()+"     "+c.getScore()+"     "+c.getTeam2()+"     ";
			scores.add(str);
        }
        myListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.item, scores);

        listv.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
	    }
	  @Override
	  public void onResume()
	  {
		  super.onResume();
		    SQLHelper db = new SQLHelper(getActivity());
		    listv=(ListView)getView().findViewById(android.R.id.list);
	        List<Score> contacts = db.getAllScores();    
	        ArrayList<String> scores = new ArrayList<String>(); 
	        String str="";
	        Iterator<Score> it=contacts.iterator();
			for(Score c:contacts)
			{
				str="\n "+c.getId()+"     "+c.getTeam1()+"     "+c.getScore()+"     "+c.getTeam2()+"     ";
				scores.add(str);
	        }
	        myListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.item, scores);

	        listv.setAdapter(myListAdapter);
	        myListAdapter.notifyDataSetChanged();
	  }
	  
}



