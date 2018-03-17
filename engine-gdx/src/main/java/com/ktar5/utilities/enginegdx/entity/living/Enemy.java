package com.ktar5.utilities.enginegdx.entity.living;

public abstract class Enemy extends LivingEntity {

    public Enemy(int maxHealth, float height, float width) {
        super(maxHealth, height, width);
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);
    }

}
