package com.dwkegu.spiderlib.BaseFrame;

import java.util.List;

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
	AddressBase<IAddressItem> address;
	CrawlerBase crawler;
	private INewUrlArriveListener newUrlArriveListener = new INewUrlArriveListener() {
		@Override
		public void newUrlArrive(List<IAddressItem> item) {
			// TODO: 2017/12/15 处理新的URL
			newURL(item);
		}
	};
	private PageArriveListener listener = new PageArriveListener() {
		@Override
		public void onPageArrive(IAddressItem url, String body, int responseCode) {
			if(responseCode==200){
				parser.parse(body);
			}else{
				System.out.println(String.valueOf(responseCode)+ "page error");
			}
		}
	};
	public Schedule(BaseParser parser, AddressBase address, CrawlerBase crawler){
		pageCount = 0;
		maxIterNum = 100;
		maxLevel = 10;
		this.parser = parser;
		this.address = address;
		this.crawler =crawler;
		this.crawler.setHub(address);
		this.crawler.addOnPageArriveListener(listener);
		this.parser.setListener(newUrlArriveListener);
	}

	public void setMaxPageCount(int maxIterNum){
	    this.maxIterNum = maxIterNum;
	    if(hasFinish()){
	        address.noMore();
        }
    }

    public void setMaxLevel(int maxLevel){
	    this.maxLevel = maxLevel;
    }

	public void start(){
		crawler.startCrawl();
	}
	public boolean hasFinish(){
		return pageCount >= maxIterNum || currentLevel >= maxLevel;
	}

	public void newURL(List<IAddressItem> url){
		if(url==null){
			return;
		}
		for(IAddressItem item:url){
			address.putItem(item);
			pageCount++;
			if(hasFinish()){
				address.noMore();
			}
		}
	}
    public void newURL(IAddressItem url){
        if(url==null){
            return;
        }
        address.putItem(url);
        pageCount++;
        if(hasFinish()){
            address.noMore();
        }
    }
}
