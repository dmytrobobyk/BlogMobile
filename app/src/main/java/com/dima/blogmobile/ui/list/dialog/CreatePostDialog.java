package com.dima.blogmobile.ui.list.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dima.blogmobile.R;
import com.dima.blogmobile.request.PostRequest;
import com.dima.blogmobile.ui.list.PostListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePostDialog extends android.support.v4.app.DialogFragment {

    @BindView(R.id.postTitleTextView)
    EditText postTitleTextView;
    @BindView(R.id.postInfoTextView)
    EditText postInfoTextView;

    private PostListActivity postListActivity;

    public void setPostListActivity(PostListActivity postListActivity) {
        this.postListActivity = postListActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_new_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.cancelPost)
    protected void cancelPost() {
        this.dismiss();
    }

    @OnClick(R.id.sendPost)
    protected void sendPost() {
        PostRequest postRequest = new PostRequest();
        postRequest.setPostTitle(postTitleTextView.getText().toString());
        postRequest.setPostText(postInfoTextView.getText().toString());
        postListActivity.getPresenter().sendPost(postRequest);
    }
}
