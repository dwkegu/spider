package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.IAddressItem;

public class NYtimesUrlItemI implements IAddressItem {
    public String category;
    public String fileName;
    public String url;
    @Override
    public String getUrl() {
        return url;
    }
}
