package com.snakeway.ui.recyclerviewlibrary.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snakeway on 2017/9/16.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder> {
    public static final int ONITEMDEFAULT = 0;
    public static final int ONITEMRANGECHANGED = 1;
    public static final int ONITEMRANGEINSERTED = 2;
    public static final int ONITEMRANGEREMOVED = 3;
    public static final int ONITEMRANGEMOVED = 4;
    public static final int ONITEMUPDATE = 5;

    private final List<T> datas = new ArrayList<T>();
    private int itemType = ONITEMDEFAULT;

    public BaseRecyclerViewAdapter(List<T> datas) {
        if (datas == null) {
            datas = new ArrayList<T>();
        }
        this.datas.addAll(datas);
    }


    public abstract int getLayoutId(int viewType);

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private void clearAll() {
        int size = datas.size();
        if (size > 0) {
            datas.clear();//通知recyclerView改变之前必须先修改datas的值
            notifyItemRangeRemoved(0, size);
        }
    }

    public void addAll(List<T> newDatas) {
        if (newDatas == null) {
            newDatas = new ArrayList<T>();
        }
        int start = datas.size();
        datas.addAll(newDatas);
        itemType = ONITEMRANGEINSERTED;
        notifyItemRangeInserted(start, newDatas.size());
    }

    public void addAllUseNotifyDataSetChanged(List<T> newDatas) {
        if (newDatas == null) {
            newDatas = new ArrayList<T>();
        }
        datas.addAll(newDatas);
        itemType = ONITEMUPDATE;
        notifyDataSetChanged();
    }

    public void itemMoved(int fromPosition, int toPosition) {
        itemType = ONITEMRANGEMOVED;
        notifyItemMoved(fromPosition, toPosition);
    }

    public void replaceAll(List<T> newDatas) {
        if (newDatas == null) {
            newDatas = new ArrayList<T>();
        }
        clearAll();
        datas.addAll(newDatas);
        itemType = ONITEMRANGEINSERTED;
        notifyItemRangeInserted(0, newDatas.size());
    }

    public void replaceAllWithScrollTop(List<T> newDatas, RecyclerView recyclerView) {
        if (newDatas == null) {
            newDatas = new ArrayList<T>();
        }
        clearAll();
        datas.addAll(newDatas);
        itemType = ONITEMRANGEINSERTED;
        notifyItemRangeInserted(0, newDatas.size());
        if (datas.size() > 0) {
            recyclerView.scrollToPosition(0);
        }
    }

    public void replaceAllUseNotifyDataSetChanged(List<T> newDatas) {
        if (newDatas == null) {
            newDatas = new ArrayList<T>();
        }
        datas.clear();
        datas.addAll(newDatas);
        itemType = ONITEMUPDATE;
        notifyDataSetChanged();
    }

    public void replaceAllUseNotifyDataSetChangedWithScrollTop(List<T> newDatas, RecyclerView recyclerView) {
        if (newDatas == null) {
            newDatas = new ArrayList<T>();
        }
        datas.clear();
        datas.addAll(newDatas);
        itemType = ONITEMUPDATE;
        notifyDataSetChanged();
        if (datas.size() > 0) {
            recyclerView.scrollToPosition(0);
        }
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }


    public List<T> getDatas() {
        return datas;
    }

    public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();

        public BaseRecyclerViewHolder(View view) {
            super(view);
        }

        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 如果某个view需要动态移除或者清除viewId对应的缓存可以调用该方法
         */
        public void removeCacheView(int viewId) {
            views.remove(viewId);
        }


        public void setText(int viewId, String text) {
            TextView textView = getView(viewId);
            textView.setText(text);
        }

    }
}
