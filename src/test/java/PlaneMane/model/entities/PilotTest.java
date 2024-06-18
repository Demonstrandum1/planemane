package PlaneMane.model.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import PlaneMane.model.GameModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PilotTest {
    private World world;
    private GameModel gameModel;
    private Pilot pilot;

    @BeforeEach
    void setUp() {
        gameModel = mock(GameModel.class);
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        world = new World(worldDef);
        pilot = new Pilot(world, 5, 5, 2, 2, gameModel);
    }

    @Test
    void testFixtureUserDataIsSet() {
        Fixture fixture = pilot.getBody().getFixtureList().first();

        assertEquals(pilot, fixture.getUserData(), "Fixture user data should be set to the pilot instance.");
    }

    @Test
    void testPilotMovement() {
        Vector2 direction = new Vector2(1, 0);
        float speed = 5f;

        pilot.move(direction, speed);

        world.step(1 / 60f, 6, 2);

        Vector2 velocity = pilot.getBody().getLinearVelocity();
        assertTrue(velocity.x > 0, "Pilot should have a positive x velocity after moving right.");
        assertTrue(Math.abs(velocity.y) < 0.2,
                "Pilot's y velocity should be near zero, allowing for gravity, after moving horizontally.");
    }

    @Test
    void testPilotStop() {
        pilot.move(new Vector2(1, 0), 5f);
        world.step(1 / 60f, 6, 2);

        pilot.stop();
        world.step(1 / 60f, 6, 2);

        Vector2 velocity = pilot.getBody().getLinearVelocity();
        assertEquals(0, velocity.x, 0.001, "Pilot's x velocity should be zero after stopping.");
        assertTrue(Math.abs(velocity.y) < 0.2, "Pilot's y velocity should be near zero after stopping.");
    }
}
