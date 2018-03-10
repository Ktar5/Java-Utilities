package com.ktar5.utilities.enginegdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.index.hash.HashIndex;
import com.googlecode.cqengine.index.unique.UniqueIndex;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.resultset.ResultSet;
import com.ktar5.utilities.enginegdx.entity.living.PlayerEntity;
import com.ktar5.utilities.enginegdx.rendering.Renderable;
import com.ktar5.utilities.libgdx.Updatable;
import lombok.Getter;
import org.pmw.tinylog.Logger;

import java.util.UUID;

import static com.googlecode.cqengine.query.QueryFactory.*;

public class EntityCoordinator<P extends PlayerEntity> implements Updatable, Renderable {
    private final IndexedCollection<Entity> entities;
    @Getter private P player;

    public EntityCoordinator(P playerEntity) {
        this.entities = new ConcurrentIndexedCollection<>();
        entities.addIndex(UniqueIndex.onAttribute(Entity.Attributes.ID));
        entities.addIndex(HashIndex.onAttribute(Entity.Attributes.LAYER),
                queryOptions(orderBy(ascending(Entity.Attributes.LAYER))));
        this.player = playerEntity;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Entity getEntity(UUID uuid) {
        for (Entity entity : entities.retrieve(equal(Entity.Attributes.ID, uuid))) {
            return entity;
        }
        Logger.debug("Could not find entity with id: " + uuid);
        return null;
    }

    public Entity[] entitiesWithinBox(float x1, float x2, float y1, float y2) {
        Query<Entity> query = and(
                between(Entity.Attributes.POSITION_X, Math.min(x1, x2), Math.max(x1, x2)),
                between(Entity.Attributes.POSITION_Y, Math.min(y1, y2), Math.max(y1, y2)));
        ResultSet<Entity> retrieve = entities.retrieve(query);
        if (retrieve.isEmpty()) {
            return new Entity[]{};
        } else {
            Entity[] entitiesArray = new Entity[retrieve.size()];
            int i = 0;
            for (Entity entity : retrieve) {
                entitiesArray[i] = entity;
                i++;
            }
            return entitiesArray;
        }
    }

    @Override
    public void update(float dTime) {
        for (Entity entity : entities) {
            entity.update(dTime);
        }
    }

    @Override
    public void render(SpriteBatch batch, float dTime) {
        for (Entity entity : entities) {
            entity.getEntityAnimator().render(batch, entity.position.x, entity.position.y, entity.position.getAngle());
        }
        player.getEntityAnimator().render(batch, player.position.x, player.position.y, 0);
    }
}
