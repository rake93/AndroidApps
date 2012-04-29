package com.demo.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Basic Application to show how to retrieve XML and parse it
 * 
 * @author Nikhil Lingutla
 */

public class XMLDemoActivity extends Activity {
	
	Button getRawContent,getParsedContent;
	EditText xmlurl;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.main);
        
        xmlurl = (EditText)this.findViewById(R.id.inputUrl);
        
        // Getting raw XML content and Displaying
        getRawContent = (Button)this.findViewById(R.id.getRawContent);
        getRawContent.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
		        String results = getRawResults(xmlurl.getText().toString());
		        Intent rawResultsScreen = new Intent(getApplicationContext(),RawResultsActivity.class);
		        rawResultsScreen.putExtra("rawresults", results);
		        startActivity(rawResultsScreen);
        	}
        });
        // Getting Parsed XML content and displaying
        getParsedContent = (Button)this.findViewById(R.id.getParsedContent);
        getParsedContent.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		String results = getParsedResults(xmlurl.getText().toString());
		        Intent parsedResultsScreen = new Intent(getApplicationContext(),ParsedResultsActivity.class);
		        parsedResultsScreen.putExtra("parsedresults", results);
		        startActivity(parsedResultsScreen);
        	}
        });
    }
    /*
     * For getting the raw XML response from the URL 
     */
    private String getRawResults(String url)
    {
    	String response = null;
    	
    	try
    	{
    		DefaultHttpClient httpClient = new DefaultHttpClient();
    		// url should be http://chukk.nuzoka.com/name.xml
	    	HttpGet httpGet = new HttpGet(url);
    		HttpResponse httpResponse = httpClient.execute(httpGet);
	    	HttpEntity httpEntity = httpResponse.getEntity();
	    	response = EntityUtils.toString(httpEntity);
	    	return response;
    	}
    	catch (UnsupportedEncodingException e)
    	{
    		response = "Error";
    	}
    	catch (MalformedURLException e) {
    		response = "Error";
    	}
    	catch (IOException e) {
    		response = "Error";
        }
    	return response;
    }
    
    /*
     * For parsing the received XML content
     */
    private String getParsedResults(String url)
    {
    	String response = null;
    	try
    	{
    		DefaultHttpClient httpClient = new DefaultHttpClient();
    		// url should be http://chukk.nuzoka.com/name.xml
	    	HttpGet httpGet = new HttpGet(url);
    		HttpResponse httpResponse = httpClient.execute(httpGet);
	    	HttpEntity httpEntity = httpResponse.getEntity();
	    	response = EntityUtils.toString(httpEntity);
	    	
	    	ArrayList<String> resArray = getArrayResults(response);
	    	String formattedString = "";
	    	for (String str : resArray)
	    	{
	    		int index = resArray.indexOf(str);
	    		if (index % 2 == 0)
	    			formattedString = formattedString + " " + str + " means";
	    		else if (index % 2 == 1)
	    			formattedString = formattedString + " " + str + " and";
	    	}
	    	return formattedString;
    	}
    	catch (UnsupportedEncodingException e)
    	{
    		response = "Error";
    	}
    	catch (MalformedURLException e)
    	{
    		response = "Error";
    	}
    	catch (IOException e)
    	{
    		response = "Error";
        }
    	return response;
    }
    
    private ArrayList<String> getArrayResults(String response)
    {
    	ArrayList<String> results = new ArrayList<String>();
    	String name,meaning;
    	
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(new InputSource(new ByteArrayInputStream(response.getBytes("utf-8"))));
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("iname");
            
            for (int i=0;i<5;i++){
                Node item = items.item(i);
            
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                	Element eElement = (Element) item;
                	name = getAtributeValue("name", eElement);
                	meaning = getAtributeValue("meaning", eElement);
                	results.add(name);
                	results.add(meaning);
                }
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);
        } 
        return results;
    }
    // For getting the content of required tag in an Element
	   private static String getAtributeValue(String attrName, Element eElement)
	    {
		   String value = eElement.getAttribute(attrName);
		   return value;
	    }
    
}