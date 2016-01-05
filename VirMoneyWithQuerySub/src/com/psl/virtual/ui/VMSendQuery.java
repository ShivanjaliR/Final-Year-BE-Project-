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
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.xml.XMLPacker;

public class VMSendQuery extends Activity {
	AlertDialog al;
	EditText Query,sub;
	String SQuery, AccNo,sSubject;
		@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		this.setContentView(R.layout.sendquery);
	}
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMSendQuery.this.finish(); 
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
	public void SendQuery(View view)
	{
		Query=(EditText)findViewById(R.id.queryedit);
		SQuery=Query.getText().toString().trim();
		AccNo = VMLoginScreen.UName;
		Query.setText("");
		
		sub=(EditText)findViewById(R.id.edit_sendQuerySub);
		sSubject=sub.getText().toString().trim();
		sub.setText("");
		
		if(SQuery.length()!=0)
		{
		SoapObject request = XMLPacker.getInstance().getSendQueryRequest(AccNo, SQuery,sSubject);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_SEND_QUERY, Events. EVENT_SEND_QUERY);
		}
	}
	
	

	 @Override 
	    public boolean onCreateOptionsMenu(Menu menu) { 
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
