package com.ktar5.utilities.libgdx;

import org.pmw.tinylog.Logger;

public enum Feature {

    PLAYER_MOVEMENT(true),
    CAMERA_MOVEMENT(true),
    DEBUG(true),
    CONTROLLER(true),
    TESTING(true),

    LOG_STATE_MACHINE(true),
    FIRST_PRESSED_MOVEMENT(true);


    private boolean feature;

    private Feature(boolean feature) {
        this.feature = feature;
    }

    public void set(boolean feature) {
        this.feature = feature;
        Logger.debug("Feature: '" + this.name().toUpperCase() + "' was changed to " + feature);
    }

    public boolean isEnabled() {
        return feature;
    }

    public boolean isDisabled() {
        return !feature;
    }

    public void disable() {
        set(false);
    }

    public void enable() {
        set(true);
    }

}
