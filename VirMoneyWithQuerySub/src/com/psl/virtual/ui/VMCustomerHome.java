package com.psl.virtual.ui;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMCustomerHome extends Activity  {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_customer);        
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
    	
    }
    
    @Override
    public void onBackPressed(){
    	// Do Nothing
    }
    
    public void dispProfile(View view)
    {		
    	SoapObject request = XMLPacker.getInstance().getProfileDetailsRequest(VMLoginScreen.UName, VMLoginScreen.type);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PROFILE, Events.EVENT_DISPLAY_PROFILE);
    }    
    public void dispVendorList(View view)
    {	
		SoapObject request = XMLPacker.getInstance().getDisplayVendorListRequest();                           
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_VENDOR_LIST, Events.EVENT_DISPLAY_VENDOR_LIST);
    }    
    public void shareMoney(View v)
    {
    	Intent i=new Intent(getBaseContext(),VMShareMoney.class);
        startActivity(i);
    }
    public void dispTrans(View v)
    {
    	SoapObject request = XMLPacker.getInstance().getTransactionDetailsRequest(VMLoginScreen.UName);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_TRANSACTION_DETAILS, Events.EVENT_TRANSACTTION_DETAILS);
    }
    
    public void signOut(View v)
    {
    	Intent i=new Intent(getBaseContext(),VMLoginScreen.class);
	    startActivity(i);
    }
  
  
}