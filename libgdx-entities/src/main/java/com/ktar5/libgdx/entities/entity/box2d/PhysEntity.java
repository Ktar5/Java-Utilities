package com.ktar5.libgdx.entities.entity.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ktar5.libgdx.entities.box2d.WorldManager;
import com.ktar5.utilities.libgdx.entities.components.EntityAnimator;
import com.ktar5.libgdx.entities.entity.box2d.datastore.BodyDatastore;
import com.ktar5.utilities.libgdx.entities.Entity;
import lombok.Getter;

@Getter
public abstract class PhysEntity extends Entity {
    private Body physBody;

    public PhysEntity(float height, float width) {
        super(height, width);
        physBody = WorldManager.getInstance().getWorld().createBody(initializeBodyDef());
        physBody.setUserData(initializeBodyDatastore());
        FixtureDef def = initializeFixtureDef();
        physBody.createFixture(def);

        //THIS LINE IS REALLY FUCKING IMPORTANT
        def.shape.dispose();
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
        //physBody.setTransform(position.x, position.y, position.getAngle());
        super.update(dTime);
    }

    public Vector2 getRawPosition() {
        return physBody.getPosition();
    }

}
