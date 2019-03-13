package com.snakeway.ui.utils;


import com.snakeway.network.BitmapRequest;
import com.snakeway.network.CacheItemRequest;
import com.snakeway.network.Request;
import com.snakeway.network.RequestManager;
import com.snakeway.network.RequestManagerUseDispatcher;
import com.snakeway.network.RequestStatusItem;
import com.snakeway.network.cache.CacheItem;
import com.snakeway.network.utils.FileTool;
import com.snakeway.network.utils.SuperKey;
import com.snakeway.ui.BaseActivity;

import java.util.List;

/**
 * Created by snakeway on 2017/8/20.
 */
public class SnakeWayManager {

    private final static Object instanceLock = new Object();

    private static SnakeWayManager snakeWayManager;
    /**
     * NotNull
     */
    private final RequestManager requestManager;


    private SnakeWayManager(SnakeWayManagerParams snakeWayManagerParams) {
        if (snakeWayManagerParams == null) {
            snakeWayManagerParams = new SnakeWayManagerParams();
        }
        if (snakeWayManagerParams.isUseDispatcher()) {
            this.requestManager = new RequestManagerUseDispatcher(snakeWayManagerParams.getContext(), snakeWayManagerParams.getNetworkThreadCount(), snakeWayManagerParams.getBitmapNetworkThreadCount(), snakeWayManagerParams.getHttpAgreement(), snakeWayManagerParams.getBitmapMemoryCache(), snakeWayManagerParams.getBitmapDiskCacheAnalyze(), snakeWayManagerParams.getDiskCacheThreadCount(), snakeWayManagerParams.getFileCache(), snakeWayManagerParams.getMaxDiskCacheSize(), snakeWayManagerParams.getResponseHandlerType(), snakeWayManagerParams.getDispatcherThreadCount());
        } else {
            this.requestManager = new RequestManager(snakeWayManagerParams.getContext(), snakeWayManagerParams.getNetworkThreadCount(), snakeWayManagerParams.getBitmapNetworkThreadCount(), snakeWayManagerParams.getHttpAgreement(), snakeWayManagerParams.getBitmapMemoryCache(), snakeWayManagerParams.getBitmapDiskCacheAnalyze(), snakeWayManagerParams.getDiskCacheThreadCount(), snakeWayManagerParams.getFileCache(), snakeWayManagerParams.getMaxDiskCacheSize(), snakeWayManagerParams.getResponseHandlerType());
        }
        open();
    }


    public static SnakeWayManager getInstance() {
        synchronized (instanceLock) {
            return snakeWayManager;
        }
    }

    public static SnakeWayManager getInstance(BaseActivity baseActivity) {
        if (baseActivity == null) {
            throw new NullPointerException("baseActivity为null");
        }
        synchronized (instanceLock) {
            if (snakeWayManager == null) {
                snakeWayManager = new SnakeWayManager(baseActivity.getSnakeWayManagerParams());
            }
            return snakeWayManager;
        }
    }

    public static SnakeWayManager getInstanceBySnakeWayManagerParams(SnakeWayManagerParams snakeWayManagerParams) {
        synchronized (instanceLock) {
            if (snakeWayManager == null) {
                snakeWayManager = new SnakeWayManager(snakeWayManagerParams);
            }
            return snakeWayManager;
        }
    }


    public RequestManager getRequestManager() {
        return requestManager;
    }

    /**
     * 如果不是RequestManagerUseDispatcher,将抛出异常
     */
    public RequestManagerUseDispatcher getRequestManagerUseDispatcher() {
        RequestManagerUseDispatcher requestManagerUseDispatcher = null;
        if (requestManager instanceof RequestManagerUseDispatcher) {
            requestManagerUseDispatcher = (RequestManagerUseDispatcher) requestManager;
        }
        if (requestManagerUseDispatcher == null) {
            throw new ClassCastException("RequestManagerUseDispatcher不存在");
        }
        return requestManagerUseDispatcher;
    }


    public void open() {
        requestManager.open();
    }

    public void closeByWait() {
        requestManager.closeByWait();
    }

    public void close() {
        requestManager.close();
    }

    public void addRequest(Request<?> request) {
        requestManager.addRequest(request);
    }

    public boolean addRequestToDispatcher(Request<?> request) {
        return getRequestManagerUseDispatcher().addRequestToDispatcher(request);
    }

    public void addRequestWithSort(Request<?> request) {
        requestManager.addRequestWithSort(request);
    }

    public boolean addRequestWithSortToDispatcher(Request<?> request) {
        return getRequestManagerUseDispatcher().addRequestWithSortToDispatcher(request);
    }

    public void addBitmapRequest(BitmapRequest<?> bitmapRequest) {
        requestManager.addBitmapRequest(bitmapRequest);
    }

    public boolean addBitmapRequestToDispatcher(BitmapRequest<?> bitmapRequest) {
        return getRequestManagerUseDispatcher().addBitmapRequestToDispatcher(bitmapRequest);
    }

    public void addBitmapRequestWithSort(BitmapRequest<?> bitmapRequest) {
        requestManager.addBitmapRequestWithSort(bitmapRequest);
    }

    public boolean addBitmapRequestWithSortToDispatcher(BitmapRequest<?> bitmapRequest) {
        return getRequestManagerUseDispatcher().addBitmapRequestWithSortToDispatcher(bitmapRequest);
    }

    public void cancelRequestWithSuperKeyByWait(SuperKey superKey, boolean removeListener) {
        requestManager.cancelRequestWithSuperKeyByWait(superKey, removeListener);
    }

    public void cancelRequestWithSuperKey(SuperKey superKey, boolean removeListener) {
        requestManager.cancelRequestWithSuperKey(superKey, removeListener);
    }

