package com.psl.virtual.transport;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.psl.virtual.util.Constants;
import com.psl.virtual.util.Events;
import com.psl.virtual.util.SOAPConstants;
import com.psl.virtual.xml.XMLParser;


public class HttpUtil 
{
	private static HttpUtil mInstance;
	public static int isQueryResponse;
	private HttpUtil() 
	{
		
	}
	
	public static HttpUtil getInstance()
	{
		if(null == mInstance)
		{
			mInstance = new HttpUtil();
		}
		return mInstance;
	}
	
	public void sendRequest(SoapObject request, String sAction, int iEvent)
	{
		try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.SERVER_URL);
            androidHttpTransport.call(sAction, envelope);

            Object result = (Object)envelope.getResponse();
            System.out.println("+++++++++++++++++result: "+result+"    "+result.hashCode());
            
            switch(iEvent)
            {
            	case Events.EVENT_DISPLAY_PRODUCT_LIST:
            			XMLParser.getInstance().parseProductList(result.toString().trim());
            			break;
            	case Events.EVENT_DISPLAY_VENDOR_LIST:
                   		XMLParser.getInstance().parseVendorList(result.toString().trim());
                   		break;                   
            	case Events.EVENT_TRANSACTTION_DETAILS:
                    	XMLParser.getInstance().parseTransDetList(result.toString().trim());
                    	break;
            	case Events.EVENT_DISPLAY_VENDOR_TRANSACTION:
                    	XMLParser.getInstance().parseVendorTrans(result.toString().trim());
                    	break;
            	case Events.EVENT_DISPLAY_CUSTOMER_LIST:
                    	XMLParser.getInstance().parseCustomerList(result.toString().trim());
                    	break;
            	case Events.EVENT_DISPLAY_QUERY_LIST:
            			//iQueryEvent=0;
                        XMLParser.getInstance().parseQueryList(result.toString().trim());                        
                        break;
            	case Events.EVENT_DISPLAY_MEMBER_LIST:
                    	XMLParser.getInstance().parseMemberList(result.toString().trim());
                    	break;
            	case Events.EVENT_VIEW_LOGS:
                    	XMLParser.getInstance().parseViewLogs(result.toString().trim());
                    	break;
            	case Events.EVENT_DISPLAY_QUERY_RESPONSE_LIST:
            			isQueryResponse=1;
                		XMLParser.getInstance().parseQueryList(result.toString().trim());
                		break;
            	default:
            			XMLParser.getInstance().parse(result.toString().trim(), iEvent);
            			break;
            }
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
	}
}
