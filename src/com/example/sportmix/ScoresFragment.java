package com.example.sportmix;
import java.util.List;

import com.example.sportmix.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class ScoresFragment extends Fragment {
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
         
        Scoreds db = new Scoreds(getActivity());
        
       
        db.insertScore(new Score("Arsenal", "Chelsea",10,0));        
        db.insertScore(new Score("Manu", "Manc",0,0));
    
         
       
        List<Score> contacts = db.getAllScores();       
        TextView t=(TextView)rootView.findViewById(R.id.textView1);
       // t.setText("Working");
        for (Score cn : contacts) {
            String log = "Id: "+cn.getId()+cn.getTeam1()+" "+cn.getScore()+"-"+cn.getScore2()+" "+cn.getTeam2();
            t.append(log);
                // Writing Contacts to log
        }
        
        
        
        
        
        return rootView;
    }
}