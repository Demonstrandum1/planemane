package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WhiteBirdTest {
    private World world;
    private WhiteBird whiteBird;
    private EntityGameModel model;
    private Plane mockPlane;

    @BeforeEach
    void setUp() {
        WorldDef worldDef = new WorldDef(new Vector2(0, -4.9f), true);
        world = new World(worldDef);
        model = mock(EntityGameModel.class);
        mockPlane = mock(Plane.class);
        Body mockPlaneBody = mock(Body.class);

        when(model.getPlane()).thenReturn(mockPlane);
        when(mockPlane.getBody()).thenReturn(mockPlaneBody);
        when(mockPlaneBody.getPosition()).thenReturn(new Vector2(0, 0));

        whiteBird = new WhiteBird(world, 0, 0, 1, 1, model);
    }

    @Test
    void testUpdateMovesToLeft() {
        whiteBird.update();
        assertTrue(whiteBird.getBody().getLinearVelocity().x < 0, "WhiteBird should move to the left.");
    }

    @Test
    void testUpdateDead() {
        assertFalse(whiteBird.isDead);
        whiteBird.kill();
        whiteBird.update();
        assertTrue(whiteBird.isDead);

        whiteBird.getBody().setTransform(0, -10, 0);
        whiteBird.update();
        assertTrue(whiteBird.isDead);
        assertTrue(whiteBird.checkIfOutOfScreen());

    }
}
