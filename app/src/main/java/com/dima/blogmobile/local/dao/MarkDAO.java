package com.dima.blogmobile.local.dao;

import android.support.annotation.Nullable;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.dima.blogmobile.common.Optional;
import com.dima.blogmobile.local.entity.DbMark;
import com.dima.blogmobile.utils.Logger;

import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class MarkDAO {
    private static final String LOG_TAG = Logger.getClassTag(MarkDAO.class);

    @Nullable
    private DbMark getMarkById(long postId) {
        return new Select().from(DbMark.class)
                .where(DbMark.COLUMN_ID + " = ?", postId)
                .executeSingle();
    }

    @NonNull
    public Single<Optional<DbMark>> getMarkByIdAsync(long postId) {
        return Single.fromCallable(() -> new Optional<>(getMarkById(postId)));
    }

    @Nullable
    private List<DbMark> getMarks() {
        return new Select().from(DbMark.class).execute();
    }

    @NonNull
    public Flowable<Optional<List<DbMark>>> getMarksAsync() {
        return Flowable.fromCallable(() -> new Optional<>(getMarks()));
    }

    public void saveMark(DbMark mark) {
        DbMark dbMark = getMarkById(mark.getMarkId());
        if (dbMark == null) {
            Logger.d(LOG_TAG, "Mark is null. Creating new one");
            dbMark = new DbMark();
        }
        dbMark.setMarkId(mark.getMarkId());
        dbMark.setName(mark.getName());

        dbMark.save();
    }

    public void savePosts(List<DbMark> marks) {
        ActiveAndroid.beginTransaction();
        try {
            for (DbMark dbMark : marks) {
                saveMark(dbMark);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
