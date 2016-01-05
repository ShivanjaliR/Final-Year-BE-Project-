package com.psl.virtual.ui;

import com.psl.virtual.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

public class VMEmpty extends Activity
{

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		setContentView(R.layout.empty);
    }
	
	@Override
	public void onBackPressed()
	{
		
	}

	
	@Override 
    public boolean onCreateOptionsMenu(Menu menu) 
    { 
        MenuInflater inflater = getMenuInflater(); 
        inflater.inflate(R.menu.homelogout, menu);
        
        return true; 
    }
	public boolean onOptionsItemSelected(MenuItem item)
    { 
        // Handle item selection 
        switch (item.getItemId())
        { 
        case R.id.home: 
        	if(VMLoginScreen.pos==0)
        	{
        	Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
	    	startActivity(i);
        	}
        	else if(VMLoginScreen.pos==1)
        	{
        		Intent i=new Intent(getBaseContext(),VMVendorHome.class);
    	    	startActivity(i);
        	}
        	else
        	{
        		Intent i=new Intent(getBaseContext(),VMAdminHome.class);
    	    	startActivity(i);
        	}
     	     return true;
        case R.id.logout_h: 
        	Intent i=new Intent(getBaseContext(),VMLoginScreen.class);
    	    startActivity(i);       
        	return true;
               
        default: 
            return super.onOptionsItemSelected(item); 
        }
      } 
    
  
}
