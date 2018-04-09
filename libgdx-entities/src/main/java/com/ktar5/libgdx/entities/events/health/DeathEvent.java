package com.ktar5.libgdx.entities.events.health;

import com.ktar5.libgdx.entities.entity.components.Health;
import com.ktar5.libgdx.entities.entity.living.LivingEntity;
import entities.entity.components.Health;
import entities.entity.living.LivingEntity;
import com.ktar5.utilities.libgdx.events.GameEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeathEvent extends GameEvent {
    private Health.DamageCause cause;
    private final LivingEntity killed;

    public DeathEvent(LivingEntity killed, Health.DamageCause cause) {
        this.killed = killed;
        this.cause = cause;
    }

}
