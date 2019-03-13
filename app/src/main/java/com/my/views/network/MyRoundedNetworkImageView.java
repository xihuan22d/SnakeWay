package com.my.views.network;

import android.content.Context;
import android.util.AttributeSet;

import com.snakeway.network.BitmapRequest;
import com.snakeway.network.BitmapRequest_Default_Params;
import com.snakeway.ui.views.RoundedNetworkImageView;
import com.my.utils.MyBitmapRequest;


public class MyRoundedNetworkImageView extends RoundedNetworkImageView {
    public MyRoundedNetworkImageView(Context context) {
        super(context);
    }

    public MyRoundedNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRoundedNetworkImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public BitmapRequest<?> getBitmapRequest(BitmapRequest_Default_Params bitmapRequest_Default_Params) {
        return new MyBitmapRequest(bitmapRequest_Default_Params);
    }
}
