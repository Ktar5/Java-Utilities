package com.ktar5.utilities.enginegdx.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Pool;
import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import com.ktar5.utilities.enginegdx.entity.components.EntityAnimator;
import com.ktar5.utilities.enginegdx.entity.components.Position;
import com.ktar5.utilities.enginegdx.entity.datastore.BodyDatastore;
import com.ktar5.utilities.enginegdx.old.box2d.WorldManager;
import com.ktar5.utilities.libgdx.Identity;
import com.ktar5.utilities.libgdx.Updatable;
import lombok.Getter;

import java.util.UUID;

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

    //region Super fast collections attributes for Entity class
    static final class Attributes {
        public static final Attribute<Entity, UUID> ID = new SimpleAttribute<Entity, UUID>("id") {
            @Override
            public UUID getValue(Entity object, QueryOptions queryOptions) {
                return object.getId();
            }
        };

        public static final Attribute<Entity, Float> POSITION_X = new SimpleAttribute<Entity, Float>("position_x") {
            @Override
            public Float getValue(Entity object, QueryOptions queryOptions) {
                return object.getPosition().x;
            }
        };

        public static final Attribute<Entity, Integer> LAYER = new SimpleAttribute<Entity, Integer>("layer") {
            @Override
            public Integer getValue(Entity object, QueryOptions queryOptions) {
                return object.getEntityAnimator().getLayer().ordinal();
            }
        };

        public static final Attribute<Entity, Float> POSITION_Y = new SimpleAttribute<Entity, Float>("position_y") {
            @Override
            public Float getValue(Entity object, QueryOptions queryOptions) {
                return object.getPosition().y;
            }
        };
    }
    //endregion

}
