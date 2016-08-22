package com.aspsine.irecyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.aspsine.irecyclerview.R;

/**
 * @author lyricgan
 * @description
 * @time 2016/8/11 17:36
 */
public class LoadingView extends FrameLayout {

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        View.inflate(getContext(), R.layout.layout_irecyclerview_loading, this);
    }
}
