package com.ktar5.utilities.enginegdx.events.projectile;

import com.ktar5.utilities.enginegdx.EngineManager;
import com.ktar5.utilities.enginegdx.entity.datastore.EntityDatastore;
import com.ktar5.utilities.enginegdx.entity.datastore.ProjectileDatastore;
import com.ktar5.utilities.enginegdx.events.contact.BeginContactEvent;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;

@Listener(references = References.Strong)
public class ProjectileContactListener {
    @Handler
    public void handleSensorEnter(BeginContactEvent event) {
        event.testPair(ProjectileDatastore.class, EntityDatastore.class).ifPresent(entry -> {
            entry.getKey().onHit(entry.getValue().getEntity());
        });
    }

    public void subscribe() {
        EngineManager.get().getEventBus().subscribe(this);
    }
}
