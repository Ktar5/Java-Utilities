package com.ktar5.utilities.common.collections;

import java.util.LinkedList;

public class TailedLimitedQueue<E> extends LimitedQueue<E>{

    private final LinkedList<E> pastItems;

    public TailedLimitedQueue(int limit) {
        super(limit);
        this.pastItems = new LinkedList<>();
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) {
            pastItems.add(0, super.removeLast());
        }
        return true;
    }

    private void addFromPast(){
        if(pastItems.size() != 0){
            super.addLast(pastItems.removeFirst());
        }
    }

    @Override
    public E remove(){
        return remove(size() - 1);
    }

    @Override
    public E remove(int position){
        E returned = super.remove(position);
        addFromPast();
        return returned;
    }


}