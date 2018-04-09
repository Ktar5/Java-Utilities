package com.ktar5.libgdx.entities.events.health;

import com.ktar5.libgdx.entities.entity.Entity;
import com.ktar5.libgdx.entities.entity.living.LivingEntity;
import com.ktar5.utilities.libgdx.events.CancellableGameEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealEvent extends CancellableGameEvent {
    private int heal;
    private final LivingEntity healed;

    public HealEvent(Entity healed, int heal) {
        this.healed = (LivingEntity) healed;
        this.heal = heal;
    }

}
