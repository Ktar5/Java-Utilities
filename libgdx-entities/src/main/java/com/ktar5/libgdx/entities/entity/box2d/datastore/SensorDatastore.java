package com.ktar5.libgdx.entities.entity.box2d.datastore;

import com.badlogic.gdx.physics.box2d.Contact;
import com.ktar5.libgdx.entities.entity.box2d.PhysEntity;

public abstract class SensorDatastore implements BodyDatastore<SensorDatastore> {

    public abstract void entityEnter(PhysEntity entity, Contact contact);

    public abstract void entityLeave(PhysEntity entity, Contact contact);

    @Override
    public SensorDatastore getThis() {
        return this;
    }
}
