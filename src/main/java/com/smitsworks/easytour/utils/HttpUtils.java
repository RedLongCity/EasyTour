package com.smitsworks.easytour.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smitsworks.easytour.SimpleRequestConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author redlongcity
 */

public class HttpUtils implements SimpleRequestConstants{
    
    
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    public static final int SOCKET_TIMEOUT = 60 * 1000;
    
    private static final ObjectMapper OM = new ObjectMapper();
    
    	private static final ResponseHandler<JsonNode> JSONNODE_CONTENT_HANDLER = (HttpResponse response) -> {
		StatusLine statusLine = response.getStatusLine();
		if(statusLine.getStatusCode() >= 300)
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());

		HttpEntity entity = response.getEntity();
		if(entity == null)
			throw new ClientProtocolException("Response contains no content");

		String content = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
		return OM.readTree(content);
	};


	public static String cleanURLFromParameters(String url) throws URISyntaxException{
		if(url != null)
			url = new URIBuilder(url)
				.clearParameters()
				.build()
				.toString();
		return url;
	}

	public static Content getRequest(String url) throws IOException{
		try{
			return Request.Get(url)
				.connectTimeout(CONNECT_TIMEOUT)
				.socketTimeout(SOCKET_TIMEOUT)
                                .addHeader(authorization,authorization_token)
                                .addHeader(acceptLanguage, response_language)
				.execute()
                                .returnContent();
		}
		catch(IOException e){
                    throw e;
		}
	}
        
        public static JsonNode getJsonNodeFromUrl(String url) throws IOException{
            try{
                return Request.Get(url)
                        .connectTimeout(CONNECT_TIMEOUT)
                        .socketTimeout(SOCKET_TIMEOUT)
                        .addHeader(authorization,authorization_token)
                        .addHeader(acceptLanguage, response_language)
                        .execute()
                        .handleResponse(JSONNODE_CONTENT_HANDLER);
            }catch(IOException e){
                throw e;
            }
        }

	public static Content postWithBodyAsRawRequestAsContent(String url, String body) throws IOException{
		try{
			return Request.Post(url)
				.connectTimeout(CONNECT_TIMEOUT)
				.socketTimeout(SOCKET_TIMEOUT)
                                .addHeader(authorization,authorization_token)
                                .addHeader(acceptLanguage,response_language)
				.bodyString(body, ContentType.APPLICATION_FORM_URLENCODED)
				.execute()
				.returnContent();
		}
		catch(IOException e){
			throw e;
		}
	}

	public static JsonNode postWithBodyAsJsonRequestAsJson(String url, String body) throws IOException{
		try{
			return Request.Post(url)
				.connectTimeout(CONNECT_TIMEOUT)
				.socketTimeout(SOCKET_TIMEOUT)
                                .addHeader(authorization,authorization_token)
                                .addHeader(acceptLanguage, response_language)
				.bodyString(body, ContentType.APPLICATION_JSON)
				.execute()
				.handleResponse(JSONNODE_CONTENT_HANDLER);
		}
		catch(IOException e){
			throw e;
		}
	}

}
