package com.ktar5.libgdx.entities.entity;

import com.ktar5.libgdx.entities.components.Health;
import lombok.Getter;

@Getter
public abstract class LivingEntity extends EntityBase implements ILivingEntity {
    public final Health health;

    public LivingEntity(int maxHealth, float height, float width) {
        super(height, width);
        this.health = new Health(maxHealth, maxHealth, this);
    }

}
