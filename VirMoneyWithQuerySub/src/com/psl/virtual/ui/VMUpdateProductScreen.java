package com.psl.virtual.ui;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import com.psl.virtual.R;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.xml.XMLPacker;

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


public class VMUpdateProductScreen extends Activity {
	AlertDialog al; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		this.setContentView(R.layout.update_product_details);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
		
		Object oObj = this.getIntent().getSerializableExtra("info");
        System.out.println("###############oObj: "+oObj);
        
    	HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
    	    	   	
		EditText temp = (EditText)findViewById(R.id.edit_update_productid);
		temp.setText(oHTValues.get("ProductId"));
		
		temp=(EditText)findViewById(R.id.edit_update_prdname);
		temp.setText(oHTValues.get("Name"));
		
		temp=(EditText)findViewById(R.id.edit_update_quantity);
		temp.setText(oHTValues.get("Quantity"));
		
		temp=(EditText)findViewById(R.id.edit_update_price);
		temp.setText(oHTValues.get("Price"));
		
		temp=(EditText)findViewById(R.id.edit_update_category);
		temp.setText(oHTValues.get("Name"));

		
		temp=(EditText)findViewById(R.id.edit_update_notes);
		temp.setText(oHTValues.get("Notes"));

		
		temp=(EditText)findViewById(R.id.edit_update_manufact);
		temp.setText(oHTValues.get("Manufacturer"));

		
		temp=(EditText)findViewById(R.id.edit_update_shippingtime);
		temp.setText(oHTValues.get("ProcessingTime"));

		temp=(EditText)findViewById(R.id.edit_update_category);
		temp.setText(oHTValues.get("Category"));
		
		temp=(EditText)findViewById(R.id.edit_update_shippingcost);
		temp.setText(oHTValues.get("ShippingCost"));

				
		temp=(EditText)findViewById(R.id.edit_update_vandoraccno);
		temp.setText(oHTValues.get("VendorAcc"));
		
		
		temp=(EditText)findViewById(R.id.edit_update_discount);
		temp.setText(oHTValues.get("Discount"));
		
	}
	
	public void check(View view)
	{
		EditText temp = (EditText)findViewById(R.id.edit_update_productid);
		String ProductId = temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_prdname);
		String ProductName = temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_quantity);
		String Quantity=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_price);
		String Price=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_category);
		String Category=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_notes);
		String Notes=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_manufact);
		String Manufacturer=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_shippingtime);
		String ShippingTime=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_shippingcost);
		String ShippingCost=temp.getText().toString().trim();
				
		temp=(EditText)findViewById(R.id.edit_update_vandoraccno);
		String VendorAccNo=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_update_discount);
		String Discount=temp.getText().toString().trim();
		
		UpdateProduct(ProductId,ProductName,Quantity,Price,Category,Manufacturer,Discount,Notes,ShippingCost,ShippingTime,VendorAccNo);
		
	}
	
	private void UpdateProduct(String sProductId,String sPrdName,String Quantity,String Price, String sCategory,String sManufacturer,String Discount,String sNotes,String ShippingCost, String sProcessingTime,String sVendorAccNo) 
	{
		// TODO Auto-generated method stub
		SoapObject request = XMLPacker.getInstance().getUpdateProductRequest( sProductId, sPrdName, Quantity, Price,sCategory,sManufacturer, Discount, sNotes,ShippingCost,sProcessingTime,sVendorAccNo);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_UPDATE_PRODUCT_DETAILS, Events.EVENT_UPDATE_PRODUCT);
		
	}
	public void cancel(View view)
	{
	       AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    	builder.setMessage("Are you sure you want to exit?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) { 
		              VMUpdateProductScreen.this.finish(); 
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
