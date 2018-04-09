package com.ktar5.libgdx.entities.entity;

import com.ktar5.libgdx.entities.components.EntityAnimator;
import com.ktar5.libgdx.entities.components.movement.Movement;
import com.ktar5.libgdx.entities.components.movement.SetVelocityMovement;
import com.ktar5.utilities.libgdx.core.EngineManager;
import com.ktar5.utilities.libgdx.fsm.GameStateMachine;
import com.ktar5.utilities.libgdx.fsm.State;
import lombok.Getter;

@Getter
public abstract class PlayerEntity<S extends State<T>, T> extends LivingEntity {
    protected Movement movement = new SetVelocityMovement(40f);
    protected GameStateMachine<S, T> playerFSM;

    public PlayerEntity(GameStateMachine<S, T> stateMachine, int maxHealth, float height, float width) {
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

}
