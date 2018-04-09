package com.ktar5.libgdx.entities.events.contact;

import com.badlogic.gdx.physics.box2d.Contact;

public class EndContactEvent extends ContactEvent {
    public EndContactEvent(Contact contact) {
        super(contact);
    }
}
