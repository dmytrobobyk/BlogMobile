package com.dima.blogmobile.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dima.blogmobile.R;
import com.dima.blogmobile.common.mvp.BaseMvpActivity;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.ui.details.comments.CommentsAdapter;
import com.dima.blogmobile.ui.list.adapter.PostsAdapter;
import com.dima.blogmobile.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailsActivity extends BaseMvpActivity<PostDetailsPresenter> implements PostDetailsView {

    public static final String POST_ID = "postId";

    @BindView(R.id.postInfoTextView)
    TextView postInfoTextView;
    @BindView(R.id.commentsRecyclerView)
    RecyclerView commentsRecyclerView;
    @BindView(R.id.itemLayout)
    NestedScrollView itemLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private long postId;
    private CommentsAdapter commentsAdapter;

    public static Intent newInstance(Context context, long postId) {
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(POST_ID, postId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        setUnbinder(ButterKnife.bind(this));

        postId = getIntent().getLongExtra(POST_ID, 0);
        initToolbar();
        initRecyclerView();
        initAdapter();
        showProgressBar(true);
    }

    private void initRecyclerView() {
        commentsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        commentsRecyclerView.setLayoutManager(llm);
    }

    private void initAdapter() {
        commentsAdapter = new CommentsAdapter();
        commentsRecyclerView.setAdapter(commentsAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getPresenter().getCommentsForPost(postId);
        getPresenter().getPostById(postId);
        getPresenter().registerForUpdates(postId);
    }

    @Override
    public void onPostLoaded(DbPost dbPost) {
        showProgressBar(false);
        setToolbarTitle(dbPost.getTitle());
        postInfoTextView.setText(dbPost.getText());
        commentsAdapter.replaceAll(dbPost.getCommentList());
    }

    private void showProgressBar(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            itemLayout.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            itemLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(Throwable throwable) {
        showProgressBar(false);
        ToastUtil.makeToast(R.string.some_error);
    }

    @Override
    public PostDetailsPresenter createPresenter() {
        return new PostDetailsPresenter();
    }

}
