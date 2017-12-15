package com.dwkegu.spiderlib;
import com.dwkegu.spiderlib.BaseFrame.AddressBase.CrawlDirection;

import com.dwkegu.spiderlib.BaseFrame.Schedule;


class TestMain{
    public static final String BASEURL = "http://music.163.com/#/discover/playlist/?cat=";
    public static void main(String[] args){
//        System.out.println(BASEURL);
//        URLHub urlHub = new URLHub(CrawlDirection.vertical);
//        urlHub.putItem(BASEURL);
//        PageCrawler crawler = new PageCrawler();
//        WYMusicParse parse = new WYMusicParse();
//        Schedule schedule = new Schedule(parse,urlHub, crawler);
//        schedule.start();
        NYTimes nyTimes = new NYTimes();
        nyTimes.start();
    }
}
