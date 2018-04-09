package com.ktar5.libgdx.entities.entity.projectile;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ktar5.libgdx.entities.entity.Entity;
import com.ktar5.libgdx.entities.entity.datastore.BodyDatastore;
import com.ktar5.libgdx.entities.entity.datastore.ProjectileDatastore;

public abstract class ProjectileEntity extends Entity {
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

    protected abstract void onHits(Entity hit);

    @Override
    public BodyDatastore initializeBodyDatastore() {
        return new ProjectileDatastore(this) {
            @Override
            public void onHit(Entity hit) {
                onHits(hit);
            }
        };
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);
    }
}
