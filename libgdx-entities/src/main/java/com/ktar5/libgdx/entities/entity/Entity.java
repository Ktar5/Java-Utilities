package com.ktar5.libgdx.entities.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Pool;
import com.ktar5.libgdx.entities.box2d.WorldManager;
import com.ktar5.libgdx.entities.entity.components.EntityAnimator;
import com.ktar5.libgdx.entities.entity.datastore.BodyDatastore;
import com.ktar5.utilities.libgdx.util.Identity;
import com.ktar5.utilities.libgdx.util.Position;
import com.ktar5.utilities.libgdx.util.Updatable;
import lombok.Getter;

@Getter
public abstract class Entity extends Identity implements Pool.Poolable, Updatable {
    public boolean needsUpdating;
    public final Position position;

    private Body physBody;
    private EntityAnimator entityAnimator;

    public Entity(float height, float width) {
        position = new Position(0, 0);

        physBody = WorldManager.getInstance().getWorld().createBody(initializeBodyDef());
        physBody.setUserData(initializeBodyDatastore());
        FixtureDef def = initializeFixtureDef();
        physBody.createFixture(def);

        //THIS LINE IS REALLY FUCKING IMPORTANT
        def.shape.dispose();

        this.entityAnimator = initializeRenderer(height, width);
    }

    //region initializations
    protected abstract EntityAnimator initializeRenderer(float height, float width);

    protected abstract FixtureDef initializeFixtureDef();

    protected abstract BodyDef initializeBodyDef();

    protected abstract BodyDatastore initializeBodyDatastore();
    //endregion

    @Override
    public void reset() {
        super.reset();
        WorldManager.getInstance().getWorld().destroyBody(physBody);
    }

    @Override
    public void update(float dTime) {
        //TODO
        //physBody.setTransform(position.x, position.y, position.getAngle());
        entityAnimator.update(dTime);
    }

    public Vector2 getRawPosition() {
        return physBody.getPosition();
    }

}
