package com.dima.blogmobile.local.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Marks")
public class DbMark extends Model {

    public static final String COLUMN_ID = "markId";
    public static final String COLUMN_NAME = "markName";

    @Column(name = COLUMN_ID)
    private long markId;
    @Column(name = COLUMN_NAME)
    private String name;

    public long getMarkId() {
        return markId;
    }

    public void setMarkId(long markId) {
        this.markId = markId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
