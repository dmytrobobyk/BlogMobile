package com.dima.blogmobile.common.adapter;


import java.util.List;

/**
 * Created by dima on 19.06.18.
 */

public interface RecyclerViewAdapter<T> {

    void addAll(List<T> items);

    void replaceAll(List<T> items);

    T getItem(int position);

    List<T> getItems();
}
