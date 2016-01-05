package com.psl.virtual.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.ui.VMProductList.MyCustomBaseAdapter;
import com.psl.virtual.ui.VMProductList.MyCustomBaseAdapter.ViewHolder;
import com.psl.virtual.util.Utils.Product;
import com.psl.virtual.util.Utils.TransDet;

import android.R.integer;
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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VMTransDetailsCustomer extends ListActivity
{
	/** Called when the activity is first created. */

	private ListView lv1;
	
	private MyCustomBaseAdapterTrans mAdapter;
	@Override
    public void onCreate(Bundle icicle) {
		
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
		
		String[] sSenderIDs = oHTValues.get("SendorAccNo").trim().split(";");
		String[] sReceiver = oHTValues.get("ReceiAccNo").trim().split(";");
		String[] sDate = oHTValues.get("Date_Time").trim().split(";");
		String[] sType = oHTValues.get("Type").trim().split(";");
		String[] sAmt = oHTValues.get("Amount").trim().split(";");
		
		ArrayList<TransDet> oTranDetList = new ArrayList<TransDet>();
		TransDet oTrans = null; 
		 
		int j=0;
		
		for (int i = 0; i < sSenderIDs.length; i++) 
		{
			
			oTrans = new TransDet(sSenderIDs[i], sReceiver[i], sDate[i], sType[i], sAmt[i]);
			oTranDetList.add(oTrans);
			System.out.println("++++++++++++oProduct["+i+"] : "+oTrans);
			j++;
			if(j==5){
				break;
			}
		}
       
		lv1 = this.getListView();
		mAdapter = new MyCustomBaseAdapterTrans(this, oTranDetList);
        lv1.setAdapter(mAdapter);
       
        lv1.setOnItemClickListener(new OnItemClickListener() {
        
         public void onItemClick(AdapterView<?> a, View v, int position, long id) 
         {
	          Object o = lv1.getItemAtPosition(position);
	          TransDet fullObject = (TransDet)o;
	          Toast.makeText(VMApp.getInstance(), "You have chosen: " + " " + fullObject.getSenderId(), Toast.LENGTH_LONG).show();
         } 
        });
        mAdapter.notifyDataSetChanged();
		}
	}
        //---------------------------------

	
	public static class MyCustomBaseAdapterTrans extends BaseAdapter 
	{
		 private static ArrayList<TransDet> searchArrayList;
		 
		 private LayoutInflater mInflater;

		 public MyCustomBaseAdapterTrans(Context context, ArrayList<TransDet> items) 
		 {
			 super();
			 searchArrayList = items;
			 mInflater = LayoutInflater.from(context);
		 }

		 public int getCount() {
			 return searchArrayList.size();
		 }

		 public TransDet getItem(int position) {
		  return searchArrayList.get(position);
		 }

		 public long getItemId(int position) {
		  return position;
		 }

		 public View getView(int position, View convertView, ViewGroup parent) {
			  ViewHolder holder;
			  if (convertView == null) 
			  {
				   convertView = mInflater.inflate(R.layout.listing, null);
				   holder = new ViewHolder();
				   holder.txtSender = (TextView) convertView.findViewById(R.id.log_sender);
				   holder.txtDate = (TextView) convertView.findViewById(R.id.log_date);
				   holder.txtRec = (TextView) convertView.findViewById(R.id.log_rec);
				   holder.txtType = (TextView) convertView.findViewById(R.id.log_type);
				   holder.txtAmt = (TextView) convertView.findViewById(R.id.log_amt);		
				   convertView.setTag(holder);
			  }
			  else
			  {
				  holder = (ViewHolder) convertView.getTag();
			  }
			  
			  holder.txtSender.setText("");
			  holder.txtDate.setText("Date : " + searchArrayList.get(position).getDate());
			  holder.txtRec.setText("Receiver : " + searchArrayList.get(position).getReceiver());
			  holder.txtType.setText("Type : " + searchArrayList.get(position).getType());
			  holder.txtAmt.setText("Amount : " + searchArrayList.get(position).getAmt());

	
			  return convertView;
		 }

		 static class ViewHolder {
			 TextView txtSender;
			  TextView txtRec;
			  TextView txtDate;
			  TextView txtType;
			  TextView txtAmt;
		 }
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
        	Intent i;
        	if(VMLoginScreen.pos== 0)
        		i=new Intent(getBaseContext(),VMCustomerHome.class);
        	else if(VMLoginScreen.pos==1)
        		i=new Intent(getBaseContext(),VMVendorHome.class);
        	else
        		i=new Intent(getBaseContext(),VMAdminHome.class);           	
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
