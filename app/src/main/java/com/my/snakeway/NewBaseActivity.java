package com.my.snakeway;

import android.content.Context;
import android.os.Bundle;

import com.snakeway.network.ResponseHandler;
import com.snakeway.network.http.HttpAgreement_Default;
import com.snakeway.network.utils.FileTool;
import com.snakeway.ui.BaseActivity;
import com.snakeway.ui.utils.SnakeWayManagerParams;

import java.io.File;


public class NewBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelType(CANCELREQUESTWITHTAG);
    }

    /**
     * SnakeWay初始化参数
     */
    @Override
    public SnakeWayManagerParams getSnakeWayManagerParams() {
        return getSnakeWayManagerParams(this);
    }


    public static SnakeWayManagerParams getSnakeWayManagerParams(Context context) {
        String rootPath = TheApplication.getFilesDirRootPath(context) + File.separator + FileTool.DEFAULT_ROOT_PATH;
        // String rootPath = FileTool.getSdcardRootPath(TheApplication.DEFAULT_ROOT_PATH) + File.separator + FileTool.DEFAULT_ROOT_PATH;
        return new SnakeWayManagerParams(context, 4, 2, new HttpAgreement_Default(), null, null, 2, rootPath, 52428800, ResponseHandler.RESPONSEHANDLERWITHMESSAGERECORD, true, 2);
    }


}
