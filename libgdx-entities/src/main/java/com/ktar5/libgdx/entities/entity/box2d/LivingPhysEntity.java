package com.ktar5.libgdx.entities.entity.box2d;

import com.ktar5.utilities.libgdx.entities.components.Health;
import com.ktar5.libgdx.entities.entity.box2d.datastore.BodyDatastore;
import com.ktar5.libgdx.entities.entity.box2d.datastore.EntityDatastore;
import com.ktar5.libgdx.entities.entity.ILivingEntity;

public abstract class LivingPhysEntity extends PhysEntity implements ILivingEntity {
    private final Health health;

    public LivingPhysEntity(int maxHealth, float height, float width) {
        super(height, width);
        this.health = new Health(maxHealth, maxHealth, this);
    }

    @Override
    public BodyDatastore initializeBodyDatastore() {
        return new EntityDatastore(this);
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);
    }

}
