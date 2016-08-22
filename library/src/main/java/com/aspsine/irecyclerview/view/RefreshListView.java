package com.aspsine.irecyclerview.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.R;

/**
 * @author lyricgan
 * @description 包含下拉刷新列表视图，空信息提示视图和加载视图
 * @time 2016/8/11 15:35
 */
public class RefreshListView extends FrameLayout {
    private IRecyclerView recyclerView;
    private EmptyView emptyView;
    private LoadingView loadingView;

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        View view = View.inflate(getContext(), R.layout.layout_irecyclerview_list_view, this);
        recyclerView = (IRecyclerView) view.findViewById(R.id.recycler_view);
        emptyView = (EmptyView) view.findViewById(R.id.empty_view);
        loadingView = (LoadingView) view.findViewById(R.id.loading_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showContent();
    }

    public IRecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        getRecyclerView().setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        getRecyclerView().setIAdapter(adapter);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        getRecyclerView().setOnRefreshListener(listener);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        getRecyclerView().setOnLoadMoreListener(listener);
    }

    public void setRefreshing(boolean refreshing) {
        getRecyclerView().setRefreshing(refreshing);
    }

    public void setRefreshEnabled(boolean enabled) {
        getRecyclerView().setRefreshEnabled(enabled);
    }

    public void setLoadMoreEnabled(boolean enabled) {
        getRecyclerView().setLoadMoreEnabled(enabled);
    }

    public void setRefreshHeaderView(View refreshHeaderView) {
        getRecyclerView().setRefreshHeaderView(refreshHeaderView);
    }

    public void setRefreshHeaderView(@LayoutRes int refreshHeaderLayoutRes) {
        getRecyclerView().setRefreshHeaderView(refreshHeaderLayoutRes);
    }

    public void setLoadMoreFooterView(View loadMoreFooterView) {
        getRecyclerView().setLoadMoreFooterView(loadMoreFooterView);
    }

    public void setLoadMoreFooterView(@LayoutRes int loadMoreFooterLayoutRes) {
        getRecyclerView().setLoadMoreFooterView(loadMoreFooterLayoutRes);
    }

    public RefreshHeaderView getRefreshHeaderView() {
        return (RefreshHeaderView) getRecyclerView().getRefreshHeaderView();
    }

    public LoadMoreFooterView getLoadMoreFooterView() {
        return (LoadMoreFooterView) getRecyclerView().getLoadMoreFooterView();
    }

    public LinearLayout getHeaderContainer() {
        return getRecyclerView().getHeaderContainer();
    }

    public LinearLayout getFooterContainer() {
        return getRecyclerView().getFooterContainer();
    }

    public void addHeaderView(View headerView) {
        getRecyclerView().addHeaderView(headerView);
    }

    public void addFooterView(View footerView) {
        getRecyclerView().addFooterView(footerView);
    }

    public RecyclerView.Adapter getAdapter() {
        return getRecyclerView().getIAdapter();
    }

    public void setRefreshFinalMoveOffset(int refreshFinalMoveOffset) {
        getRecyclerView().setRefreshFinalMoveOffset(refreshFinalMoveOffset);
    }

    public void hideLoadMoreView() {
        showContent();
        getLoadMoreFooterView().hide();
    }

    public boolean isLoadMoreLoading() {
        return getLoadMoreFooterView().isLoading();
    }

    public void showLoadMoreLoading(boolean visible) {
        showContent();
        if (visible) {
            getLoadMoreFooterView().showLoading();
        } else {
            getLoadMoreFooterView().showDefault();
        }
    }

    public void showLoadMoreError() {
        showContent();
        getLoadMoreFooterView().showError();
    }

    public void showLoadMoreEnd() {
        showContent();
        getLoadMoreFooterView().showEnd();
    }

    public void setOnLoadMoreRetryListener(LoadMoreFooterView.OnRetryListener listener) {
        getLoadMoreFooterView().setOnRetryListener(listener);
    }

    public EmptyView getEmptyView() {
        return emptyView;
    }

    public void setOnRetryListener(EmptyView.OnRetryListener listener) {
        getEmptyView().setOnRetryListener(listener);
    }

    public LoadingView getLoadingView() {
        return loadingView;
    }

    public void showContent() {
        getRecyclerView().setVisibility(View.VISIBLE);
        getLoadingView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);
    }

    public void showLoading() {
        getRecyclerView().setVisibility(View.GONE);
        getLoadingView().setVisibility(View.VISIBLE);
        getEmptyView().setVisibility(View.GONE);
    }

    private void showEmptyView() {
        getRecyclerView().setVisibility(View.GONE);
        getLoadingView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.VISIBLE);
    }

    public void showEmpty(int textId) {
        showEmptyView();
        getEmptyView().setEmptyTips(textId);
    }

    public void showEmpty(String text) {
        showEmptyView();
        getEmptyView().setEmptyTips(text);
    }
}
