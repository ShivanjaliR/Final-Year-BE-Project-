package com.psl.virtual.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils.Product;
import com.psl.virtual.xml.XMLPacker;

public class VMCustProductList extends ListActivity{
    /** Called when the activity is first created. */

	private ListView lv1;
	private MyCustomBaseAdapter mAdapter;
	public static String PID;
	String[] sNames;
	AlertDialog al;
	@Override
    public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		// Use your own layout
		setContentView(R.layout.main);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
    	
		HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
		if(oHTValues.isEmpty())
		{
			Intent i=new Intent(getBaseContext(),VMEmpty.class);
	    	startActivity(i);
		}
		else
		{
		
		String[] sProductIDs = oHTValues.get("ProductID").trim().split(";");
		sNames = oHTValues.get("Name").trim().split(";");
		String[] sPrices = oHTValues.get("Price").trim().split(";");
		
		ArrayList<Product> oProductList = new ArrayList<Product>();
		Product oProduct = null; 
		 
		for (int i = 0; i < sProductIDs.length; i++) 
		{
			oProduct = new Product(sProductIDs[i], sNames[i], sPrices[i]);
			oProductList.add(oProduct);
			System.out.println("++++++++++++oProduct["+i+"] : "+oProduct);
		}
       
		lv1 = this.getListView();
		mAdapter = new MyCustomBaseAdapter(this, oProductList);
        lv1.setAdapter(mAdapter);
       
        lv1.setOnItemClickListener(new OnItemClickListener() {
        
         public void onItemClick(AdapterView<?> a, View v, int position, long id) 
         {
        	  SoapObject request;
	          Object o = lv1.getItemAtPosition(position);
	          Product fullObject = (Product)o;
	          Toast.makeText(VMApp.getInstance(), "You have chosen: " + " " + fullObject.getPName(), Toast.LENGTH_LONG).show();
	          PID = fullObject.getPID();
	          
	          if(VMLoginScreen.pos==1)	        	  
	        	  request = XMLPacker.getInstance().getProductDetailsSoRequest(VMLoginScreen.UName, fullObject.getPID(),VMLoginScreen.UName);
	          else
	        	  request = XMLPacker.getInstance().getProductDetailsSoRequest(VMVendorList.VendorAcc, fullObject.getPID(),VMLoginScreen.UName);
	          
	          HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PRODUCT_DETAILS, Events.EVENT_DISPLAY_PRODUCT_DETAILS);
         } 
        });
        mAdapter.notifyDataSetChanged();
		}
	}//End of onCreate

	                                                                                                                                  
	public static class MyCustomBaseAdapter extends BaseAdapter {
		 private static ArrayList<Product> searchArrayList;
		 
		 private LayoutInflater mInflater;

		 public MyCustomBaseAdapter(Context context, ArrayList<Product> items) 
		 {
			 super();
			 searchArrayList = items;
			 mInflater = LayoutInflater.from(context);
		 }

		 public int getCount() {
			 return searchArrayList.size();
		 }

		 public Product getItem(int position) {
		  return searchArrayList.get(position);
		 }

		 public long getItemId(int position) {
		  return position;
		 }

		 public View getView(int position, View convertView, ViewGroup parent) {
			  ViewHolder holder;
			  if (convertView == null) 
			  {
				   convertView = mInflater.inflate(R.layout.imagelist, null);
				   holder = new ViewHolder();
				   holder.txtPid = (TextView) convertView.findViewById(R.id.pid);
				   holder.txtPname = (TextView) convertView.findViewById(R.id.pname);
				   holder.txtPrice = (TextView) convertView.findViewById(R.id.pprice);
		
				   convertView.setTag(holder);
			  }
			  else
			  {
				  holder = (ViewHolder) convertView.getTag();
			  }
			  
			  holder.txtPid.setText(searchArrayList.get(position).getPID());
			  holder.txtPname.setText(searchArrayList.get(position).getPName());
			  holder.txtPrice.setText(searchArrayList.get(position).getPrice());
	
			  return convertView;
		 }

		 static class ViewHolder {
		  TextView txtPid;
		  TextView txtPname;
		  TextView txtPrice;
		 }
		}
    
    public void doMySearch(String str)
	{
		int flag=0,count=0;
        while(count < sNames.length)
        {
        	if(str.equalsIgnoreCase(sNames[count]))
        	{
        		Toast.makeText(this,"product found ", Toast.LENGTH_SHORT).show();
     		    flag=1;
     		   ListView lv=getListView();
     		   this.setSelection(count);
       		   View view=lv.getChildAt(count);
     		   if(view!=null)
     		   {
     			   view.requestFocus();
     		   }
 
     		  String item = (String) getListAdapter().getItem(count);
     		  Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
        	}
        	
        	count++;
        }
        if(flag==0)
        {
        	Toast.makeText(this,"product not found", Toast.LENGTH_SHORT).show();
        }

	}


    @Override 
    public boolean onCreateOptionsMenu(Menu menu)
    { 
        MenuInflater inflater = getMenuInflater(); 
        inflater.inflate(R.menu.list, menu);
        
        return true; 
    }
    
    @Override 
    public boolean onOptionsItemSelected(MenuItem item)
    { 
        // Handle item selection 
        switch (item.getItemId())
        { 
        case R.id.search: 
     	     onSearchRequested(); 
     	     return true;
        case R.id.home: 
        	Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
	    	startActivity(i);
    	     return true;
       case R.id.logout_ven: 
    	    i=new Intent(getBaseContext(),VMLoginScreen.class);
   	    	startActivity(i); 
           return true;
        default: 
            return super.onOptionsItemSelected(item); 
        } 
    }
    
    public void deleteproduct()
    {
    	if(PID!=null)
    	{
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	    builder.setMessage("Are you sure you want to Delete Product: "+PID  +" ?") 
		       .setCancelable(false) 
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		           public void onClick(DialogInterface dialog, int id) {  
		        	   SoapObject request = XMLPacker.getInstance().getDeleteProductRequest(VMLoginScreen.UName, PID);
		       	       HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DELETE_PRODUCT, Events.EVENT_DELETE_PRODUCT);
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
    	else
    		Toast.makeText(VMApp.getInstance(), "Please select Product for Deletion", Toast.LENGTH_LONG).show();
		
	    	
	    	
    }
    
    public void addproduct()
    {
    	Intent i=new Intent(getBaseContext(),VMAddProductScreen.class);
	    startActivity(i);

    }
}

