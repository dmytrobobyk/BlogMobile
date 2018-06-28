package com.dima.blogmobile.ui.details;

import android.util.Pair;

import com.dima.blogmobile.Injector;
import com.dima.blogmobile.common.Optional;
import com.dima.blogmobile.common.presenter.BasePresenter;
import com.dima.blogmobile.local.ContentChangesFlowable;
import com.dima.blogmobile.local.dao.CommentDAO;
import com.dima.blogmobile.local.dao.PostDAO;
import com.dima.blogmobile.local.entity.DbComment;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.utils.Logger;

import java.util.List;

import io.reactivex.Single;


public class PostDetailsPresenter extends BasePresenter<PostDetailsView> {

    private final String LOG_TAG = Logger.getClassTag(PostDetailsPresenter.class);

    private PostDAO postDAO = new PostDAO();
    private CommentDAO commentDAO = new CommentDAO();

    public void registerForUpdates(long postId) {
        addSubscription(ContentChangesFlowable.posts(true)
                .flatMapSingle(event -> postDAO.getPostByIdAsync(postId))
                .flatMapSingle(post -> {
                    List<DbComment> comments = commentDAO.getCommentsByPostId(postId);
                    post.get().setCommentList(comments);
                    return Single.fromCallable(() -> post);
                })
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessRegisterForUpdates, this::onErrorRegisterForUpdates)
        );
    }

    private void onSuccessRegisterForUpdates(Optional<DbPost> dbPostOptional) {
        performOnView(view -> view.onPostLoaded(dbPostOptional.get()));
    }

    private void onErrorRegisterForUpdates(Throwable throwable) {
        Logger.t(LOG_TAG, throwable);
        performOnView(view -> view.showError(throwable));
    }


    public void getPostById(long postId) {
        addSubscription(Injector.getBlogMobileManager().getPostById(postId)
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessGetPostById, this::onErrorGetPostById)
        );
    }

    private void onSuccessGetPostById() {
        Logger.d(LOG_TAG, "getPostById successfully");
    }

    private void onErrorGetPostById(Throwable throwable) {
        Logger.t(LOG_TAG, throwable);
        performOnView(view -> view.showError(throwable));
    }

    public void getCommentsForPost(long postId) {
        addSubscription(Injector.getBlogMobileManager().getCommentsForPostById(postId)
                .subscribeOn(Injector.getIoScheduler())
                .observeOn(Injector.getMainScheduler())
                .subscribe(this::onSuccessGetCommentsForPost, this::onErrorGetCommentsForPost)
        );
    }

    private void onSuccessGetCommentsForPost() {
        Logger.d(LOG_TAG, "onSuccessGetCommentsForPost");
    }

    private void onErrorGetCommentsForPost(Throwable throwable) {
        Logger.t(LOG_TAG, throwable);
        performOnView(view -> view.showError(throwable));
    }

}
