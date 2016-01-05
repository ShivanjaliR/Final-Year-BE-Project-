package com.psl.virtual.ui;

import java.util.Collection;

import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.ui.VMProduct_Info;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import com.psl.virtual.ui.VMLoginScreen;

import com.psl.virtual.R;
import com.psl.virtual.VMApp;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.util.Utils;
import com.psl.virtual.util.Utils.Product;
import com.psl.virtual.xml.XMLPacker;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//----------------Working 20th March--------------------

public class VMResponseHandler extends Handler 
{

	
	String str;
	public static ArrayList<Product> oProductList = new ArrayList<Product>();
	@SuppressWarnings("unchecked")
	@Override
	
	public void handleMessage(Message msg) 
	{
		switch (msg.what) 
		{
			case Events.EVENT_REGISTRATION:
			{
				Hashtable<String ,String> htValues = (Hashtable<String, String>)msg.obj;
                String sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Toast.makeText(VMApp.getInstance(), "Successful Registration!!!!", Toast.LENGTH_LONG).show();
                	Intent oIntent =new Intent(Intent.ACTION_MAIN);
                	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMLoginScreen");
	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                VMApp.getInstance().startActivity(oIntent);
                 }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_LONG).show();
                }
			}
				break;	
			case Events.EVENT_TRANSACTTION_DETAILS:
			{
				//TODO: Use oProductList for populating VMTransDetailsCustomer
				Hashtable<String, String> htValues= (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++Trans Details htValues: "+htValues);
        		
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMTransDetailsCustomer");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
				
			}
				break;
				
			case Events.EVENT_LOGIN:
				Hashtable<String, String> htValues = (Hashtable<String, String>)msg.obj;
                String sResult = htValues.get(XMLPacker.TAG_RESULT);
                       	                
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
	                switch (VMLoginScreen.pos)
	                {
					case 0:
						oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Successful Login", Toast.LENGTH_LONG).show();
    	                break;

					case 1:
					    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Successful Login", Toast.LENGTH_LONG).show();
      	                break;
					case 2:
					    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMAdminHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Successful Login", Toast.LENGTH_LONG).show();
      	                break;
					}
               	}
                else
                {
          
                	Toast.makeText(VMApp.getInstance(), "Wrong Username or Password", Toast.LENGTH_LONG).show();
                }
				break;
				
			case Events.EVENT_DISPLAY_PROFILE:
			{
				switch(VMLoginScreen.pos)
				{
					case 2://If User Login as ADMIN*/
					{			
						htValues = (Hashtable<String, String>)msg.obj;
						System.out.println("++++++++++++htValues: "+htValues);
				//		Toast.makeText(VMApp.getInstance(), msg.obj.toString(), Toast.LENGTH_LONG).show();	
			          	
						Intent oIntent =  new Intent(Intent.ACTION_MAIN);
						switch ( VMMyAccount.isUpdate)
						{	
							case 0:
								oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMMyAccountAdmin");
								oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								oIntent.putExtra("info", htValues);
								VMApp.getInstance().startActivity(oIntent);
								break;
							case 1:
								oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMUpdateProfile");
								oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								oIntent.putExtra("info", htValues);
								VMMyAccount.isUpdate=0;
								VMApp.getInstance().startActivity(oIntent);
								break;
						}
					 }	 	     		
					 break;
					 default://If User Login as CUSTOMER AND VENDOR*/
					 {
						    htValues = (Hashtable<String, String>)msg.obj;
							System.out.println("++++++++++++htValues: "+htValues);
					  //	Toast.makeText(VMApp.getInstance(), msg.obj.toString(), Toast.LENGTH_LONG).show();				          	
							Intent oIntent =  new Intent(Intent.ACTION_MAIN);
							switch ( VMMyAccount.isUpdate)
							{	
								case 0:
									oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMMyAccount");
									oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									oIntent.putExtra("info", htValues);
									VMApp.getInstance().startActivity(oIntent);
									break;
								case 1:
									oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMUpdateProfile");
									oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									oIntent.putExtra("info", htValues);
									VMMyAccount.isUpdate=0;
									VMApp.getInstance().startActivity(oIntent);
									break;
							}
					 }break;
				}		
			}break;
			
			case Events.EVENT_UPDATE_PROFILE:
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Toast.makeText(VMApp.getInstance(), "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
	                switch (VMLoginScreen.pos)
	                {
					case 0:
						oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                break;

					case 1:
					    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
      	                break;
					case 2:
					    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMAdminHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
      	                break;
					}
                 }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_LONG).show();
                }
				break;
			
			case Events.EVENT_DISPLAY_VENDOR_TRANSACTION:
			{
				//TODO: Use oProductList for populating VMProductList
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorTransaction");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
				break;
			}
	
			case Events.EVENT_DISPLAY_CUSTOMER_LIST:
			{
				//TODO: Use oProductList for populating VMProductList
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerList");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
				break;
			}
			case Events.EVENT_DISPLAY_PRODUCT_LIST:
			{
				//TODO: Use oProductList for populating VMProductList
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
				if(VMLoginScreen.pos==0)
				{
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustProductList");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				}
				else
				{
	            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMProductList");
	            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				}	
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
				break;
			}
			case Events.EVENT_DISPLAY_VENDOR_LIST:
			{
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorList");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
			}
			break;
			case Events.EVENT_DISPLAY_MEMBER_LIST:
			{
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMMember");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
				break;
			}	
			case Events.EVENT_DELETE_ACCOUNT:
			{
				
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                       	                
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Toast.makeText(VMApp.getInstance(),"Account Deleted Succcessfully", Toast.LENGTH_LONG).show();
                	if(VMLoginScreen.pos==2)
                	{
                	 SoapObject request = XMLPacker.getInstance().getDisplayMemberListRequest();
        	 		 HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_MEMBER_TRANSACTION, Events.EVENT_DISPLAY_MEMBER_LIST);
                	}
                	else
                	{
                		Intent oIntent =new Intent(Intent.ACTION_MAIN);
                    	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMLoginScreen");
                    	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    	VMApp.getInstance().startActivity(oIntent);
                	}
                }
                else
                	Toast.makeText(VMApp.getInstance(),"Error : Can Not process Request", Toast.LENGTH_LONG).show();
                
                
				/*Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMMember");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);*/
			}				break;
			case Events.EVENT_DISPLAY_PRODUCT_DETAILS:
			{
				switch(VMLoginScreen.pos)
				{
					case 0://If User Login as CUSTOMER*/
					{
						htValues = (Hashtable<String, String>)msg.obj;
						System.out.println("++++++++++++htValues: "+htValues);
						//Toast.makeText(VMApp.getInstance(), msg.obj.toString(), Toast.LENGTH_LONG).show();	
			          		
						Intent oIntent =  new Intent(Intent.ACTION_MAIN);
						oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMProduct_Info_Customer");
						oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						oIntent.putExtra("info", htValues);					
						VMApp.getInstance().startActivity(oIntent);
					}	
						break;
					case 1://If User Login as VENDOR
					{	
						htValues = (Hashtable<String, String>)msg.obj;
						System.out.println("++++++++++++htValues: "+htValues);
						//Toast.makeText(VMApp.getInstance(), msg.obj.toString(), Toast.LENGTH_LONG).show();
						Intent oIntent =  new Intent(Intent.ACTION_MAIN);
						switch ( VMProduct_Info.doUpdate)
						{	
							case 0:
								oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMProduct_Info");
								oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								oIntent.putExtra("info", htValues);					
								VMApp.getInstance().startActivity(oIntent);
								break;
							case 1:
								oIntent =  new Intent(Intent.ACTION_MAIN);
								oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMUpdateProductScreen");
								oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								oIntent.putExtra("info", htValues);
								VMProduct_Info.doUpdate=0;
								VMApp.getInstance().startActivity(oIntent);
								break;
						}
					}break;
				}
			}break;				
			case Events.EVENT_BUY_PRODUCT:
				
				htValues = (Hashtable<String, String>)msg.obj;
				sResult = htValues.get("Total");
				System.out.println("++++++++++++htValues: "+htValues);				
                if(sResult.equalsIgnoreCase("0"))
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_LONG).show();
                 }
                else
                {
                	
                	Toast.makeText(VMApp.getInstance(), "Transaction Successful", Toast.LENGTH_LONG).show();
                	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
    				oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMBill");
    				oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				oIntent.putExtra("info", htValues);					
    				VMApp.getInstance().startActivity(oIntent);
                }
                break;
			case Events.EVENT_ADD_PRODUCT:
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                		
    	                Toast.makeText(VMApp.getInstance(), "Product Added Successfully!!", Toast.LENGTH_SHORT).show();
    	            	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
	                    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMAddProductScreen");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	        
                }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_SHORT).show();
                }
				break;

                
			case Events.EVENT_UPDATE_PRODUCT:
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
	                    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Product Updated Successfully!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_LONG).show();
                }
				break;
			case Events.EVENT_DELETE_PRODUCT:
			{
				//TODO: Use oProductList for populating VMProductList
				
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                       	                
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	 Toast.makeText(VMApp.getInstance(),"Product Deleted Succcessfully", Toast.LENGTH_LONG).show();
                	 //SoapObject request = XMLPacker.getInstance().getDisplayProductListRequest();
        	 		 //HttpUtil.getInstance().sendRequest(request, SOAPConstants.SOAP_ACTION_DISPLAY_PRODUCT_LIST, Events.EVENT_DISPLAY_PRODUCT_LIST);
                }
                else
                	Toast.makeText(VMApp.getInstance(),"Error : Can Not process Request", Toast.LENGTH_LONG).show();
			}break;
            
            case Events.EVENT_SHARE_MONEYPOINTS:
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Toast.makeText(VMApp.getInstance(), "Transaction Successful", Toast.LENGTH_SHORT).show();
                	Intent oIntent =  new Intent(Intent.ACTION_MAIN);                    
	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	if(VMLoginScreen.pos==0)
                        oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerHome");    	                
                	else                	
                		oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
	                
                	VMApp.getInstance().startActivity(oIntent);
                	
                 }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_LONG).show();
                }
				break;
			case Events.EVENT_SEND_QUERY:
				 
				htValues = (Hashtable<String, String>)msg.obj;
	            sResult = htValues.get(XMLPacker.TAG_RESULT);
	            if(sResult.equalsIgnoreCase("TRUE"))
	            {
	            	Toast.makeText(VMApp.getInstance(), "Query Sent", Toast.LENGTH_SHORT).show();
	            }
	            else
	            {
	            	Toast.makeText(VMApp.getInstance(), "Could Not Process Query", Toast.LENGTH_LONG).show();
	            }
	        break;
	            
			case Events.EVENT_CHANGE_PASSWORD:
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Toast.makeText(VMApp.getInstance(), "Password Successfully Changed", Toast.LENGTH_LONG).show();
                	Intent oIntent =  new Intent(Intent.ACTION_MAIN);
	                switch (VMLoginScreen.pos)
	                {
					case 0:
						oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Successful Login", Toast.LENGTH_LONG).show();
    	                break;

					case 1:
					    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Successful Login", Toast.LENGTH_LONG).show();
      	                break;
					case 2:
					    oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMAdminHome");
    	                oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	                VMApp.getInstance().startActivity(oIntent);
    	                Toast.makeText(VMApp.getInstance(), "Successful Login", Toast.LENGTH_LONG).show();
      	                break;
					}
                 }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Cannot Process Request", Toast.LENGTH_LONG).show();
                }
				break;
				
            case Events.EVENT_REPLY_QUERY:
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	Toast.makeText(VMApp.getInstance(), "Reply is transfer", Toast.LENGTH_LONG).show();
                 }
                else
                {
                	Toast.makeText(VMApp.getInstance(), "Reply is not transfer", Toast.LENGTH_LONG).show();
                }
				break;
            case Events.EVENT_DISPLAY_QUERY_RESPONSE_LIST:
			{
				Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMQueryResponseList");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
			}
			break;
		
            case Events.EVENT_DISPLAY_QUERY_LIST:
            {	
            	//Toast.makeText(VMApp.getInstance(), "query", Toast.LENGTH_LONG).show();
        
            	Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual","com.psl.virtual.ui.VMDisplayQueryList");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
            }
            	break;
             
             case Events.EVENT_VIEW_LOGS:
            	Intent oIntent =new Intent(Intent.ACTION_MAIN);
            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMLogs");
            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
            	htValues = (Hashtable<String, String>)msg.obj;
        		System.out.println("++++++++++++htValues: "+htValues);
   				oIntent.putExtra("info", htValues);
            	VMApp.getInstance().startActivity(oIntent);
		
            	break;
 			case Events.EVENT_RECHARGE:
			{
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	oIntent =new Intent(Intent.ACTION_MAIN);
                	Toast.makeText(VMApp.getInstance(), " Recharge Successful!!!!", Toast.LENGTH_SHORT).show();
                	if(VMLoginScreen.pos==0)
    				{
    					oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerHome");
    					oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				}
    				else
    				{
    	            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
    	            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				}
	                VMApp.getInstance().startActivity(oIntent);
                }
                else
                	Toast.makeText(VMApp.getInstance(), "Recharge FAILED ", Toast.LENGTH_SHORT).show();
			}break;
			
 			case Events.EVENT_LIQUIFY:
			{
				htValues = (Hashtable<String, String>)msg.obj;
                sResult = htValues.get(XMLPacker.TAG_RESULT);
                if(sResult.equalsIgnoreCase("TRUE"))
                {
                	oIntent =new Intent(Intent.ACTION_MAIN);
                	Toast.makeText(VMApp.getInstance(), "Request Successful. \nPlease Contact Your Bank for Further Assistance!!!!", Toast.LENGTH_SHORT).show();
                	if(VMLoginScreen.pos==0)
    				{
    					oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMCustomerHome");
    					oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				}
    				else
    				{
    	            	oIntent.setClassName("com.psl.virtual", "com.psl.virtual.ui.VMVendorHome");
    	            	oIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				}
	                VMApp.getInstance().startActivity(oIntent);
                }
                else
                	Toast.makeText(VMApp.getInstance(), "Request FAILED ", Toast.LENGTH_SHORT).show();
			}break;
			
		}//end of switch
			
				
	}//end of function	
	
	
	public void enter(Intent o)
	{
		VMProduct_Info o1=new VMProduct_Info();
		o1.option(o);
	}
	
		
	//Toast.makeText(VMApp.getInstance(), msg.obj.toString(), Toast.LENGTH_LONG).show();
}//end of class 

