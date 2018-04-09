package com.ktar5.libgdx.entities.events.sensor;

import com.ktar5.utilities.libgdx.core.EngineManager;
import com.ktar5.libgdx.entities.entity.datastore.EntityDatastore;
import com.ktar5.libgdx.entities.entity.datastore.SensorDatastore;
import com.ktar5.libgdx.entities.events.contact.BeginContactEvent;
import com.ktar5.libgdx.entities.events.contact.EndContactEvent;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;

@Listener(references = References.Strong)
public class SensorListener {

    @Handler
    public void handleSensorEnter(BeginContactEvent event) {
        event.testPair(SensorDatastore.class, EntityDatastore.class).ifPresent(entry -> {
            entry.getKey().entityEnter(entry.getValue().getEntity(), event.getContact());
        });
    }

    @Handler
    public void handleSensorLeave(EndContactEvent event) {
        event.testPair(SensorDatastore.class, EntityDatastore.class).ifPresent(entry -> {
            entry.getKey().entityLeave(entry.getValue().getEntity(), event.getContact());
        });
    }

    public void subscribe() {
        EngineManager.get().getEventBus().subscribe(this);
    }
}
