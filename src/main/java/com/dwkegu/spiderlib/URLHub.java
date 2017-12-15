package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.AddressBase;
import com.dwkegu.spiderlib.BaseFrame.AddressItem;

/**
 *
 * Created by dwkeg on 2017/12/8.
 */

public class URLHub<T extends AddressItem> extends AddressBase<T> {
    public URLHub(CrawlDirection crawlDirection) {
        super(crawlDirection);
    }

    @Override
    public T getItem() {
        return urlBase.poll();
    }


    @Override
    public void putItem(T url) {
        urlBase.add(url);
    }

    @Override
    public void putItem(T url, int level) {

    }

}
