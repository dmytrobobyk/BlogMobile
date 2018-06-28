package com.dima.blogmobile.common.presenter;

import android.support.annotation.Nullable;

import com.dima.blogmobile.common.mvp.MvpView;
import com.dima.blogmobile.utils.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dima on 19.06.18.
 */

public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {
    private static final String LOG_TAG = Logger.getClassTag(BasePresenter.class);

    @Nullable
    V view;

    private CompositeDisposable subscriptions;

    public BasePresenter() {
        subscriptions = new CompositeDisposable();
    }

    public void addSubscription(Disposable s) {
        if (subscriptions == null || subscriptions.isDisposed()) {
            Logger.d(LOG_TAG, "Composite subscription is null");
        }
        subscriptions.add(s);
    }

    protected interface ActionView<V extends MvpView> {
        void run(V view);
    }

    public void performOnView(ActionView<V> actionView) {
        V view = getView();
        if (view == null) {
            Logger.d(LOG_TAG, "performOnView: view is null");
            return;
        }
        actionView.run(view);
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onDestroy() {
        if (subscriptions != null) {
            subscriptions.dispose();
        }
    }

    @Nullable
    public V getView() {
        return view;
    }
}
