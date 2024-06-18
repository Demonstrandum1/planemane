package PlaneMane.model;

import java.util.ArrayList;
import java.util.List;

import PlaneMane.model.entities.*;
import PlaneMane.model.factories.EntityFactory;
import PlaneMane.model.factories.RandomEnemyFactory;
import PlaneMane.model.factories.RandomObjectFactory;
import PlaneMane.model.utils.AudioUtil;
import PlaneMane.model.utils.IGameAssets;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;
import com.badlogic.gdx.utils.Array;
import PlaneMane.view.ViewableGameModel;

public class GameModel implements ViewableGameModel, EntityGameModel {
    private World world;
    private Array<Entity> entities;
    private EntityFactory enemyFactory;
    private EntityFactory objectFactory;
    private GameState gameState;
    private IGameAssets assets;
    private Plane plane;
    private Pilot pilot;
    private List<Entity> entitiesToRemove;
    private AudioUtil audio;
    private boolean controllerAllowed;
    private TaskManager taskManager;
    private long currentScore;

    private float spawnEnemyTimer;
    private float spawnSeekingEnemyTimer;
    private float spawnObjectTimer;
    private float spawnEnemyInterval;
    private float spawnSeekingEnemyInterval;
    private float spawnObjectInterval;

    private String currentSong;
    private boolean planeInGame;

    public GameModel(IGameAssets assets) {
        this.assets = assets;
        this.audio = new AudioUtil();
        this.gameState = GameState.WELCOME_SCREEN;
        this.world = createWorld();
        this.entities = new Array<>();
        this.entitiesToRemove = new ArrayList<>();

        this.pilot = new Pilot(world, -Configurations.WORLD_WIDTH / 2, 1.0f,
                Configurations.WORLD_WIDTH / 30,
                Configurations.WORLD_HEIGHT / 30 * 26 / 12, this);
        entities.add(pilot);
        this.plane = new Plane(world, -3, Configurations.WORLD_HEIGHT / 2,
                Configurations.WORLD_WIDTH / 8,
                Configurations.WORLD_HEIGHT / 8 * 9 / 16, this);

        addWalls();
        createGame();
    }

    private World createWorld() {
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        return new World(worldDef);
    }

    private void createGame() {
        this.currentSong = "audio/pm-music1.mp3";
        updateSong(currentSong);
        this.enemyFactory = new RandomEnemyFactory(this, Configurations.FACTORY_HEIGHT);
        this.objectFactory = new RandomObjectFactory(this, Configurations.FACTORY_HEIGHT);
        this.controllerAllowed = true;
        resetSpawnTimers();
        resetScore();

        initializeEntities();
        setUpContactListener();
        this.taskManager = new TaskManager(enemyFactory, objectFactory, "PEPCP");

    }

    private void initializeEntities() {
        entities.add(plane);
        this.planeInGame = false;
        plane.increaseEnergyLevel(100);
    }

    private void setUpContactListener() {
        world.setContactListener(new PlaneContactListener(this));
    }

    @Override
    public void update(float delta) {
        if (isGameOver()) {
            handleGameOver();
            return;
        }

        updateWorldPhysics();
        updateEntities(delta);
        setRandomSpawnIntervals();
        spawnEnemies(delta);
        spawnObjects(delta);
        removeEntities();
        checkPlaneInGame();
    }

    protected void updateSong(String path) {
        audio.disposeMusic();
        audio.streamMusic(path);
        currentSong = path;
    }

    protected String getCurrentSong() {
        return currentSong;
    }

    private boolean isGameOver() {
        return plane.getEnergyLevel() <= 0;
    }

    private void handleGameOver() {
        updateSong("audio/gameoverahhbeat.mp3");
        gameState = GameState.GAME_OVER;
        resetScore();
    }

    private void updateWorldPhysics() {
        world.step(1 / 60f, 6, 2);
    }

    private void updateEntities(float delta) {
        entities.forEach(Entity::update);
    }

    @Override
    public void restartGame() {
        taskManager.restartGameTasks();
        clearEntities();
        createGame();
        setGameState(GameState.PLAYING);
        resetPlanePosition();
        enemyFactory.setSpawnInterval(5);
        objectFactory.setSpawnInterval(10);
        updateSong("audio/pm-music1.mp3");
    }

