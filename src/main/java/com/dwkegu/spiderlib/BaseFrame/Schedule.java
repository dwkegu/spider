package com.dwkegu.spiderlib.BaseFrame;

import com.dwkegu.spiderlib.PageArriveListener;

/**
 * ??????
 * Created by dwkeg on 2017/12/8.
 */

public class Schedule {
	private int pageCount;
	private int maxIterNum;
	private int maxLevel;
	private int currentLevel;
	BaseParser parser;
	AddressBase address;
	CrawlerBase crawler;
	private PageArriveListener listener = new PageArriveListener() {
		@Override
		public void onPageArrive(AddressItem url, String body, int responseCode) {
			if(responseCode==200){
				parser.parse(body);
			}else{
				System.out.println(String.valueOf(responseCode)+ "page error");
			}
		}
	};
	public Schedule(BaseParser parser, AddressBase address, CrawlerBase crawler){
		pageCount = 0;
		maxIterNum = 1000000;
		maxLevel = 10;
		this.parser = parser;
		this.address = address;
		this.crawler =crawler;
		this.crawler.setHub(address);
		this.crawler.addOnPageArriveListener(listener);
	}

	public void start(){
		crawler.startCrawl();
	}
	public boolean hasFinish(){
		return pageCount > maxIterNum || currentLevel > maxLevel;
	}

	public void newURL(AddressItem url){
		address.putItem(url);
		pageCount++;
		if(hasFinish()){
			address.noMore();
		}
	}
}
