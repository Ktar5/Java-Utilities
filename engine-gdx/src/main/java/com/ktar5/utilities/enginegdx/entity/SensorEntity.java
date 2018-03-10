package com.ktar5.utilities.enginegdx.entity;

import com.badlogic.gdx.physics.box2d.Contact;
import com.ktar5.utilities.common.annotations.CallSuper;
import com.ktar5.utilities.enginegdx.entity.datastore.BodyDatastore;
import com.ktar5.utilities.enginegdx.entity.datastore.SensorDatastore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SensorEntity extends Entity {

    public SensorEntity(float height, float width) {
        super(height, width);
    }

    @Override
    @CallSuper
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