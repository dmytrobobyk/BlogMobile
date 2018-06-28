package com.dima.blogmobile.response.marks;

import com.dima.blogmobile.response.ServerResponse;
import com.google.gson.annotations.SerializedName;

public class MarkResponse extends ServerResponse {

    @SerializedName("id")
    private long markId;
    @SerializedName("name")
    private String markName;

    public long getMarkId() {
        return markId;
    }

    public void setMarkId(long markId) {
        this.markId = markId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    @Override
    public boolean isItemValid() {
        return markId != 0 && !markName.isEmpty();
    }
}
