package com.dima.blogmobile.local.entity;

import android.support.annotation.Nullable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Posts")
public class DbPost extends Model {

    public static final String COLUMN_ID = "postId";
    public static final String COLUMN_TITLE = "postTitle";
    public static final String COLUMN_TEXT = "postText";
    public static final String COLUMN_DATE = "postDatePublic";

    @Column(name = COLUMN_ID)
    private long postId;
    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_TEXT)
    private String text;
    @Column(name = COLUMN_DATE)
    private long datePublic;
    @Nullable
    private List<DbComment> commentList;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<DbComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<DbComment> commentList) {
        this.commentList = commentList;
    }
}
