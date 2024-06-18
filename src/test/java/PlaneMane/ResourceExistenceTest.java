package PlaneMane;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

/**
 * Test for verifying the existence of game resources.
 */
public class ResourceExistenceTest {

    /**
     * Initialize LibGDX headless application before running any tests.
     */
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

    /**
     * Test to verify the existence of the plane.png graphic resource.
     */
    @Test
    void testPlaneGraphicExists() {
        assertTrue(Gdx.files.internal("graphics/plane.png").exists());
    }

    /**
     * Test to verify the existence of the energy.mp3 audio resource.
     */
    @Test
    void testEnergyAudioExists() {
        assertTrue(Gdx.files.internal("audio/energy.mp3").exists());
    }


    /**
     * Test to verify the existence of the hit.mp3 audio resource.
     */
    @Test
    void testHitAudioExists() {
        assertTrue(Gdx.files.internal("audio/hit.mp3").exists());
    }

    /**
     * Test to verify the existence of the point.mp3 audio resource.
     */
    @Test
    void testPointAudioExists() {
        assertTrue(Gdx.files.internal("audio/point.mp3").exists());
    }

    /**
     * Test to verify the existence of the pm-music1.mp3 audio resource.
     */
    @Test
    void testMusic1AudioExists() {
        assertTrue(Gdx.files.internal("audio/pm-music1.mp3").exists());
    }

}
