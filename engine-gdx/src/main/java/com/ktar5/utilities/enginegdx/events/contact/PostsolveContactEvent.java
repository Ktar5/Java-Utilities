package com.ktar5.utilities.enginegdx.events.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import lombok.Getter;

@Getter
public class PostsolveContactEvent extends ContactEvent {
    public final ContactImpulse contactImpulse;

    public PostsolveContactEvent(Contact contact, ContactImpulse contactImpulse) {
        super(contact);
        this.contactImpulse = contactImpulse;
    }
}
