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
import com.psl.virtual.util.Utils.Query;
import com.psl.virtual.xml.XMLPacker;

public class VMDisplayQueryList extends ListActivity{
    /** Called when the activity is first created. */

	private ListView lv1;
	private MyCustomBaseAdapter mAdapter;
	public static String Query, SAccNo,QuerySub; 
	@Override
    public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		// Use your own layout
		setContentView(R.layout.main);
		
		HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
		if(oHTValues.isEmpty())
		{
			Intent i=new Intent(getBaseContext(),VMEmpty.class);
	    	startActivity(i);
		}
		else
		{
		
		String[] sSenderAccs = oHTValues.get("SenderAccNo").trim().split(";");
		String[] sSubjects  = oHTValues.get("Subject").trim().split(";");		
		String[] sQuerys = oHTValues.get("AskedQuery").trim().split(";");
		String[] sDates = oHTValues.get("Date").trim().split(";");
		//String[] sResponse =" ";// oHTValues.get("Response").trim().split(";");		
		//String[] sAnsDates = " ";//oHTValues.get("AnsDate").trim().split(";");
		
		ArrayList<Query> oProductList = new ArrayList<Query>();
		Query oProduct = null; 
		 
		for (int i = 0; i < sSenderAccs.length; i++) 
		{
			oProduct = new Query(sSenderAccs[i], sSubjects[i], sQuerys[i],sDates[i],"","");//sResponse[i],sAnsDates[i]);
			oProductList.add(oProduct);
			System.out.println("+++++++++++Query List["+i+"] : "+oProduct);
		}
       
		lv1 = this.getListView();
		mAdapter = new MyCustomBaseAdapter(this, oProductList);
        lv1.setAdapter(mAdapter);
       
        lv1.setOnItemClickListener(new OnItemClickListener() {
        
         public void onItemClick(AdapterView<?> a, View v, int position, long id) 
         {
	          Object o = lv1.getItemAtPosition(position);
	          Query fullObject = (Query)o;
	          Toast.makeText(VMApp.getInstance(), "You have chosen: " + " " + fullObject.getSendorAccNo(), Toast.LENGTH_LONG).show();
	          //soap request for response
	      	SAccNo = fullObject.mSendorAccNo;
			Query = fullObject.mAskedQuery;
			QuerySub=fullObject.mSubject;
			Intent oIntent = new Intent(Intent.ACTION_VIEW);
			oIntent.setClass(getApplicationContext(), VMReplyToQuery.class);
			startActivity(oIntent);
	         } 
        });
        mAdapter.notifyDataSetChanged();
		}
	}

	                                                                                                                                  
	public static class MyCustomBaseAdapter extends BaseAdapter {
		 private static ArrayList<Query> searchArrayList;
		 
		 private LayoutInflater mInflater;

		 public MyCustomBaseAdapter(Context context, ArrayList<Query> items) 
		 {
			 super();
			 searchArrayList = items;
			 mInflater = LayoutInflater.from(context);
		 }
		 public int getCount() {
			 return searchArrayList.size();
		 }
		 public Query getItem(int position) {
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
			  
			  holder.txtPid.setText("Sender: "+searchArrayList.get(position).getSendorAccNo());
			  holder.txtPname.setText("Subject: "+searchArrayList.get(position).getSubject());
			  holder.txtPrice.setText("Date: "+searchArrayList.get(position).getDate_Time());
	
			  return convertView;
		 }
		 static class ViewHolder {
		  TextView txtPid;
		  TextView txtPname;
		  TextView txtPrice;
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
	        	if(VMLoginScreen.pos==0)
	        	{
	        		Intent i=new Intent(getBaseContext(),VMCustomerHome.class);
	        		startActivity(i);
	        	}
	        	else
	        	{
		        	Intent i=new Intent(getBaseContext(),VMVendorHome.class);
			    	startActivity(i);
		        }
	     	     return true;
	        case R.id.logout_h: 
	        	Intent i=new Intent(getBaseContext(),VMLoginScreen.class);
	    	    startActivity(i);
	       
	        	return true;
	               
	        default: 
	            return super.onOptionsItemSelected(item); 
	        }
	      }

}

