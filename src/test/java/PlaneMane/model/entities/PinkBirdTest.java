package PlaneMane.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldDef;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PinkBirdTest {
    private World world;
    private PinkBird pinkBird;
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
        pinkBird = new PinkBird(world, 0, 0, 1, 1, model);
    }

    @Test
    void testUpdateMovesTowardsPlane() {
        when(mockPlane.getBody().getPosition()).thenReturn(new Vector2(10, 0));
        pinkBird.update();
        assertTrue(pinkBird.getBody().getLinearVelocity().x > 0, "PinkBird should move towards the Plane.");
    }

    @Test
    void testUpdateDodgesPlane() {
        when(mockPlane.getBody().getPosition()).thenReturn(new Vector2(1.5f, 0));
        pinkBird.update();
        assertTrue(pinkBird.getBody().getLinearVelocity().x < 0, "PinkBird should dodge away from the Plane.");
    }

    @Test
    void testShouldStartDodging() {
        when(mockPlane.getBody().getPosition()).thenReturn(new Vector2(1.5f, 0));
        assertTrue(pinkBird.shouldStartDodging(new Vector2(1.5f, 0), new Vector2(0, 0)),
                "PinkBird should start dodging when the Plane is very close.");
    }

    @Test
    void testUpdateDead() {
        assertFalse(pinkBird.isDead);

        pinkBird.kill();
        pinkBird.update();
        assertTrue(pinkBird.isDead);
        assertFalse(pinkBird.checkIfOutOfScreen());

        pinkBird.getBody().setTransform(0, -10, 0);
        pinkBird.update();
        assertTrue(pinkBird.isDead);
        assertTrue(pinkBird.checkIfOutOfScreen());
    }
}
