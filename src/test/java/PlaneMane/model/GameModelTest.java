package PlaneMane.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;
import com.badlogic.gdx.utils.Array;

import PlaneMane.model.entities.Energy;
import PlaneMane.model.entities.Entity;
import PlaneMane.model.entities.Pilot;
import PlaneMane.model.entities.Plane;
import PlaneMane.model.entities.Point;
import PlaneMane.model.entities.WhiteBird;
import PlaneMane.model.utils.AudioUtil;
import PlaneMane.model.utils.GameAssets;
import PlaneMane.model.utils.IGameAssets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameModelTest {

    private GameModel gameModel;
    private IGameAssets assets;

    @BeforeAll
    static void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        }, config);
    }

    @BeforeEach
    void setUpEach() {
        assets = new GameAssets();
        gameModel = new GameModel(assets);
    }

    @Test
    void testInitialGameSetUpStart() {
        // Test the initial gamestate is WELCOME_SCREEN
        assertEquals(GameState.WELCOME_SCREEN, gameModel.getGameState(),
                "Game should initially be in the WELCOME_SCREEN state.");

        // Test that the pilot is initialized and added to the entities list
        assertNotNull(gameModel.getPilot(), "Pilot should be initialized.");
        assertTrue(gameModel.getEntities().contains(gameModel.getPilot(), true), "Entities should contain the pilot.");
        assertTrue(gameModel.getPilot() instanceof Pilot,
                "Pilot should be an instance of inf112.skeleton.app.model.Pilot.");

    }

    @Test
    void testInitialGameSetUpStartAndRestart() {
        // Test that assets are initialized
        assertNotNull(gameModel.getAssets(), "Assets should be initialized.");
        assertEquals(assets, gameModel.getAssets(), "Assets should be the same as the mock assets.");

        // Test that audio is initialized
        assertNotNull(gameModel.getAudio(), "Audio should be initialized.");
        assertTrue(gameModel.getAudio() instanceof AudioUtil,
                "Audio should be an instance of inf112.skeleton.app.model.utils.AudioUtil.");
        assertEquals(gameModel.getCurrentSong(), "audio/pm-music1.mp3",
                "The current song should be 'audio/pm-music1.mp3'.");

        // Test the initial world
        assertNotNull(gameModel.getWorld(), "World should be initialized.");
        assertTrue(gameModel.getWorld() instanceof World,
                "World should be an instance of com.badlogic.gdx.physics.box2d.World.");
        WorldDef mockWorldDef = new WorldDef(new Vector2(0, -4.9f), true);
        assertEquals(new World(mockWorldDef).getGravity(), gameModel.getWorld().getGravity());

        // Test that the plane is initialized and added to the entities list
        assertNotNull(gameModel.getPlane(), "Plane should be initialized.");
        assertTrue(gameModel.getEntities().contains(gameModel.getPlane(), true), "Entities should contain the plane.");
        assertTrue(gameModel.getPlane() instanceof Plane,
                "Plane should be an instance of inf112.skeleton.app.model.Plane.");
        assertFalse(gameModel.isPlaneInGame(),
                "Plane should not be in game at the start of the game because it is in the welcome_screen.");
        assertTrue(gameModel.getPlane().getEnergyLevel() == 100, "Plane's energy should be initialized to 100.");

        // Test that the entitiesToRemove list is initialized
        assertNotNull(gameModel.getEntitiesToRemove(), "EntitiesToRemove should be initialized.");
        assertTrue(gameModel.getEntitiesToRemove().size() == 0, "EntitiesToRemove should be empty.");

        // Test that the controller is allowed
        assertTrue(gameModel.getControllerAllowed(),
                "Controller should be allowed by default at the start of the game.");
        // Test that the score is initialized to 0
        assertTrue(gameModel.getCurrentScore() == 0, "Scores should be initialized to 0.");

    }

    @Test
    void testBoardPlaneTransitionsToPlaying() {
        gameModel.boardPlane();
        gameModel.update(0.1f);
        assertFalse(gameModel.getControllerAllowed());
        assertEquals(GameState.PLAYING, gameModel.getGameState(),
                "Game state should be PLAYING after boarding the plane.");
        gameModel.getPlane().getBody().setTransform(1, 1, 0);
        gameModel.update(0.1f);
        assertTrue(gameModel.isPlaneInGame(), "Plane should be in game after boarding.");
        assertTrue(gameModel.getControllerAllowed(), "Controller should be allowed after boarding the plane.");

    }

    @Test
    void testGameOverStateTransition() {
        // Ensure the game is in the correct state
        gameModel.setGameState(GameState.PLAYING);
        // Deplete all energy
        gameModel.getPlane().decreaseEnergyLevel(gameModel.getPlane().getEnergyLevel());
        // Trigger game over condition
        gameModel.update(0.1f);

        // check that plane health is 0
        assertEquals(0, gameModel.getPlane().getEnergyLevel(), "Plane's energy should be 0.");

        // Verify game state transition
        assertEquals(GameState.GAME_OVER, gameModel.getGameState(),
                "Game should transition to GAME_OVER state when plane's energy depletes.");

        // Check that correct song is playing
        assertEquals("audio/gameoverahhbeat.mp3", gameModel.getCurrentSong(), "Game over song should be playing.");
    }

    @Test
    void testRestartGame() {
        gameModel.setGameState(GameState.GAME_OVER);
        assertEquals(GameState.GAME_OVER, gameModel.getGameState(), "Game state should be GAME_OVER.");
        gameModel.restartGame();
        // Check that the game state is set to PLAYING after restart
        assertEquals(GameState.PLAYING, gameModel.getGameState(),
                "Game state should be WELCOME_SCREEN after restart.");
        // Test that the game is set up like it was at the start
        testInitialGameSetUpStartAndRestart();
        assertFalse(gameModel.getEntities().contains(gameModel.getPilot(), true),
                "Entities should not contain the pilot.");
    }

    @Test
    void testEnemySpawn() {
        assertEquals(5, gameModel.getEntities().size, "Pilot, plane and three walls should have spawned.");
        gameModel.setGameState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gameModel.getGameState(),
                "Game state should be PLAYING after boarding the plane.");

        gameModel.setPlaneInGame();
        float delta = 5;
        gameModel.update(delta);

        assertTrue(gameModel.getEntities().size > 5, "Enemies should have been spawned after update.");

        boolean whiteBirdSpawned = false;

        for (Entity entity : gameModel.getEntities()) {
            if (entity instanceof WhiteBird) {
                whiteBirdSpawned = true;
            }
        }
        assertTrue(whiteBirdSpawned, "WhiteBird should be spawned after update.");
    }

    @Test
    void testUpdateEntities() {
        gameModel.setGameState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gameModel.getGameState(),
                "Game state should be PLAYING after boarding the plane.");
        gameModel.setPlaneInGame();

        float delta = 10;
        gameModel.update(delta);

        assertTrue(gameModel.getEntities().size > 5, "Entities should have been spawned after update.");

        boolean energySpawned = false;
        boolean pointSpawned = false;

        while (!energySpawned || !pointSpawned) {
            gameModel.update(delta);
            for (Entity entity : gameModel.getEntities()) {
                if (entity instanceof Energy) {
                    energySpawned = true;
                } else if (entity instanceof Point) {
                    pointSpawned = true;
                }
                if (energySpawned && pointSpawned) {
                    break;
                }
            }
        }

        assertTrue(energySpawned, "Energy should be spawned after update.");
        assertTrue(pointSpawned, "Point should be spawned after update.");
    }

    @Test
    void testGetWorld() {
        IGameAssets mockAssets = mock(IGameAssets.class);
        GameModel gameModel = new GameModel(mockAssets);
        assertNotNull(gameModel.getWorld(), "World should be initialized.");
        assertTrue(gameModel.getWorld() instanceof World,
                "World should be an instance of com.badlogic.gdx.physics.box2d.World.");

    }

    @Test
    void testGetEntities() {
        assertNotNull(gameModel.getEntities(), "Entities should be initialized.");
        assertTrue(gameModel.getEntities() instanceof Array,
                "Entities should be an instance of com.badlogic.gdx.utils.Array.");
    }

    @Test
    void testGetPlane() {
        assertNotNull(gameModel.getPlane(), "Plane should be initialized.");
        assertTrue(gameModel.getPlane() instanceof Plane,
                "Plane should be an instance of inf112.skeleton.app.model.Plane.");

    }

    @Test
    void testSetGameState() {
        gameModel.setGameState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gameModel.getGameState(), "Game state should be PLAYING after setting.");
        gameModel.setGameState(GameState.PAUSED);
        assertEquals(GameState.PAUSED, gameModel.getGameState(), "Game state should be PAUSED after setting.");
    }

    @Test
    void testGetGameState() {
        assertEquals(GameState.WELCOME_SCREEN, gameModel.getGameState(),
                "Game state should be WELCOME_SCREEN at the start of the game.");
    }

    @Test
    void testPauseGame() {
        gameModel.setGameState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gameModel.getGameState(), "Game state should be PLAYING.");
        gameModel.pauseGame(GameState.PAUSED);
        assertEquals(GameState.PAUSED, gameModel.getGameState(), "Game state should be PAUSED after pausing.");

    }

    @Test
    void testResumeGame() {
        gameModel.setGameState(GameState.PAUSED);
        assertEquals(GameState.PAUSED, gameModel.getGameState(), "Game state should be PAUSED.");
        gameModel.resumeGame();
        assertEquals(GameState.PLAYING, gameModel.getGameState(), "Game state should be PLAYING after resuming.");
    }

    @Test
    void testAddEntityToRemove() {
        Entity mockEntity = mock(Entity.class);
        gameModel.addEntityToRemove(mockEntity);
        assertTrue(gameModel.getEntitiesToRemove().contains(mockEntity), "Entity should be added to entitiesToRemove.");
    }

    @Test
    void testRemoveEntities() {
        gameModel.addEntityToRemove(gameModel.getPlane());
        gameModel.removeEntities();
        assertFalse(gameModel.getEntitiesToRemove().contains(gameModel.getPlane()),
                "Plane should be removed from entitiesToRemove.");
        assertFalse(gameModel.getEntities().contains(gameModel.getPlane(), true),
                "Plane should be removed from entities.");
        gameModel.addEntityToRemove(gameModel.getPilot());
        gameModel.removeEntities();
        assertFalse(gameModel.getEntitiesToRemove().contains(gameModel.getPilot()),
                "Pilot should be removed from entitiesToRemove.");
        assertFalse(gameModel.getEntities().contains(gameModel.getPilot(), true),
                "Pilot should be removed from entities.");
    }

    @Test
    void testGetGameTime() {
        assertTrue(gameModel.getGameTime() == (int) (System.currentTimeMillis() / 1000));
    }

    @Test
    void testAddPoints() {
        gameModel.addPoints(100);
        assertEquals(100, gameModel.getCurrentScore(), "Score should be 100 after adding 100 points.");
    }
}
