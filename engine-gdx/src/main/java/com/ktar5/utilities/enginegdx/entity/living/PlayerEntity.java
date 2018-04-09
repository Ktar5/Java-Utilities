package com.ktar5.utilities.enginegdx.entity.living;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ktar5.utilities.common.constants.Direction;
import com.ktar5.utilities.enginegdx.EngineManager;
import com.ktar5.utilities.enginegdx.entity.components.EntityAnimator;
import com.ktar5.utilities.enginegdx.entity.components.movement.Movement;
import com.ktar5.utilities.enginegdx.entity.components.movement.SetVelocityMovement;
import com.ktar5.utilities.enginegdx.old.box2d.Fixtures;
import com.ktar5.utilities.libgdx.Feature;
import com.ktar5.utilities.libgdx.Updatable;
import com.ktar5.utilities.libgdx.fsm.GameStateMachine;
import com.ktar5.utilities.libgdx.fsm.State;
import lombok.Getter;

@Getter
public abstract class PlayerEntity<S extends State<T>, T> extends LivingEntity implements Updatable {
    protected Movement movement = new SetVelocityMovement(40f);
    protected GameStateMachine<S, T> playerFSM;

    public PlayerEntity(GameStateMachine<S, T> stateMachine, int maxHealth, float height, float width) {
        super(maxHealth, height, width);
        playerFSM = stateMachine;
    }

    @Override
    protected EntityAnimator initializeRenderer(float height, float width) {
        return new EntityAnimator(EntityAnimator.RenderLayer.MIDDLE,
                EngineManager.get().getAnimationLoader().getAnimation("player_idle_0"),
                width, height);
    }

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
    public boolean isPlayer() {
        return true;
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);
    }

}