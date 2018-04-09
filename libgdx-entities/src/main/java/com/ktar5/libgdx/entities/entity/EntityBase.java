package com.ktar5.libgdx.entities.entity;

import com.ktar5.libgdx.entities.components.EntityAnimator;
import com.ktar5.libgdx.entities.entity.IEntity;
import com.ktar5.utilities.annotation.callsuper.CallSuper;
import com.ktar5.utilities.libgdx.util.Identity;
import com.ktar5.utilities.libgdx.util.Position;
import lombok.Getter;

@Getter
public abstract class EntityBase extends Identity implements IEntity {
    public final Position position;
    private final EntityAnimator entityAnimator;

    public EntityBase(float height, float width) {
        position = new Position(0, 0);
        this.entityAnimator = initializeRenderer(height, width);
    }

    protected abstract EntityAnimator initializeRenderer(float height, float width);

    @Override
    @CallSuper
    public void update(float dTime) {
        entityAnimator.update(dTime);
    }

}
