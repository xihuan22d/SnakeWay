package com.snakeway.network;

import com.snakeway.network.http.HttpResponse;
import com.snakeway.network.utils.SuperKey;

/**
 * Created by snakeway on 2017/9/19.
 */

public class UploadRequest_Default extends UploadRequest<String> {
    public UploadRequest_Default(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener);
    }

    public UploadRequest_Default(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener, int priority) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener, priority);
    }

    public UploadRequest_Default(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener, int priority, String encoding) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener, priority, encoding);
    }

    public UploadRequest_Default(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener, int priority, String encoding, int connectTimeoutMs, int readTimeoutMs, OnUploadProgressListener onUploadProgressListener) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener, priority, encoding, connectTimeoutMs, readTimeoutMs, onUploadProgressListener);
    }

    public UploadRequest_Default(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, SuperKey superKey, OnResponseListener<String> onResponseListener, String encoding, int connectTimeoutMs, int readTimeoutMs, OnUploadProgressListener onUploadProgressListener) {
        super(urlString, names, values, fileNames, filePaths, superKey, onResponseListener, encoding, connectTimeoutMs, readTimeoutMs, onUploadProgressListener);
    }

    public UploadRequest_Default(UploadRequest_Default_Params uploadRequest_Default_Params) {
        super(uploadRequest_Default_Params.getUrlString(), uploadRequest_Default_Params.getNames(), uploadRequest_Default_Params.getValues(), uploadRequest_Default_Params.getFileNames(), uploadRequest_Default_Params.getFilePaths(), uploadRequest_Default_Params.getTag(), uploadRequest_Default_Params.getOnResponseListener(), uploadRequest_Default_Params.getPriority(), uploadRequest_Default_Params.getEncoding(), uploadRequest_Default_Params.getConnectTimeoutMs(), uploadRequest_Default_Params.getReadTimeoutMs(), uploadRequest_Default_Params.getOnUploadProgressListener());
    }

    @Override
    public String convertResponseType(Request<String> request, HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        return httpResponse.getResult();
    }
}
