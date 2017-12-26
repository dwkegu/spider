package com.dwkegu.spiderlib.BaseFrame;

import com.dwkegu.spiderlib.BaseFrame.IAddressItem;

/**
 * Created by dwkeg on 2017/12/8.
 */

public interface PageArriveListener {
	public void onPageArrive(IAddressItem item, String body, int responseCode);
}
