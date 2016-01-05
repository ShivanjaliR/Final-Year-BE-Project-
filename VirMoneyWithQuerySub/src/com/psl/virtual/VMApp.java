package com.psl.virtual;

import com.psl.virtual.ui.VMProduct_Info;
import com.psl.virtual.ui.VMResponseHandler;

import android.app.Application;
import android.content.Intent;

public class VMApp extends Application 
{
	public static VMApp mInstance;
	
	public VMResponseHandler mResponseHandler;
	
	public VMResponseHandler getResponseHandler() 
	{
		return mResponseHandler;
	}

	public VMApp() 
	{
		mInstance =  this;
		mResponseHandler = new VMResponseHandler();
	}
	
	
	
	public static VMApp getInstance()
	{
		return mInstance;	
	}
}
