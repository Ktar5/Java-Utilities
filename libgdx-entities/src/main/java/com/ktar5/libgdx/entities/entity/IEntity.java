package com.ktar5.libgdx.entities.entity;

import com.ktar5.utilities.libgdx.util.Position;
import com.ktar5.utilities.libgdx.util.Updatable;
import org.mini2Dx.gdx.utils.Pool;

import java.util.UUID;

public interface IEntity extends Pool.Poolable, Updatable {

    public Position getPosition();

    public UUID getId();

}
