package com.ktar5.utilities.enginegdx.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.ktar5.utilities.enginegdx.EngineManager;
import com.ktar5.utilities.enginegdx.old.debug.Debug;
import com.ktar5.utilities.libgdx.Const;
import com.ktar5.utilities.libgdx.Updatable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class RenderManager implements Disposable, Updatable {
    protected final SpriteBatch spriteBatch;
    protected final ShapeRenderer shapeRenderer;
    protected final List<Renderable> renderables;

    public RenderManager() {
        this.spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.renderables = new ArrayList<>();
        initializeRenderables();
    }

    @Override
    public void update(float dTime) {
        //Because OpenGL needs this.
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(EngineManager.get().getCameraBase().getCamera().combined);

        preRender(dTime);

        spriteBatch.begin();
        for (Renderable renderable : renderables) {
            renderable.render(spriteBatch, dTime);
        }
        spriteBatch.end();

        postRender(dTime);
        preDebug(dTime);

        if (Const.DEBUG) {
            shapeRenderer.setProjectionMatrix(EngineManager.get().getCameraBase().getCamera().combined);
            for (Renderable renderable : renderables) {
                if (Debug.shouldDebug(renderable.getClass())) {
                    renderable.debug(dTime);
                }
            }
        }

        postDebug(dTime);
    }

    protected abstract void initializeRenderables();

    protected void preRender(float dTime) {
    }

    protected void postRender(float dTime) {
    }

    protected void preDebug(float dTime) {
    }

    protected void postDebug(float dTime) {
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}

