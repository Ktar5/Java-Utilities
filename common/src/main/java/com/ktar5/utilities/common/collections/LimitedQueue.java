package com.ktar5.utilities.common.collections;

import com.ktar5.utilities.annotation.callsuper.CallSuper;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {

    protected int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    @CallSuper
    public boolean add(E o) {
        super.addFirst(o);
        while (size() > limit) {
            super.removeLast();
        }
        return true;
    }
}