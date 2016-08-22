package com.aspsine.irecyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.R;

/**
 * @author lyricgan
 * @description
 * @time 2016/8/11 17:35
 */
public class EmptyView extends FrameLayout {
    private TextView tv_empty_tips;
    private OnRetryListener mOnRetryListener;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        View view = View.inflate(getContext(), R.layout.layout_irecyclerview_empty, this);
        tv_empty_tips = (TextView) view.findViewById(R.id.tv_empty_tips);

        tv_empty_tips.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry(EmptyView.this);
                }
            }
        });
    }

    public void setEmptyTips(int textId) {
        tv_empty_tips.setText(textId);
    }

    public void setEmptyTips(String text) {
        tv_empty_tips.setText(text);
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.mOnRetryListener = listener;
    }

    public interface OnRetryListener {

        void onRetry(EmptyView view);
    }
}
