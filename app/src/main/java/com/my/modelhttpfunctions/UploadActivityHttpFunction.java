package com.my.modelhttpfunctions;

import com.snakeway.network.Request;
import com.snakeway.network.UploadRequest;
import com.snakeway.network.UploadRequest_Default_Params;
import com.snakeway.network.http.HttpResponse;
import com.snakeway.ui.BaseActivity;
import com.snakeway.ui.utils.SnakeWayManager;
import com.snakeway.ui.utils.NetworkStateTool;
import com.my.snakeway.TheApplication;
import com.my.models.net.BaseGsonModel;
import com.my.models.net.UploadActivity_UploadWallpaper_GsonModel;
import com.my.utils.HttpConnectTool;
import com.my.utils.MyUploadRequest;

public class UploadActivityHttpFunction {


    public static UploadRequest<?> uploadActivity_uploadWallpaper(final BaseActivity baseActivity, final String[] names, final String[] values, final String[] fileNames, final String[] filePaths, final Request.OnResponseListener<String> onResponseListener, final BaseGsonModel.OnBaseGsonModelListener<UploadActivity_UploadWallpaper_GsonModel.Data> onBaseGsonModelListener, UploadRequest.OnUploadProgressListener onUploadProgressListener) {
        String url = HttpConnectTool.getServerRootUrl() + HttpConnectTool.uploadActivity_uploadWallpaper;
        if (!NetworkStateTool.isNetworkConnected(baseActivity)) {
            BaseActivity.showShortToast(baseActivity, TheApplication.NETOPENERROR);
            if (onResponseListener != null) {
                onResponseListener.onErrorResponse(null);
            }
            return null;
        } else {
            UploadRequest_Default_Params uploadRequest_Default_Params = new UploadRequest_Default_Params();
            uploadRequest_Default_Params.setUrlString(url);
            uploadRequest_Default_Params.setNames(names);
            uploadRequest_Default_Params.setValues(values);
            uploadRequest_Default_Params.setFileNames(fileNames);
            uploadRequest_Default_Params.setFilePaths(filePaths);
            uploadRequest_Default_Params.setTag(baseActivity.activityKey);
            uploadRequest_Default_Params.setOnResponseListener(new Request.OnResponseListener<String>() {
                @Override
                public void onSuccessResponse(String response) {
                    uploadActivity_uploadWallpaperResponse(response, baseActivity, onResponseListener, onBaseGsonModelListener);
                }

                @Override
                public void onErrorResponse(String error) {
                    if (TheApplication.SHOWTIMEOUTTIPS) {
                        if (error != null && !error.equals(HttpResponse.CANCEL_TIPS)) {
                            BaseActivity.showShortToast(baseActivity, TheApplication.NETTIMEOUTERROR);
                        }
                    }
                    if (error != null && error.equals(HttpResponse.CANCEL_TIPS)) {
                        error = TheApplication.CANCELTIPS;
                    }
                    if (onResponseListener != null) {
                        onResponseListener.onErrorResponse(error);
                    }
                }
            });
            uploadRequest_Default_Params.setOnUploadProgressListener(onUploadProgressListener);
            MyUploadRequest myUploadRequest = new MyUploadRequest(uploadRequest_Default_Params);
            myUploadRequest.setReceiveCancel(true);
            SnakeWayManager.getInstance(baseActivity).addRequestWithSort(myUploadRequest);
            return myUploadRequest;
        }
    }

    private static void uploadActivity_uploadWallpaperResponse(String response, BaseActivity baseActivity, Request.OnResponseListener<String> onResponseListener, BaseGsonModel.OnBaseGsonModelListener<UploadActivity_UploadWallpaper_GsonModel.Data> onBaseGsonModelListener) {
        boolean isHaveErrorOrTips = BaseGsonModel.isBaseGsonModelHaveErrorOrTips(response, onBaseGsonModelListener);
        if (!isHaveErrorOrTips) {
            UploadActivity_UploadWallpaper_GsonModel.Data data = UploadActivity_UploadWallpaper_GsonModel.getGsonModel(response);
            if (onBaseGsonModelListener != null) {
                onBaseGsonModelListener.success(data);
            }
        }
        if (onResponseListener != null) {
            onResponseListener.onSuccessResponse(response);
        }
    }

}
