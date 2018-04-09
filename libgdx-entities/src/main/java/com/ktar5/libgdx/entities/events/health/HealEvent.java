package com.ktar5.libgdx.entities.events.health;

import com.ktar5.libgdx.entities.entity.ILivingEntity;
import com.ktar5.utilities.libgdx.events.CancellableGameEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealEvent extends CancellableGameEvent {
    private int heal;
    private final ILivingEntity healed;

    public HealEvent(ILivingEntity healed, int heal) {
        this.healed = healed;
        this.heal = heal;
    }

}
