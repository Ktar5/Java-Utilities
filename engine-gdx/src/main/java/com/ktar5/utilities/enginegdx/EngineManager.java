package com.ktar5.utilities.enginegdx;

import com.badlogic.gdx.utils.Disposable;
import com.ktar5.utilities.enginegdx.entity.EntityCoordinator;
import com.ktar5.utilities.enginegdx.entity.living.PlayerEntity;
import com.ktar5.utilities.enginegdx.events.projectile.ProjectileContactListener;
import com.ktar5.utilities.enginegdx.events.sensor.SensorListener;
import com.ktar5.utilities.enginegdx.old.box2d.WorldManager;
import com.ktar5.utilities.libgdx.animation.AnimationLoader;
import com.ktar5.utilities.libgdx.camera.CameraBase;
import com.ktar5.utilities.libgdx.cooldown.CooldownManager;
import com.ktar5.utilities.libgdx.events.GameEvent;
import com.ktar5.utilities.libgdx.tweenengine.TweenManager;
import lombok.Getter;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

import java.util.function.Supplier;

@Getter
public class EngineManager<G extends AbstractGame<G, P>, P extends PlayerEntity> implements Disposable {
    private static EngineManager<?, ?> instance;

    private G game;
    /**
     * {@link MBassador#publish(Object)} and {@link MBassador#subscribe(Object)}
     */
    private final MBassador<GameEvent> eventBus;
    private CameraBase cameraBase;

    private TweenManager tweenManager;
    private AnimationLoader animationLoader;
    private WorldManager worldManager;
    private EntityCoordinator<P> entityCoordinator;
    private CooldownManager cooldownManager;

    private EngineManager(G game) {
        initializeLogger();
        this.game = game;
        this.tweenManager = new TweenManager();
        this.cameraBase = game.initializeCameraBase();
        this.eventBus = this.initializeEventBus();
    }

    public void initializeGame(Supplier<P> playerSupplier) {
        this.animationLoader = new AnimationLoader();
        this.worldManager = WorldManager.getInstance();
        this.entityCoordinator = new EntityCoordinator<>(playerSupplier.get());
        this.cooldownManager = new CooldownManager();

        worldManager.setupTiledMap();
        initializeListeners();
    }

    private void initializeLogger() {
        Configurator.defaultConfig()
                .writer(new ConsoleWriter())
                .level(Level.DEBUG)
                .addWriter(new FileWriter("log.txt"))
                //{date:yyyy-MM-dd HH:mm:ss} {class}.{method}()\n{level}: {message}
                .formatPattern("{date:mm:ss:SSS} {class_name}.{method}() [{level}]: {message}")
                .activate();
    }

    private MBassador<GameEvent> initializeEventBus() {
        return new MBassador<>(new BusConfiguration()
                .addFeature(Feature.SyncPubSub.Default())
                .addFeature(Feature.AsynchronousMessageDispatch.Default())
                .addFeature(Feature.AsynchronousHandlerInvocation.Default())
                .addPublicationErrorHandler(Logger::error)
                .setProperty(IBusConfiguration.Properties.BusId, "Game Event Bus"));
    }

    public static synchronized EngineManager<?, ?> get() {
        if (instance == null) {
            throw new RuntimeException("Initialize EngineManager first, nerd.");
        }
        return instance;
    }

    private void initializeListeners() {
        new SensorListener().subscribe();
        new ProjectileContactListener().subscribe();
    }

    public P getPlayer() {
        return this.getEntityCoordinator().getPlayer();
    }

    @Override
    public void dispose() {
        worldManager.getWorld().dispose();
        eventBus.shutdown();
        game.dispose();
    }


    public static <A extends AbstractGame<A, B>, B extends PlayerEntity> EngineManager<A, B> initialize(A game) {
        EngineManager<A, B> abEngineManager = new EngineManager<>(game);
        instance = abEngineManager;
        return abEngineManager;
    }
}
