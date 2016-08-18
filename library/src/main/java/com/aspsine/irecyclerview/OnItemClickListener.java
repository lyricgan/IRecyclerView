package com.aspsine.irecyclerview;

import android.view.View;

/**
 * Created by aspsine on 16/4/6.
 */
public interface OnItemClickListener<T> {

    void onItemClick(int position, T item, View view);
}
