package com.dima.blogmobile.common;

import java.util.NoSuchElementException;

import io.reactivex.annotations.Nullable;

/**
 * Created by dima on 19.06.18.
 */

public final class Optional<M> {

    private final M optional;

    public Optional(@Nullable M optional) {
        this.optional = optional;
    }

    public boolean isEmpty() {
        return this.optional == null;
    }

    public M get() {
        if (optional == null) {
            throw new NoSuchElementException("No value present");
        }
        return optional;
    }
}