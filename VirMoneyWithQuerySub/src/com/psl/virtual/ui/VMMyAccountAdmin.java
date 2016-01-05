package com.psl.virtual.ui;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMMyAccountAdmin extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.myaccount);
    	getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
    	        
        Object oObj = this.getIntent().getSerializableExtra("info");
        System.out.println("###############oObj: "+oObj);
        
    	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
    	
    	TextView AccNo =(TextView)findViewById(R.id.ans_vm_acc_no);
    	AccNo.setText(oHTValues.get("AccNo"));
    	System.out.println("+++++++AccNo value: "+oHTValues.get("AccNo"));
    	
    	TextView oName =(TextView)findViewById(R.id.ans_user_name);
    	oName.setText(oHTValues.get("Name"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Name"));
    	
    	TextView Gender =(TextView)findViewById(R.id.ans_gender);
    	Gender.setText(oHTValues.get("Gender"));
    	System.out.println("+++++++Gender value: "+oHTValues.get("Gender"));
    	
    	TextView Address =(TextView)findViewById(R.id.ans_acc_address);
    	Address.setText(oHTValues.get("Address"));
    	System.out.println("+++++++Address value: "+oHTValues.get("Address"));
    	
    	TextView email =(TextView)findViewById(R.id.acc_email);
    	email.setText(oHTValues.get("email"));
    	System.out.println("+++++++email value: "+oHTValues.get("email"));
    	
    	TextView BankName =(TextView)findViewById(R.id.ans_bank);
    	BankName.setText(oHTValues.get("BankName"));
    	System.out.println("+++++++BankName value: "+oHTValues.get("BankName"));
    	
    	TextView Type =(TextView)findViewById(R.id.ans_acc_user);
    	Type.setText(oHTValues.get("Type"));
    	System.out.println("+++++++Type value: "+oHTValues.get("Type"));
    	
    	TextView BankAccNo =(TextView)findViewById(R.id.ans_acc_no);
    	BankAccNo.setText(oHTValues.get("BankAccNo"));
    	System.out.println("+++++++BankAccNo value: "+oHTValues.get("BankAccNo"));
    	
    	TextView DOB =(TextView)findViewById(R.id.ans_dob);
    	DOB.setText(oHTValues.get("DOB"));
    	System.out.println("+++++++DOB value: "+oHTValues.get("DOB"));
    	
    	TextView ContactNo =(TextView)findViewById(R.id.ans_acc_mb_no);
    	ContactNo.setText(oHTValues.get("ContactNo"));
    	System.out.println("+++++++ContactNo value: "+oHTValues.get("ContactNo"));
    	
    	TextView MoneyPoints =(TextView)findViewById(R.id.ans_acc_curr_bal);
    	MoneyPoints.setText("");
    	System.out.println("+++++++MoneyPoints value: "+oHTValues.get("MoneyPoints"));

    	TextView MPoints =(TextView)findViewById(R.id.acc_bal);
    	MPoints.setText(""); 	
		   
    }
    
    @Override 
    public boolean onCreateOptionsMenu(Menu menu) { 
        MenuInflater inflater = getMenuInflater(); 
        inflater.inflate(R.menu.optionadmin, menu); 
        return true; 
    }
    @Override 
    public boolean onOptionsItemSelected(MenuItem item)
    { 
        // Handle item selection 
        switch (item.getItemId())
        { 
        case R.id.chg_pass: 
            change_pass(); 
            return true;
        case R.id.query:
        	respondToquery();
        	return true;
        case R.id.up_profile: 
              updateprofile();
            return true;
        case R.id.logout: 
        	Intent i=new Intent(getBaseContext(),VMLoginScreen.class);
    	    startActivity(i);
            return true;
            
        default: 
            return super.onOptionsItemSelected(item); 
        } 
    }
    
    public void change_pass()
    {
    	Intent i=new Intent(getBaseContext(),VMChangePass.class);
	    startActivity(i);

    }

        
    public void respondToquery()
    {
    	SoapObject request = XMLPacker.getInstance().getDisplayQueryListRequest();
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_QUERY_LIST, Events.EVENT_DISPLAY_QUERY_LIST);
    }

    
    public void updateprofile()
    {
    	//Intent i=new Intent(getBaseContext(),VMUpdateProfile.class);
	    //startActivity(i);
    	VMMyAccount.isUpdate=1;
	    SoapObject request = XMLPacker.getInstance().getProfileDetailsRequest(VMLoginScreen.UName, VMLoginScreen.type);
	    HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PROFILE, Events.EVENT_DISPLAY_PROFILE);

    }

}