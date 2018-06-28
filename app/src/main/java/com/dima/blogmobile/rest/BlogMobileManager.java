package com.dima.blogmobile.rest;

import com.dima.blogmobile.local.ContentChangesFlowable;
import com.dima.blogmobile.local.SyncEvent;
import com.dima.blogmobile.local.dao.CommentDAO;
import com.dima.blogmobile.local.dao.MarkDAO;
import com.dima.blogmobile.local.dao.PostDAO;
import com.dima.blogmobile.local.dao.UserDAO;
import com.dima.blogmobile.request.LoginRequest;
import com.dima.blogmobile.request.PostRequest;
import com.dima.blogmobile.rest.retrofit.ApiFactory;
import com.dima.blogmobile.rest.retrofit.BlogMobileService;
import com.dima.blogmobile.utils.Logger;
import com.dima.blogmobile.utils.ResponseUtil;

import io.reactivex.Completable;

public class BlogMobileManager {
    private static final String LOG_TAG = Logger.getClassTag(BlogMobileManager.class);

    private final BlogMobileService blogMobileService;
    private UserDAO userDAO = new UserDAO();
    private PostDAO postDAO = new PostDAO();
    private MarkDAO markDAO = new MarkDAO();
    private CommentDAO commentDAO = new CommentDAO();

    public BlogMobileManager(BlogMobileService blogMobileService) {
        this.blogMobileService = blogMobileService;
    }

    public Completable getLogin(LoginRequest loginRequest) {
        return ApiFactory.getBlogMobileService().getLogin(loginRequest);
    }

    public Completable getPosts() {
        return ApiFactory.getBlogMobileService().getPosts()
                .doOnSuccess(postResponses -> {
                    postDAO.savePosts(ResponseUtil.getPostsFromResponse(postResponses));
                    ContentChangesFlowable.send(SyncEvent.POST);
                }).toCompletable();
    }

    public Completable getPostById(long postId) {
        return ApiFactory.getBlogMobileService().getPostById(postId)
                .doOnSuccess(postResponse -> {
                    postDAO.savePost(ResponseUtil.getPostFromResponse(postResponse));
                    ContentChangesFlowable.send(SyncEvent.POST);
                }).toCompletable();
    }

    public Completable getCommentsForPostById(long postId) {
        return ApiFactory.getBlogMobileService().getCommentsForPost(postId)
                .doOnSuccess(commentResponses -> {
                    commentDAO.saveComments(ResponseUtil.getCommentsFromResponse(commentResponses, postId));
                    ContentChangesFlowable.send(SyncEvent.COMMENT);
                }).toCompletable();
    }

    public Completable sendPost(PostRequest postRequest) {
        return ApiFactory.getBlogMobileService().sendPost(postRequest)
                .doOnSuccess(postResponse -> {
                    postDAO.savePost(ResponseUtil.getPostFromResponse(postResponse));
                    ContentChangesFlowable.send(SyncEvent.POST);
                }).toCompletable();
    }
}
