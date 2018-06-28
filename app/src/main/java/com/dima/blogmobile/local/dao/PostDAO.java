package com.dima.blogmobile.local.dao;

import android.support.annotation.Nullable;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.dima.blogmobile.common.Optional;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.utils.Logger;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class PostDAO {
    private static final String LOG_TAG = Logger.getClassTag(PostDAO.class);

    @Nullable
    private DbPost getPostById(long postId) {
        return new Select().from(DbPost.class)
                .where(DbPost.COLUMN_ID + " = ?", postId)
                .executeSingle();
    }

    @NonNull
    public Single<Optional<DbPost>> getPostByIdAsync(long postId) {
        return Single.fromCallable(() -> new Optional<>(getPostById(postId)));
    }

    @Nullable
    private List<DbPost> getPosts() {
        return new Select().from(DbPost.class).execute();
    }

    @NonNull
    public Flowable<Optional<List<DbPost>>> getPostsAsync() {
        return Flowable.fromCallable(() -> new Optional<>(getPosts()));
    }

    public void savePost(DbPost post) {
        DbPost dbPost = getPostById(post.getPostId());
        if (dbPost == null) {
            Logger.d(LOG_TAG, "Post is null. Creating new one");
            dbPost = new DbPost();
        }
        dbPost.setPostId(post.getPostId());
        dbPost.setTitle(post.getTitle());
        dbPost.setText(post.getText());
        dbPost.setDatePublic(post.getDatePublic());

        dbPost.save();
    }

    public void savePosts(List<DbPost> posts) {
        ActiveAndroid.beginTransaction();
        try {
            for (DbPost dbPost : posts) {
                savePost(dbPost);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
