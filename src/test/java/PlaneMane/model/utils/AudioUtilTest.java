package PlaneMane.model.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import static org.junit.jupiter.api.Assertions.*;

public class AudioUtilTest {
    private AudioUtil audioUtil;

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
        audioUtil = new AudioUtil();
    }

    @AfterEach
    void tearDown() {
        audioUtil.disposeMusic();
    }

    @Test
    void testStreamMusic() {

        assertNull(audioUtil.getMusicPlayer(), "Music player should be null before streaming music");

        audioUtil.streamMusic("audio/gameoverahhbeat.mp3");

        assertNotNull(audioUtil.getMusicPlayer(), "Music player should not be null after streaming music");
    }

    @Test
    void stopMusic() {

        audioUtil.streamMusic("audio/gameoverahhbeat.mp3");

        assertNotNull(audioUtil.getMusicPlayer(), "Music player should not be null before stopping music");

        audioUtil.stopMusic();

        assertNull(audioUtil.getMusicPlayer(), "Music player should be null after stopping music");
    }

    @Test
    void testPlaySoundEffects() {

        assertNull(audioUtil.getSoundPlayer(), "Sound player should be null before playing sound effect");

        audioUtil.playSoundEffects("audio/energy.mp3");

        assertNotNull(audioUtil.getSoundPlayer(), "Sound player should not be null after playing sound effect");
    }

    @Test
    void testDispose() {
        audioUtil.streamMusic("audio/gameoverahhbeat.mp3");

        assertNotNull(audioUtil.getMusicPlayer(), "Music player should not be null before disposal");

        audioUtil.disposeMusic();

        assertNull(audioUtil.getMusicPlayer(), "Music player should be null after disposal");
    }
}
