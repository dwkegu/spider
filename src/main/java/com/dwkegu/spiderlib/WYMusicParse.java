package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.BaseParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * Created by dwkeg on 2017/12/8.
 */

public class WYMusicParse extends BaseParser{
	@Override
	public void parse(String body) {
		System.out.println("page arrived");
		Document document = Jsoup.parse(body);
		for(Element elem:document.children()){
			if(elem.hasClass("w4")){
				System.out.println(elem.text());
			}
		}
		listener.newUrlArrive(null);
	}
}
