package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import PlaneMane.model.GameModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    private World world;
    private Point point;
    private GameModel model;

    @BeforeEach
    void setUp() {
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        world = new World(worldDef);
        model = mock(GameModel.class);
        point = new Point(world, 0, 0, 1, 1, model, "point");

        when(model.getGameTime()).thenReturn(100000);
    }

    @Test
    void testTakePoint() {
        assertFalse(point.isTaken, "Point should not be taken initially.");
        point.takePoint();
        assertTrue(point.isTaken, "Point should be marked as taken.");
    }

    @Test
    void testUpdateNoMovementWhenTaken() {
        point.takePoint();
        Vector2 positionBeforeUpdate = point.getBody().getPosition().cpy();
        point.update();
        assertEquals(positionBeforeUpdate, point.getBody().getPosition(), "Taken Point should not move.");
    }

    @Test
    void testUpdatePointIsTaken() {
        point.takePoint();
        point.update();
        assertTrue(point.isTaken);
    }
}
