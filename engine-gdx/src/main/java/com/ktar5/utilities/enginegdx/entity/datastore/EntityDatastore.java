package com.ktar5.utilities.enginegdx.entity.datastore;

import com.ktar5.utilities.enginegdx.entity.Entity;
import com.ktar5.utilities.enginegdx.entity.living.LivingEntity;
import lombok.Getter;

public class EntityDatastore implements BodyDatastore<EntityDatastore> {
    @Getter
    protected Entity entity;

    public EntityDatastore(Entity entity) {
        this.entity = entity;
    }

    public boolean isLivingEntity() {
        return this.entity instanceof LivingEntity;
    }

    @Override
    public EntityDatastore getThis() {
        return this;
    }
}
