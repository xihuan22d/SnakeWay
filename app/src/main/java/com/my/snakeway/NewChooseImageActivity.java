package com.my.snakeway;

import android.content.Intent;

import com.snakeway.ui.imagechooselibrary.ChooseImageActivity;
import com.snakeway.ui.utils.SnakeWayManagerParams;

/**
 * 如果继承重写了BaseActivity的getSnakeWayManagerParams方法,如果需要用到相册的话,建议把ChooseImageActivity里面的getSnakeWayManagerParams方法也改成一样的
 */
public class NewChooseImageActivity extends ChooseImageActivity {


    public Intent getPreviewActivityIntent() {
        Intent intent = new Intent(NewChooseImageActivity.this,
                NewPreviewActivity.class);
        return intent;
    }

    @Override
    public SnakeWayManagerParams getSnakeWayManagerParams() {
        return NewBaseActivity.getSnakeWayManagerParams(this);
    }
}
