package com.aspsine.irecyclerview.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aspsine.irecyclerview.demo.R;
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
    }

    private void initialize() {

    }
}
