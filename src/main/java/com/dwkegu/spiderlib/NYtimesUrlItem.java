package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.AddressItem;

public class NYtimesUrlItem implements AddressItem {
    public String category;
    public String fileName;
    public String url;
    @Override
    public String getUrl() {
        return url;
    }
}
