package com.psl.virtual.ui;

import org.ksoap2.serialization.SoapObject;

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
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;

public class VMBill_input extends Activity
{
    /** Called when the activity is first created. */
	public String AvailQty, IpQty;
	Integer AvlQty, IpQuant;
	EditText mAvailQty, mQuantity, mCustAccNo, mProductId, mVenAccNo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_input);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
    	        
        mProductId=(EditText)findViewById(R.id.editTextipBillPrdId);
        mProductId.setText(VMProduct_Info_Customer.PID_C);
        
        mCustAccNo=(EditText)findViewById(R.id.editTextipBillCustAcc);
        
        mVenAccNo=(EditText)findViewById(R.id.editTextipBillVenAcc);
        mVenAccNo.setText(VMProduct_Info_Customer.VendorAcc_C);
        
        mAvailQty=(EditText)findViewById(R.id.editTextipAvailBillQuantity);
        mAvailQty.setText(VMProduct_Info_Customer.Qty_C);
        
        mQuantity=(EditText)findViewById(R.id.editTextipBillQuantity);
        mQuantity.setText("1");
    }
    
    
    public void check(View view)
    {
    	IpQty = mQuantity.getText().toString();
        IpQuant = Integer.parseInt(IpQty);
    	AvailQty = mAvailQty.getText().toString();
        AvlQty = Integer.parseInt(AvailQty);
        String inAccNo=mCustAccNo.getText().toString();
    	if(AvlQty < IpQuant)
    	{
    		Toast.makeText(VMApp.getInstance(), "Quantity Exceeded", Toast.LENGTH_SHORT).show();
    	}
    	else if(!inAccNo.equals(VMLoginScreen.UName))
    	{
    		Toast.makeText(VMApp.getInstance(), "Invalid Username \nPlease Enter Your Account No", Toast.LENGTH_SHORT).show();
    	}
    	else
    	{
    		String sVendor=mVenAccNo.getText().toString();   
    		String sPrdId=mProductId.getText().toString();
    		String sCustAcc=mCustAccNo.getText().toString();
    		
			SoapObject request = XMLPacker.getInstance().getBuyRequest( sVendor, sPrdId, sCustAcc, IpQty);
			HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_BUY_PRODUCT, Events.EVENT_BUY_PRODUCT);
    		
    	}
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