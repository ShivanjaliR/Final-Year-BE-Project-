package com.psl.virtual.ui;

import com.psl.virtual.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class VMResponseOfQuery extends Activity
{
	//For-------->Customer And Vendor
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.user_query_responce);
		
		TextView txt =(TextView)findViewById(R.id.text_userQueryResQuerySub_ans);
		txt.setText(VMQueryResponseList.sSubjectR);

		TextView sQuery =(TextView)findViewById(R.id.text_userQueryResQuery_ans);
		sQuery.setText(VMQueryResponseList.sQuery);
		
		TextView sDate =(TextView)findViewById(R.id.text_userQueryResAnsDate_ans);
		sDate.setText(VMQueryResponseList.oDate);
    	
		TextView Response =(TextView)findViewById(R.id.text_userQueryResResponse_ans);
		Response.setText(VMQueryResponseList.sResponse);
		
		
	}
	public void check(View v)
	{
		Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
        startActivity(i);
	}
	public void cancel(View v)
	{
		Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
        startActivity(i);
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
        	Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
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
