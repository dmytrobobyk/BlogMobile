package com.dima.blogmobile.common.mvp;

import com.dima.blogmobile.common.presenter.Presenter;


/**
 * Created by dima on 19.06.18.
 */

public interface MvpView<P extends Presenter> {

    P createPresenter();

    void showError(Throwable throwable);
}