    private void resetPlanePosition() {
        plane.getBody().setGravityScale(0);
        plane.getBody().setLinearVelocity(0, 0);
        plane.getBody().setTransform(1, Configurations.WORLD_HEIGHT / 2, 0);
    }

    private void spawnEnemies(float delta) {
        spawnEnemyTimer += delta;
        if (spawnEnemyTimer >= spawnEnemyInterval && planeInGame) {
            entities.add(enemyFactory.createObject());
            spawnEnemyTimer = 0;
        }

        spawnSeekingEnemyTimer += delta;
        if (spawnSeekingEnemyTimer >= spawnSeekingEnemyInterval && planeInGame) {
            entities.add(enemyFactory.createObject());
            spawnSeekingEnemyTimer = 0;
        }
    }

    private void spawnObjects(float delta) {
        spawnObjectTimer += delta;
        if (spawnObjectTimer >= spawnObjectInterval && planeInGame) {
            entities.add(objectFactory.createObject());
            spawnObjectTimer = 0;
        }
    }

    private void setRandomSpawnIntervals() {
        spawnEnemyInterval = (float) (Math.random() * 1 + 1);
        spawnSeekingEnemyInterval = (float) (Math.random() * 2 + enemyFactory.getSpawnInterval());
        spawnObjectInterval = (float) (Math.random() * 5 + objectFactory.getSpawnInterval());
    }

    private void resetSpawnTimers() {
        spawnEnemyTimer = 0;
        spawnSeekingEnemyTimer = 0;
        spawnObjectTimer = 0;
    }

    private void checkPlaneInGame() {
        if (plane.getBody().getPosition().x > 0 && !planeInGame) {
            setPlaneInGame();
            controllerAllowed = true;
            taskManager.startGameTasks();
        }
    }

    protected void setPlaneInGame() {
        planeInGame = true;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Array<Entity> getEntities() {
        return entities;
    }

    @Override
    public Plane getPlane() {
        return plane;
    }

    @Override
    public Pilot getPilot() {
        return pilot;
    }

    @Override
    public void setGameState(GameState gs) {
        this.gameState = gs;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void pauseGame(GameState gs) {
        setGameState(gs);
        taskManager.pauseGameTasks();
    }

    @Override
    public void resumeGame() {
        setGameState(GameState.PLAYING);
        taskManager.resumeGameTasks();
    }

    @Override
    public void addEntityToRemove(Entity entity) {
        entitiesToRemove.add(entity);
    }

    @Override
    public void removeEntities() {
        for (Entity entity : entitiesToRemove) {
            world.destroyBody(entity.getBody());
            entities.removeValue(entity, true);
        }
        entitiesToRemove.clear();
    }

    private void clearEntities() {
        entities.clear();
        entitiesToRemove.clear();
    }

    protected void boardPlane() {
        controllerAllowed = false;
        gameState = GameState.PLAYING;
        plane.getBody().setGravityScale(0);
        plane.getBody().setLinearVelocity(10, 1);
    }

    private void addWalls() {
        entities
                .add(new Wall(world, -Configurations.WORLD_WIDTH, 0.9f, Configurations.WORLD_WIDTH * 2,
                        Configurations.WALLTHICKNESS, this));
        entities.add(
                new Wall(world, 0, Configurations.WORLD_HEIGHT - Configurations.WALLTHICKNESS,
                        Configurations.WORLD_WIDTH,
                        Configurations.WALLTHICKNESS,
                        this));
        entities.add(
                new Wall(world, Configurations.WORLD_WIDTH - Configurations.WALLTHICKNESS, 0,
                        Configurations.WALLTHICKNESS,
                        Configurations.WORLD_HEIGHT,
                        this));
    }

    @Override
    public int getGameTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    @Override
    public boolean isPlaneInGame() {
        return planeInGame;
    }

    @Override
    public AudioUtil getAudio() {
        return audio;
    }

    @Override
    public IGameAssets getAssets() {
        return assets;
    }

    @Override
    public boolean getControllerAllowed() {
        return this.controllerAllowed;
    }

    @Override
    public long getCurrentScore() {
        return currentScore;
    }

    @Override
    public void addPoints(int points) {
        currentScore += points;
    }

    private void resetScore() {
        currentScore = 0;
    }

    protected List<Entity> getEntitiesToRemove() {
        return entitiesToRemove;
    }
}
