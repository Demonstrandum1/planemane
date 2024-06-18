package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnergyTest {
    private World world;
    private Energy energy;
    private EntityGameModel model;

    @BeforeEach
    void setUp() {
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        world = new World(worldDef);
        model = mock(EntityGameModel.class);
        energy = new Energy(world, 0, 0, 1, 1, model);

        when(model.getGameTime()).thenReturn(100000);
    }

    @Test
    void testEnergyNotTakenInitially() {
        assertFalse(energy.isTaken, "Energy should not be taken initially.");
    }

    @Test
    void testTakeEnergy() {
        energy.takeEnergy();
        assertTrue(energy.isTaken, "Energy should be marked as taken.");
    }

    @Test
    void testUpdateEnergy() {

        energy.update();
        assertFalse(energy.isTaken);

        energy.takeEnergy();
        energy.update();
        assertTrue(energy.isTaken);
    }

}