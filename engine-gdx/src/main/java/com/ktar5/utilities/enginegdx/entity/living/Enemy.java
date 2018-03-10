package com.ktar5.utilities.enginegdx.entity.living;
import com.ktar5.utilities.common.annotations.CallSuper;

public abstract class Enemy extends LivingEntity {

    public Enemy(int maxHealth, float height, float width) {
        super(maxHealth, height, width);
    }

    @Override
    @CallSuper
    public void reset() {
        super.reset();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    @CallSuper
    public void update(float dTime) {
        super.update(dTime);
    }

}
