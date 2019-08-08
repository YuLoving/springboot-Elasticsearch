package com.example.ccq.springelasticsearch.utils.HttpwithToken;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ClientDemo2 {
	private static final String ENCODETYPE = "UTF-8";
	private static final String REQUEST_HEADER_AUTHORIZATION_KEY = "Authorization";
	private static final String REQUEST_HEADER_AUTHORIZATION_VALUE = "Bearer ";
	private static final String REQUEST_HEADER_ACCEPT_KEY = "Accept";
	private static final String REQUEST_HEADER_ACCEPT_VALUE = "*/*";
	private static final String REQUEST_HEADER_CONNECTION_KEY = "Connection";
	private static final String REQUEST_HEADER_CONNECTION_VALUE = "keep-alive";
	private static final String REQUEST_HTTPS = "https";

	
	  public static void main(String[] s) { 
	 /* Map<String, String> bodyMap = new HashMap<String, String>();
	  String token= ""; //账号
	  String url =""; 
	  bodyMap.put("mobileNo", "13482972538");
	  bodyMap.put("date", "20181009"); 
	  String response = postToWSO2(url, token, bodyMap); 
	  System.out.println(response); */
		  
		  	String url="https://api.sreader.net:8443/data/cucc/mobileInTime7";
			String token="def5e5413298251330b6c0d0cc44d51bccd59d3a";
			String sendTelNo="13182888297";
			
			
			Map<String, String> map = new HashMap<>();
			map.put("sendTelNo", sendTelNo);
			
			  String response = postToWSO2(url, token, map); 
			  System.out.println(response);
	  }
	 
	 

	public static String getToWSO2(String url, String token, Map<String, String> params) {

		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ENCODETYPE);
		GetMethod getMethod = new GetMethod();
		if (url.startsWith(REQUEST_HTTPS)) {
			Protocol myhttps = new Protocol(REQUEST_HTTPS, new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol(REQUEST_HTTPS, myhttps);
		}
		try {

			getMethod.setRequestHeader(REQUEST_HEADER_AUTHORIZATION_KEY, REQUEST_HEADER_AUTHORIZATION_VALUE + token);
			getMethod.setRequestHeader(REQUEST_HEADER_ACCEPT_KEY, REQUEST_HEADER_ACCEPT_VALUE);
			getMethod.setRequestHeader(REQUEST_HEADER_CONNECTION_KEY, REQUEST_HEADER_CONNECTION_VALUE);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(0, false));
			Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
			StringBuffer logRequestParam = new StringBuffer();
			while (iterator.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) iterator.next();
				String parameterName = (String) entry.getKey();
				String parameterValue = (String) entry.getValue();
				logRequestParam.append(parameterName + "=" + parameterValue + "&");
			}
			logRequestParam.deleteCharAt(logRequestParam.length() - 1);
			getMethod.setURI(new org.apache.commons.httpclient.URI(url + "?" + logRequestParam, false));

			System.out.println("getToWSO2，请求参数：——》》" + logRequestParam);
			int statusCode = httpClient.executeMethod(getMethod);
			byte[] responseByte = getMethod.getResponseBody();
			String responseMsg = new String(responseByte, 0, responseByte.length, ENCODETYPE);
			System.out.println("getToWSO2，返回参数：——》》" + responseMsg);
			return responseMsg;
		} catch (URIException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (HttpException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String postToWSO2(String url, String token, Map<String, String> params) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ENCODETYPE);
		PostMethod postMethod = new PostMethod();
		if (url.startsWith(REQUEST_HTTPS)) {
			Protocol myhttps = new Protocol(REQUEST_HTTPS, new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol(REQUEST_HTTPS, myhttps);
		}
		try {

			postMethod.setURI(new URI(url, false));
			postMethod.setRequestHeader(REQUEST_HEADER_AUTHORIZATION_KEY, REQUEST_HEADER_AUTHORIZATION_VALUE + token);
			postMethod.setRequestHeader(REQUEST_HEADER_ACCEPT_KEY, REQUEST_HEADER_ACCEPT_VALUE);
			postMethod.setRequestHeader(REQUEST_HEADER_CONNECTION_KEY, REQUEST_HEADER_CONNECTION_VALUE);
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(0, false));
			Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
			StringBuffer logRequestParam = new StringBuffer();
			while (iterator.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) iterator.next();
				String parameterName = (String) entry.getKey();
				String parameterValue = (String) entry.getValue();
				postMethod.setParameter(parameterName, parameterValue);
				logRequestParam.append(parameterName + "=" + parameterValue + "&");
			}
			logRequestParam.deleteCharAt(logRequestParam.length() - 1);
			//System.out.println("postToWSO2，请求参数：——》》" + logRequestParam);
			int statusCode = httpClient.executeMethod(postMethod);
			//System.out.println("statusCode============" + statusCode);
			byte[] responseByte = postMethod.getResponseBody();
			String responseMsg = new String(responseByte, 0, responseByte.length, ENCODETYPE);
			//System.out.println("postToWSO2，返回参数：——》》" + responseMsg);
			return responseMsg;
		} catch (URIException e) {

			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		} catch (HttpException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

}
