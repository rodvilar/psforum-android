package com.alt32.psforum.clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;

public class DataParser {
	
	private static String path = "http://php5.barratres.com.uy/ps/";
	
	public static Object parse(String url, Type tipo) {
	
		InputStream source = retrieveStream(path + url);
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(source);
		return gson.fromJson(reader, tipo);
	
	}

	private static InputStream retrieveStream(String url) {
	    
	    DefaultHttpClient client = new DefaultHttpClient(); 
	    
	    HttpGet getRequest = new HttpGet(url);
	    Log.d("PS", url);
	    try {
	       
	       HttpResponse getResponse = client.execute(getRequest);
	       final int statusCode = getResponse.getStatusLine().getStatusCode();
	       
	       if (statusCode != HttpStatus.SC_OK) { 
	          return null;
	       }
	
	       HttpEntity getResponseEntity = getResponse.getEntity();
	       
	       return getResponseEntity.getContent();
	      
	    } 
	    catch (IOException e) {
	       getRequest.abort();
	    }
	    
	    return null;
    
 	}
	
}