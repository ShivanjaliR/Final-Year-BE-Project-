package com.psl.virtual.ui;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMChangePass extends Activity {
	AlertDialog al;
	EditText OldPass, NewPass, ReEnter;
	String OPass, NPass, RPass;
		@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.chpass);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
    	
	}
		
	public void check(View view)
	{
		
		OldPass=(EditText)findViewById(R.id.curr_pass_edit);
		OPass=OldPass.getText().toString().trim();
		NewPass=(EditText)findViewById(R.id.new_pass_edit);
		NPass=NewPass.getText().toString().trim();
		ReEnter=(EditText)findViewById(R.id.con_pass_edit);
		RPass=ReEnter.getText().toString().trim();
		
		if(NPass.matches(RPass))
		{
			SoapObject request = XMLPacker.getInstance().getChangePasswordRequest(VMLoginScreen.UName, OPass, NPass);
			HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_CHANGE_PASSWORD, Events.EVENT_CHANGE_PASSWORD);			
		}
		else
		{
			Utils.showToast(this, "Current Passwords do not match");
		}		
	}
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMChangePass.this.finish(); 
		           } 
		       }) 
		       .setNegativeButton("No", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		                dialog.cancel(); 
		           } 
		       });
		
	    	 al = builder.create();
	    	 al.show();
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
	        	else
	        	{
		        	Intent i=new Intent(getBaseContext(),VMVendorHome.class);
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
