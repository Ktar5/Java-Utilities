package com.ktar5.utilities.enginegdx.events.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ktar5.utilities.enginegdx.EngineManager;

public class ContactEventDispatcher implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Begin");
        EngineManager.get().getEventBus().publish(new BeginContactEvent(contact));
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("End");
        EngineManager.get().getEventBus().publish(new EndContactEvent(contact));
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        System.out.println("pre");
        EngineManager.get().getEventBus().publish(new PresolveContactEvent(contact, oldManifold));
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        System.out.println("Post");
        EngineManager.get().getEventBus().publish(new PostsolveContactEvent(contact, impulse));
    }
}
