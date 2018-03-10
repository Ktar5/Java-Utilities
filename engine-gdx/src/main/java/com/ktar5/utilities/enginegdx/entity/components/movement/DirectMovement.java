package com.ktar5.utilities.enginegdx.entity.components.movement;

public class DirectMovement extends Movement {

    public DirectMovement(float speed) {
        super(speed);
    }

    @Override
    protected void calculateMovement(float dTime) {
        velocity = input.scl(speed);
        normalizeVelocity();
    }
}
