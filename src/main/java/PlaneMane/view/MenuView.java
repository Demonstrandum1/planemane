package PlaneMane.view;

import PlaneMane.model.Configurations;
import PlaneMane.model.GameState;
import PlaneMane.model.utils.IGameAssets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/** This class contains how to draw the different screens */
public class MenuView {
    public TextureRegion gameover, pausescreen, helpscreen;
    private ViewableGameModel model;
    private SpriteBatch batch;
    protected boolean assetsInit = false;

    public MenuView(ViewableGameModel model, SpriteBatch batch) {
        this.model = model;
        this.batch = batch;
    }

    protected void initAssets() {
        IGameAssets assets = model.getAssets();
        helpscreen = assets.getRegion("helpscreen");
        gameover = assets.getRegion("gameoverscreen");
        pausescreen = assets.getRegion("pausescreen");
        assetsInit = true;
    }

    /**
     * Draws the start menu
     * 
     * @param batch
     */
    protected void drawStartMenu(SpriteBatch batch) {
        if (!assetsInit) {
            initAssets();
        }
        batch.draw(helpscreen, -Configurations.WORLD_WIDTH + 1, 0, Configurations.WORLD_WIDTH,
                Configurations.WORLD_HEIGHT);
    }

    /**
     * Draws the help screen
     * 
     * @param batch
     */
    protected void drawHelpScreen(SpriteBatch batch) {
        if (!assetsInit) {
            initAssets();
        }
        float x = 0;
        if (!model.isPlaneInGame() && model.getGameState() == GameState.HELP
                || !model.isPlaneInGame() && model.getGameState() == GameState.WELCOME_SCREEN) {
            x = -Configurations.WORLD_WIDTH + 1;
        } else if (model.isPlaneInGame() && model.getGameState() == GameState.PLAYING) {
            x = 0;
        }
        batch.draw(helpscreen, x, 0, Configurations.WORLD_WIDTH,
                Configurations.WORLD_HEIGHT);
    }

    /**
     * Draws the pause screen
     * 
     * @param batch
     */
    protected void drawPauseScreen(SpriteBatch batch) {
        if (!assetsInit) {
            initAssets();
        }
        float x = 0;
        if (!model.isPlaneInGame() && model.getGameState() == GameState.PAUSED
                || !model.isPlaneInGame() && model.getGameState() == GameState.WELCOME_SCREEN) {
            x = -Configurations.WORLD_WIDTH + 1;
        } else if (!model.isPlaneInGame() && model.getGameState() == GameState.PLAYING) {
            x = 0;
        }
        batch.draw(pausescreen, x, 0, Configurations.WORLD_WIDTH,
                Configurations.WORLD_HEIGHT);
    }

    /**
     * Draws the game over screen
     * 
     * @param batch
     */
    protected void drawGameOverScreen(SpriteBatch batch) {
        if (!assetsInit) {
            initAssets();
        }
        batch.draw(gameover, 0, 0, Configurations.WORLD_WIDTH,
                Configurations.WORLD_HEIGHT);
    }

    /**
     * Disposes the batch
     */
    protected void dispose() {
        batch.dispose();
    }
}
