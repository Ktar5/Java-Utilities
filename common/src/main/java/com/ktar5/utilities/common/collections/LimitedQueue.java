package com.ktar5.utilities.common.collections;


import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {

    protected int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.addFirst(o);
        while (size() > limit) { super.removeLast(); }
        return true;
    }
}