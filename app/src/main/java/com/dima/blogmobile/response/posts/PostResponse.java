package com.dima.blogmobile.response.posts;

import com.dima.blogmobile.response.ServerResponse;
import com.google.gson.annotations.SerializedName;

public class PostResponse extends ServerResponse {

    @SerializedName("id")
    private long postId;
    @SerializedName("title")
    private String title;
    @SerializedName("text")
    private String text;
    @SerializedName("datePublic")
    private String datePublic;

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

    public String getDatePublic() {
        return datePublic;
    }

    public void setDatePublic(String datePublic) {
        this.datePublic = datePublic;
    }

    @Override
    public boolean isItemValid() {
        return postId != 0 && !title.isEmpty() && !text.isEmpty();
    }
}
