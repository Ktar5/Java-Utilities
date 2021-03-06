package com.ktar5.utilities.libgdx.entities.components.movement;

import com.badlogic.gdx.math.Vector2;
import com.ktar5.utilities.libgdx.Const;

public class SetVelocityMovement extends Movement {
    private Vector2 acceleration = new Vector2(0, 0);
    private final float accelFactor = Const.PLAYER_ACCEL_FACTOR * Const.SCALE;
    private float timeAdjAccelFactor, timeAdjDecelFactor;

    public SetVelocityMovement(float speed) {
        super(speed); //kinda ignore
    }

    @Override
    protected void calculateMovement(float dTime) {
        timeAdjAccelFactor = accelFactor * dTime;
        timeAdjDecelFactor = Const.PLAYER_DECEL_FACTOR * dTime;

        //Accelerate
        acceleration.set(input.x * timeAdjAccelFactor, input.y * timeAdjAccelFactor);
        velocity.add(acceleration);

        //Decelerate
        velocity.scl(1 - (timeAdjDecelFactor));

        velocity.clamp(0, Const.PLAYER_MAX_VEL);
    }

}