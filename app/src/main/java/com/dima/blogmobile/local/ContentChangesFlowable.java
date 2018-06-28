package com.dima.blogmobile.local;

import com.dima.blogmobile.utils.Logger;

import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;


public class ContentChangesFlowable {
    private static final String LOG_TAG = Logger.getClassTag(ContentChangesFlowable.class);

    private static final PublishProcessor<SyncEvent> BUS = PublishProcessor.create();

    public static void send(SyncEvent o) {
        BUS.onNext(o);
    }

    private static Flowable<SyncEvent> toFlowable() {
        return BUS.doOnNext(event -> Logger.d(LOG_TAG, "Event fired:" + event));
    }

    private static Flowable<SyncEvent> createInitWith(SyncEvent event, boolean initWith) {
        if (initWith) {
            return Flowable.just(event);
        } else {
            return Flowable.empty();
        }
    }

    public static Flowable<SyncEvent> sync(SyncEvent syncEvent, boolean startWithEvent) {
        return toFlowable().filter(event -> event == syncEvent)
                .startWith(createInitWith(syncEvent, startWithEvent));
    }

    public static Flowable<SyncEvent> posts(boolean startWithEvent) {
        return sync(SyncEvent.POST, startWithEvent);
    }

    public static Flowable<SyncEvent> users(boolean startWithEvent) {
        return sync(SyncEvent.USER, startWithEvent);
    }

    public static Flowable<SyncEvent> comments(boolean startWithEvent) {
        return sync(SyncEvent.COMMENT, startWithEvent);
    }
}
