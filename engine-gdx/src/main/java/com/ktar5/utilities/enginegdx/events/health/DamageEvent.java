package com.ktar5.utilities.enginegdx.events.health;

import com.ktar5.utilities.enginegdx.entity.Entity;
import com.ktar5.utilities.enginegdx.entity.components.Health;
import com.ktar5.utilities.enginegdx.entity.living.LivingEntity;
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
    private final LivingEntity damaged;

    public DamageEvent(Entity damaged, Health.DamageCause cause, int damage) {
        this.damaged = (LivingEntity) damaged;
        this.cause = cause;
        this.damage = damage;
    }

}
