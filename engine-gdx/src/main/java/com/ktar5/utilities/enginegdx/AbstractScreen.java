package com.ktar5.utilities.enginegdx;

import com.badlogic.gdx.Screen;
import com.ktar5.utilities.enginegdx.rendering.RenderManager;
import com.ktar5.utilities.libgdx.Updatable;
import com.ktar5.utilities.libgdx.camera.CameraBase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractScreen implements Screen {
    protected final List<Updatable> updatableList;
    protected final RenderManager renderManager;
    protected final CameraBase camera;

    public AbstractScreen(CameraBase camera) {
        this.camera = camera;
        this.updatableList = new ArrayList<>();
        this.renderManager = initilizeRenderables();
        if(renderManager == null){
            throw new RuntimeException("RenderManager cannot be null");
        }
        initializeUpdatables();
    }

    public abstract RenderManager initilizeRenderables();

    public abstract void initializeUpdatables();

    @Override
    public void dispose() {
        renderManager.dispose();
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void render(float delta) {
        //Do nothing?
    }

    @Override
    public void resize(int width, int height) {
        //Nothing
    }
}
