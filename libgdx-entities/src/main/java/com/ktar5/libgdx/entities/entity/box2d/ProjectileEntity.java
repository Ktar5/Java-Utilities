package com.ktar5.libgdx.entities.entity.box2d;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ktar5.libgdx.entities.entity.IEntity;
import com.ktar5.libgdx.entities.entity.box2d.datastore.BodyDatastore;
import com.ktar5.libgdx.entities.entity.box2d.datastore.ProjectileDatastore;

public abstract class ProjectileEntity extends PhysEntity {
    public ProjectileEntity(float height, float width) {
        super(height, width);
    }

    @Override
    protected BodyDef initializeBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(0, 0);
        return bodyDef;
    }

    protected abstract void onHit(IEntity entity);

    @Override
    public BodyDatastore initializeBodyDatastore() {
        return new ProjectileDatastore(this) {
            @Override
            public void onHit(IEntity hit) {
                onHit(hit);
            }
        };
    }

}
