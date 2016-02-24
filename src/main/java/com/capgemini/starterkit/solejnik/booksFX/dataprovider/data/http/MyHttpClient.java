package com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class MyHttpClient {
	private final String httpGetURL = "http://localhost:9721/workshop/services/books/books-by-title?titlePrefix=";
	private final String httpPostURL = "http://localhost:9721/workshop/services/books/book/";

	public void delete(Long id) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete delete = new HttpDelete(httpPostURL + id);
		try {
			client.execute(delete);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String prefix) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(httpGetURL + prefix);
		HttpResponse response = null;
		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String responseJson = "";
		try {
			while ((responseJson = rd.readLine()) != null) {
				sb.append(responseJson);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void post(String bookJson) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(httpPostURL);
		post.setHeader("Content-Type", "Application/JSON");
		try {
			post.setEntity(new StringEntity(bookJson));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
