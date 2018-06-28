package com.dima.blogmobile.ui.list;

import com.dima.blogmobile.common.mvp.MvpView;
import com.dima.blogmobile.local.entity.DbPost;

import java.util.List;

public interface PostListView extends MvpView<PostListPresenter> {

    void onPostsLoaded(List<DbPost> postsList);

    void onPostSent();
}
