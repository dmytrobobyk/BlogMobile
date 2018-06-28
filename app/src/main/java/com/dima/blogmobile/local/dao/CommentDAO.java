package com.dima.blogmobile.local.dao;

import android.support.annotation.Nullable;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.dima.blogmobile.common.Optional;
import com.dima.blogmobile.local.entity.DbComment;
import com.dima.blogmobile.utils.Logger;

import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class CommentDAO {
    private static final String LOG_TAG = Logger.getClassTag(CommentDAO.class);

    @Nullable
    private DbComment getCommentById(long commentId) {
        return new Select().from(DbComment.class)
                .where(DbComment.COLUMN_ID + " = ?", commentId)
                .executeSingle();
    }

    @NonNull
    public Single<Optional<DbComment>> getCommentByIdAsync(long postId) {
        return Single.fromCallable(() -> new Optional<>(getCommentById(postId)));
    }

    @Nullable
    public List<DbComment> getCommentsByPostId(long postId) {
        return new Select().from(DbComment.class)
                .where(DbComment.COLUMN_POST_ID + " = ?", postId)
                .execute();
    }

    public Flowable<Optional<List<DbComment>>> getCommentsByPostIdAsync(long postId) {
        return Flowable.fromCallable(() -> new Optional<>(getCommentsByPostId(postId)));
    }


    @Nullable
    private List<DbComment> getComments() {
        return new Select().from(DbComment.class).execute();
    }

    @NonNull
    public Flowable<Optional<List<DbComment>>> getCommentsAsync() {
        return Flowable.fromCallable(() -> new Optional<>(getComments()));
    }

    public void saveComment(DbComment comment) {
        DbComment dbComment = getCommentById(comment.getCommentId());
        if (dbComment == null) {
            Logger.d(LOG_TAG, "Comment is null. Creating new one");
            dbComment = new DbComment();
        }
        dbComment.setCommentId(comment.getCommentId());
        dbComment.setAuthor(comment.getAuthor());
        dbComment.setText(comment.getText());
        dbComment.setDatePublic(comment.getDatePublic());
        dbComment.setPostId(comment.getPostId());

        dbComment.save();
    }

    public void saveComments(List<DbComment> comments) {
        ActiveAndroid.beginTransaction();
        try {
            for (DbComment dbComment : comments) {
                saveComment(dbComment);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
