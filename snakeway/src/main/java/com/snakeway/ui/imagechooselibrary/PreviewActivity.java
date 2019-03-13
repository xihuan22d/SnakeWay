package com.snakeway.ui.imagechooselibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snakeway.R;
import com.snakeway.ui.BaseActivity;
import com.snakeway.ui.utils.NetworkGifImageViewHelp;
import com.snakeway.ui.views.NetworkGifImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by snakeway on 2017/9/16.
 */
public class PreviewActivity extends BaseActivity {
    private ImageView imageViewBack;
    private ViewPager viewPager;
    private TextView textViewTips;
    private final List<String> selectImagePaths = new ArrayList<String>();
    private View.OnClickListener onClickListener;
    private MyPagerAdapter myPagerAdapter;
    private MyOnPageChangeListener myOnPageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snakeway_activity_preview);
        Intent intent = getIntent();
        List<String> selectImagePaths = intent.getStringArrayListExtra(ChooseImageActivity.SELECTIMAGEPATHS_KEY);
        if (selectImagePaths != null) {
            this.selectImagePaths.addAll(selectImagePaths);
        }
        initAll();
    }

    @Override
    protected void initHandler() {

    }

    @Override
    protected void initUi() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        textViewTips = (TextView) findViewById(R.id.textViewTips);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);
        myOnPageChangeListener = new MyOnPageChangeListener();
        viewPager.addOnPageChangeListener(myOnPageChangeListener);
        onClickListener();
    }

    @Override
    protected void initConfigUi() {
        viewPager.setOffscreenPageLimit(3);
        int position = 0;
        if (selectImagePaths.size() > 0) {
            position = 1;
        }
        textViewTips.setText(position + "/" + selectImagePaths.size());
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initOther() {

    }


    private void onClickListener() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.imageViewBack) {
                    doBack();
                }
            }
        };
        imageViewBack.setOnClickListener(onClickListener);
    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            textViewTips.setText((position + 1) + "/" + selectImagePaths.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    public class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return selectImagePaths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NetworkGifImageView networkGifImageView = new NetworkGifImageView(PreviewActivity.this);
            networkGifImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            networkGifImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            NetworkGifImageViewHelp.getImageFromDiskCache(networkGifImageView, selectImagePaths.get(position), 1, activityKey, 0, 0);
            container.addView(networkGifImageView);
            return networkGifImageView;
        }

    }

    @Override
    protected void doBack() {
        super.doBack();
        finish();
    }

}
