package com.aspsine.irecyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.aspsine.irecyclerview.R;

/**
 * Created by aspsine on 16/3/14.
 */
public class LoadMoreFooterView extends FrameLayout {
    private Status mStatus;

    private View mLoadingView;
    private View mErrorView;
    private View mDefaultView;
    private View mTheEndView;

    private OnRetryListener mOnRetryListener;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_load_more_footer_view, this, true);
        mLoadingView = findViewById(R.id.loadingView);
        mErrorView = findViewById(R.id.errorView);
        mDefaultView = findViewById(R.id.defaultView);
        mTheEndView = findViewById(R.id.theEndView);

        mErrorView.setOnClickListener(mOnClickListener);
        mDefaultView.setOnClickListener(mOnClickListener);
        setStatus(Status.GONE);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnRetryListener != null) {
                mOnRetryListener.onRetry(LoadMoreFooterView.this);
            }
        }
    };

    public void setOnRetryListener(OnRetryListener listener) {
        this.mOnRetryListener = listener;
    }

    public Status getStatus() {
        return mStatus;
    }

    private void setStatus(Status status) {
        this.mStatus = status;
        change();
    }

    public boolean canLoadMore() {
        return (mStatus == Status.GONE || mStatus == Status.ERROR || mStatus == Status.DEFAULT);
    }

    public void hide() {
        setStatus(Status.GONE);
    }

    public boolean isLoading() {
        return (Status.LOADING == mStatus);
    }

    public void showLoading() {
        if (canLoadMore()) {
            setStatus(Status.LOADING);
        }
    }

    public void showError() {
        setStatus(Status.ERROR);
    }

    public void showDefault() {
        setStatus(Status.DEFAULT);
    }

    public void showEnd() {
        setStatus(Status.THE_END);
    }

    private void change() {
        switch (mStatus) {
            case GONE: {
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mDefaultView.setVisibility(GONE);
                mTheEndView.setVisibility(GONE);
            }
                break;
            case LOADING: {
                mLoadingView.setVisibility(VISIBLE);
                mErrorView.setVisibility(GONE);
                mDefaultView.setVisibility(GONE);
                mTheEndView.setVisibility(GONE);
            }
                break;
            case ERROR: {
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(VISIBLE);
                mDefaultView.setVisibility(GONE);
                mTheEndView.setVisibility(GONE);
            }
                break;
            case DEFAULT: {
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mDefaultView.setVisibility(VISIBLE);
                mTheEndView.setVisibility(GONE);
            }
                break;
            case THE_END: {
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mDefaultView.setVisibility(GONE);
                mTheEndView.setVisibility(VISIBLE);
            }
                break;
        }
    }

    public enum Status {
        GONE, LOADING, ERROR, DEFAULT, THE_END
    }

    public interface OnRetryListener {

        void onRetry(LoadMoreFooterView view);
    }
}
