package com.ktar5.libgdx.entities.entity.datastore;

import com.ktar5.libgdx.entities.entity.projectile.ProjectileEntity;
import entities.entity.Entity;
import entities.entity.projectile.ProjectileEntity;
import lombok.Getter;

public abstract class ProjectileDatastore implements BodyDatastore<ProjectileDatastore> {
    @Getter
    protected ProjectileEntity projectile;

    public ProjectileDatastore(ProjectileEntity projectile) {
        this.projectile = projectile;
    }

    public abstract void onHit(Entity hit);

    @Override
    public ProjectileDatastore getThis() {
        return this;
    }
}