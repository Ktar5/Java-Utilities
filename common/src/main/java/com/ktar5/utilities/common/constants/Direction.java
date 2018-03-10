package com.ktar5.utilities.common.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {
    N(0, 1, 90, 0),
    NE(1, 1, 45, 0),
    E(1, 0, 0, 3),
    SE(1, -1, 315, 1),
    S(0, -1, 270, 1),
    SW(-1, -1, 225, 1),
    W(-1, 0, 180, 2),
    NW(-1, 1, 135, 0);

    public final int x, y;
    public final int rotation;
    public final int animationDirection;

    public static final Direction[]
            ALL = {N, NE, E, SE, S, SW, W, NW},
            CARDINAL = {N, E, S, W},
            ANGLE_SORT_C = {E, N, W, S},
            ANGLE_SORT = {E, NE, N, NW, W, SW, S, SE},
            DIAGNOL = {NE, SE, SW, NW};

    private static final Direction[][] ROTATIONS = {
            {NW, N, NE},
            {W, null, E},
            {SW, S, SE}
    }, CORNERS = {
            {N, NE, E},
            {E, SE, S},
            {S, SW, W},
            {W, NW, N}
    };

    public static Direction leftOrRight(float angle) {
        return angle >= 90 && angle <= 270 ? W : E;
    }

    public static Direction upOrDown(float angle) {
        return angle >= 0 && angle <= 180 ? N : S;
    }

    public static Direction fromAngleCardinal(float angle) {
        if (angle < 0) {
            return fromAngleCardinal(angle + 360);
        }
        return ANGLE_SORT_C[Math.round((angle % 360) / 90) % ANGLE_SORT_C.length];
    }

    public static Direction fromAngle(float angle) {
        if (angle < 0) {
            return fromAngleCardinal(angle + 360);
        }
        return ANGLE_SORT[Math.round((angle % 360) / 45) % ANGLE_SORT.length];
    }

    public Direction left45() {
        int ordinal = this.ordinal() - 1;
        if (ordinal == -1) {
            ordinal = values().length - 1;
        }
        return values()[ordinal];
    }

    public Direction right45() {
        int ordinal = this.ordinal() + 1;
        if (ordinal == values().length - 1) {
            ordinal = 0;
        }
        return values()[ordinal];

    }

    public Direction left90() {
        return left45().left45();
    }

    public Direction right90() {
        return right45().right45();
    }

}
