package com.ktar5.utilities.enginegdx.entity.living;

import com.ktar5.utilities.common.annotations.CallSuper;
import com.ktar5.utilities.enginegdx.entity.Entity;
import com.ktar5.utilities.enginegdx.entity.components.Health;
import com.ktar5.utilities.enginegdx.entity.datastore.BodyDatastore;
import com.ktar5.utilities.enginegdx.entity.datastore.EntityDatastore;
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

    @Override @CallSuper
    public void update(float dTime) {
        super.update(dTime);
    }

    public abstract boolean isPlayer();

}
