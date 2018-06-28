package com.dima.blogmobile.ui.list;

import com.dima.blogmobile.Injector;
import com.dima.blogmobile.common.Optional;
import com.dima.blogmobile.common.presenter.BasePresenter;
import com.dima.blogmobile.local.ContentChangesFlowable;
import com.dima.blogmobile.local.dao.PostDAO;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.request.PostRequest;
import com.dima.blogmobile.utils.Logger;

import java.util.List;

public class PostListPresenter extends BasePresenter<PostListView> {

    private final String LOG_TAG = Logger.getClassTag(PostListPresenter.class);

    private PostDAO postDAO = new PostDAO();

    public void registerForUpdates() {
        addSubscription(ContentChangesFlowable.posts(true)
                .flatMap(syncEvent -> postDAO.getPostsAsync())
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessRegisterForUpdates, this::onErrorRegisterForUpdates)
        );
    }

    private void onSuccessRegisterForUpdates(Optional<List<DbPost>> optionalList) {
        performOnView(view -> view.onPostsLoaded(optionalList.get()));
    }

    private void onErrorRegisterForUpdates(Throwable throwable) {
        Logger.t(LOG_TAG, throwable);
        performOnView(view -> view.showError(throwable));
    }

    public void getPosts() {
        addSubscription(Injector.getBlogMobileManager().getPosts()
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessGetPosts, this::onErrorGetPosts)
        );
    }

    private void onSuccessGetPosts() {
        Logger.d(LOG_TAG, "posts got successfully");
    }

    private void onErrorGetPosts(Throwable throwable) {
        Logger.e(LOG_TAG, (Exception) throwable);
        performOnView(view -> view.showError(throwable));
    }

    public void sendPost(PostRequest postRequest) {
        addSubscription(Injector.getBlogMobileManager().sendPost(postRequest)
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessSendPost, this::onErrorSendPost)
        );
    }

    private void onSuccessSendPost() {
        Logger.d(LOG_TAG, "Post sent successfully");
        performOnView(PostListView::onPostSent);
    }

    private void onErrorSendPost(Throwable throwable) {
        Logger.t(LOG_TAG, throwable);
        performOnView(view -> view.showError(throwable));
    }
}
