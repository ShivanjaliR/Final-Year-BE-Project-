package com.psl.virtual.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.psl.virtual.R;

public class VMSplashScreen extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
        setContentView(R.layout.splash);
        
        Timer oTimer = new Timer();
        oTimer.schedule(new SplashTimerTask(this), 4000); 
    }
    
    
}

class SplashTimerTask extends TimerTask
{
	/** Parent activity reference */
	private Activity mParent;
	
	/**
	 * Constructor
	 */
	public SplashTimerTask(Activity oParent)
	{
		mParent = oParent;
	}
	
	@Override
	public void run() 
	{
		this.cancel();
		
		Intent oIntent = new Intent(Intent.ACTION_VIEW);
		oIntent.setClass(mParent.getApplicationContext(), VMLoginScreen.class);
		mParent.startActivity(oIntent);
		
		mParent.finish();
	}
	
}