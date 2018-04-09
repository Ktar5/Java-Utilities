package com.ktar5.libgdx.entities.events.projectile;

import com.ktar5.libgdx.entities.entity.box2d.datastore.EntityDatastore;
import com.ktar5.libgdx.entities.entity.box2d.datastore.ProjectileDatastore;
import com.ktar5.libgdx.entities.events.contact.BeginContactEvent;
import com.ktar5.utilities.libgdx.core.EngineManager;
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
