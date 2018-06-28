package com.dima.blogmobile.ui.login;

import android.support.annotation.VisibleForTesting;

import com.dima.blogmobile.Injector;
import com.dima.blogmobile.common.presenter.BasePresenter;
import com.dima.blogmobile.request.LoginRequest;
import com.dima.blogmobile.rest.BlogMobileManager;
import com.dima.blogmobile.utils.Logger;

public class LoginPresenter extends BasePresenter<LoginView> {

    private final String LOG_TAG = Logger.getClassTag(LoginPresenter.class);

    private BlogMobileManager blogMobileManager = Injector.getBlogMobileManager();

    public LoginPresenter() {
    }

    @VisibleForTesting
    public LoginPresenter(BlogMobileManager blogMobileManager) {
        this.blogMobileManager = blogMobileManager;
    }

    public void getLogin(LoginRequest loginRequest) {
        addSubscription(blogMobileManager.getLogin(loginRequest)
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessGetLogin, this::onErrorGetLogin)
        );
    }

    private void onSuccessGetLogin() {
        performOnView(LoginView::onAuthorizationSuccess);
    }

    private void onErrorGetLogin(Throwable throwable) {
        Logger.t(LOG_TAG, throwable);
        performOnView(view -> view.showError(throwable));
    }

}
