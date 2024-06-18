package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private World world;
    private TestEnemy enemy;
    private EntityGameModel model;

    private static class TestEnemy extends Enemy {

        public TestEnemy(World world, float x, float y, float width, float height, EntityGameModel model) {
            super(world, x, y, width, height, model, "testEnemy");
        }

        @Override
        public void update() {
            if (!isDead) {
                move(new Vector2(-1, 0), 1);
            }
        }
    }

    @BeforeEach
    void setUp() {
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        world = new World(worldDef);
        model = mock(EntityGameModel.class);
        enemy = new TestEnemy(world, 0, 0, 1, 1, model);
    }

    @Test
    void testEnemyNotDeadInitially() {
        assertFalse(enemy.isDead, "Enemy should not be dead initially.");
    }

    @Test
    void testKillEnemy() {
        enemy.kill();
        assertTrue(enemy.isDead, "Enemy should be marked as dead after being killed.");
    }

    @Test
    void testStopMovementWhenKilled() {
        enemy.kill();
        enemy.update();
        assertEquals(new Vector2(0, -20), enemy.getBody().getLinearVelocity(), "Enemy should stop moving when killed.");
    }

    @Test
    void testStopLeadsToCollision() {
        Enemy mockEnemy = mock(Enemy.class);
        Body mockBody = mock(Body.class);
        when(mockBody.getPosition()).thenReturn(new Vector2(0, -11));
        when(mockEnemy.getBody()).thenReturn(mockBody);
        when(mockBody.getLinearVelocity()).thenReturn(new Vector2(0, -20));

        enemy.stop();
        assertEquals(enemy.getBody().getLinearVelocity(), new Vector2(0, -20));
        assertEquals(mockEnemy.getBody().getLinearVelocity(), new Vector2(0, -20));
        assertEquals(enemy.getBody().getLinearVelocity(), mockEnemy.getBody().getLinearVelocity());
    }

    @Test
    void testCheckIfOutOfScreen() {
        assertFalse(enemy.checkIfOutOfScreen());
        enemy.getBody().setTransform(0, -2, 0);
        assertTrue(enemy.checkIfOutOfScreen());
    }
}
