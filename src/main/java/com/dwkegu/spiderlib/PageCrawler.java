package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.AddressItem;
import com.dwkegu.spiderlib.BaseFrame.CrawlerBase;

import java.io.IOException;
import java.util.Map;

/**
 * Created by dwkeg on 2017/12/8.
 */

public class PageCrawler extends CrawlerBase {
	@Override
	public void crawl(AddressItem url, Map<String, String> args) {
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
