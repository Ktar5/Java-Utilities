package com.ktar5.libgdx.entities.entity.living;

import com.ktar5.libgdx.entities.entity.components.Health;
import com.ktar5.libgdx.entities.entity.datastore.BodyDatastore;
import com.ktar5.libgdx.entities.entity.datastore.EntityDatastore;
import entities.entity.Entity;
import entities.entity.components.Health;
import entities.entity.datastore.BodyDatastore;
import entities.entity.datastore.EntityDatastore;
import lombok.Getter;

@Getter
public abstract class LivingEntity extends Entity {
    public final Health health;

    public LivingEntity(int maxHealth, float height, float width) {
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

    public abstract boolean isPlayer();

}
