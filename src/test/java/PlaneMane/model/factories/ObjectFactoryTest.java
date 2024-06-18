package PlaneMane.model.factories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import PlaneMane.model.Configurations;
import PlaneMane.model.GameModel;
import PlaneMane.model.entities.Energy;
import PlaneMane.model.entities.Entity;
import PlaneMane.model.entities.Point;
import PlaneMane.model.utils.GameAssets;

public class ObjectFactoryTest {
    private GameAssets assets;
    private GameModel gameModel;
    private RandomObjectFactory objectFactory;

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
        assets = mock(GameAssets.class);
        gameModel = new GameModel(assets);
        objectFactory = new RandomObjectFactory(gameModel, Configurations.FACTORY_HEIGHT);

    }

    @Test
    public void testObjectFactory() {
        assertNotNull(objectFactory);
        assertTrue(objectFactory.objects.length() == 2);
        assertTrue(objectFactory.objects.equals("EC"));
        assertTrue(objectFactory.objects.contains("C"));
        assertTrue(objectFactory.objects.contains("E"));
    }

    @Test
    public void testCreatePoint() {
        objectFactory.objects = "C";
        Entity point = objectFactory.createObject();
        assertTrue(point instanceof Point);
    }

    @Test
    public void testCreateEnergy() {
        objectFactory.objects = "E";
        Entity energy = objectFactory.createObject();
        assertTrue(energy instanceof Energy);
    }

    @Test
    public void testAddEntity() {

        objectFactory.addEntity('C');
        assertTrue(objectFactory.objects.charAt(2) == ('C'));
        assertTrue(objectFactory.objects.length() == 3);

        objectFactory.addEntity('E');
        assertTrue(objectFactory.objects.charAt(3) == ('E'));
        assertTrue(objectFactory.objects.length() == 4);
        assertTrue(objectFactory.objects.equals("ECCE"));

        objectFactory.addEntity('Q');
        assertTrue(objectFactory.objects.length() == 4);
        assertFalse(objectFactory.objects.contains("Q"));
        assertTrue(objectFactory.objects.equals("ECCE"));
    }

    @Test
    public void testCreateNull() {
        objectFactory.objects = "Q";
        Entity entity = objectFactory.createObject();
        assertTrue(entity == null);
    }

    @Test
    public void testCreateRandom() {
        Entity entity = objectFactory.createObject();
        assertTrue(entity instanceof Energy || entity instanceof Point);
    }

    @Test
    public void testCreateManyObjects() {
        float minWidth = Configurations.WORLD_HEIGHT / 25f;
        float maxWidth = Configurations.WORLD_HEIGHT / 15f;
        float minHeight = minWidth * (16f / 9f);
        float maxHeigh = maxWidth * (16f / 9f);
        float minY = Configurations.FACTORY_HEIGHT[0] * Configurations.WORLD_HEIGHT;
        float maxY = Configurations.FACTORY_HEIGHT[Configurations.FACTORY_HEIGHT.length - 1]
                * Configurations.WORLD_HEIGHT;
        float valueX = Configurations.WORLD_WIDTH;

        for (int i = 0; i < 100; i++) {
            Entity entity = objectFactory.createObject();
            assertTrue(entity instanceof Energy || entity instanceof Point);

            assertTrue(entity.getHeight() >= minHeight && entity.getHeight() <= maxHeigh);
            assertTrue(entity.getWidth() >= minWidth && entity.getWidth() <= maxWidth);
            assertTrue(entity.getBody().getPosition().y >= minY &&
                    entity.getBody().getPosition().y <= maxY);
            assertEquals(valueX, entity.getBody().getPosition().x);
        }
    }

    @Test
    public void testSpawnIntervalDecrease() {
        assertEquals(10, objectFactory.getSpawnInterval());
        objectFactory.addEntity('C');
        assertEquals(9, objectFactory.getSpawnInterval());
        objectFactory.addEntity('E');
        assertEquals(8, objectFactory.getSpawnInterval());
        objectFactory.addEntity('Q');
        assertEquals(8, objectFactory.getSpawnInterval());
    }

    @Test
    public void testSetSpawnInterval() {
        objectFactory.setSpawnInterval(5);
        assertEquals(5, objectFactory.getSpawnInterval());

        objectFactory.setSpawnInterval(0);
        assertEquals(0, objectFactory.getSpawnInterval());
    }

    @Test
    public void testSpawnIntervallOne() {
        objectFactory.setSpawnInterval(1);
        objectFactory.addEntity('E');

        assertEquals(1, objectFactory.getSpawnInterval());

    }
}