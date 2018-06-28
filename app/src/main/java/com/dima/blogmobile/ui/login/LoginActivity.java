package com.dima.blogmobile.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.dima.blogmobile.R;
import com.dima.blogmobile.common.mvp.BaseMvpActivity;
import com.dima.blogmobile.request.LoginRequest;
import com.dima.blogmobile.ui.list.PostListActivity;
import com.dima.blogmobile.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.loginEditText)
    EditText loginEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.loginLayout)
    LinearLayout loginLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    LoginRequest loginRequest = new LoginRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUnbinder(ButterKnife.bind(this));

        loginEditText.setText("admin");
        passwordEditText.setText("1111");
    }

    @OnClick(R.id.signInButton)
    public void onSignInButtonClicked() {

        loginRequest.setName(loginEditText.getText().toString());
        loginRequest.setPassword(passwordEditText.getText().toString());

        getPresenter().getLogin(loginRequest);
        showProgressBar(true);
    }

    @Override
    public void onAuthorizationSuccess() {
        startActivity(PostListActivity.newInstance(this));
        showProgressBar(false);
    }

    private void showProgressBar(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(Throwable throwable) {
        ToastUtil.makeToast(R.string.authorization_failed);
        showProgressBar(false);
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
