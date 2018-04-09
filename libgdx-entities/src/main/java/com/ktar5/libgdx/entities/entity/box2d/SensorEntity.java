package com.ktar5.libgdx.entities.entity.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.ktar5.libgdx.entities.entity.box2d.datastore.BodyDatastore;
import com.ktar5.libgdx.entities.entity.box2d.datastore.SensorDatastore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SensorEntity extends PhysEntity {

    public SensorEntity(float height, float width) {
        super(height, width);
    }

    @Override
    public void reset() {
        super.reset();
    }

    protected abstract void entityEnters(PhysEntity entity, Contact contact);

    protected abstract void entityLeaves(PhysEntity entity, Contact contact);

    @Override
    public BodyDatastore initializeBodyDatastore() {
        return new SensorDatastore() {
            @Override
            public void entityEnter(PhysEntity entity, Contact contact) {
                entityEnters(entity, contact);
            }

            @Override
            public void entityLeave(PhysEntity entity, Contact contact) {
                entityLeaves(entity, contact);
            }
        };
    }
}
