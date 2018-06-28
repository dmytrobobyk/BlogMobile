package com.dima.blogmobile.ui.details;

import com.dima.blogmobile.common.mvp.MvpView;
import com.dima.blogmobile.local.entity.DbPost;

public interface PostDetailsView extends MvpView<PostDetailsPresenter> {

    void onPostLoaded(DbPost dbPost);
}
