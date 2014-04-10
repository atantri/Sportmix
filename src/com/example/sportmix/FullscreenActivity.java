package com.example.sportmix;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.sportmix.util.SystemUiHider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@SuppressLint("NewApi")
public class FullscreenActivity extends FragmentActivity implements ActionBar.TabListener {

private ViewPager viewPager;
private TabsPagerAdapter mAdapter;
private ActionBar actionBar;
// Tab titles
private String[] tabs = { "Preferences", "Scores", "Map View" };
private final String USER_AGENT="Mozilla/5.0";
@Override
protected void onCreate(Bundle savedInstanceState) {
/*	
try
{
	String url = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetHistoricMatchesByTeamAndDateInterval?ApiKey=VHXYDGWSMDFBZPOJGKDDEVPFGKBJOYAWINCMNFUUIHOYOFWKGL&teamId=46&startDateString=2011-4-4&endDateString=2011-5-5";
	 
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	// optional default is GET
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", USER_AGENT);

	int responseCode = con.getResponseCode();
	//System.out.println("\nSending 'GET' request to URL : " + url);
	//System.out.println("Response Code : " + responseCode);

	BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

	//print result
	//System.out.println(response.toString());
	String str=response.toString();
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
	String t1,t2,s1,s2;
	DefaultHandler handler = new DefaultHandler() {
		 
		boolean HomeGoals = false;
		boolean AwayGoals = false;
		boolean HomeTeam=false;
		boolean AwayTeam=false;
		public void startElement(String uri, String localName,String qName
	                ) throws SAXException {
	 
			//System.out.println("Start Element :" + qName);
	 
			if (qName.equalsIgnoreCase("AwayGoals")) {
				AwayGoals = true;
			}
	 
			if (qName.equalsIgnoreCase("HomeGoals")) {
				HomeGoals = true;
			}
			if (qName.equalsIgnoreCase("HomeTeam")) {
				HomeTeam = true;
			}
			if (qName.equalsIgnoreCase("AwayTeam")) {
				AwayTeam = true;
			}
			
	 
			
		}
	 
		public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			//System.out.println("End Element :" + qName);
	 
		}
	 
		public void characters(char ch[], int start, int length) throws SAXException {
	 
			if (AwayGoals) {
				System.out.println("First Name : " + new String(ch, start, length));
				AwayGoals = false;
			}
	 
			if (HomeGoals) {
				System.out.println("Last Name : " + new String(ch, start, length));
				HomeGoals = false;
			}
	 
			if (AwayTeam) {
				System.out.println("Nick Name : " + new String(ch, start, length));
				AwayTeam = false;
			}
	 
			if (HomeTeam) {
				System.out.println("Salary : " + new String(ch, start, length));
				HomeTeam = false;
			}
	 
		}
	 
	  };
	  saxParser.parse(new InputSource(new StringReader(str)), handler);
}
catch(Exception e)
{
	
}*/
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_fullscreen);

// Initilization
viewPager = (ViewPager) findViewById(R.id.pager);
actionBar = getActionBar();
mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

viewPager.setAdapter(mAdapter);
actionBar.setHomeButtonEnabled(false);
actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        

// Adding Tabs
for (String tab_name : tabs) {
    actionBar.addTab(actionBar.newTab().setText(tab_name)
            .setTabListener(this));
}


viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	
    @Override
    public void onPageSelected(int position) {
        // on changing the page
        // make respected tab selected
    	
    	if(position==1)
    	{
    		FragmentLifeCycle f=(FragmentLifeCycle) mAdapter.instantiateItem(viewPager, position);
    		if(f!=null)
    		{
    			f.refresh();
    		}
    	}
    	actionBar.setSelectedNavigationItem(position);
    
    }
 
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
 
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
});
}

@Override
public void onTabReselected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTabSelected(Tab tab, FragmentTransaction ft) {
	if(tab.getText()=="Scores")
	{
		FragmentLifeCycle f=(FragmentLifeCycle) mAdapter.instantiateItem(viewPager, 1);
		if(f!=null)
		{
			f.refresh();
		}
	}
	viewPager.setCurrentItem(tab.getPosition());
	Fragment fragment;
	//ft.replace(R.id.pager, fragment);
	
	if(tab.getText()=="Scores")
	{
		//startActivity(new Intent("com.example.sportmix.Transp"));
 
			/*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder((Context) this);
 
			// set title
			alertDialogBuilder.setTitle("Alert");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Click yes to exit!")
				.setCancelable(false)
				
				.setNeutralButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();*/
			}
	}
	

	
	// TODO Auto-generated method stub
	


@Override
public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	
}
public void onStart() {
	super.onStart();
	tToast("onStart");
}

public void onRestart() {
	super.onRestart();
	tToast("onRestart");
}

public void onResume() {
	 super.onResume();
    
	tToast("onResume");
}

public void onPause() {
	super.onPause();
	tToast("onPause: bye bye!");
}

public void onStop() {
	super.onStop();
	tToast("onStop.");
}

public void onDestroy() {
	super.onStop();
	tToast("onDestroy.");
}

private void tToast(String s) {
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast toast = Toast.makeText(context, s, duration);
    toast.show();
}

}

