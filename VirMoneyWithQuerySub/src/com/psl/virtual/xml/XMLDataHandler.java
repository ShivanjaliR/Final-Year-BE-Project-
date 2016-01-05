package com.psl.virtual.xml;

import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLDataHandler extends DefaultHandler 
{
	private static XMLDataHandler oInstance;
	private Hashtable<String, String> oHTValues = new Hashtable<String, String>();
	private String sCurrentTag;
	
	public static XMLDataHandler getInstance()
	{
		if(null == oInstance)
		{
			oInstance = new XMLDataHandler();
		}
		return oInstance;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		sCurrentTag = qName;
		
		System.out.println("+++++startElement: localName: "+localName+"		qName: "+qName);
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		//super.startDocument();
		System.out.println("++++++++++startDocument:XMLDataHandler");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		sCurrentTag = null;
		
		System.out.println("+++++endElement: localName: "+localName+"		qName: "+qName);
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		//super.endDocument();
		System.out.println("++++++++++endDocument:XMLDataHandler");
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
//		super.characters(ch, start, length);
		oHTValues.put(sCurrentTag, new String(ch, start, length));
		System.out.println("+++++characters: localName: "+new String(ch, start, length));
	}
	
	public void clear()
	{
		oHTValues.clear();
	}
	
	public Hashtable<String, String> getData()
	{
		return oHTValues;
	}
}
