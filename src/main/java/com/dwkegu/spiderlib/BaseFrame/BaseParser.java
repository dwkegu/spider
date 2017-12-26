package com.dwkegu.spiderlib.BaseFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础解析
 * Created by dwkeg on 2017/12/8.
 */

public abstract class BaseParser {

    protected List<IAddressItem> newUrl;
	protected INewUrlArriveListener listener;

	public BaseParser(){
	    newUrl = new ArrayList<>();
    }

	public void setListener(INewUrlArriveListener listener){
	    this.listener = listener;
    }

	public abstract void parse(String body);

}
