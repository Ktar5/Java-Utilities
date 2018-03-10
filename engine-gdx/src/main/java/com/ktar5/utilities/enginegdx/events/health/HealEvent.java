package com.ktar5.utilities.enginegdx.events.health;

import com.ktar5.utilities.enginegdx.entity.Entity;
import com.ktar5.utilities.enginegdx.entity.living.LivingEntity;
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
