package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.IAddressItem;

public class CommonAddressItem implements IAddressItem {
    private String url;
    public static CommonAddressItem instance(String url){
        CommonAddressItem instance = new CommonAddressItem();
        instance.url = url;
        return instance;
    }
    @Override
    public String getUrl() {
        return url;
    }
}
