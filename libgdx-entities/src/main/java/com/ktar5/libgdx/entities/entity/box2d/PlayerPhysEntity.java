package com.ktar5.libgdx.entities.entity.box2d;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ktar5.libgdx.entities.box2d.Fixtures;
import com.ktar5.utilities.libgdx.entities.components.EntityAnimator;
import com.ktar5.utilities.libgdx.entities.components.movement.Movement;
import com.ktar5.utilities.libgdx.entities.components.movement.SetVelocityMovement;
import com.ktar5.utilities.common.constants.Direction;
import com.ktar5.utilities.libgdx.Feature;
import com.ktar5.utilities.libgdx.core.EngineManager;
import com.ktar5.utilities.libgdx.fsm.GameStateMachine;
import com.ktar5.utilities.libgdx.fsm.State;
import lombok.Getter;

@Getter
public abstract class PlayerPhysEntity<S extends State<T>, T> extends LivingPhysEntity {
    protected Movement movement = new SetVelocityMovement(40f);
    protected GameStateMachine<S, T> playerFSM;

    public PlayerPhysEntity(GameStateMachine<S, T> stateMachine, int maxHealth, float height, float width) {
        super(maxHealth, height, width);
        playerFSM = stateMachine;
    }

    @Override
    protected EntityAnimator initializeRenderer(float height, float width) {
        return new EntityAnimator(EntityAnimator.RenderLayer.MIDDLE,
                EngineManager.get().getAnimationLoader().getAnimation(getDefaultAnimation()),
                width, height);
    }

    protected abstract String getDefaultAnimation();

    @Override
    protected FixtureDef initializeFixtureDef() {
        FixtureDef fixtureDef = Fixtures.playerFixtureDef();
        fixtureDef.isSensor = false;
        return fixtureDef;
    }

    @Override
    protected BodyDef initializeBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(0, 0);
        return bodyDef;
    }

    public Direction getSpriteDirection() {
        if (movement.getPreferred() == null || Feature.FIRST_PRESSED_MOVEMENT.isDisabled()) {
            return Direction.fromAngleCardinal(position.getAngle());
        }
        switch (movement.getPreferred()) {
            case HORIZONTAL:
            case X:
                return Direction.leftOrRight(position.getAngle());
            case VERTICAL:
            case Y:
                return Direction.upOrDown(position.getAngle());
            default:
                return Direction.fromAngleCardinal(position.getAngle());
        }
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);
    }

}