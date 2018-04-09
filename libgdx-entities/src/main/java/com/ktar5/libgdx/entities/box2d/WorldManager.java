package com.ktar5.libgdx.entities.box2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ktar5.utilities.libgdx.core.EngineManager;
import com.ktar5.libgdx.entities.events.contact.ContactEventDispatcher;
import com.ktar5.utilities.libgdx.rendering.Renderable;
import entities.EngineManager;
import entities.events.contact.ContactEventDispatcher;
import entities.rendering.Renderable;
import com.ktar5.utilities.libgdx.Const;
import com.ktar5.utilities.libgdx.util.Updatable;
import lombok.Getter;

public class WorldManager implements Renderable, Updatable {
    @Getter
    private World world;
    private TiledMap tileMap;
    private Box2DDebugRenderer debugRenderer;
    private OrthogonalTiledMapRenderer tileMapRenderer;

    private static WorldManager sInstance;

    public static WorldManager getInstance() {
        if (sInstance == null) {
            sInstance = new WorldManager(new Vector2(Const.H_GRAV, Const.V_GRAV));
        }
        return sInstance;
    }

    private WorldManager(Vector2 gravVector) {
        world = new World(gravVector, false);
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.setDrawVelocities(true);
        debugRenderer.setDrawAABBs(true);
        debugRenderer.setDrawContacts(true);
        debugRenderer.setDrawBodies(true);
        world.setContactListener(new ContactEventDispatcher());
    }

    public void setupTiledMap() {
        AtlasTmxMapLoader.AtlasTiledMapLoaderParameters params = new AtlasTmxMapLoader.AtlasTiledMapLoaderParameters();
        //TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();

        params.textureMinFilter = Texture.TextureFilter.Linear;
        params.textureMagFilter = Texture.TextureFilter.Nearest;
        params.generateMipMaps = true;

        tileMap = new AtlasTmxMapLoader().load(Const.MAPPATH_TESTING, params);
        //tileMap = new TmxMapLoader().load(Const.MAPPATH_TESTING, params);

        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap, Const.MAP_SCALE,
                EngineManager.get().getGame().getScreen().getRenderManager().getSpriteBatch());

        MapBodyBuilder.buildShapes(tileMap, Const.MAP_PPT, world);
    }

    @Override
    public void update(float dTime) {
        world.step(Const.STEP_TIME, Const.VELOCITY_ITERATIONS, Const.POSITION_ITERATIONS);
    }

    @Override
    public void render(SpriteBatch batch, float dTime) {
        tileMapRenderer.setView(EngineManager.get().getCameraBase().getCamera());
        tileMapRenderer.render();
    }

    @Override
    public void debug(float dTime) {
        debugRenderer.render(world, EngineManager.get().getCameraBase().getCamera().combined);
    }
}
