package com.ktar5.libgdx.entities.events.contact;

import com.badlogic.gdx.physics.box2d.Contact;

public class BeginContactEvent extends ContactEvent {
    public BeginContactEvent(Contact contact) {
        super(contact);
    }
}
