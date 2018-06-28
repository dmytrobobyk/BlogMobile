package com.dima.blogmobile.ui.login;

import com.dima.blogmobile.common.mvp.MvpView;

public interface LoginView extends MvpView<LoginPresenter> {

    void onAuthorizationSuccess();

}
