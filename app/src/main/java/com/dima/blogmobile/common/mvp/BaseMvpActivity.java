package com.dima.blogmobile.common.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.dima.blogmobile.common.BaseActivity;
import com.dima.blogmobile.common.presenter.Presenter;


/**
 * Created by dima on 19.06.18.
 */

public abstract class BaseMvpActivity<P extends Presenter> extends BaseActivity implements MvpView<P> {

    private P presenter;

    @CallSuper
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        presenter = createPresenter();
        presenter.attachView(this);
    }

    public P getPresenter() {
        return presenter;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.detachView();
        }
    }
}
