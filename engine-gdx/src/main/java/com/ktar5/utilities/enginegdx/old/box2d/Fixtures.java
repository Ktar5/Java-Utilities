package com.ktar5.utilities.enginegdx.old.box2d;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.ktar5.utilities.libgdx.Const;

public class Fixtures {

    public static FixtureDef customFixtureDef(Shape shape, float density, float friction, float restitution) {
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = density;
        fd.friction = friction;
        fd.restitution = restitution;

        return fd;
    }

    public static FixtureDef stdFixtureDef(Shape shape) {
        return customFixtureDef(shape, Const.STD_FIXTURE_DENSITY, Const.STD_FIXTURE_FRICTION, Const.STD_FICTURE_RESTITUTION);
    }

    public static FixtureDef sensorFixture(Shape shape) {
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;

        return fd;
    }

    public static FixtureDef playerFixtureDef() {
        PolygonShape plrShape = new PolygonShape();
        plrShape.setAsBox(.5f, 1f);
        return stdFixtureDef(plrShape);
    }
}
