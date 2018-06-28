package com.dima.blogmobile;

import android.support.multidex.MultiDexApplication;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.dima.blogmobile.local.LocalStorage;
import com.dima.blogmobile.local.entity.DbComment;
import com.dima.blogmobile.local.entity.DbMark;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.local.entity.DbUser;
import com.dima.blogmobile.rest.BlogMobileManager;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.context = this;
        Injector.localStorage = new LocalStorage();
        Injector.blogMobileManager = createMobileBlogManager();
        initDatabase();

    }

    private BlogMobileManager createMobileBlogManager() {
        return new BlogMobileManager(null);
    }


    private void initDatabase() {
        Configuration configuration = new Configuration.Builder(this)
                .setDatabaseName("blogmobile.db")
                .setDatabaseVersion(1)
                .addModelClass(DbComment.class)
                .addModelClass(DbPost.class)
                .addModelClass(DbMark.class)
                .addModelClass(DbUser.class)
                .create();

        ActiveAndroid.initialize(configuration);
    }
}
