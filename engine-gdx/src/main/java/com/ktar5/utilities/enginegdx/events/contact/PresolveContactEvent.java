package com.ktar5.utilities.enginegdx.events.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Manifold;
import lombok.Getter;

@Getter
public class PresolveContactEvent extends ContactEvent {
    public final Manifold manifold;


    public PresolveContactEvent(Contact contact, Manifold manifold) {
        super(contact);
        this.manifold = manifold;
    }
}
