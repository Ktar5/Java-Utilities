package com.ktar5.utilities.enginegdx.events.contact;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.ktar5.utilities.enginegdx.entity.datastore.BodyDatastore;
import com.ktar5.utilities.libgdx.events.GameEvent;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ContactEvent extends GameEvent {
    public final Contact contact;

    public ContactEvent(Contact contact) {
        this.contact = contact;
    }


    public <T extends BodyDatastore, R extends BodyDatastore> Optional<T> test(Class<T> c1, Class<R> c2) {
        Body b1 = contact.getFixtureA().getBody();
        Body b2 = contact.getFixtureB().getBody();
        if (c1.isInstance(b1.getUserData()) && c2.isInstance(b2.getUserData())) {
            return Optional.of(c1.cast(b1.getUserData()));
        } else if (c1.isInstance(b2.getUserData()) && c2.isInstance(b1.getUserData())) {
            return Optional.of(c1.cast(b2.getUserData()));
        } else {
            return Optional.empty();
        }
    }

    public <T extends BodyDatastore, R extends BodyDatastore> Optional<net.dermetfan.utils.Pair<T, R>> testPair(Class<T> c1, Class<R> c2) {
        Body b1 = contact.getFixtureA().getBody();
        Body b2 = contact.getFixtureB().getBody();
        if (c1.isInstance(b1.getUserData()) && c2.isInstance(b2.getUserData())) {
            return Optional.of(new net.dermetfan.utils.Pair<>(c1.cast(b1.getUserData()), c2.cast(b2.getUserData())));
        } else if (c1.isInstance(b2.getUserData()) && c2.isInstance(b1.getUserData())) {
            return Optional.of(new net.dermetfan.utils.Pair<>(c1.cast(b2.getUserData()), c2.cast(b1.getUserData())));
        } else {
            return Optional.empty();
        }
    }

}
