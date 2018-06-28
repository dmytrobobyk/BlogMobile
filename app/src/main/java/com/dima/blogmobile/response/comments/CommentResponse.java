package com.dima.blogmobile.response.comments;

import com.dima.blogmobile.response.ServerResponse;
import com.google.gson.annotations.SerializedName;

public class CommentResponse extends ServerResponse {

    @SerializedName("id")
    private long commentId;
    @SerializedName("author")
    private String author;
    @SerializedName("text")
    private String text;
    @SerializedName("datePublic")
    private String datePublic;

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

    public String getDatePublic() {
        return datePublic;
    }

    public void setDatePublic(String datePublic) {
        this.datePublic = datePublic;
    }

    @Override
    public boolean isItemValid() {
        return commentId != 0 && !text.isEmpty();
    }
}
