package com.aspsine.irecyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.R;

/**
 * @author lyricgan
 * @description view for error tips
 * @time 2016/8/11 17:35
 */
public class ErrorView extends FrameLayout {
    private TextView tv_error_tips;
    private OnRetryListener mOnRetryListener;

    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        View view = View.inflate(getContext(), R.layout.layout_irecyclerview_error, this);
        tv_error_tips = (TextView) view.findViewById(R.id.tv_error_tips);

        tv_error_tips.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry(ErrorView.this);
                }
            }
        });
    }

    public void setMessage(int textId) {
        tv_error_tips.setText(textId);
    }

    public void setMessage(String text) {
        tv_error_tips.setText(text);
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.mOnRetryListener = listener;
    }

    public interface OnRetryListener {

        void onRetry(ErrorView view);
    }
}
