package com.ktar5.libgdx.entities.entity.box2d.datastore;

import com.ktar5.libgdx.entities.entity.IEntity;
import com.ktar5.libgdx.entities.entity.box2d.ProjectileEntity;
import lombok.Getter;

public abstract class ProjectileDatastore implements BodyDatastore<ProjectileDatastore> {
    @Getter
    protected ProjectileEntity projectile;

    public ProjectileDatastore(ProjectileEntity projectile) {
        this.projectile = projectile;
    }

    public abstract void onHit(IEntity hit);

    @Override
    public ProjectileDatastore getThis() {
        return this;
    }
}