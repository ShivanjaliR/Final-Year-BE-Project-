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
import com.psl.virtual.util.Utils.Product;
import com.psl.virtual.util.Utils.VendorTrans;
import com.psl.virtual.xml.XMLPacker;

public class VMVendorTransaction extends ListActivity{
    /** Called when the activity is first created. */

	private ListView lv1;
	private MyCustomBaseAdapter mAdapter;
	@Override
    public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		// Use your own layout
		setContentView(R.layout.main);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.vm1);
		
		
		HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
		
		String[] sSenAcc = oHTValues.get("SenderAcc").trim().split(";");
		String[] sDate_Time = oHTValues.get("Date_Time").trim().split(";");
		String[] sRecAcc = oHTValues.get("ReceiverAcc").trim().split(";");
		
		ArrayList<VendorTrans> oVendorTransList = new ArrayList<VendorTrans>();
		VendorTrans oVendorTrans = null; 
		 
		for (int i = 0; i < sSenAcc.length; i++) 
		{
			oVendorTrans = new VendorTrans(sSenAcc[i], sDate_Time[i], sRecAcc[i]);
			oVendorTransList.add(oVendorTrans);
			System.out.println("++++++++++++oVendorTrans["+i+"] : "+oVendorTrans);
		}
       
		lv1 = this.getListView();
		mAdapter = new MyCustomBaseAdapter(this, oVendorTransList);
        lv1.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
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
		 private static ArrayList<VendorTrans> searchArrayList;
		 
		 private LayoutInflater mInflater;

		 public MyCustomBaseAdapter(Context context, ArrayList<VendorTrans> items) 
		 {
			 super();
			 searchArrayList = items;
			 mInflater = LayoutInflater.from(context);
		 }

		 public int getCount() {
			 return searchArrayList.size();
		 }

		 public VendorTrans getItem(int position) {
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
				   holder.txtSender = (TextView) convertView.findViewById(R.id.pid);
				   holder.txtDT = (TextView) convertView.findViewById(R.id.pname);
				   holder.txtRec = (TextView) convertView.findViewById(R.id.pprice);
		
				   convertView.setTag(holder);
			  }
			  else
			  {
				  holder = (ViewHolder) convertView.getTag();
			  }
			  
			  holder.txtSender.setText(searchArrayList.get(position).getSendAcc());
			  holder.txtDT.setText(searchArrayList.get(position).getDate_Time());
			  holder.txtRec.setText(searchArrayList.get(position).getRecAcc());
	
			  return convertView;
		 }

		 static class ViewHolder {
		  TextView txtSender;
		  TextView txtDT;
		  TextView txtRec;
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

