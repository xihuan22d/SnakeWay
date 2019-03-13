package com.snakeway.network.utils;

import android.os.Looper;

/**
 * Created by snakeway on 2017/7/30.
 */

public class ThreadTool {

    /**
     * Must running on mainThread
     * 必须运行在主线程
     */
    public static void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Must running on mainThread");
        }
    }


}
