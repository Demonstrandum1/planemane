package PlaneMane.model.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import PlaneMane.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaneTest {
    private World world;
    private GameModel gameModel;
    private Plane mockPlane;

    @BeforeEach
    void setUp() {
        gameModel = mock(GameModel.class);
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        world = new World(worldDef);
        mockPlane = new Plane(world, 5.0f, 5.0f, 2.0f, 2.0f, gameModel);
        mockPlane.increaseEnergyLevel(100);
    }

    @Test
    void testDecreaseEnergyLevel() {
        int decreaseAmount = 10;
        mockPlane.decreaseEnergyLevel(decreaseAmount);
        assertEquals(90, mockPlane.getEnergyLevel(), "Plane's energy level should decrease by the specified amount.");
    }

    @Test
    void testIncreaseEnergyLevelWithinLimit() {
        mockPlane.decreaseEnergyLevel(50); // Decrease to 50 to ensure we're not at max
        int increaseAmount = 30; // Increase to 80, should be within the limit
        mockPlane.increaseEnergyLevel(increaseAmount);
        assertEquals(80, mockPlane.getEnergyLevel(),
                "Plane's energy level should increase by the specified amount within limit.");
    }

    @Test
    void testIncreaseEnergyLevelExceedsLimit() {
        mockPlane.decreaseEnergyLevel(10); // Decrease to 90 to set up the test
        mockPlane.increaseEnergyLevel(20); // Attempt to increase to 110, should cap at 100
        assertEquals(100, mockPlane.getEnergyLevel(), "Plane's energy level should not exceed 100.");
    }

    @Test
    void testFixtureUserDataIsSet() {
        Fixture fixture = mockPlane.getBody().getFixtureList().get(0);
        assertEquals(mockPlane, fixture.getUserData(), "Fixture user data should be set to the plane instance.");
    }
}
