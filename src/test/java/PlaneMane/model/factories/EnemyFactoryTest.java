package PlaneMane.model.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import PlaneMane.model.Configurations;
import PlaneMane.model.GameModel;
import PlaneMane.model.entities.Enemy;
import PlaneMane.model.entities.PinkBird;
import PlaneMane.model.entities.WhiteBird;
import PlaneMane.model.utils.GameAssets;

public class EnemyFactoryTest {
    private GameAssets assets;
    private GameModel gameModel;
    private RandomEnemyFactory enemyFactory;

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
    public void setUpEach() {
        assets = mock(GameAssets.class);
        gameModel = new GameModel(assets);
        enemyFactory = new RandomEnemyFactory(gameModel, Configurations.FACTORY_HEIGHT);
    }

    @Test
    public void testEnemyFactory() {
        assertNotNull(enemyFactory);
        assertTrue(enemyFactory.birds.length() == 3);
        assertTrue(enemyFactory.birds.equals("WWW"));
        assertFalse(enemyFactory.birds.contains("P"));
    }

    @Test
    public void testAddBirds() {
        assertTrue(enemyFactory.birds.length() == 3);
        assertTrue(enemyFactory.birds.charAt(0) == ('W'));
        assertTrue(enemyFactory.birds.charAt(1) == ('W'));
        assertTrue(enemyFactory.birds.charAt(2) == ('W'));

        enemyFactory.addEntity('P');
        assertTrue(enemyFactory.birds.length() == 4);
        assertTrue(enemyFactory.birds.charAt(3) == ('P'));
        assertTrue(enemyFactory.birds.equals(("WWWP")));

        enemyFactory.addEntity('P');
        assertTrue(enemyFactory.birds.length() == 5);
        assertTrue(enemyFactory.birds.charAt(4) == ('P'));
        assertTrue(enemyFactory.birds.equals("WWWPP"));

        enemyFactory.addEntity('W');
        assertTrue(enemyFactory.birds.length() == 6);
        assertTrue(enemyFactory.birds.charAt(5) == ('W'));
        assertTrue(enemyFactory.birds.equals("WWWPPW"));

        enemyFactory.addEntity('E');
        assertTrue(enemyFactory.birds.length() == 6);
        assertTrue(enemyFactory.birds.equals("WWWPPW"));
    }

    @Test
    public void testCreateWhiteBird() {
        enemyFactory.birds = "W";
        Enemy whiteBird = enemyFactory.createObject();
        assertTrue(whiteBird instanceof WhiteBird);
    }

    @Test
    public void testCreatePinkBird() {
        enemyFactory.birds = "P";
        Enemy pinkBird = enemyFactory.createObject();
        assertTrue(pinkBird instanceof PinkBird);
    }

    @Test
    public void testCreateNull() {
        enemyFactory.birds = "Q";
        Enemy enemy = enemyFactory.createObject();
        assertTrue(enemy == null);
    }

    @Test
    public void testCreateRandom() {
        enemyFactory.birds = "WP";
        Enemy enemy = enemyFactory.createObject();
        assertTrue(enemy instanceof WhiteBird || enemy instanceof PinkBird);
    }

    @Test
    public void testCreateManyObjects() {
        float minHeigh = Configurations.WORLD_WIDTH / 25f;
        float maxHeigh = Configurations.WORLD_WIDTH / 15f;
        float minWidth = minHeigh * 16 / 9;
        float maxWidth = maxHeigh * 16 / 9;
        float minY = Configurations.FACTORY_HEIGHT[0] * Configurations.WORLD_HEIGHT;
        float maxY = Configurations.FACTORY_HEIGHT[Configurations.FACTORY_HEIGHT.length - 1]
                * Configurations.WORLD_HEIGHT;
        float valueX = Configurations.WORLD_WIDTH;

        for (int i = 0; i < 100; i++) {
            Enemy enemy = enemyFactory.createObject();
            assertNotNull(enemy);
            assertTrue(enemy instanceof WhiteBird);
            assertFalse(enemy instanceof PinkBird);

            assertTrue(enemy.getHeight() >= minHeigh && enemy.getHeight() <= maxHeigh);
            assertTrue(enemy.getWidth() >= minWidth && enemy.getWidth() <= maxWidth);
            assertTrue(enemy.getBody().getPosition().y >= minY && enemy.getBody().getPosition().y <= maxY);
            assertEquals(valueX, enemy.getBody().getPosition().x);
        }

        enemyFactory.addEntity('P');
        for (int i = 0; i < 100; i++) {
            Enemy enemy2 = enemyFactory.createObject();
            assertNotNull(enemy2);
            assertTrue(enemy2 instanceof WhiteBird || enemy2 instanceof PinkBird);

            assertTrue(enemy2.getHeight() >= minHeigh && enemy2.getHeight() <= maxHeigh);
            assertTrue(enemy2.getWidth() >= minWidth && enemy2.getWidth() <= maxWidth);
            assertTrue(enemy2.getBody().getPosition().y >= minY && enemy2.getBody().getPosition().y <= maxY);
            assertEquals(valueX, enemy2.getBody().getPosition().x);
        }
    }

    @Test
    public void testSetAndGetSpawnInterval() {
        assertEquals(5, enemyFactory.getSpawnInterval());
        enemyFactory.addEntity('W');
        assertEquals(5, enemyFactory.getSpawnInterval());

        enemyFactory.setSpawnInterval(0);
        assertEquals(0, enemyFactory.getSpawnInterval());

        enemyFactory.setSpawnInterval(10);
        assertEquals(10, enemyFactory.getSpawnInterval());
    }
}
