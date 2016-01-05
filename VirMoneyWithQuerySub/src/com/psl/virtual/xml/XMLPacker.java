package com.psl.virtual.xml;

import org.ksoap2.serialization.SoapObject;

import com.psl.virtual.util.SOAPConstants;

public class XMLPacker 
{ 
	private static XMLPacker mInstance;
	
	private final static String TAG_ACCOUNT_NO = "sAccNo";
	private final static String TAG_PASSWORD = "sPassword";
	private final static String TAG_NAME = "sName";
	private final static String TAG_GENDER = "sGender";
	private final static String TAG_ADDRESS = "sAddress";
	private final static String TAG_EMAIL = "sEmail";
	private final static String TAG_BANKNAME = "sBankName";
	private final static String TAG_USER_TYPE = "sUserType";
	private final static String TAG_CONTACT_NO = "sContactNo";
	private final static String TAG_BANK_ACCOUNT_NO = "sBankAccNo";
	private final static String TAG_DOB = "sDOB";
	
	public final static String TAG_DATE_TIME = "Date_Time";
	public final static String TAG_RESULT = "Value";

	private final static String TAG_PRODUCT_ID = "sProductId";
	private final static String TAG_PRODUCT_NAME = "sProductName";
	private final static String TAG_QUANTITY = "iQuantity";
	private final static String TAG_PRICE = "dPrice";
	private final static String TAG_CATEGORY = "sCategory";
	private final static String TAG_MANUFACTURER = "sManufacturer";
	private final static String TAG_DISCOUNT = "dDiscount";
	private final static String TAG_NOTES = "sNotes";
	private final static String TAG_SHIPPING_COST = "dShippingCost";
	private final static String TAG_PROCESSING_TIME = "sProcessingTime";
	private final static String TAG_VENDOR_ACC_NO = "sVendorAccNo";
	
	
	private final static String TAG_SUSERNAME = "sUserName";
	private final static String TAG_SPASSWORD = "sPassword";
	private final static String TAG_SUSERTYPE = "sUserType";
		
	public final static String TAG_SENDER = "sSenderAccNo";
	public final static String TAG_RECEIVER = "sRecAccNo";
	public final static String TAG_MPOINTS = "dMoneyPoints";
	
	private final static String TAG_QUERY = "sQuery";
	private final static String TAG_QUERY_SUBJECT = "sQuerySubject";
	private final static String TAG_REPLY = "sReply";
	private final static String TAG_SUBJECT = "sSubject";
	
	public final static String TAG_NEWPASS = "NewPassword";
	public final static String TAG_OLDPASS = "OldPassword";
	
	public final static String TAG_CUSTOMER = "sCustAccNo";
		
