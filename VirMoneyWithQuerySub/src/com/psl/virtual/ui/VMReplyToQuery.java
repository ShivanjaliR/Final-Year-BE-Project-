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
import com.psl.virtual.xml.XMLPacker;

public class VMReplyToQuery extends Activity {
	//   For ADMINISTRATOR
	AlertDialog al; 
	String reply,sub;	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		this.setContentView(R.layout.replyquery);
		
		TextView txt =(TextView)findViewById(R.id.text_dis_querySub);
    	txt.setText(VMDisplayQueryList.QuerySub); 
		
		txt =(TextView)findViewById(R.id.text_dis_acc_no);
    	txt.setText(VMDisplayQueryList.SAccNo);
    	
    	txt =(TextView)findViewById(R.id.text_dis_query);
    	txt.setText(VMDisplayQueryList.Query);
		
	}
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMReplyToQuery.this.finish(); 
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
	
	public void replyquery(View view)
	{
		EditText temp = (EditText)findViewById(R.id.replyedit);
		reply = temp.getText().toString().trim();
	
		TextView temp1 = (TextView)findViewById(R.id.text_dis_querySub);
		sub = temp1.getText().toString().trim();
	
		SoapObject request = XMLPacker.getInstance().getReplyQueryRequest( VMDisplayQueryList.SAccNo,reply,sub);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_REPLY_TO_QUERY, Events.EVENT_REPLY_QUERY);	
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
	        	Intent i=new Intent(getBaseContext(),VMAdminHome.class);
		    	startActivity(i);
	     	     return true;
	        case R.id.logout_h: 
	        	i=new Intent(getBaseContext(),VMLoginScreen.class);
	    	    startActivity(i);
	       
	        	return true;
	               
	        default: 
	            return super.onOptionsItemSelected(item); 
	        }
	      } 
	        
	
}
