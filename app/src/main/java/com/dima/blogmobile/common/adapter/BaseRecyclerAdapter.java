package com.dima.blogmobile.common.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dima on 19.06.18.
 */

public abstract class BaseRecyclerAdapter<T, V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> implements RecyclerViewAdapter<T> {

    private List<T> items = new ArrayList();

    @Nullable
    public CompositeDisposable subscriptions;

    protected final void addSubscription(@NonNull Disposable s) {
        if (subscriptions != null) {
            subscriptions.add(s);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        subscriptions = new CompositeDisposable();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (subscriptions != null) {
            subscriptions.dispose();
            subscriptions = null;
        }
    }

    @Override
    public void addAll(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public List<T> getItems() {
        return items;
    }
}
