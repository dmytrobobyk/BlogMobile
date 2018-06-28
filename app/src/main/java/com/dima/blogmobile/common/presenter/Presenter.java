package com.dima.blogmobile.common.presenter;


import com.dima.blogmobile.common.mvp.MvpView;

/**
 * Created by dima on 19.06.18.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    void onDestroy();
}
