package com.psl.virtual.util;

import android.content.Context;
import android.widget.Toast;

public class Utils 
{
	
	public static void showToast(Context oContext, String sMsg)
	{
		Toast.makeText(oContext, sMsg, Toast.LENGTH_SHORT).show();
	}
	
	public static class Product
	{
		public String mSProductId;
		public String mSName;
		public String mSPrice;
		
		public Product(String sProductId, String sName, String sPrice) 
		{
			// TODO Auto-generated constructor stub
			mSProductId = sProductId;
			mSName = sName;
			mSPrice = sPrice;
		}		
		@Override
		public String toString()
		{
			// TODO Auto-generated method stub
			return ("Product: "+ mSProductId+"|"+mSName+"|"+mSPrice);
		}		
		 public void setPID(String name) 
		 {
			  this.mSProductId = name;
		 }
		 public String getPID() 
		 {
			 return mSProductId;
		 }
		public void setPName(String cityState) 
		{
		  this.mSName = cityState;
		}
		public String getPName() 
		{
		  return mSName;
		}
		public void setPrice(String phone)
		{
		  this.mSPrice = phone;
		}
		public String getPrice() 
		{
			return mSPrice;
		}
	}//End of Product Class
	
	public static class Vendor
	{
		public String mSVendorId;
		public String mSName;
		public Vendor(String Id,String Name)
		{
			mSVendorId=Id;
			mSName=Name;
		}
		@Override
		public String toString()
		{
			return ("Vendor: "+mSVendorId+"|"+mSName);
		}
		public void setVendorId(String Id)
		{
			this.mSVendorId=Id;
		}
		public String getVendorId()
		{
			return this.mSVendorId;
		}
		public void setName(String Name)
		{
			this.mSName=Name;
		}
		public String getName()
		{
			return this.mSName;
		}
		
	}//End of Vendor Class
	public static class TransDet
	{
		public String mSSenderId;
		public String mSReceiver;
		public String mSDate;
		public String mType;
		public String mAmt;
		
		public TransDet(String sSenderAccNo, String sReceiAccNo, String sDate_Time, String sType, String sAmt) 
		{
			// TODO Auto-generated constructor stub
			mSSenderId = sSenderAccNo;
			mSReceiver = sReceiAccNo;
			mSDate = sDate_Time;
			mType = sType;
			mAmt = sAmt;
		}	
		@Override
		public String toString()
		{
			// TODO Auto-generated method stub
			return ("TransDet: "+ mSSenderId+"|"+mSReceiver+"|"+mSDate+"|"+mType+"|"+mAmt);
		}		
		 public void setSenderId(String name) 
		 {
			  this.mSSenderId = name;
		 }
		 public String getSenderId() 
		 {
			 return mSSenderId;
		 }
		public void setReceiver(String Receiver) 
		{
		  this.mSReceiver = Receiver;
		}
		public String getReceiver() 
		{
		  return mSReceiver;
		}
		public void setDate(String Date)
		{
		  this.mSDate = Date;
		}
		public String getDate() 
		{
			return mSDate;
		}
		
		public void setAmt(String Amt)
		{
		  this.mAmt =Amt;
		}
		public String getAmt() 
		{
			return mAmt;
		}
		
		public void setType(String Type)
		{
		  this.mType =Type;
		}
		public String getType() 
		{
			return mType;
		}
	}//End of Trans Class

	
	public static class VendorTrans
	{
		public String mSSendAcc;
		public String mSDate_Time;
		public String mSRecAcc;
		
		public VendorTrans (String sSendAcc, String sDate_Time, String sRecAcc) {
			// TODO Auto-generated constructor stub
			mSSendAcc = sSendAcc;
			mSDate_Time = sDate_Time;
			mSRecAcc = sRecAcc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ("Product: "+ mSSendAcc+"|"+mSDate_Time+"|"+mSRecAcc);
		}
		
		 public void setSendAcc(String acc) {
			  this.mSSendAcc = acc;
			 }

			 public String getSendAcc() {
			  return mSSendAcc;
			 }

			 public void setDate_Time(String dt) {
			  this.mSDate_Time = dt;
			 }

			 public String getDate_Time() {
			  return mSDate_Time;
			 }

			 public void setRecAcc(String acc) {
			  this.mSRecAcc = acc;
			 }

