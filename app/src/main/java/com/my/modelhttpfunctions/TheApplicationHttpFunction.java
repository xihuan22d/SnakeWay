package com.my.modelhttpfunctions;

import android.content.Context;

import com.snakeway.network.Request;
import com.snakeway.ui.BaseActivity;
import com.snakeway.ui.utils.SnakeWayManager;
import com.snakeway.ui.utils.NetworkStateTool;
import com.my.snakeway.NewBaseActivity;
import com.my.snakeway.TheApplication;
import com.my.utils.EncryptionTool;
import com.my.utils.HttpConnectTool;
import com.my.utils.MyStringRequest;

import java.util.HashMap;
import java.util.Map;

public class TheApplicationHttpFunction {

    public static void theApplication_feedback(final Context context, final String activityKey, final String content, final Request.OnResponseListener<String> onResponseListener) {
        String url = HttpConnectTool.getServerRootUrl() + HttpConnectTool.theApplication_feedback;
        if (!NetworkStateTool.isNetworkConnected(context)) {
            BaseActivity.showShortToast(context, TheApplication.NETOPENERROR);
            if (onResponseListener != null) {
                onResponseListener.onErrorResponse(null);
            }
        } else {
            MyStringRequest myStringRequest = new MyStringRequest(url, Request.POST, activityKey, new Request.OnResponseListener<String>() {
                @Override
                public void onSuccessResponse(String response) {
                    if (onResponseListener != null) {
                        onResponseListener.onSuccessResponse(response);
                    }
                }

                @Override
                public void onErrorResponse(String error) {
                    if (onResponseListener != null) {
                        onResponseListener.onErrorResponse(error);
                    }
                }
            }) {
                @Override
                public Map<String, String> getBodyParams() {
                    Map<String, String> bodyParams = new HashMap<String, String>();
                    EncryptionTool.addParamWithEncryption(bodyParams, "content", content);
                    EncryptionTool.addParamWithEncryption(bodyParams, "type", "SnakeWay");
                    return bodyParams;
                }
            };
            SnakeWayManager.getInstanceBySnakeWayManagerParams(NewBaseActivity.getSnakeWayManagerParams(context)).addRequestWithSort(myStringRequest);
        }
    }

}
