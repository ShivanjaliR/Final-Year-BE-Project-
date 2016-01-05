package com.psl.virtual.ui;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.xml.XMLPacker;

public class VMLiquify extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		setContentView(R.layout.liquify_money_pt);
        
    	TextView AccNo =(TextView)findViewById(R.id.edit_liq_user);
    	AccNo.setText(VMMyAccount.UAcc);
    	
    	TextView oMPoints =(TextView)findViewById(R.id.edit_liq_bal);
    	oMPoints.setText(VMMyAccount.MPoints);
    
    	TextView oBankName =(TextView)findViewById(R.id.edit_liq_bankname);
    	oBankName.setText(VMMyAccount.BName);
    
    	TextView oBankAcc =(TextView)findViewById(R.id.edit_liq_bankacc);
    	oBankAcc.setText(VMMyAccount.BAcc);
    
	}
	
	public void liquify(View view)
	{
		TextView oMPoints =(TextView)findViewById(R.id.edit_liq_moneypt);
		String MPoint=oMPoints.getText().toString();
		
		SoapObject request = XMLPacker.getInstance().getLiquifyRequest(VMLoginScreen.UName,MPoint);
	    HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_LIQUIFY, Events.EVENT_LIQUIFY);
	}
	public void cancel(View view)
	{
		Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
        startActivity(i);
	}
	
}
