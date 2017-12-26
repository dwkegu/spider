package com.dwkegu.spiderlib.BaseFrame;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Url hub
 * Created by dwkeg on 2017/12/8.
 */

public abstract class AddressBase<T extends IAddressItem> {
    protected int initSize;
    protected Queue<T> urlBase;
    public enum CrawlDirection{
        horizontal, vertical
    }

    protected CrawlDirection direction;
    public AddressBase(CrawlDirection crawlDirection){
        init(0,crawlDirection);
    }
    public AddressBase(int size, CrawlDirection crawlDirection){
        init(size,crawlDirection);
    }
    private void init(int size, CrawlDirection crawlDirection){
        this.initSize = size;
        this.direction = crawlDirection;
        urlBase = new LinkedList<>();
        hasMore = true;
    }

    /**
     * save method for multi thread crawler
     * @return addressItem
     */
    public synchronized T getItemSync(){
        return getItem();
    }

    /**
     * get item url
     * @return addressItem
     */
    protected abstract T getItem();

    /**
     *add item url
     * @param url url
     */
    public abstract void putItem(T url);

    /**
     * put item url with level
     * @param url url
     * @param level level
     */
    public abstract void putItem(T url, int level);

    private boolean hasMore = true;
    public synchronized void noMore(){
        hasMore = false;
    }
    public boolean hasMore(){
        synchronized (this){
            return hasMore || urlBase.size() > 0;
        }
    }

}
