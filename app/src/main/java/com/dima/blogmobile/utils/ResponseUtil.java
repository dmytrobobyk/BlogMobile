package com.dima.blogmobile.utils;

import com.dima.blogmobile.local.entity.DbComment;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.response.comments.CommentResponse;
import com.dima.blogmobile.response.posts.PostResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ResponseUtil {

    public static DbPost getPostFromResponse(PostResponse postResponse) {
        DbPost dbPost = new DbPost();
        dbPost.setPostId(postResponse.getPostId());
        dbPost.setTitle(postResponse.getTitle());
        dbPost.setText(postResponse.getText());
        dbPost.setDatePublic(DateConvertUtil.dateFromString(postResponse.getDatePublic()));
        return dbPost;
    }

    public static List<DbPost> getPostsFromResponse(List<PostResponse> postResponseList) {
        List<DbPost> dbPostList = new ArrayList<>();
        for (PostResponse postResponse : postResponseList) {
            dbPostList.add(getPostFromResponse(postResponse));
        }
        return dbPostList;
    }

    public static DbComment getCommentFromResponse(CommentResponse commentResponse, long postId) {
        DbComment dbComment = new DbComment();
        dbComment.setCommentId(commentResponse.getCommentId());
        dbComment.setAuthor(commentResponse.getAuthor());
        dbComment.setText(commentResponse.getText());
        dbComment.setDatePublic(DateConvertUtil.dateFromString(commentResponse.getDatePublic()));
        dbComment.setPostId(postId);

        return dbComment;
    }

    public static List<DbComment> getCommentsFromResponse(List<CommentResponse> commentResponseList, long postId) {
        List<DbComment> dbCommentList = new ArrayList<>();
        for (CommentResponse commentResponse : commentResponseList) {
            dbCommentList.add(getCommentFromResponse(commentResponse, postId));
        }
        return dbCommentList;
    }
}
