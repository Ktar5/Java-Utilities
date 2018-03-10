package com.ktar5.utilities.enginegdx.events.sensor;

import com.ktar5.utilities.enginegdx.EngineManager;
import com.ktar5.utilities.enginegdx.events.contact.BeginContactEvent;
import com.ktar5.utilities.enginegdx.events.contact.EndContactEvent;
import com.ktar5.utilities.enginegdx.entity.datastore.EntityDatastore;
import com.ktar5.utilities.enginegdx.entity.datastore.SensorDatastore;
import com.ktar5.utilities.libgdx.events.GameListener;
import com.sun.xml.internal.fastinfoset.stax.events.EventBase;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;

@Listener(references = References.Strong)
public class SensorListener implements GameListener {

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

    @Override
    public void subscribe() {
        EngineManager.get().getEventBus().subscribe(this);
    }
}