	public static XMLPacker getInstance()
	{
		if(null == mInstance)
		{
			mInstance = new XMLPacker();
		}
		return mInstance;
	}		
	public SoapObject getRegistrationSoRequest( String sAccNo, String sPassword, String sName, String sGender, String sAddr, String sEmailId, String sBank, String sType, String sContact, String sBankAcc, String sDOB)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DO_REGISTRATION);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);
		request.addProperty(TAG_PASSWORD, sPassword);
		request.addProperty(TAG_NAME, sName);
		request.addProperty(TAG_GENDER, sGender);
		request.addProperty(TAG_ADDRESS, sAddr);
		request.addProperty(TAG_EMAIL, sEmailId);
		request.addProperty(TAG_BANKNAME, sBank);
		request.addProperty(TAG_USER_TYPE, sType);
		request.addProperty(TAG_CONTACT_NO, sContact);
		request.addProperty(TAG_BANK_ACCOUNT_NO, sBankAcc);
		request.addProperty(TAG_DOB, sDOB);	
	
		return request;
	}
	public SoapObject getUpdateProfileRequest( String sAccNo, String sName, String sGender, String sAddr, String sEmailId, String sBank,  String sContact, String sBankAcc, String sDOB)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DO_REGISTRATION);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);		
		request.addProperty(TAG_NAME, sName);
		request.addProperty(TAG_GENDER, sGender);
		request.addProperty(TAG_ADDRESS, sAddr);
		request.addProperty(TAG_EMAIL, sEmailId);
		request.addProperty(TAG_BANKNAME, sBank);		
		request.addProperty(TAG_CONTACT_NO, sContact);
		request.addProperty(TAG_BANK_ACCOUNT_NO, sBankAcc);
		request.addProperty(TAG_DOB, sDOB);
		return request;
	}	
	public SoapObject getLoginRequest(String sUserId,String sPassword, String sUserType)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DO_LOGIN);
		request.addProperty(TAG_SUSERNAME, sUserId);
		request.addProperty(TAG_SPASSWORD, sPassword);
		request.addProperty(TAG_SUSERTYPE, sUserType);
		return request;
	}	
	public SoapObject getDisplayProductListRequest(String sVendorAcc)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_PRODUCT_LIST);
		request.addProperty(TAG_VENDOR_ACC_NO, sVendorAcc);
		return request;
	}
	public SoapObject getDisplayVendorListRequest()
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_VENDOR_LIST);
		return request;
	}	
	public SoapObject getDisplayMemberListRequest()
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_MEMBER_TRANSACTION);
		return request;
	}	
	public SoapObject getTransactionDetailsRequest(String sUserId)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_TRANSACTION_DETAILS );
		request.addProperty(TAG_SENDER, sUserId);
		return request;
	}
	public SoapObject getAddProductRequest(String sProductId, String sPrdName,String quantity, String price, String sCategory,
			String sManufacturer, String discount, String sNotes,String shippingCost, String sProcessingTime, String sVendorAccNo) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_ADD_PRODUCT_DETAILS);
		request.addProperty(TAG_PRODUCT_ID, sProductId);
		request.addProperty(TAG_PRODUCT_NAME, sPrdName);
		request.addProperty(TAG_QUANTITY, quantity);
		request.addProperty(TAG_PRICE, price);
		request.addProperty(TAG_CATEGORY, sCategory);
		request.addProperty(TAG_MANUFACTURER, sManufacturer);
		request.addProperty(TAG_DISCOUNT, discount);
		request.addProperty(TAG_NOTES, sNotes);
		request.addProperty(TAG_SHIPPING_COST, shippingCost);
		request.addProperty(TAG_PROCESSING_TIME, sProcessingTime);
		request.addProperty(TAG_VENDOR_ACC_NO, sVendorAccNo);
		
		return request;
	}	
	public SoapObject getUpdateProductRequest(String sProductId, String sPrdName,String quantity, String price, String sCategory,
			String sManufacturer, String discount, String sNotes,String shippingCost, String sProcessingTime, String sVendorAccNo) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_UPDATE_PRODUCT_DETAILS);
		request.addProperty(TAG_PRODUCT_ID, sProductId);
		request.addProperty(TAG_PRODUCT_NAME, sPrdName);
		request.addProperty(TAG_QUANTITY, quantity);
		request.addProperty(TAG_PRICE, price);
		request.addProperty(TAG_CATEGORY, sCategory);
		request.addProperty(TAG_MANUFACTURER, sManufacturer);
		request.addProperty(TAG_DISCOUNT, discount);
		request.addProperty(TAG_NOTES, sNotes);
		request.addProperty(TAG_SHIPPING_COST, shippingCost);
		request.addProperty(TAG_PROCESSING_TIME, sProcessingTime);
		request.addProperty(TAG_VENDOR_ACC_NO, sVendorAccNo);
		
		return request;
	}
	public SoapObject getDeleteAccountRequest(String sAccNo)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DELETE_ACCOUNT);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);		
		return request;
	}
	
	public SoapObject getProductDetailsSoRequest(String sVendorAccNo,String sProductId,String sAccNo) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_PRODUCT_DETAILS);
		request.addProperty(TAG_PRODUCT_ID, sProductId);
		request.addProperty(TAG_VENDOR_ACC_NO, sVendorAccNo);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);		
		return request;
	}
	public SoapObject getSendQueryRequest(String sSenderAccNo,String sQuery, String sSubject) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_SEND_QUERY);
		request.addProperty(TAG_SENDER, sSenderAccNo);
		request.addProperty(TAG_QUERY , sQuery);
		request.addProperty(TAG_QUERY_SUBJECT , sSubject);
		return request;
	}	
	public SoapObject getDisplayQueryResponseListRequest(String sAccNo) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_QUERY_RESPONSE_LIST);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);				
		return request;
	}	
	
	public SoapObject getBuyRequest(String sVendor, String sProdId, String sCustomer, String sQuantity)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_BUY_PRODUCT);
		request.addProperty(TAG_VENDOR_ACC_NO, sVendor);
		request.addProperty(TAG_PRODUCT_ID, sProdId);
		request.addProperty(TAG_CUSTOMER, sCustomer);
		request.addProperty(TAG_QUANTITY, sQuantity);
		return request;
	}	
	public SoapObject getShareMoneyRequest(String Sender, String Receiver, String sMPoints)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_SHARE_MONEYPOINTS);
		request.addProperty(TAG_SENDER, Sender);
		request.addProperty(TAG_RECEIVER, Receiver);
		request.addProperty(TAG_MPOINTS, sMPoints);
		return request;
	}
	public SoapObject getViewLogsRequest()
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_VIEW_LOGS);
		return request;
	}
	public SoapObject getReplyQueryRequest(String sSenderAccNo,String sReply,String sub)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_REPLY_TO_QUERY);
		request.addProperty(TAG_SENDER, sSenderAccNo);
		request.addProperty(TAG_REPLY, sReply);
		request.addProperty(TAG_SUBJECT, sub);
		return request;
	}	
	public SoapObject getDeleteProductRequest(String sVendorId,String sProductId)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DELETE_PRODUCT);
		request.addProperty(TAG_VENDOR_ACC_NO, sVendorId);
		request.addProperty(TAG_PRODUCT_ID, sProductId);
		return request;
	}
	public SoapObject getChangePasswordRequest(String sAccNo,String sOldPass,String sNewPass) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_CHANGE_PASSWORD);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);
		request.addProperty(TAG_OLDPASS , sOldPass);
		request.addProperty(TAG_NEWPASS, sNewPass);
		
		return request;
	}
	public SoapObject getProfileDetailsRequest(String sAccNo,String sType) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_PROFILE);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);
		request.addProperty(TAG_SUSERTYPE, sType);
		
		return request;
	}
	public SoapObject getDisplayCustomerListRequest() 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_CUSTOMER_LIST);
		return request;
	}
	
	public SoapObject getDisplayTransactionRequest(String sAccNo) 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLAY_VENDOR_TRANSACTION);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);
		return request;
	}
	public SoapObject getDisplayQueryListRequest() 
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_DISPLY_QUERY_LIST);
		return request;
	}
	
	public SoapObject getRechargeRequest(String sAccNo,String MoneyPoints)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_RECHARGE);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);		
		request.addProperty(TAG_MPOINTS, MoneyPoints);
		return request;
	}
	
	public SoapObject getLiquifyRequest(String sAccNo,String MoneyPoints)
	{
		SoapObject request = new SoapObject(SOAPConstants.NAMESPACE, SOAPConstants.METHOD_LIQUIFY);
		request.addProperty(TAG_ACCOUNT_NO, sAccNo);		
		request.addProperty(TAG_MPOINTS, MoneyPoints);
		return request;
	}

}
