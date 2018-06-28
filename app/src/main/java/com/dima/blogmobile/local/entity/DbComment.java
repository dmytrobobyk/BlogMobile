package com.dima.blogmobile.local.entity;

import android.support.annotation.Nullable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Comments")
public class DbComment extends Model {

    public static final String COLUMN_ID = "commentId";
    public static final String COLUMN_AUTHOR = "commentAuthor";
    public static final String COLUMN_TEXT = "commentText";
    public static final String COLUMN_DATE = "commentDatePublic";
    public static final String COLUMN_POST_ID = "postId";

    @Column(name = COLUMN_ID)
    private long commentId;
    @Column(name = COLUMN_AUTHOR)
    private String author;
    @Column(name = COLUMN_TEXT)
    private String text;
    @Column(name = COLUMN_DATE)
    private long datePublic;
    @Column(name = COLUMN_POST_ID)
    @Nullable
    private long postId;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDatePublic() {
        return datePublic;
    }

    public void setDatePublic(long datePublic) {
        this.datePublic = datePublic;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
