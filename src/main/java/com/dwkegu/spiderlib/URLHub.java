package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.AddressBase;
import com.dwkegu.spiderlib.BaseFrame.IAddressItem;

/**
 *
 * Created by dwkeg on 2017/12/8.
 */

public class URLHub<T extends IAddressItem> extends AddressBase<T> {
    public URLHub(CrawlDirection crawlDirection) {
        super(crawlDirection);
    }

    @Override
    public T getItem() {
        if(Config.Debug){
            System.out.println("pop url from url");
        }
        return urlBase.poll();
    }


    @Override
    public void putItem(T url) {
        if(Config.Debug){
            System.out.println("add url to base");
        }
        urlBase.add(url);
    }

    @Override
    public void putItem(T url, int level) {

    }

}
