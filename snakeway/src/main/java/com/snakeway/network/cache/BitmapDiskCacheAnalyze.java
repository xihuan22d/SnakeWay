package com.snakeway.network.cache;


import com.snakeway.network.BitmapRequest;
import com.snakeway.network.http.DoRequestParams;
import com.snakeway.network.http.HttpResponse;

/**
 * Created by snakeway on 2017/9/7.
 */

public interface BitmapDiskCacheAnalyze {

    HttpResponse doDiskCacheAnalyze(BitmapRequest<?> bitmapRequest, DoRequestParams doRequestParams);

}
