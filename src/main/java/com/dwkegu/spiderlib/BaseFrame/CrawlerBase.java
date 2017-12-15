package com.dwkegu.spiderlib.BaseFrame;

import com.dwkegu.spiderlib.PageArriveListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * Created by dwkeg on 2017/12/8.
 */

public class CrawlerBase {
	private AddressBase hub;
	public List<PageArriveListener> listeners;
	private OkHttpClient client;
	boolean stop;

	public void stop(){
		stop = true;
	}

	public void setHub(AddressBase hub){
		this.hub = hub;
	}
	protected MediaType JSON
			= MediaType.parse("application/json; charset=utf-8");
	public CrawlerBase(){
		client = new OkHttpClient();
		stop = false;
	}
	public void crawl(AddressItem url, Map<String, String> args){
		try {
			PageResponse response = get(url.getUrl());
			newPage(url, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void startCrawl(){
		Thread crawlThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop){
					if(!hub.hasMore()){
						stop=true;
						break;
					}
					AddressItem url = hub.getItem();
					if(url!=null){
						crawl(url,null);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		crawlThread.start();
	}
	public void addOnPageArriveListener(PageArriveListener listener){
		if(listeners==null){
			listeners = new ArrayList<PageArriveListener>();
		}
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}

	protected PageResponse get(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			PageResponse result = new PageResponse();
			result.body= response.body().string();
			result.code = response.code();
			return result;
		} else {
			PageResponse result = new PageResponse();
			result.code = response.code();
			return result;
		}
	}

	protected void newPage(AddressItem url, PageResponse response){
		for(PageArriveListener listener:listeners){
			listener.onPageArrive(url,response.body, response.code);
		}
	}
	public class PageResponse{
		String body;
		int code;
	}

}
