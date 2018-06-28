package com.dima.blogmobile.request;

import com.google.gson.annotations.SerializedName;

public class PostRequest {

    @SerializedName("title")
    private String postTitle;
    @SerializedName("text")
    private String postText;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
