package com.snakeway.network.cache;


import com.snakeway.network.utils.SuperBitmap;


/**
 * Created by snakeway on 2017/8/27.
 */

public interface BitmapMemoryCache {

    void putSuperBitmap(String key, SuperBitmap superBitmap);

    SuperBitmap getSuperBitmap(String key);

    void clearMemory(String key);

    void clearAllMemory();

}
