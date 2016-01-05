package com.psl.virtual.ui;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMMyAccount extends Activity {
    /** Called when the activity is first created. */
	public static int isUpdate=0;
	public static String MPoints, UAcc, BName, BAcc;
	private String UserAccNo="";
	AlertDialog al;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
        setContentView(R.layout.myaccount);
        
        Object oObj = this.getIntent().getSerializableExtra("info");
        System.out.println("###############oObj: "+oObj);
        
    	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
    	
    	TextView AccNo =(TextView)findViewById(R.id.ans_vm_acc_no);
    	UserAccNo=oHTValues.get("AccNo");
    	AccNo.setText(oHTValues.get("AccNo"));
    	System.out.println("+++++++Name value: "+oHTValues.get("AccNo"));
    	UAcc = UserAccNo;
    	
    	TextView oName =(TextView)findViewById(R.id.ans_user_name);
    	oName.setText(oHTValues.get("Name"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Name"));
    	
    	TextView Gender =(TextView)findViewById(R.id.ans_gender);
    	Gender.setText(oHTValues.get("Gender"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Gender"));
    	
    	TextView Address =(TextView)findViewById(R.id.ans_acc_address);
    	Address.setText(oHTValues.get("Address"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Address"));
    	
    	TextView email =(TextView)findViewById(R.id.ans_acc_email);
    	email.setText(oHTValues.get("email"));
    	System.out.println("+++++++Name value: "+oHTValues.get("email"));
    	
    	TextView BankName =(TextView)findViewById(R.id.ans_bank);
    	BankName.setText(oHTValues.get("BankName"));
    	System.out.println("+++++++Name value: "+oHTValues.get("BankName"));
    	BName = oHTValues.get("BankName");
    	
    	TextView Type =(TextView)findViewById(R.id.ans_acc_user);
    	Type.setText(oHTValues.get("Type"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Type"));
    	
    	TextView BankAccNo =(TextView)findViewById(R.id.ans_acc_no);
    	BankAccNo.setText(oHTValues.get("BankAccNo"));
    	System.out.println("+++++++Name value: "+oHTValues.get("BankAccNo"));
    	BAcc = oHTValues.get("BankAccNo");
    	
    	TextView DOB =(TextView)findViewById(R.id.ans_dob);
    	DOB.setText(oHTValues.get("DOB"));
    	System.out.println("+++++++Name value: "+oHTValues.get("DOB"));
    	
    	TextView ContactNo =(TextView)findViewById(R.id.ans_acc_mb_no);
    	ContactNo.setText(oHTValues.get("ContactNo"));
    	System.out.println("+++++++Name value: "+oHTValues.get("ContactNo"));
    	
    	TextView MoneyPoints =(TextView)findViewById(R.id.ans_acc_curr_bal);
    	MoneyPoints.setText(oHTValues.get("MoneyPoints"));
    	MPoints = oHTValues.get("MoneyPoints");
    	System.out.println("+++++++Name value: "+oHTValues.get("MoneyPoints"));
    }
    
    @Override 
    public boolean onCreateOptionsMenu(Menu menu)
    { 
        MenuInflater inflater = getMenuInflater(); 
        inflater.inflate(R.menu.option, menu); 
        return true; 
    }
    @Override 
    public boolean onOptionsItemSelected(MenuItem item)
    {   // Handle item selection 
        switch (item.getItemId())
        { 
        case R.id.recharge: 
            recharge(); 
            return true;
        case R.id.liquify: 
            liquify(); 
            return true;
        case R.id.chg_pass: 
            change_pass(); 
            return true;
        case R.id.acc_query:
        	accquery();
        	return true;        	
        case R.id.send_query: 
            sendquery(); 
            return true;
        case R.id.up_profile: 
              updateprofile();
            return true;
        case R.id.delete_acc:
            delete_account(); 
            return true;
        case R.id.logout:
        	Intent i=new Intent(getBaseContext(),VMLoginScreen.class);
    	    startActivity(i);
            return true;
        default: 
            return super.onOptionsItemSelected(item); 
        } 
    }
    
    
    public void recharge()
    {
    	Intent i=new Intent(getBaseContext(),VMRechargeAccount.class);
	    startActivity(i);
    }
    
    public void liquify()
    {
    	Intent i=new Intent(getBaseContext(),VMLiquify.class);
	    startActivity(i);
    }
    
    public void change_pass()
    {
    	Intent i=new Intent(getBaseContext(),VMChangePass.class);
	    startActivity(i);

    }

    public void sendquery()
    {
    	Intent i=new Intent(getBaseContext(),VMSendQuery.class);
	    startActivity(i);
    }
    
    public void accquery()
    {
    	SoapObject request = XMLPacker.getInstance().getDisplayQueryResponseListRequest(UserAccNo);
	    HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_QUERY_RESPONSE_LIST, Events.EVENT_DISPLAY_QUERY_RESPONSE_LIST);
	}    
    public void updateprofile()
    {	isUpdate=1;
    	
	    SoapObject request = XMLPacker.getInstance().getProfileDetailsRequest(VMLoginScreen.UName, VMLoginScreen.type);
	    HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PROFILE, Events.EVENT_DISPLAY_PROFILE);

    }
    public void delete_account()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    builder.setMessage("Are you sure you want to Permanently Delete Account: "+UserAccNo  +" ?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) {  
		        	   SoapObject request = XMLPacker.getInstance().getDeleteAccountRequest(UserAccNo);
		       	       HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DELETE_ACCOUNT, Events.EVENT_DELETE_ACCOUNT);
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

}