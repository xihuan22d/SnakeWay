package com.snakeway.ui.views;

import com.snakeway.network.BitmapResponse;

/**
 * Created by snakeway on 2017/9/16.
 */
public interface OnNetworkImageViewResponseListener {
    void onReset();

    void onSuccessResponse(BitmapResponse response);

    void onErrorResponse(String error);
}