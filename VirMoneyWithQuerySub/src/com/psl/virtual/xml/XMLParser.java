package com.psl.virtual.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.os.Message;

import com.psl.virtual.VMApp;
import com.psl.virtual.transport.HttpUtil;
import com.psl.virtual.util.Events;

public class XMLParser 
{
	public static XMLParser mInstance;
	
	public static XMLParser getInstance()
	{
		if(null == mInstance)
		{
			mInstance = new XMLParser();
		}
		return mInstance;
	}
	
	public void parse(String sResponse, int iEvent)
	{
		Hashtable<String, String> oValue = new Hashtable<String, String>();
		
		int nStartIndex = sResponse.indexOf("{");
		
		if(-1 != nStartIndex)
		{
			sResponse = sResponse.substring(nStartIndex+1, sResponse.length());
		}
		
		int nEndIndex = sResponse.indexOf("}");
		
		if(-1 != nEndIndex) 
		{
			sResponse = sResponse.substring(0, nEndIndex-1);
		}
		
		System.out.println("++++++++++sResponse: "+sResponse);
		
		String[] sKeyValues = sResponse.split(";");
		if(null != sKeyValues)
		{
			if(sKeyValues.length == 1)
			{
				System.out.println("++++++++++++sKeyValues[0]: "+sKeyValues[0]);
				oValue.put(XMLPacker.TAG_RESULT, sKeyValues[0]);
			}
			else
			{
				for(int i=0; i<sKeyValues.length; i++) 
				{
					String[] sTagValue = sKeyValues[i].trim().split("=");
					System.out.println("++++++++++++sTagValue[0]: "+sTagValue[0]);
					System.out.println("++++++++++++sTagValue[1]: "+sTagValue[1]);
					
					oValue.put(sTagValue[0], sTagValue[1]);
				}
			}
		}
		
		Message oMsg = Message.obtain();
		oMsg.what = iEvent;
		oMsg.obj = oValue;
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	
	public void parseProductList(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_DISPLAY_PRODUCT_LIST;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	public void parseVendorList(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_DISPLAY_VENDOR_LIST;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	
	public void parseTransDetList(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_TRANSACTTION_DETAILS;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	public void parseVendorTrans(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_DISPLAY_VENDOR_TRANSACTION;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	
	public void parseCustomerList(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_DISPLAY_CUSTOMER_LIST;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	public void parseQueryList(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		if(HttpUtil.isQueryResponse==0)
			oMsg.what = Events.EVENT_DISPLAY_QUERY_LIST;
		else
		{			oMsg.what = Events.EVENT_DISPLAY_QUERY_RESPONSE_LIST;
					HttpUtil.isQueryResponse=0;
		}
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	
	public void parseMemberList(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_DISPLAY_MEMBER_LIST;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}
	public void parseViewLogs(String sResponse) throws ParserConfigurationException, SAXException, IOException
	{
		sResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+sResponse;
		SAXParser oParser = SAXParserFactory.newInstance().newSAXParser();
		
		ByteArrayInputStream oIs = new ByteArrayInputStream(sResponse.getBytes());
		XMLDataHandler oHandler = XMLDataHandler.getInstance();
		oParser.parse(oIs, oHandler);
		
		Message oMsg = Message.obtain();
		oMsg.what = Events.EVENT_VIEW_LOGS;
		oMsg.obj = oHandler.getData();
		
		VMApp.getInstance().getResponseHandler().sendMessage(oMsg);
	}

}
