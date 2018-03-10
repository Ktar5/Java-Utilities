package com.ktar5.utilities.enginegdx.entity.datastore;

import com.badlogic.gdx.physics.box2d.Contact;
import com.ktar5.utilities.enginegdx.entity.Entity;

public abstract class SensorDatastore implements BodyDatastore<SensorDatastore> {

    public abstract void entityEnter(Entity entity, Contact contact);

    public abstract void entityLeave(Entity entity, Contact contact);

    @Override
    public SensorDatastore getThis() {
        return this;
    }
}
