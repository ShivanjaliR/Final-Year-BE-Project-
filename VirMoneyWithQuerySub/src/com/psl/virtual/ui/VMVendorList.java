package com.psl.virtual.ui;


import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.ListActivity;
import android.content.Context;
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
import com.psl.virtual.util.Utils.Customer;
import com.psl.virtual.util.Utils.Product;
import com.psl.virtual.util.Utils.Vendor;
import com.psl.virtual.xml.XMLPacker;

public class VMVendorList extends ListActivity{
    /** Called when the activity is first created. */

	private ListView lv1;
	private MyCustomBaseAdapter mAdapter;
	public static String VendorAcc;
	@Override
    public void onCreate(Bundle icicle) {
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
		
		String[] sNames = oHTValues.get("Name").trim().split(";");
		String[] sAddr = oHTValues.get("VendorId").trim().split(";");
		
		ArrayList<Vendor> oVendorList = new ArrayList<Vendor>();
		Vendor oVendor = null; 
		 
		for (int i = 0; i < sNames.length; i++) 
		{
			oVendor = new Vendor(sNames[i], sAddr[i]);
			oVendorList.add(oVendor);
			System.out.println("++++++++++++oVendor["+i+"] : "+oVendor);
		}
       
		lv1 = this.getListView();
		mAdapter = new MyCustomBaseAdapter(this, oVendorList);
        lv1.setAdapter(mAdapter);
        
        lv1.setOnItemClickListener(new OnItemClickListener() 
        {
	        public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	        {
		          Object o = lv1.getItemAtPosition(position);
		          Vendor fullObject = (Vendor)o;
		          
		          Toast.makeText(VMApp.getInstance(), "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
		          VendorAcc=fullObject.getName();
		          SoapObject request = XMLPacker.getInstance().getDisplayProductListRequest(VendorAcc);
		          HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PRODUCT_LIST, Events.EVENT_DISPLAY_PRODUCT_LIST);
	        } 
       });
        Intent oIntent =new Intent(Intent.ACTION_MAIN);
        //oHTValues = null;
    	oIntent.putExtra("info", "");
        mAdapter.notifyDataSetChanged();
		}
	}
        //---------------------------------
/*		setListAdapter(adapter);
//		Intent intent = getIntent(); 
		if (Intent.ACTION_SEARCH.equals(intent.getAction()))
		{    
			String query = intent.getStringExtra(SearchManager.QUERY); 
			//result=
			doMySearch(query);
			//if(result==true)
		    // this.setSelection(count);
		}
	
	}
}*/
	                                                                                                                                  
	public static class MyCustomBaseAdapter extends BaseAdapter {
		 private static ArrayList<Vendor> searchArrayList;
		 
		 private LayoutInflater mInflater;

		 public MyCustomBaseAdapter(Context context, ArrayList<Vendor> items) 
		 {
			 super();
			 searchArrayList = items;
			 mInflater = LayoutInflater.from(context);
		 }

		 public int getCount() {
			 return searchArrayList.size();
		 }

		 public Vendor getItem(int position) {
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
				   holder.txtPname = (TextView) convertView.findViewById(R.id.pname);
				   holder.txtAddr = (TextView) convertView.findViewById(R.id.pprice);
				   holder.txtBlank = (TextView) convertView.findViewById(R.id.pid);
				   convertView.setTag(holder);
			  }
			  else
			  {
				  holder = (ViewHolder) convertView.getTag();
			  }
			  
			  holder.txtPname.setText(searchArrayList.get(position).getName());
			  holder.txtAddr.setText(searchArrayList.get(position).getVendorId());
			  holder.txtBlank.setText("");
			  return convertView;
		 }

		 static class ViewHolder {
		  TextView txtPname;
		  TextView txtAddr;
		  TextView txtBlank;
		 }
		}
    
/*    public void doMySearch(String str)
	{
		int flag=0,count=0;
        while(count < values.length)
        {
        	if(str.equalsIgnoreCase(values[count]))
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

                //result=true;		    
        	}
        	
        	count++;
        }
        if(flag==0)
        {
        	Toast.makeText(this,"product not found", Toast.LENGTH_SHORT).show();
           // result=false;
        }
        
        //return result;
	}*/


    @Override 
    public boolean onCreateOptionsMenu(Menu menu) { 
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
    }
    
    public void addproduct()
    {
    	Intent i=new Intent(getBaseContext(),VMAddProductScreen.class);
	    startActivity(i);

    }
    
}

