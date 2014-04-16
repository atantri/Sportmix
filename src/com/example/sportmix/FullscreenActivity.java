package com.example.sportmix;


import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import com.example.sportmix.util.SystemUiHider;

import android.annotation.SuppressLint;

import android.app.ActionBar;
import android.app.ActionBar.Tab;

import android.app.FragmentTransaction;
import android.content.Context;

import android.os.AsyncTask;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

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


super.onCreate(savedInstanceState);
//new Sportref().execute("");
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
    	
    	if(position==1||position==2)
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

private class Sportref extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        for (int i = 0; i < 5; i++) {
            try {
            	try
            	{
            		String url = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetHistoricMatchesByTeamAndDateInterval?ApiKey=VHXYDGWSMDFBZPOJGKDDEVPFGKBJOYAWINCMNFUUIHOYOFWKGL&teamId=46&startDateString=2011-4-4&endDateString=2011-5-5";
            		 
            		DefaultHttpClient httpClient = new DefaultHttpClient();
            	    HttpGet httpGet = new HttpGet(url);

            	    HttpResponse httpResponse = httpClient.execute(httpGet);
            	    HttpEntity httpEntity = httpResponse.getEntity();
            	    String str = EntityUtils.toString(httpEntity);
            	    
            		//print result
            		//System.out.println(response.toString());
            		
            		SAXParserFactory factory = SAXParserFactory.newInstance();
            		SAXParser saxParser = factory.newSAXParser();
            		
            		DefaultHandler handler = new DefaultHandler() {
            			
            			boolean HomeGoals = false;
            			boolean AwayGoals = false;
            			boolean HomeTeam=false;
            			boolean AwayTeam=false;
            			
            			public void startElement(String uri, String localName,String qName,Attributes a
            		                ) throws SAXException {
            		 
            				Log.d("info","Start Element :" + qName);
            		 
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
            		 
            				Log.d("info","End Element :" + qName);
            		 
            			}
            		 
            			public void characters(char ch[], int start, int length) throws SAXException {
            		 
            				if (AwayGoals) {
            					Log.d("goal","AwayGoals : " + new String(ch, start, length));
            					AwayGoals = false;
            				}
            		 
            				if (HomeGoals) {
            					Log.d("info","HomeGoals : " + new String(ch, start, length));
            					HomeGoals = false;
            				}
            		 
            				if (AwayTeam) {
            					Log.d("info","AwayTeam : " + new String(ch, start, length));
            					AwayTeam = false;
            				}
            		 
            				if (HomeTeam) {
            					Log.d("info","HomeTeam : " + new String(ch, start, length));
            					HomeTeam = false;
            				}
            		 
            			}
            		 
            		  };
            		 
            		  saxParser.parse(new InputSource(new StringReader(str)), handler);
            		
            	}
            	catch(Exception e)
            	{
            		Log.d("Exception",e.toString());
            	}
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {
       Log.d("excec", "executed"); // txt.setText(result);
        // might want to change "executed" for the returned string passed
        // into onPostExecute() but that is upto you
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
}

}


