package com.ktar5.libgdx.entities.entity.datastore;

import entities.entity.Entity;
import entities.entity.living.LivingEntity;
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