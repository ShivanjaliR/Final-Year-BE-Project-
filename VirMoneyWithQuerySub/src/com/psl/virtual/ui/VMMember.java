package com.psl.virtual.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.SearchManager;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.ui.VMProductList.MyCustomBaseAdapter;
import com.psl.virtual.ui.VMProductList.MyCustomBaseAdapter.ViewHolder;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils.Member;
import com.psl.virtual.xml.XMLPacker;

public class VMMember extends ListActivity {
	
	AlertDialog al;
	private ListView lv1;
	private MyCustomBaseAdapter mAdapter;
	private String UserId=null,UserName=null;
	@Override
    public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		// Use your own layout
		setContentView(R.layout.main);
		
		HashMap<String, String> oHTValues = (HashMap<String, String>)this.getIntent().getSerializableExtra("info");
		
		String[] sMemberIDs = oHTValues.get("MemberId").trim().split(";");
		String[] sNames = oHTValues.get("Name").trim().split(";");
		
		ArrayList<Member> oMemberList = new ArrayList<Member>();
		Member oMember = null; 
		 
		for (int i = 0; i < sMemberIDs.length; i++) 
		{
			oMember = new Member(sMemberIDs[i], sNames[i]);
			oMemberList.add(oMember);
			System.out.println("++++++++++++oProduct["+i+"] : "+oMember);
		}
       
		lv1 = this.getListView();
		mAdapter = new MyCustomBaseAdapter(this, oMemberList);
        lv1.setAdapter(mAdapter);
       
        lv1.setOnItemClickListener(new OnItemClickListener() {
        
         public void onItemClick(AdapterView<?> a, View v, int position, long id) 
         {
	          Object o = lv1.getItemAtPosition(position);
	          Member fullObject = (Member)o;
	          Toast.makeText(VMApp.getInstance(), "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
	          UserId=fullObject.getMemberId();
	          UserName=fullObject.getName();
         } 
        });
        mAdapter.notifyDataSetChanged();
	}
	                                                                                                                                  
	public static class MyCustomBaseAdapter extends BaseAdapter {
		 private static ArrayList<Member> searchArrayList;
		 
		 private LayoutInflater mInflater;

		 public MyCustomBaseAdapter(Context context, ArrayList<Member> items) 
		 {
			 super();
			 searchArrayList = items;
			 mInflater = LayoutInflater.from(context);
		 }

		 public int getCount() {
			 return searchArrayList.size();
		 }

		 public Member getItem(int position) {
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
				   holder.txtUserId = (TextView) convertView.findViewById(R.id.pid);
				   holder.txtName = (TextView) convertView.findViewById(R.id.pname);
				   holder.txtBlank = (TextView) convertView.findViewById(R.id.pprice);
		
				   convertView.setTag(holder);
			  }
			  else
			  {
				  holder = (ViewHolder) convertView.getTag();
			  }
			  
			  holder.txtUserId.setText(searchArrayList.get(position).getMemberId());
			  holder.txtName.setText(searchArrayList.get(position).getName());
			  holder.txtBlank.setText("");
			  return convertView;
		 }

		 static class ViewHolder {
		  TextView txtUserId;
		  TextView txtName;
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
    public boolean onCreateOptionsMenu(Menu menu)
    { 
    	//super.onCreateOptionsMenu(menu);
    	   
    	    /* MenuItem item = menu.add("Painting");
    	     item.setIcon(R.drawable.ic_menu_add);
    	     
    	     item = menu.add("Photos");
    	     item.setIcon(R.drawable.ic_menu_edit);
    	     */
        MenuInflater inflater = getMenuInflater(); 
        inflater.inflate(R.menu.member, menu);
        
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
        case R.id.delAcc: 
        	deleteAccount(); 
            return true; 
        case R.id.home: 
        	Intent i=new Intent(getBaseContext(),VMAdminHome.class);
	    	startActivity(i);
            return true;
        case R.id.logout_m: 
         	i=new Intent(getBaseContext(),VMLoginScreen.class);
    	    startActivity(i); 
            return true;               
        default: 
            return super.onOptionsItemSelected(item); 
        } 
    }    
    public void deleteAccount()    
    {  if(UserId!=null)
    	{
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
    	    builder.setMessage("Are you sure you want to Delete Account: "+UserId +" "+ UserName +" ?") 
    		       .setCancelable(false) 
    		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
    		           public void onClick(DialogInterface dialog, int id) { 
    		              //VMLoginScreen.this.finish(); 
    		              SoapObject request = XMLPacker.getInstance().getDeleteAccountRequest(UserId);
    		      		HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DELETE_ACCOUNT, Events.EVENT_DELETE_ACCOUNT);
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
    {
        Toast.makeText(VMApp.getInstance(), "Please select member for Deletion", Toast.LENGTH_LONG).show();
    }
    }
    
    public void addproduct()
    {
    	Intent i=new Intent(getBaseContext(),VMAddProductScreen.class);
	    startActivity(i);

    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) 
    {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
    
    }
	    		    
}