			 public String getRecAcc() {
			  return mSRecAcc;
			 }
	}
	public static class Customer
	{
		public String mSName;
		public String mSAddr;
		
		public Customer(String sName, String sAddr) {
			// TODO Auto-generated constructor stub
			mSName = sName;
			mSAddr = sAddr;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ("Customer: " + mSName + "|" + mSAddr);
		}
		
		 
			 public void setPName(String cityState) {
			  this.mSName = cityState;
			 }

			 public String getPName() {
			  return mSName;
			 }

			 public void setAddress(String addr) {
			  this.mSAddr = addr;
			 }

			 public String getAddress() {
			  return mSAddr;
			 }
	}
	public static class ViewLogs
	{
		public String mSenderAccNo;
		public String mReceiAccNo;
		public String mDate_Time;
		
		public ViewLogs(String sSenderAccNo, String sReceiAccNo, String sDate_Time) 
		{
			// TODO Auto-generated constructor stub
			mSenderAccNo = sSenderAccNo;
			mReceiAccNo = sReceiAccNo;
			mDate_Time = sDate_Time;
		}		
		@Override
		public String toString()
		{
			// TODO Auto-generated method stub
			return ("Product: "+ mSenderAccNo+"|"+mReceiAccNo+"|"+mDate_Time);
		}		
		 public void setSenderAccNo(String name) 
		 {
			  this.mSenderAccNo = name;
		 }
		 public String getSenderAccNo() 
		 {
			 return mSenderAccNo;
		 }
		public void setReceiAccNo(String cityState) 
		{
		  this.mReceiAccNo = cityState;
		}
		public String getReceiAccNo() 
		{
		  return mReceiAccNo;
		}
		public void setDate_Time(String Date_Time)
		{
		  this.mDate_Time =Date_Time;
		}
		public String getDate_Time() 
		{
			return mDate_Time;
		}
	}//End of viewlogs Class
	public static class Member
	{
		public String mSMemberId;
		public String mSName;
		public Member(String Id,String Name)
		{
			mSMemberId=Id;
			mSName=Name;
		}
		@Override
		public String toString()
		{
			return ("Vendor: "+mSMemberId+"|"+mSName);
		}
		public void setMemberId(String Id)
		{
			this.mSMemberId=Id;
		}
		public String getMemberId()
		{
			return this.mSMemberId;
		}
		public void setName(String Name)
		{
			this.mSName=Name;
		}
		public String getName()
		{
			return this.mSName;
		}
	}//End of  Member Class
	public static class Query
	{
		public String mSendorAccNo;
		public String mSubject;
		public String mAskedQuery;
		public String mDate_Time;
		public String mResponse;
		public String mAnsDate;
		
		public Query(String SendorAccNo,String sSub,String AskedQuery,String Date_Time,String sRespons,String sAnsDt)
		{
			mSendorAccNo=SendorAccNo;
			mSubject=sSub;
			mAskedQuery=AskedQuery;
			mDate_Time=Date_Time;
			mResponse=sRespons;
			mAnsDate=sAnsDt;
		}
		@Override
		public String toString()
		{
			return ("Vendor: "+mSendorAccNo+"|"+mSubject+"|"+mAskedQuery+"|"+mDate_Time+"|"+mResponse+"|"+mAnsDate);
		}
		public void setSendorAccNo(String Id)
		{
			this.mSendorAccNo=Id;
		}
		public String getSendorAccNo()
		{
			return this.mSendorAccNo;
		}
		public void setSubject(String sub)
		{
			this.mSendorAccNo=sub;
		}
		public String getSubject()
		{
			return this.mSubject;
		}
		public void setmAskedQuery(String Name)
		{
			this.mAskedQuery=Name;
		}
		public String getmAskedQuery()
		{
			return this.mAskedQuery;
		}
		
		public String getDate_Time()
		{
			return this.mDate_Time;
		}
		
		public void setDate_Time(String dt)
		{
	         this.mDate_Time = dt;
		}
		public String getResponse()
		{
			return this.mResponse;
		}
		
		public void setResponse(String res)
		{
	         this.mResponse = res;
		}
		public String getAnsDate()
		{
			return this.mAnsDate;
		}
		
		public void setAnsDate(String date)
		{
	         this.mAnsDate = date;
		}
		
		
	}//End of Query Class
	}
