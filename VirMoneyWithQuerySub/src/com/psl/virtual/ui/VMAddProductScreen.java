package com.psl.virtual.ui;

import org.ksoap2.serialization.SoapObject;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
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


public class VMAddProductScreen extends Activity {
	AlertDialog al; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addproduct);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
		
		TextView AccNo =(TextView)findViewById(R.id.edit_vendoraccno);
    	AccNo.setText(VMLoginScreen.UName);
    	
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
	        
	public void check(View view)
	{
		EditText temp = (EditText)findViewById(R.id.edit_productid);
		String ProductId = temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_name);
		String ProductName = temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_quantity);
		String Quantity=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_price);
		String Price=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_category);
		String Category=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_notes);
		String Notes=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_manufact);
		String Manufacturer=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_shippingtime);
		String ShippingTime=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_shippingcost);
		String ShippingCost=temp.getText().toString().trim();
				
		temp=(EditText)findViewById(R.id.edit_vendoraccno);
		String VendorAccNo=temp.getText().toString().trim();
		
		temp=(EditText)findViewById(R.id.edit_discount);
		String Discount=temp.getText().toString().trim();
		
		AddProduct(ProductId,ProductName,Quantity,Price,Category,Manufacturer,Discount,Notes,ShippingCost,ShippingTime,VendorAccNo);
		
		/*UpdateProduct(ProductId,ProductName,Quantity,Price,Category,Manufacturer,Discount,Notes,"100",ShippingTime,VendorAccNo)
		UpdateProduct("P8","SPRITE","5","25","Cold drink","Coco cola","1","Cool","100","2-2-10","v1");*/
	}
	
	private void AddProduct(String sProductId,String sPrdName,String Quantity,String Price, String sCategory,String sManufacturer,String Discount,String sNotes,String ShippingCost, String sProcessingTime,String sVendorAccNo) 
	{
		// TODO Auto-generated method stub
		SoapObject request = XMLPacker.getInstance().getAddProductRequest( sProductId, sPrdName, Quantity, Price,sCategory,sManufacturer, Discount, sNotes,ShippingCost,sProcessingTime,sVendorAccNo);
		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_ADD_PRODUCT, Events.EVENT_ADD_PRODUCT);
		
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
		              VMAddProductScreen.this.finish(); 
		              
		          	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
                    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                VMApp.getInstance().startActivity(oIntent);
	        
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
