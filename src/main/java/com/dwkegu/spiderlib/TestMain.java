package com.dwkegu.spiderlib;
import com.dwkegu.spiderlib.BaseFrame.AddressBase.CrawlDirection;

import com.dwkegu.spiderlib.BaseFrame.IAddressItem;
import com.dwkegu.spiderlib.BaseFrame.Schedule;
import com.dwkegu.spiderlib.job.JobSchedule;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


class TestMain{
    public static final String BASEURL = "http://music.163.com/#/discover/playlist/?cat=";
    public static void main(String[] args){
//        System.out.println(BASEURL);
//        URLHub<CommonAddressItem> urlHub = new URLHub<>(CrawlDirection.vertical);
//        PageCrawler crawler = new PageCrawler(4);
//        WYMusicParse parse = new WYMusicParse();
//        Schedule schedule = new Schedule(parse,urlHub, crawler);
//        schedule.setMaxPageCount(1);
//        addURLNYTimes(schedule);
//        schedule.start();
        NYTimes nyTimes = new NYTimes();
        nyTimes.start();
    }

    public static void addURLNYTimes(Schedule schedule){
        JobSchedule.getInstance().addJob(new Runnable() {
            @Override
            public void run() {
                String fileDir = "f:/tmp/nytimes/";
                File myFile = new File(fileDir);
                List<File> allFile = new ArrayList<>();
                Collections.addAll(allFile, Objects.requireNonNull(myFile.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().endsWith(".json");
                    }
                })));
            }
        });
    }
}
