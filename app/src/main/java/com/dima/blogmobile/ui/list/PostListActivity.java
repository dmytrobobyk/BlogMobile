package com.dima.blogmobile.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.dima.blogmobile.R;
import com.dima.blogmobile.common.mvp.BaseMvpActivity;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.ui.details.PostDetailsActivity;
import com.dima.blogmobile.ui.list.adapter.PostsAdapter;
import com.dima.blogmobile.ui.list.dialog.CreatePostDialog;
import com.dima.blogmobile.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostListActivity extends BaseMvpActivity<PostListPresenter> implements PostListView, PostsAdapter.OnPostItemClickListener {

    @BindView(R.id.postsRecyclerView)
    RecyclerView postsRecyclerView;
    @BindView(R.id.itemsLayout)
    RelativeLayout itemsLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.addFAB)
    FloatingActionButton addFAB;

    private PostsAdapter postsAdapter;
    private CreatePostDialog dialog;


    public static Intent newInstance(Context context) {
        return new Intent(context, PostListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        setUnbinder(ButterKnife.bind(this));

        initToolbar();
        setToolbarTitle(getString(R.string.activity_posts_list_toolbar_title));
        initRecyclerView();
        initAdapter();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getPresenter().registerForUpdates();
        getPresenter().getPosts();
        showProgressBar(true);
    }

    private void initRecyclerView() {
        postsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        postsRecyclerView.setLayoutManager(llm);
    }

    private void initAdapter() {
        postsAdapter = new PostsAdapter();
        postsAdapter.setOnPostItemClickListener(this);
        postsRecyclerView.setAdapter(postsAdapter);
    }

    @Override
    public void onPostsLoaded(List<DbPost> postsList) {
        showProgressBar(false);
        postsAdapter.replaceAll(postsList);
    }

    @Override
    public void onPostSent() {
        ToastUtil.makeToast(R.string.post_sent_successfully);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onPostItemClicked(DbPost dbPost) {
        startActivity(PostDetailsActivity.newInstance(this, dbPost.getPostId()));
    }

    @OnClick(R.id.addFAB)
    protected void onAddFABClicked() {
        FragmentManager manager = getSupportFragmentManager();
        dialog = new CreatePostDialog();
        dialog.setPostListActivity(this);
        dialog.show(manager, null);
    }

    private void showProgressBar(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            itemsLayout.setVisibility(View.GONE);
            addFAB.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            itemsLayout.setVisibility(View.VISIBLE);
            addFAB.setEnabled(true);
        }
    }

    @Override
    public void showError(Throwable throwable) {
        ToastUtil.makeToast(R.string.post_loading_failed);
        showProgressBar(false);
        if (dialog != null && dialog.isVisible()) {
            dialog.dismiss();
        }
    }

    @Override
    public PostListPresenter createPresenter() {
        return new PostListPresenter();
    }
}
