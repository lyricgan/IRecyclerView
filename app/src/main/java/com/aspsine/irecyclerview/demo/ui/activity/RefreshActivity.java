package com.aspsine.irecyclerview.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.demo.R;
import com.aspsine.irecyclerview.demo.model.Image;
import com.aspsine.irecyclerview.demo.network.NetworkAPI;
import com.aspsine.irecyclerview.demo.ui.adapter.RefreshListAdapter;
import com.aspsine.irecyclerview.demo.utils.ListUtils;
import com.aspsine.irecyclerview.view.ErrorView;
import com.aspsine.irecyclerview.view.RefreshListView;

import java.util.List;

/**
 * @author lyricgan
 * @description
 * @time 16/8/20
 */
public class RefreshActivity extends AppCompatActivity {
    private RefreshListView refreshListView;
    private RefreshListAdapter mRefreshListAdapter;

    private int mPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        refreshListView = (RefreshListView) findViewById(R.id.refresh_list_view);

        initialize();
    }

    private void initialize() {
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListView.setRefreshing(true);
                refresh();
            }
        });
        refreshListView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(View loadMoreView) {
                if (refreshListView.canLoadMore() && !mRefreshListAdapter.isEmpty()) {
                    refreshListView.showLoadMoreLoading(true);
                    loadMore();
                }
            }
        });
        refreshListView.setOnErrorRetryListener(new ErrorView.OnRetryListener() {
            @Override
            public void onRetry(ErrorView view) {
                refreshListView.showLoading();
                refresh();
            }
        });
        mRefreshListAdapter = new RefreshListAdapter(this);
        refreshListView.setAdapter(mRefreshListAdapter);
        refreshListView.showLoading();
        refresh();
    }

    private void refresh() {
        mPage = 1;
        NetworkAPI.requestImages(mPage, new NetworkAPI.Callback<List<Image>>() {
            @Override
            public void onSuccess(List<Image> images) {
                refreshListView.showContent();
                refreshListView.setRefreshing(false);
                mRefreshListAdapter.clear();
                if (ListUtils.isEmpty(images)) {
                    refreshListView.showEmpty();
                } else {
                    mPage = 2;
                    mRefreshListAdapter.add(images);
                    refreshListView.showLoadMoreLoading(false);
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                refreshListView.showContent();
                refreshListView.setRefreshing(false);
            }
        });
    }

    private void loadMore() {
        NetworkAPI.requestImages(mPage, new NetworkAPI.Callback<List<Image>>() {
            @Override
            public void onSuccess(final List<Image> images) {
                if (ListUtils.isEmpty(images)) {
                    refreshListView.showLoadMoreEnd();
                } else {
                    mPage++;
                    refreshListView.showLoadMoreLoading(false);
                    mRefreshListAdapter.add(images);
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                refreshListView.showLoadMoreError();
            }
        });
    }
}
