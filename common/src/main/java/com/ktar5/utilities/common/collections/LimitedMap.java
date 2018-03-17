package com.ktar5.utilities.common.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class LimitedMap<K, V> extends LinkedHashMap<K, V> {

    private int limit;

    public LimitedMap(int limit){
        this.limit = limit;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
        return size() > limit;
    }

}