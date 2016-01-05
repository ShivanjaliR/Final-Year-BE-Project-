package com.psl.virtual.ui;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Constants;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMRegistrationScreen extends Activity {

	AlertDialog al; 
	String s,s1,s2,s3,s4,s5,s6,s7,s8,sGender,dateString;
	EditText name,add,mbno,email,bank,accno,pass,VMAccNo;
	int pos;
	String type = "",gender = "";
	//private Activity mParent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		this.setContentView(R.layout.registration);
	}
	
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMRegistrationScreen.this.finish(); 
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
		
		name=(EditText)findViewById(R.id.usrnameedit);
		String sUserName = name.getText().toString().trim();
		
		add=(EditText)findViewById(R.id.addressedit);
		String sAddr = add.getText().toString().trim();
		
		mbno=(EditText)findViewById(R.id.mobileedit);
		String sContact = mbno.getText().toString().trim();
		
		email=(EditText)findViewById(R.id.emailedit);
		String sEmail = email.getText().toString().trim();
		
		bank=(EditText)findViewById(R.id.bankedit);
		String sBankName = bank.getText().toString().trim();
		
		accno=(EditText)findViewById(R.id.accountedit);
		String sBankAccNo = accno.getText().toString().trim();
		
		VMAccNo=(EditText)findViewById(R.id.editAccNo);
		String sUAccNo = VMAccNo.getText().toString().trim();
		
		pass=(EditText)findViewById(R.id.password);
		String sPassword = pass.getText().toString().trim();

		
		if(sUserName.length()==0 || sAddr.length()==0 || sContact.length()==0 || sEmail.length()==0 || sBankName.length()==0 || sBankAccNo.length()==0 || sUAccNo.length()==0 ||sPassword.length()==0)
		{
		  Utils.showToast(this, "Enter all fields");
		}
		else
		{
			if(sPassword.length()<6 || sPassword.length()>20)
				Utils.showToast(this, "Enter minimum 6 digit password and maximum 20 digit password");
			else
				{
		
					RadioGroup radioGroup = (RadioGroup) findViewById(R.id.type);
					int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
					switch (checkedRadioButton) {
					  case R.id.customer : type= "customer";
					                   	              break;
					  case R.id.vendor : type = "vendor";
							                      break;
					}
			        
			        
					radioGroup = (RadioGroup) findViewById(R.id.gender);
					checkedRadioButton = radioGroup.getCheckedRadioButtonId();
					switch (checkedRadioButton) {
					  case R.id.malegender : gender= "male";
					                   	              break;
					  case R.id.femalegender : gender = "female";
							                      break;
					}
					
					DatePicker dt=(DatePicker)findViewById(R.id.birthdatepicker);
					Date date=(Date)new Date(dt.getYear()-1900,dt.getMonth(),dt.getDayOfMonth());
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					dateString=sdf.format(date);					
					doRegistration(sUAccNo,sPassword,sUserName,gender,sAddr,sEmail,sBankName,type,sContact,sBankAccNo,dateString);
			  }
		   }
			
		}
	
	public void doRegistration(String sAccNo,String sPassword,String sName,String sGender,String sAddr,String sEmail,String sBankName,String sUserType,String sContactNo,String sBankAccNo,String sDOB)
	{
		//TODO: Fetch actual values from UI component
		SoapObject request = XMLPacker.getInstance().getRegistrationSoRequest( sAccNo,sPassword,sName,sGender,sAddr,sEmail,sBankName,sUserType,sContactNo,sBankAccNo,sDOB);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DO_REGISTRATION, Events.EVENT_REGISTRATION);
	}
	
	
}
