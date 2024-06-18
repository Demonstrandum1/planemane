package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class EntityTest {

    private World world;
    private Entity entity;
    private EntityGameModel model;

    private static class TestEntity extends Entity {
        public TestEntity(World world, float x, float y, float width, float height, EntityGameModel model,
                String texRegionString) {
            super(world, x, y, width, height, model, texRegionString);
        }
    }

    @BeforeEach
    void setUp() {
        WorldDef worldDef = new WorldDef(new Vector2(0, 0), true);
        world = new World(worldDef);
        model = mock(EntityGameModel.class);
        entity = new TestEntity(world, 0, 0, 1, 1, null, "plane");
    }

    @Test
    void testUpdate() {
        Vector2 initialPosition = entity.getBody().getPosition().cpy();
        entity.update();
        assertEquals(initialPosition.x, entity.getBody().getPosition().x, 0.001);
        assertEquals(initialPosition.y, entity.getBody().getPosition().y, 0.001);

    }

    @Test
    void testMove() {

        Vector2 direction = new Vector2(5, 5);
        float speed = 1;
        Vector2 expectedVelocity = direction.scl(speed);

        entity.move(direction, speed);

        Vector2 velocity = entity.getBody().getLinearVelocity();

        assertEquals(expectedVelocity.x, velocity.x, 0.001);
        assertEquals(expectedVelocity.y, velocity.y, 0.001);
    }

    @Test
    void testStop() {
        entity.move(new Vector2(1, 1), 5);

        entity.stop();

        assertEquals(0, entity.getBody().getLinearVelocity().len(), 0.001);
    }

    @Test
    void testGetBody() {
        assertEquals(entity.getBody(), entity.getBody());
    }

    @Test
    void testGetEntitySprite() {
        assertEquals(entity.getEntitySprite(), entity.entitySprite);
    }

    @Test
    void testGetTexRegionString() {
        assertEquals(entity.getTexRegionString(), entity.texRegionString);
    }
}
