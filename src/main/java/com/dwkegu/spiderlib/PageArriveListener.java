package com.dwkegu.spiderlib;

import com.dwkegu.spiderlib.BaseFrame.AddressItem;

/**
 * Created by dwkeg on 2017/12/8.
 */

public interface PageArriveListener {
	public void onPageArrive(AddressItem item, String body, int responseCode);
}
