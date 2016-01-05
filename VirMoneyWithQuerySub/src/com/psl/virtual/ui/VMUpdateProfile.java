package com.psl.virtual.ui;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.xml.XMLPacker;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class VMUpdateProfile extends Activity {
	AlertDialog al; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		this.setContentView(R.layout.update_profile);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
		
		Object oObj = this.getIntent().getSerializableExtra("info");
        System.out.println("###############oObj: "+oObj);
        
    	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
    	    	
    	EditText temp;
		temp = (EditText)findViewById(R.id.usrnameedit);
		temp.setText(oHTValues.get("Name"));
		
		temp=(EditText)findViewById(R.id.addressedit);
		temp.setText(oHTValues.get("Address"));
		
		temp=(EditText)findViewById(R.id.mobileedit);
		temp.setText(oHTValues.get("ContactNo"));
		
		temp=(EditText)findViewById(R.id.emailedit);
		temp.setText(oHTValues.get("email"));
		
		temp=(EditText)findViewById(R.id.bankedit);
		temp.setText(oHTValues.get("BankName"));

		
		temp=(EditText)findViewById(R.id.accountedit);
		temp.setText(oHTValues.get("BankAccNo"));
		
		temp=(EditText)findViewById(R.id.editAccNo);
		temp.setText(oHTValues.get("AccNo"));

		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.updategender);
		if(oHTValues.get("Gender").equalsIgnoreCase("female"))
		{
		     radioGroup.check(R.id.femalegender);
		}
		else
		{
			 radioGroup.check(R.id.malegender);
	    }
		

	}
	
	public void updateprofile(View view)
	{
		EditText temp = (EditText)findViewById(R.id.usrnameedit);
		String Name = temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.addressedit);
		String Address = temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.mobileedit);
		String ContactNo=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.emailedit);
		String email=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.bankedit);
		String BankName=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.accountedit);
		String BankAccNo=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.editAccNo);
		String AccNo=temp.getText().toString().trim();

		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gender);
		int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
		String gender="";
		switch (checkedRadioButton) {
		  case R.id.malegender : gender= "male";
		                   	              break;
		  case R.id.femalegender : gender = "female";
				                      break;
		}
		
		DatePicker dt=(DatePicker)findViewById(R.id.birthdatepicker);
		Date date=(Date)new Date(dt.getYear()-1900,dt.getMonth(),dt.getDayOfMonth());
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String dateString = sdf.format(date);					

		UpdateProfile(AccNo,Name,gender,Address,email,BankName,ContactNo,BankAccNo,dateString);
	}
	
	private void UpdateProfile(String sAccNo,String sName,String sGender,String sAddr, String sEmailId,String sBank,String sContact,String sBankAcc,String sDOB) 
	{
		// TODO Auto-generated method stub
		SoapObject request = XMLPacker.getInstance().getUpdateProfileRequest(sAccNo, sName, sGender, sAddr, sEmailId, sBank, sContact, sBankAcc, sDOB);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_UPDATE_PROFILE, Events.EVENT_UPDATE_PROFILE);
		
	}
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMUpdateProfile.this.finish(); 
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
