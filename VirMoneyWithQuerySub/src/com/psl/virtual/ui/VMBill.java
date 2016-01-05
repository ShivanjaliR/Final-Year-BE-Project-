package com.psl.virtual.ui;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.psl.virtual.R;

public class VMBill extends Activity  {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
    	
        Object oObj = this.getIntent().getSerializableExtra("info");
        System.out.println("###############oObj: "+oObj);
        
    	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
        
    	TextView oProdID =(TextView)findViewById(R.id.edit_bill_prod_id);
    	oProdID.setText(oHTValues.get("ProductId"));
    	System.out.println("+++++++Name value: "+oHTValues.get("ProductId"));
    	
    	TextView oProdName =(TextView)findViewById(R.id.edit_bill_prod_name);
    	oProdName.setText(oHTValues.get("Name"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Name"));   
    	
    	TextView oVendorAcc =(TextView)findViewById(R.id.edit_bill_vendor);
    	oVendorAcc.setText(oHTValues.get("Vendor"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Vendor"));
    	
    	TextView oPrice =(TextView)findViewById(R.id.edit_bill_price);
    	oPrice.setText(oHTValues.get("Price"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Price"));
    	
    	TextView oQuantity =(TextView)findViewById(R.id.edit_bill_quantity);
    	oQuantity.setText(oHTValues.get("Quantity"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Quantity"));
    	
    	TextView oShipCost =(TextView)findViewById(R.id.edit_bill_shippingcost);
    	oShipCost.setText(oHTValues.get("ShippingCost"));
    	System.out.println("+++++++Name value: "+oHTValues.get("ShippingCost"));
    	
    	TextView oShipTime =(TextView)findViewById(R.id.edit_bill_shippingtime);
    	oShipTime.setText(oHTValues.get("ProcessingTime"));
    	System.out.println("+++++++Name value: "+oHTValues.get("ProcessingTime"));
    	
    	TextView oTotal =(TextView)findViewById(R.id.edit_bill_total);
    	oTotal.setText(oHTValues.get("Total"));
    	System.out.println("+++++++Name value: "+oHTValues.get("Total"));
    	
    	TextView oCalDiscount =(TextView)findViewById(R.id.edit_saving);
    	oCalDiscount.setText(oHTValues.get("CalculatedDiscount"));
    	System.out.println("+++++++Name value: "+oHTValues.get("CalculatedDiscount"));
    	    	
    }
    
    @Override
    public void onBackPressed() {
    	// Do Nothing
    }
    
    public void return_to_home_page(View v)
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