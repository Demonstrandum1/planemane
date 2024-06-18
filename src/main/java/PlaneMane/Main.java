package PlaneMane;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Main class for the game
 */
public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Plane Mane");
        cfg.setWindowedMode(1400, 960);
        cfg.setResizable(false);
        cfg.useVsync(true);
        cfg.setForegroundFPS(60);
        cfg.setBackBufferConfig(4, 4, 4, 4, 16, 0, 4);
        new Lwjgl3Application(new PlaneMane(), cfg);
    }
}