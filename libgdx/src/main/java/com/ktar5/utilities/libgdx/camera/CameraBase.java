package com.ktar5.utilities.libgdx.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ktar5.utilities.libgdx.util.Updatable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public abstract class CameraBase implements Updatable {
    protected final OrthographicCamera camera;
    protected final Viewport viewport;
}