    public boolean cancelRequestWithSuperKeyToDispatcher(SuperKey superKey, boolean removeListener) {
        return getRequestManagerUseDispatcher().cancelRequestWithSuperKeyToDispatcher(superKey, removeListener);
    }

    public void cancelRequestWithRequestByWait(Request<?> request, boolean removeListener) {
        requestManager.cancelRequestWithRequestByWait(request, removeListener);
    }

    public void cancelRequestWithRequest(Request<?> request, boolean removeListener) {
        requestManager.cancelRequestWithRequest(request, removeListener);
    }

    public boolean cancelRequestWithRequestToDispatcher(Request<?> request, boolean removeListener) {
        return getRequestManagerUseDispatcher().cancelRequestWithRequestToDispatcher(request, removeListener);
    }

    public void cancelBitmapRequestWithSuperKeyByWait(SuperKey superKey, boolean removeListener) {
        requestManager.cancelBitmapRequestWithSuperKeyByWait(superKey, removeListener);
    }

    public void cancelBitmapRequestWithSuperKey(SuperKey superKey, boolean removeListener) {
        requestManager.cancelBitmapRequestWithSuperKey(superKey, removeListener);
    }

    public boolean cancelBitmapRequestWithSuperKeyToDispatcher(SuperKey superKey, boolean removeListener) {
        return getRequestManagerUseDispatcher().cancelBitmapRequestWithSuperKeyToDispatcher(superKey, removeListener);
    }

    public void cancelBitmapRequestWithRequestByWait(BitmapRequest<?> bitmapRequest, boolean removeListener) {
        requestManager.cancelBitmapRequestWithRequestByWait(bitmapRequest, removeListener);
    }

    public void cancelBitmapRequestWithRequest(BitmapRequest<?> bitmapRequest, boolean removeListener) {
        requestManager.cancelBitmapRequestWithRequest(bitmapRequest, removeListener);
    }

    public boolean cancelBitmapRequestWithRequestToDispatcher(BitmapRequest<?> bitmapRequest, boolean removeListener) {
        return getRequestManagerUseDispatcher().cancelBitmapRequestWithRequestToDispatcher(bitmapRequest, removeListener);
    }

    public void cancelRequestWithTagByWait(String tag, boolean removeListener) {
        requestManager.cancelRequestWithTagByWait(tag, removeListener);
    }


    public void cancelRequestWithTag(String tag, boolean removeListener) {
        requestManager.cancelRequestWithTag(tag, removeListener);
    }

    public boolean cancelRequestWithTagToDispatcher(String tag, boolean removeListener) {
        return getRequestManagerUseDispatcher().cancelRequestWithTagToDispatcher(tag, removeListener);
    }

    public void cancelAllRequestByWait(boolean removeListener) {
        requestManager.cancelAllRequestByWait(removeListener);
    }

    public void cancelAllRequest(boolean removeListener) {
        requestManager.cancelAllRequest(removeListener);
    }

    public boolean cancelAllRequestToDispatcher(boolean removeListener) {
        return getRequestManagerUseDispatcher().cancelAllRequestToDispatcher(removeListener);
    }


    public RequestStatusItem addRequestStatusItem(int status, int kind, SuperKey superKey) {
        return requestManager.addRequestStatusItem(status, kind, superKey);
    }

    public RequestStatusItem addRequestStatusItem(int status, int kind, String tag) {
        return requestManager.addRequestStatusItem(status, kind, tag);
    }

    public RequestStatusItem addRequestStatusItemWithAll(int status, int kind) {
        return requestManager.addRequestStatusItemWithAll(status, kind);
    }

    public void removeRequestStatusItem(RequestStatusItem requestStatusItem) {
        requestManager.removeRequestStatusItem(requestStatusItem);
    }


    public void clearMemory(String key) {
        requestManager.clearMemory(key);
    }

    public void clearAllMemory() {
        requestManager.clearAllMemory();
    }

    public CacheItem getCacheItemByKey(String key, RequestManager.CacheItemTAG cacheItemTAG) {
        return requestManager.getCacheItemByKey(key, cacheItemTAG);
    }

    public List<CacheItem> getCacheItemsByKeys(List<String> keys, RequestManager.CacheItemTAG cacheItemTAG) {
        return requestManager.getCacheItemsByKeys(keys, cacheItemTAG);
    }

    public void clearCacheItem(String key, RequestManager.CacheItemTAG cacheItemTAG) {
        requestManager.clearCacheItem(key, cacheItemTAG);
    }

    public void clearCacheItems(List<String> keys, RequestManager.CacheItemTAG cacheItemTAG) {
        requestManager.clearCacheItems(keys, cacheItemTAG);
    }

    public void clearAllCacheItem() {
        requestManager.clearAllCacheItem();
    }

    public boolean doCacheItemsWithCallback(CacheItemRequest cacheItemRequest) {
        return getRequestManagerUseDispatcher().doCacheItemsWithCallback(cacheItemRequest);
    }

    public static String getDataFromCache(String url, int requestType, BaseActivity baseActivity) {
        String data = null;
        CacheItem cacheItem = SnakeWayManager.getInstance(baseActivity).getCacheItemByKey(RequestManager.getRequestKey(url, requestType), RequestManager.CacheItemTAG.NORMALREQUEST);
        if (cacheItem != null) {
            String cachePath = cacheItem.getCachePath();
            if (cachePath != null) {
                String cacheContent = FileTool.readContentWithLock(cachePath);
                if (cacheContent != null) {
                    data = RequestManager.getDataFromCacheContent(cacheContent);
                }
            }
        }
        return data;
    }
}
