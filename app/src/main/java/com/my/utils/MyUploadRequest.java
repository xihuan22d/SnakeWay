package com.my.utils;

import com.snakeway.network.Request;
import com.snakeway.network.UploadRequest_Default;
import com.snakeway.network.UploadRequest_Default_Params;
import com.snakeway.network.http.HttpResponse;
import com.snakeway.network.utils.SuperKey;
import com.my.snakeway.TheApplication;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class MyUploadRequest extends UploadRequest_Default {


    public MyUploadRequest(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener);
        setOnHttpStatusListener();
    }

    public MyUploadRequest(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener, int priority) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener, priority);
        setOnHttpStatusListener();
    }

    public MyUploadRequest(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener, int priority, String encoding) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener, priority, encoding);
        setOnHttpStatusListener();
    }

    public MyUploadRequest(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, String tag, OnResponseListener<String> onResponseListener, int priority, String encoding, int connectTimeoutMs, int readTimeoutMs, OnUploadProgressListener onUploadProgressListener) {
        super(urlString, names, values, fileNames, filePaths, tag, onResponseListener, priority, encoding, connectTimeoutMs, readTimeoutMs, onUploadProgressListener);
        setOnHttpStatusListener();
    }

    public MyUploadRequest(String urlString, String[] names, String[] values, String[] fileNames, String[] filePaths, SuperKey superKey, OnResponseListener<String> onResponseListener, String encoding, int connectTimeoutMs, int readTimeoutMs, OnUploadProgressListener onUploadProgressListener) {
        super(urlString, names, values, fileNames, filePaths, superKey, onResponseListener, encoding, connectTimeoutMs, readTimeoutMs, onUploadProgressListener);
        setOnHttpStatusListener();
    }

    public MyUploadRequest(UploadRequest_Default_Params uploadRequest_Default_Params) {
        super(uploadRequest_Default_Params);
        setOnHttpStatusListener();
    }


    @Override
    public Map<String, String> getHeaderParams() {
        Map<String, String> headerParams = new HashMap<String, String>();
        headerParams.put("Connection", "keep-alive");
        headerParams.put("Accept-Charset", getEncoding());
        headerParams.put("Content-Type", "multipart/form-data;charset=" + getEncoding() + ";boundary=*****");
        headerParams.put("uid", "5c0b4645579bcb6796cc79b9");
        headerParams.put("token", "11e79db7afa0f62e919fde99e2e870e6_198ddbb613de217c");
        return headerParams;
    }

    @Override
    public String convertResponseType(Request<String> request, HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        return EncryptionTool.stringToDecode(httpResponse.getResult());
    }

    private void setOnHttpStatusListener() {
        if (HttpConnectTool.USEHTTPS) {
            setOnHttpStatusListener(new OnHttpStatusListener() {
                @Override
                public void onHttpInit(HttpURLConnection httpURLConnection, Object object) {

                }

                @Override
                public void onHttpsInit(HttpsURLConnection httpsURLConnection, Object object) {
                    if (httpsURLConnection != null) {
                        httpsURLConnection.setSSLSocketFactory(HttpConnectTool.getInstanceSslSocketFactory(TheApplication.getTheApplication()));
                        HttpConnectTool.setHostnameVerifier(httpsURLConnection);
                    }
                }

                @Override
                public void onHttpCompleted(HttpURLConnection httpURLConnection, Object object) {

                }

                @Override
                public void onHttpsCompleted(HttpsURLConnection httpsURLConnection, Object object) {

                }
            });
        }
    }
}
