package com.aspsine.irecyclerview.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IViewHolder;
import com.aspsine.irecyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lyricgan
 * @description
 * @time 2016/8/11 16:57
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected List<T> mDataList;
    private int mLayoutId;
    private OnItemClickListener<T> mOnItemClickListener;

    public RecyclerAdapter(Context context, int layoutId) {
        this(context, new ArrayList<T>(), layoutId);
    }

    public RecyclerAdapter(Context context, T[] arrays, int layoutId) {
        this(context, Arrays.asList(arrays), layoutId);
    }

    public RecyclerAdapter(Context context, List<T> dataList, int layoutId) {
        this.mContext = context;
        this.mDataList = dataList;
        this.mLayoutId = layoutId;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        final BaseViewHolder holder = new BaseViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * in order to get the right position, you must use the method with i- prefix in
                 * {@link IViewHolder} eg:
                 * {@code IViewHolder.getIPosition()}
                 * {@code IViewHolder.getILayoutPosition()}
                 * {@code IViewHolder.getIAdapterPosition()}
                 */
                final int position = holder.getIAdapterPosition();
                T object = null;
                if (mDataList != null && !mDataList.isEmpty()) {
                    object = mDataList.get(position);
                }
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, object, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T object = null;
        if (mDataList != null && !mDataList.isEmpty()) {
            object = mDataList.get(position);
        }
        convert(holder, object);
    }

    public abstract void convert(BaseViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDataList(List<T> dataList) {
        this.mDataList = dataList;
    }

    public List<T> getDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        return mDataList;
    }

    public boolean isEmpty() {
        return (mDataList == null || mDataList.isEmpty());
    }

    public void add(T object) {
        this.add(this.mDataList.size(), object);
    }

    public void add(int location, T object) {
        if (location < 0 || location > this.mDataList.size()) {
            return;
        }
        if (object != null) {
            this.mDataList.add(location, object);
            this.notifyDataSetChanged();
        }
    }

    public void add(List<T> dataList) {
        if (dataList != null && dataList.size() > 0) {
            this.mDataList.addAll(dataList);
            this.notifyDataSetChanged();
        }
    }

    public void remove(T object) {
        if (object != null) {
            this.mDataList.remove(object);
            this.notifyDataSetChanged();
        }
    }

    public void remove(int location) {
        if (location < 0 || location >= this.mDataList.size()) {
            return;
        }
        this.mDataList.remove(location);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.mDataList.clear();
        this.notifyDataSetChanged();
    }

    static class ViewHolder extends IViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
