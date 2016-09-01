package com.aspsine.irecyclerview.demo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.aspsine.irecyclerview.demo.R;
import com.aspsine.irecyclerview.view.ErrorView;
import com.aspsine.irecyclerview.view.RefreshListView;

/**
 * @author lyricgan
 * @description
 * @time 16/8/20
 */
public class RefreshActivity extends AppCompatActivity {
    private RefreshListView refreshListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        refreshListView = (RefreshListView) findViewById(R.id.refresh_list_view);

        initialize();
    }

    private void initialize() {
        refreshListView.setOnErrorRetryListener(new ErrorView.OnRetryListener() {
            @Override
            public void onRetry(ErrorView view) {
                refreshListView.showLoading();
                showMessage(getString(R.string.empty_no_data));
            }
        });
        refreshListView.showLoading();
        showMessage("");
    }

    private void showMessage(final String message) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(message)) {
                    refreshListView.showError();
                } else {
                    refreshListView.showError(message);
                }
            }
        }, 2000);
    }
}
