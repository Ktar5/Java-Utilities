package com.ktar5.libgdx.entities.entity;

import com.badlogic.gdx.physics.box2d.Contact;
import com.ktar5.libgdx.entities.entity.datastore.BodyDatastore;
import com.ktar5.libgdx.entities.entity.datastore.SensorDatastore;
import entities.entity.datastore.BodyDatastore;
import entities.entity.datastore.SensorDatastore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SensorEntity extends Entity {

    public SensorEntity(float height, float width) {
        super(height, width);
    }

    @Override
    public void reset() {
        super.reset();
    }

    protected abstract void entityEnters(Entity entity, Contact contact);

    protected abstract void entityLeaves(Entity entity, Contact contact);

    @Override
    public BodyDatastore initializeBodyDatastore() {
        return new SensorDatastore() {
            @Override
            public void entityEnter(Entity entity, Contact contact) {
                entityEnters(entity, contact);
            }

            @Override
            public void entityLeave(Entity entity, Contact contact) {
                entityLeaves(entity, contact);
            }
        };
    }
}
