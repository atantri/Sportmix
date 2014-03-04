package com.example.sportmix;

import com.example.sportmix.util.SystemUiHider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
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
private String[] tabs = { "Preferences", "Scores", "Settings" };

@Override
protected void onCreate(Bundle savedInstanceState) {
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
	// TODO Auto-generated method stub
	
}

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

