package com.ktar5.libgdx.entities.entity.box2d.datastore;

import com.ktar5.libgdx.entities.entity.box2d.LivingPhysEntity;
import com.ktar5.libgdx.entities.entity.box2d.PhysEntity;
import lombok.Getter;

public class EntityDatastore implements BodyDatastore<EntityDatastore> {
    @Getter
    protected PhysEntity entity;

    public EntityDatastore(PhysEntity entity) {
        this.entity = entity;
    }

    public boolean isLivingEntity() {
        return this.entity instanceof LivingPhysEntity;
    }

    @Override
    public EntityDatastore getThis() {
        return this;
    }
}
