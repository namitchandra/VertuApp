package com.example.tabdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.StaticLayout;
import android.util.Log;

interface JsonRequestResponse{
	
	void failedToGetJsonResponse(String key,String message);
	void successJsonResponse(String key,String response);
	
}


public class parserProxy {

	static InputStream is;
	JsonRequestResponse post;
	String getUrl;
	String	json ;

	public void setOnGetResponselistener(JsonRequestResponse jsonRequestResponse){
		this.post = jsonRequestResponse;
	}
	
	public void parse(String url,String  key ){
		  this.getUrl = url;
		
		Log.d("Post result", "------------"+"hhhhhhhhhhhhhhhhhhhhhhhh"+json+"------------------------------------------");

		  JSONParser jsonParser=new JSONParser();
		  String jsonString   = 	jsonParser.getJSONFromUrl(getUrl);
		  
		  try{
			  	Log.d("Token : ", jsonString);
		  
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		  	int n = jsonString.length();
			if(n>0)
			this.post.successJsonResponse(key,jsonString);
			else
			this.post.successJsonResponse(key,"Empty Value");	

			
			Log.d("Post result", "------------"+"hhhhhhhhhhhhhhhhhhhhhhhh"+jsonString+"------------------------------------------");

		
		
	}
	
	
	

}


