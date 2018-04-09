package com.ktar5.libgdx.entities.events.health;

import com.ktar5.libgdx.entities.components.Health;
import com.ktar5.libgdx.entities.entity.ILivingEntity;
import com.ktar5.utilities.libgdx.events.GameEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeathEvent extends GameEvent {
    private Health.DamageCause cause;
    private final ILivingEntity killed;

    public DeathEvent(ILivingEntity killed, Health.DamageCause cause) {
        this.killed = killed;
        this.cause = cause;
    }

}
