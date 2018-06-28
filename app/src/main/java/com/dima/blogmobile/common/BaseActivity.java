package com.dima.blogmobile.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.dima.blogmobile.R;

import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dima on 19.06.18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView;

    @Nullable
    private Unbinder unbinder;

    @Nullable
    private CompositeDisposable subscriptions;

    protected final void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriptions != null) {
            subscriptions.dispose();
            subscriptions = null;
        }
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    protected final void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleTextView = (TextView) findViewById(R.id.toolbarTitle);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected void setToolbarTitle(String title) {
        if (toolbar != null && titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    protected void setToolbarTitle(@StringRes int titleRes) {
        if (toolbar != null && titleTextView != null) {
            titleTextView.setText(getString(titleRes));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
