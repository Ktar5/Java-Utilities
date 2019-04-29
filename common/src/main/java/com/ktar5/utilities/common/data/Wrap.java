package com.ktar5.utilities.common.data;

public class Wrap<T> {
    private T value;

    public Wrap(T value){
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
