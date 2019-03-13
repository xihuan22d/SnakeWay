package com.snakeway.ui.imagechooselibrary.adapters;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.snakeway.R;
import com.snakeway.ui.BaseActivity;
import com.snakeway.ui.imagechooselibrary.ChooseImageActivity;
import com.snakeway.ui.imagechooselibrary.models.ImageItem;
import com.snakeway.ui.recyclerviewlibrary.adapters.BaseRecyclerViewAdapter;
import com.snakeway.ui.recyclerviewlibrary.adapters.UnLoadMoreRecyclerViewAdapter;
import com.snakeway.ui.recyclerviewlibrary.models.ViewItem;
import com.snakeway.ui.utils.NetworkGifImageViewHelp;
import com.snakeway.ui.utils.NetworkImageViewHelp;
import com.snakeway.ui.views.NetworkGifImageView;

import java.util.List;


/**
 * Created by snakeway on 2017/7/7.
 */

public class ChooseImageActivity_RecyclerViewAdapter extends UnLoadMoreRecyclerViewAdapter {
    private ChooseImageActivity chooseImageActivity;
    private int itemWidth = 0;
    private int snakeway_activity_chooseimage_item_background_select;
    private int snakeway_activity_chooseimage_item_background_unselect;


    public ChooseImageActivity_RecyclerViewAdapter(ChooseImageActivity chooseImageActivity, List<ViewItem> datas) {
        super(datas);
        init(chooseImageActivity);
    }

    private void init(ChooseImageActivity chooseImageActivity) {
        this.chooseImageActivity = chooseImageActivity;
        int activity_chooseimage_recyclerView_space = BaseActivity.getRecyclerViewSpace_Default(this.chooseImageActivity);
        itemWidth = (BaseActivity.getScreenWidth(this.chooseImageActivity) - 6 * activity_chooseimage_recyclerView_space) / 3;
        snakeway_activity_chooseimage_item_background_select = ContextCompat.getColor(this.chooseImageActivity, R.color.snakeway_activity_chooseimage_item_background_select);
        snakeway_activity_chooseimage_item_background_unselect = ContextCompat.getColor(this.chooseImageActivity, R.color.snakeway_activity_chooseimage_item_background_unselect);
    }

    @Override
    public int getNormalLayoutId(int viewType) {
        if (viewType == ViewItem.VIEW_TYPE_NORMAL_ITEM_TYPE1) {
            return R.layout.snakeway_activity_chooseimage_item;
        } else {
            return R.layout.snakeway_recyclerview_lostviewtype_item;
        }
    }

    @Override
    public void onBindNormalViewHolder(final BaseRecyclerViewAdapter.BaseRecyclerViewHolder holder, int position) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {//规范一下SDK版本的判断,小于的时候不加等于号,大于时候加等于号,api13以及以下的recyclerView复用的view会有不显示的bug,测试发现最新的'com.android.support:recyclerview-v7:25.3.1'也没有修复
            holder.itemView.post(new Runnable() {
                @Override
                public void run() {
                    holder.itemView.setAlpha(1.0f);
                }
            });
        }
        ViewItem viewItem = getDatas().get(position);
        if (viewItem != null) {
            if (viewItem.getViewType() == ViewItem.VIEW_TYPE_NORMAL_ITEM_TYPE1) {
                final ImageItem imageItem = (ImageItem) viewItem.getModel();
                if (imageItem != null) {
                    NetworkGifImageView networkGifImageView = (NetworkGifImageView) holder.getView(R.id.networkGifImageView);
                    final CheckBox checkBox = (CheckBox) holder.getView(R.id.checkBox);
                    final FrameLayout frameLayoutCover = (FrameLayout) holder.getView(R.id.frameLayoutCover);
                    NetworkImageViewHelp.setLayoutParamsIfChanged(frameLayoutCover, itemWidth, itemWidth);
                    networkGifImageView.setOpenUpdateScaleType(true);
                    networkGifImageView.setUpdateScaleTypeWhenGetBitmap(ImageView.ScaleType.CENTER_CROP);
                    NetworkGifImageViewHelp.getImageFromDiskCacheWithNewParams(networkGifImageView, imageItem.getPath(), 1, chooseImageActivity.activityKey, itemWidth, itemWidth, R.mipmap.normal_loading, R.mipmap.normal_loading_error);
                    if (imageItem.isSelect()) {
                        checkBox.setChecked(true);
                        frameLayoutCover.setBackgroundColor(snakeway_activity_chooseimage_item_background_select);
                    } else {
                        checkBox.setChecked(false);
                        frameLayoutCover.setBackgroundColor(snakeway_activity_chooseimage_item_background_unselect);
                    }
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (imageItem.isSelect()) {
                                checkBox.setChecked(false);
                                frameLayoutCover.setBackgroundColor(snakeway_activity_chooseimage_item_background_unselect);
                                imageItem.setSelect(false);
                                chooseImageActivity.updateSelectImageItems();
                            } else {
                                if (chooseImageActivity.isCanSelect()) {
                                    checkBox.setChecked(true);
                                    frameLayoutCover.setBackgroundColor(snakeway_activity_chooseimage_item_background_select);
                                    imageItem.setSelect(true);
                                    chooseImageActivity.updateSelectImageItems();
                                }
                            }
                        }
                    });
                }
            }
        }
    }


}
