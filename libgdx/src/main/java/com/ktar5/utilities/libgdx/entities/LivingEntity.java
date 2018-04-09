package com.ktar5.utilities.libgdx.entities;

import com.ktar5.utilities.libgdx.entities.components.Health;
import lombok.Getter;

@Getter
public abstract class LivingEntity extends Entity {
    public final Health health;

    public LivingEntity(int maxHealth, float height, float width) {
        super(height, width);
        this.health = new Health(maxHealth, maxHealth, this);
    }

}
