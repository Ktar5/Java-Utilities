package com.ktar5.utilities.libgdx.debug;

public interface Debuggable {

    public void debug();

    public default void setDebug(boolean value){
        Debug.setDebug(this.getClass(), value);
    }

}
