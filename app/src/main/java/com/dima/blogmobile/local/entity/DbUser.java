package com.dima.blogmobile.local.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Users")
public class DbUser extends Model {

    public static final String COLUMN_ID = "userId";
    public static final String COLUMN_NAME = "userName";

    @Column(name = COLUMN_ID)
    private long userId;
    @Column(name = COLUMN_NAME)
    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
