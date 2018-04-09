package com.ktar5.libgdx.entities.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pools;
import com.ktar5.libgdx.entities.entity.box2d.PhysEntity;
import com.ktar5.libgdx.entities.entity.box2d.PlayerPhysEntity;
import com.ktar5.utilities.libgdx.rendering.Renderable;
import com.ktar5.utilities.libgdx.util.JsonUtil;
import com.ktar5.utilities.libgdx.util.Updatable;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.pmw.tinylog.Logger;

import java.util.UUID;

public class EntityCoordinator<P extends PlayerPhysEntity> implements Updatable, Renderable {
    private final ObjectMap<UUID, PhysEntity> entities;
    @Getter
    private P player;

    public EntityCoordinator() {
        entities = new ObjectMap<>();
    }

    public EntityCoordinator(P player) {
        entities = new ObjectMap<>();
        this.player = player;
    }

    public void removeEntity(UUID uuid) {
        if (!entities.containsKey(uuid)) {
            Logger.debug(new RuntimeException("PhysEntity with uuid: " + uuid.toString() + " does not exist"));
            return;
        }
        Pools.free(entities.remove(uuid));
    }

    public void addEntity(PhysEntity entity) {
        if (entities.containsKey(entity.getId())) {
            Logger.debug(new RuntimeException("PhysEntity already exists with information"));
            Logger.debug("PhysEntity already exists with information:" + entity.toString());
            Logger.debug(JsonUtil.prettify(ToStringBuilder.reflectionToString(entity, ToStringStyle.JSON_STYLE)));
            return;
        }
        entities.put(entity.getId(), entity);
    }

    public PhysEntity getEntity(UUID uuid) {
        if (entities.containsKey(uuid)) {
            return entities.get(uuid);
        }
        Logger.debug("Could not find entity with id: " + uuid);
        return null;
    }

    @Override
    public void update(float dTime) {
        for (PhysEntity entity : entities.values()) {
            entity.update(dTime);
        }
    }

    @Override
    public void render(SpriteBatch batch, float dTime) {
        for (PhysEntity entity : entities.values()) {
            entity.getEntityAnimator().render(batch, entity.position.x, entity.position.y, entity.position.getAngle());
        }
        player.getEntityAnimator().render(batch, player.getPosition().x, player.getPosition().y, 0);
    }
}
