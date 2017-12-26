package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.IAddressItem;
import com.dwkegu.spiderlib.BaseFrame.CrawlerBase;

import java.io.IOException;
import java.util.Map;

/**
 * Created by dwkeg on 2017/12/8.
 */

public class PageCrawler extends CrawlerBase {

	public PageCrawler() {
		super();
	}

	public PageCrawler(int threadCount) {
		super(threadCount);
	}

	@Override
	public void crawl(IAddressItem url, Map<String, String> args) {
		if(url!=null){
			try {
				PageResponse response = get(url.getUrl());
				newPage(url,response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
