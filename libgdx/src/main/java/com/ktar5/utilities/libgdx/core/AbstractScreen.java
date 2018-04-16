package com.ktar5.utilities.libgdx.core;

import com.badlogic.gdx.Screen;
import com.ktar5.utilities.libgdx.rendering.RenderManager;
import com.ktar5.utilities.libgdx.rendering.Renderable;
import com.ktar5.utilities.libgdx.util.Updatable;
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
        this.renderManager = EngineManager.get().getRenderManager();
        if(renderManager == null){
            throw new RuntimeException("RenderManager cannot be null");
        }
        this.renderManager.setRenderables(initilizeRenderables());
        //todo set customized renderer
        initializeUpdatables();
    }

    public abstract List<Renderable> initilizeRenderables();

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
