package com.ktar5.utilities.enginegdx.events.health;

import com.ktar5.utilities.enginegdx.entity.components.Health;
import com.ktar5.utilities.enginegdx.entity.living.LivingEntity;
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
