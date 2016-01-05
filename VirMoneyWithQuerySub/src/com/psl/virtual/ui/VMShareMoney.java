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
import android.widget.TextView;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMShareMoney extends Activity
{
	AlertDialog al; 
	EditText sender,rec,mpts;
	String s1,s2,s3;
		@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		this.setContentView(R.layout.sharemoney);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
		
		TextView AccNo =(TextView)findViewById(R.id.edit_sender);
    	AccNo.setText(VMLoginScreen.UName);
    	
	}
		
		public void callShareMoneyWebMethod(View view )
		{
			
			sender=(EditText)findViewById(R.id.edit_sender);
			//sender.setText(VMLoginScreen.UName);
			s1=sender.getText().toString();
			rec=(EditText)findViewById(R.id.receiver_accnoedit);
			s2=rec.getText().toString();
			mpts=(EditText)findViewById(R.id.money_pt_edit);
			s3=mpts.getText().toString();
			
			if(s1.length()==0 || s2.length()==0 || s3.length()==0)
			{
			  Utils.showToast(this, "Enter all fields");
			}
			else
			{
			  ShareMoneyRequest(s1,s2,s3);
			}
			//viewLogs();
		}
		
		private void viewLogs()
		{
			SoapObject request = XMLPacker.getInstance().getViewLogsRequest();
			HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_VIEW_LOGS, Events.EVENT_VIEW_LOGS);
		}
		
		private void ShareMoneyRequest( String sSender,String sReceiver,String sMPoints)
		{
			SoapObject request = XMLPacker.getInstance().getShareMoneyRequest(sSender, sReceiver, sMPoints);
			HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_SHARE_MONEYPOINTS, Events.EVENT_SHARE_MONEYPOINTS);
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
		        
		   
		   
		
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMShareMoney.this.finish(); 
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
