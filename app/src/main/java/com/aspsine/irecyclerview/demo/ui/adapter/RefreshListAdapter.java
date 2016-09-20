package com.aspsine.irecyclerview.demo.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.aspsine.irecyclerview.demo.R;
import com.aspsine.irecyclerview.demo.model.Image;
import com.aspsine.irecyclerview.view.RecyclerViewHolder;
import com.aspsine.irecyclerview.view.RecyclerAdapter;
import com.bumptech.glide.Glide;

/**
 * @author lyricgan
 * @description list adapter for images
 * @time 2016/9/6 14:44
 */
public class RefreshListAdapter extends RecyclerAdapter<Image> {

    public RefreshListAdapter(Context context) {
        super(context, R.layout.item_refresh_list);
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, Image item) {
        ImageView ivItemImage = holder.getView(R.id.iv_item_image);
        Glide.with(getContext()).load(item.image).placeholder(R.mipmap.superman).dontAnimate().into(ivItemImage);
    }
}
