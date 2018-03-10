package com.ktar5.utilities.enginegdx.entity.components;

import com.badlogic.gdx.utils.Pool;
import com.ktar5.utilities.enginegdx.EngineManager;
import com.ktar5.utilities.enginegdx.entity.living.LivingEntity;
import com.ktar5.utilities.enginegdx.events.health.DamageEvent;
import com.ktar5.utilities.enginegdx.events.health.DeathEvent;
import com.ktar5.utilities.enginegdx.events.health.HealEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@Getter
@Setter
public class Health implements Pool.Poolable {
    private int health, maxHealth;
    @ToStringExclude
    private LivingEntity holder;

    public void heal(int amount) {
        amount = Math.abs(amount);
        HealEvent event = onHeal(amount);
        if (event.isCancelled() || event.getHeal() <= 0) {
            return;
        }
        amount = event.getHeal();
        if (health + amount >= maxHealth) {
            health = maxHealth;
        } else {
            health += amount;
        }
    }

    /**
     * @param damageCause Note that this is used simply for specifying to on-death
     *                    and that this method should only be used when dealing direct damage
     */
    public void harm(int amount, DamageCause damageCause) {
        amount = Math.abs(amount);
        DamageEvent event = onDamage(damageCause, amount);
        if (event.isCancelled() || event.getDamage() <= 0) {
            return;
        }
        amount = event.getDamage();
        if (health - amount <= 0) {
            health = 0;
            onDeath(event.getCause());
        } else {
            health -= amount;
        }
    }

    @Override
    public void reset() {
        health = maxHealth;
        holder = null;
    }

    //TODO event system
    private void onDeath(DamageCause damageCause) {
        DeathEvent event = new DeathEvent(holder, damageCause);
        EngineManager.get().getEventBus().publish(event);
    }

    private DamageEvent onDamage(DamageCause damageCause, int amount) {
        DamageEvent event = new DamageEvent(holder, damageCause, amount);
        EngineManager.get().getEventBus().publish(event);
        return event;
    }

    private HealEvent onHeal(int amount) {
        HealEvent event = new HealEvent(holder, amount);
        EngineManager.get().getEventBus().publish(event);
        return event;
    }

    public enum DamageCause {
        PLAYER,
        HAZARD,
        CLIP,
        CUSTOM
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
