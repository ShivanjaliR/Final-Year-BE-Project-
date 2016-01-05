package com.psl.virtual.ui;

import java.io.ObjectInputStream.GetField;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.xml.*;



public class VMProduct_Info extends Activity {
	public static String PID,VendorAcc,Qty;

    public static int doUpdate=0;

	public void onCreate(Bundle savedInstanceState) {
		
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_LEFT_ICON);
			
	        setContentView(R.layout.product_info);
	      
	        Object oObj = this.getIntent().getSerializableExtra("info");
	        System.out.println("###############oObj: "+oObj);
	        
        	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");

        	TextView oProdID =(TextView)findViewById(R.id.edit_prod_id);
        	oProdID.setText(oHTValues.get("ProductId"));
        	PID=oHTValues.get("ProductId");
        	System.out.println("+++++++Name value: "+oHTValues.get("ProductId")); 
        	
        	TextView oUsernameText =(TextView)findViewById(R.id.edit_prod_name);
        	oUsernameText.setText(oHTValues.get("Name"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Name"));
        	
        	TextView oQuantity =(TextView)findViewById(R.id.edit_prod_quantity);
        	String Qnt = oHTValues.get("Quantity");
        	if(Qnt.matches("0"))
        	{
        		oQuantity.setText("Out of Stock");	
        	}
        	else
        	{
        		oQuantity.setText(Qnt);
        	}
        	Qty=oHTValues.get("Quantity");
        	System.out.println("+++++++Name value: "+oHTValues.get("Quantity"));
        	
        	TextView oPrice =(TextView)findViewById(R.id.edit_prod_price);
        	oPrice.setText(oHTValues.get("Price"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Price"));
        	
        	TextView oProdDesc =(TextView)findViewById(R.id.edit_descrip);
        	oProdDesc.setText(oHTValues.get("Notes"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Notes"));
        	
        	TextView oManuf =(TextView)findViewById(R.id.edit_manufacturer);
        	oManuf.setText(oHTValues.get("Manufacturer"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Manufacturer")); 
        	
        	TextView oShippingCost =(TextView)findViewById(R.id.edit_shippingcost);
        	oShippingCost.setText(oHTValues.get("ShippingCost"));
        	System.out.println("+++++++Name value: "+oHTValues.get("ShippingCost"));
        	
        	TextView oShippingTime =(TextView)findViewById(R.id.edit_shippingtime);
        	oShippingTime.setText(oHTValues.get("ProcessingTime"));
        	System.out.println("+++++++Name value: "+oHTValues.get("ProcessingTime"));
        	
        	TextView oVendorAcc =(TextView)findViewById(R.id.edit_vendor_acc);
        	oVendorAcc.setText(oHTValues.get("VendorAcc"));
        	VendorAcc=oHTValues.get("VendorAcc");
        	System.out.println("+++++++Name value: "+oHTValues.get("VendorAcc")); 
        	
        	TextView oDiscount =(TextView)findViewById(R.id.edit_discount);
        	String sDisc = oHTValues.get("Discount");
        	sDisc = sDisc + " %";
        	oDiscount.setText(sDisc);
        	System.out.println("+++++++Name value: "+oHTValues.get("Discount")); 

        	
        	Toast.makeText(this, "Product details received from server: \n"+oHTValues.toString(), Toast.LENGTH_LONG);
	         
	}//end of class
	 
	
	public void delete(View view)
	 {
		 SoapObject request = XMLPacker.getInstance().getDeleteProductRequest(VendorAcc, PID);
     	 HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DELETE_PRODUCT, Events.EVENT_DELETE_PRODUCT);
 
	 }
	
	public void update(View view)
	 {
		doUpdate = 1;
		
		SoapObject request = XMLPacker.getInstance().getProductDetailsSoRequest(VMLoginScreen.UName, VMProductList.PID,VMLoginScreen.UName);
        HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PRODUCT_DETAILS, Events.EVENT_DISPLAY_PRODUCT_DETAILS);
	 }
	
	 public void option(Intent o1)
   	 {
   		   Bundle extras = o1.getExtras();
   			if (extras == null) {
   		       	return;
   			}
   			Hashtable hash = (Hashtable) extras.get("info");  
   		
   		    int i=0; 
   			for (Enumeration e = hash.keys(); e.hasMoreElements();)
   				{
   				  i++;
   				}
   	 }//end of m1

        
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
	        	Intent i=new Intent(getBaseContext(),VMVendorHome.class);
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

