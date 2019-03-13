package com.snakeway.network.http;


import com.snakeway.network.ResponseHandler;
import com.snakeway.network.cache.ThreadSafelyLinkedHasMapCacheItem;

/**
 * Created by snakeway on 2017/9/7.
 */

public class DoRequestParams {

    private final ResponseHandler mainResponseHandler;

    private final ThreadSafelyLinkedHasMapCacheItem threadSafelyLinkedHasMapCacheItem;

    public DoRequestParams(ResponseHandler mainResponseHandler, ThreadSafelyLinkedHasMapCacheItem threadSafelyLinkedHasMapCacheItem) {
        this.mainResponseHandler = mainResponseHandler;
        this.threadSafelyLinkedHasMapCacheItem = threadSafelyLinkedHasMapCacheItem;
    }

    public ResponseHandler getMainResponseHandler() {
        return mainResponseHandler;
    }

    public ThreadSafelyLinkedHasMapCacheItem getThreadSafelyLinkedHasMapCacheItem() {
        return threadSafelyLinkedHasMapCacheItem;
    }

    @Override
    public String toString() {
        return "DoRequestParams{" +
                "mainResponseHandler=" + mainResponseHandler +
                ", threadSafelyLinkedHasMapCacheItem=" + threadSafelyLinkedHasMapCacheItem +
                '}';
    }
}
