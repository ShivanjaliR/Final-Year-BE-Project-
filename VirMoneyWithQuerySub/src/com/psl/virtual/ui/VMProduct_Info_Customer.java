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
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.ui.VMLoginScreen;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.xml.XMLPacker;


public class VMProduct_Info_Customer extends Activity 
{
	public static String PID_C,VendorAcc_C,Qty_C;
	int flag;
	
	public void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_LEFT_ICON);
			setContentView(R.layout.product_info_cust);
			getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
			
	        flag =0;
	        
	        Object oObj = this.getIntent().getSerializableExtra("info");
	        System.out.println("###############oObj: "+oObj);
	        
        	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
        	

        	TextView oProdID =(TextView)findViewById(R.id.edit_Cust_prod_id);
        	oProdID.setText(oHTValues.get("ProductId"));
        	System.out.println("+++++++Name value: "+oHTValues.get("ProductId"));
        	PID_C=oHTValues.get("ProductId");
        	
        	TextView oUsernameText =(TextView)findViewById(R.id.edit_Cust_prod_name);
        	oUsernameText.setText(oHTValues.get("Name"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Name"));
        	
        	TextView oQuantity =(TextView)findViewById(R.id.edit_Cust_prod_quantity);
        	String Qnt = oHTValues.get("Quantity");
        	if(Qnt.matches("0"))
        	{
        		oQuantity.setText("Out of Stock");
        		flag =1;
        	}
        	else
        	{
        		oQuantity.setText(Qnt);
        	}
        	System.out.println("+++++++Name value: "+oHTValues.get("Quantity"));
        	Qty_C=oHTValues.get("Quantity");
        	
        	TextView oPrice =(TextView)findViewById(R.id.edit_Cust_prod_price);
        	oPrice.setText(oHTValues.get("Price"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Price"));
        	
        	TextView oProdDesc =(TextView)findViewById(R.id.edit_Cust_descrip);
        	oProdDesc.setText(oHTValues.get("Notes"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Notes"));
        	
        	TextView oManuf =(TextView)findViewById(R.id.edit_Cust_manufacturer);
        	oManuf.setText(oHTValues.get("Manufacturer"));
        	System.out.println("+++++++Name value: "+oHTValues.get("Manufacturer")); 
        	
        	TextView oShippingCost =(TextView)findViewById(R.id.edit_Cust_shippingcost);
        	oShippingCost.setText(oHTValues.get("ShippingCost"));
        	System.out.println("+++++++Name value: "+oHTValues.get("ShippingCost"));
        	
        	TextView oShippingTime =(TextView)findViewById(R.id.edit_Cust_shippingtime);
        	oShippingTime.setText(oHTValues.get("ProcessingTime"));
        	System.out.println("+++++++Name value: "+oHTValues.get("ProcessingTime"));
        	
        	TextView oVendorAcc =(TextView)findViewById(R.id.edit_Cust_vendor_acc);
        	oVendorAcc.setText(oHTValues.get("VendorAcc"));
        	System.out.println("+++++++Name value: "+oHTValues.get("VendorAcc"));
        	VendorAcc_C=oHTValues.get("VendorAcc");
        	
        	TextView oDiscount =(TextView)findViewById(R.id.edit_Cust_discount);
        	String sDisc = oHTValues.get("Discount");
        	sDisc = sDisc + " %";
        	oDiscount.setText(sDisc);
        	System.out.println("+++++++Name value: "+oHTValues.get("Discount"));
        	
        	TextView balance = (TextView)findViewById(R.id.edit_Cust_bal);
        	balance.setText(oHTValues.get("MoneyPoint"));

        	
        	Toast.makeText(this, "Product details received from server: \n"+oHTValues.toString(), Toast.LENGTH_LONG);
	         
	}//end of class
	
	
	public void cancel(View view)
	{	   AlertDialog al;
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	       builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMProduct_Info_Customer.this.finish(); 
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
	public void buy(View view)
	{   
		if(flag==1)
		{
			Utils.showToast(this, "Product Out of Stock");
		}
		else
		{
		Object oObj = this.getIntent().getSerializableExtra("info");
        System.out.println("###############oObj: "+oObj);
        HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");

		Intent oIntent =  new Intent(Intent.ACTION_MAIN);
		oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMBill_input");
		oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		oIntent.putExtra("info", oHTValues);					
		VMApp.getInstance().startActivity(oIntent);
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
	 
	
