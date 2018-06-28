package com.dima.blogmobile.presenters;

import com.dima.blogmobile.BaseTest;
import com.dima.blogmobile.RxImmediateSchedulerRule;
import com.dima.blogmobile.request.LoginRequest;
import com.dima.blogmobile.rest.BlogMobileManager;
import com.dima.blogmobile.ui.login.LoginPresenter;
import com.dima.blogmobile.ui.login.LoginView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Completable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest extends BaseTest {

    @Rule
    public RxImmediateSchedulerRule rule = new RxImmediateSchedulerRule();

    LoginPresenter presenter;

    @Mock
    BlogMobileManager blogMobileManager;

    @Mock
    LoginView loginView;


    @Before
    public void setUp() {
        super.setUp();

        presenter = new LoginPresenter(blogMobileManager);
        presenter.attachView(loginView);
    }

    @Test
    public void loginUser_success() throws Exception {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName("Name");
        loginRequest.setPassword("1234");

        when(blogMobileManager.getLogin(loginRequest)).thenReturn(Completable.complete());

        presenter.getLogin(loginRequest);

        verify(loginView).onAuthorizationSuccess();
        verify(loginView, never()).showError(new Throwable());

    }

    @Test
    public void loginUser_fail() throws Exception {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName("Name");
        loginRequest.setPassword("1234");

        when(blogMobileManager.getLogin(loginRequest)).thenReturn(Completable.error(new Throwable()));

        presenter.getLogin(loginRequest);

        verify(loginView, never()).onAuthorizationSuccess();
    }

}
