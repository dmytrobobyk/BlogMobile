package com.dima.blogmobile.local.dao;

import android.support.annotation.Nullable;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.dima.blogmobile.common.Optional;
import com.dima.blogmobile.local.entity.DbUser;
import com.dima.blogmobile.utils.Logger;

import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class UserDAO {
    private static final String LOG_TAG = Logger.getClassTag(UserDAO.class);

    @Nullable
    private DbUser getUserById(long userId) {
        return new Select().from(DbUser.class)
                .where(DbUser.COLUMN_ID + " = ?", userId)
                .executeSingle();
    }

    @NonNull
    public Single<Optional<DbUser>> getUserByIdAsync(long userId) {
        return Single.fromCallable(() -> new Optional<>(getUserById(userId)));
    }


    @Nullable
    private DbUser getUserByName(String userName) {
        return new Select().from(DbUser.class)
                .where(DbUser.COLUMN_NAME + " = ?", userName)
                .executeSingle();
    }

    @NonNull
    public Single<Optional<DbUser>> getUserByNameAsync(String userName) {
        return Single.fromCallable(() -> new Optional<>(getUserByName(userName)));
    }

    @Nullable
    private List<DbUser> getUsers() {
        return new Select().from(DbUser.class).execute();
    }

    @NonNull
    public Flowable<Optional<List<DbUser>>> getUsersAsync() {
        return Flowable.fromCallable(() -> new Optional<>(getUsers()));
    }

    public void saveUser(DbUser user) {
        DbUser dbUser = getUserById(user.getUserId());
        if (dbUser == null) {
            Logger.d(LOG_TAG, "User is null. Creating new one");
            dbUser = new DbUser();
        }
        dbUser.setUserId(user.getUserId());
        dbUser.setUserName(user.getUserName());

        dbUser.save();
    }

    public void saveUsers(List<DbUser> users) {
        ActiveAndroid.beginTransaction();
        try {
            for (DbUser dbUser : users) {
                saveUser(dbUser);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
