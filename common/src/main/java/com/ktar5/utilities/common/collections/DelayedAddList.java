package com.ktar5.utilities.common.collections;

import java.util.ArrayList;

public class DelayedAddList<T> {
    private final ArrayList<T> items;
    private final ArrayList<T> itemsToAdd;

    public DelayedAddList() {
        itemsToAdd = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void add(T item) {
        itemsToAdd.add(item);
    }

    public ArrayList<T> getItems() {
        items.addAll(itemsToAdd);
        itemsToAdd.clear();
        return items;
    }

    public void clearItems() {
        items.clear();
    }
}