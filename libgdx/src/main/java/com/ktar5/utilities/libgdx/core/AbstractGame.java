package com.ktar5.utilities.libgdx.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.ktar5.utilities.annotation.dontoverride.DontOverride;
import com.ktar5.utilities.libgdx.Const;
import com.ktar5.utilities.libgdx.camera.CameraBase;
import com.ktar5.utilities.libgdx.util.Updatable;

public abstract class AbstractGame<G extends AbstractGame<G>> implements ApplicationListener {
    protected AbstractScreen screen;
    
    protected EngineManager<G> engineManager;
    
    protected abstract CameraBase initializeCameraBase();
    
    protected abstract AbstractScreen getStartingScreen();
    
    
    @DontOverride
    public final void create() {
        engineManager = EngineManager.initialize(getThis());
        initialize();
        setScreen(getStartingScreen());
    }
    
    public abstract void initialize();
    
    public abstract G getThis();
    
    @Override
    public void dispose() {
        if (screen != null) {
            screen.hide();
            screen.dispose();
        }
    }
    
    @Override
    public void pause() {
        if (screen != null) screen.pause();
    }
    
    @Override
    public void resume() {
        if (screen != null) screen.resume();
    }
    
    float time = 0;
    
    @Override
    public void render() {
        //Get time since last frame
        float dTime = Gdx.graphics.getDeltaTime();
        //If game too laggy, prevent massive bugs by using a small constant number
        time += Math.min(dTime, Const.MAX_FRAME_TIME);
        //While our time is greater than our fixed step size...
        while (time >= Const.STEP_TIME) {
            time -= Const.STEP_TIME;
            //Update the camera
            screen.getCamera().getCamera().update();
            for (Updatable updatable : screen.getUpdatableList()) {
                //Update all updatables, in order
                updatable.update(Const.STEP_TIME);
            }
        }
        
        screen.getRenderManager().update(Const.STEP_TIME);
        screen.render(dTime);
    }
    
    @Override
    public void resize(int width, int height) {
        if (screen != null) screen.resize(width, height);
    }
    
    /**
     * Sets the current screen. {@link AbstractScreen#hide()} is called on any old screen, and {@link AbstractScreen#show()} is called on the new
     * screen, if any.
     *
     * @param screen may be {@code null}
     */
    public void setScreen(AbstractScreen screen) {
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
    
    /**
     * @return the currently active {@link AbstractScreen}.
     */
    public AbstractScreen getScreen() {
        return screen;
    }
}
