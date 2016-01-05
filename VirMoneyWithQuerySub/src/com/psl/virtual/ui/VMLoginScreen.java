package com.psl.virtual.ui;

import org.ksoap2.serialization.SoapObject;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.GetChars;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;


import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMLoginScreen extends Activity 
//implements OnClickListener 
{
	AlertDialog al; 
	String s,s1;
	EditText name,pass;
	public static int pos;
	private Activity mParent;
	String AccNo;
	String Password;
	public static String UName, type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		this.setContentView(R.layout.login);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
		final Spinner oSpinner = (Spinner)findViewById(R.id.vmuser);
	    OnItemSelectedListener lis =new OnItemSelectedListener() {
	    public void onItemSelected(AdapterView parent, View v, int position,long id) {
	    			                       pos = oSpinner.getSelectedItemPosition();
	    			               }
	    			               public void onNothingSelected(AdapterView arg0) {}
	    };
	    oSpinner.setOnItemSelectedListener(lis);
	    
	}
	

	@Override
	public void onBackPressed(){
		// Do Nothing
	}
	
	public void check(View view)
	{
		name=(EditText)findViewById(R.id.usrnameedit);
		s=name.getText().toString().trim();
		pass=(EditText)findViewById(R.id.passwordedit);
		s1=pass.getText().toString().trim();
		if(pos==0)
			type="CUSTOMER";
		if(pos==1)
			type="VENDOR";
		if(pos==2)
			type="ADMIN";
		
		if(s.length()==0 && s1.length()==0)
		{	
		  Utils.showToast(this, "Enter username and password");
		   
		}
		else
		{
			if(s.length()==0 && s1.length()!=0)
			Utils.showToast(this, "Enter username");
			else
			{
				if(s.length()!=0 && s1.length()==0)
				Utils.showToast(this, "Enter password");
				else
				{
					
					if(s1.length()<6)
					Utils.showToast(this, "Enter minimum 6 digit password");
					else
					{	
					UName = s;
					SoapObject request = XMLPacker.getInstance().getLoginRequest( s, s1,type);
					HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DO_LOGIN, Events.EVENT_LOGIN);					
				  }	
				}	
					
			}

		}		
	}
	
	public int enter()
	{
	  return pos;
	}
		@Override
   public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();
		menu.add(R.string.text_register);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		
		if(item.getTitle().toString().equalsIgnoreCase("Register")) 
		{
			Utils.showToast(this, "Navigating to registration form.");
			Intent oIntent = new Intent(Intent.ACTION_VIEW);
			oIntent.setClass(getApplicationContext(), VMRegistrationScreen.class);
			startActivity(oIntent);
			return true;	
		}
		else
		{
			return super.onMenuItemSelected(featureId, item);
		}
	}
	
	
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		        	   
		        	   VMLoginScreen.this.finish();
				          
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
	
	
 public void reg(View view)
 {
	Utils.showToast(this, "Navigating to Registration Form.");
    Intent i=new Intent(getBaseContext(),VMRegistrationScreen.class);
    startActivity(i);
 }
  
}

