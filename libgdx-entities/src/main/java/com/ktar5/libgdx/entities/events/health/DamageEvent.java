package com.ktar5.libgdx.entities.events.health;

import com.ktar5.utilities.libgdx.entities.components.Health;
import com.ktar5.libgdx.entities.entity.ILivingEntity;
import com.ktar5.utilities.libgdx.events.CancellableGameEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DamageEvent extends CancellableGameEvent {
    private int damage;
    private Health.DamageCause cause;
    private final ILivingEntity damaged;

    public DamageEvent(ILivingEntity damaged, Health.DamageCause cause, int damage) {
        this.damaged = damaged;
        this.cause = cause;
        this.damage = damage;
    }

}
