package com.example.sportmix;
/*
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
*/

import com.example.sportmix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {
    protected boolean active = true;
    protected int splashTime = 5000;
    protected int timeIncrement = 100;
    protected int sleepTime = 100;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        // thread for displaying the SplashScreen
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int elapsedTime = 0;
                    while(active && (elapsedTime < splashTime)) {
                        sleep(sleepTime);
                        if(active) elapsedTime = elapsedTime + timeIncrement;
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    //startActivity(new Intent("com.wiley.fordummies.androidsdk.tictactoe.Login"));
                }
            }
        };
        splashThread.start();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            active = false;
        }
        return true;
    }
}