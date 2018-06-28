package com.dima.blogmobile.rest.retrofit;

import com.dima.blogmobile.request.PostRequest;
import com.dima.blogmobile.request.LoginRequest;
import com.dima.blogmobile.response.comments.CommentResponse;
import com.dima.blogmobile.response.login.LoginResponse;
import com.dima.blogmobile.response.marks.MarkResponse;
import com.dima.blogmobile.response.posts.PostResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BlogMobileService {

    //Login
    @POST("security/login")
    Completable getLogin(@Body LoginRequest loginRequest);
//    Completable getLogin(@Body LoginRequest loginRequest);

    //Posts
    @GET("posts")
    Single<List<PostResponse>> getPosts();

    @GET("posts/{postId}")
    Single<PostResponse> getPostById(@Path("postId") long postId);

    @POST("posts")
    Single<PostResponse> sendPost(@Body PostRequest postRequest);

    @GET("posts/{postId}/marks")
    Single<List<MarkResponse>> getMarksForPost(@Path("postId") long postId);

    @GET("posts/marks/{postId}")
    Single<List<PostResponse>> getPostsWithCurrentMark(@Path("postId") long postId);

    //Marks

    @GET("marks")
    Single<List<MarkResponse>> getMarks();

    @GET("marks/{markId}")
    Single<MarkResponse> getMarkById(@Path("markId") long markId);

    //Comments

    @GET("comments")
    Single<List<CommentResponse>> getComments();

    @GET("comments/{commentId}")
    Single<CommentResponse> getCommentById(@Path("commentId") long commentId);

    @GET("comments/posts/{postId}")
    Single<List<CommentResponse>> getCommentsForPost(@Path("postId") long postId);

}
