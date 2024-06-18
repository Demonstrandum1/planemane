package PlaneMane.view;

import PlaneMane.model.Configurations;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class for the background view of the game.
 */

public class BackgroundView {
    private TextureRegion groundLayer, roadLayer, treesLayer, cloudsLayer;
    protected boolean assetsInit = false;
    protected float groundX, roadX, treesX, cloudsX = 0;
    private SpriteBatch batch;
    private ViewableGameModel model;

    public BackgroundView(ViewableGameModel model, SpriteBatch batch) {
        this.batch = batch;
        this.model = model;
    }

    /**
     * Gets the assets that make up the layers of the background and flags that they
     * have been initialized.
     */
    private void initAssets() {
        groundLayer = model.getAssets().getRegion("ground");
        roadLayer = model.getAssets().getRegion("road");
        treesLayer = model.getAssets().getRegion("trees");
        cloudsLayer = model.getAssets().getRegion("clouds");
        assetsInit = true;
    }

    /**
     * Updates the background layers
     * 
     * @param delta
     */
    public void update(float delta) {
        if (!assetsInit) {
            initAssets();
        }
        groundX = updatePos(groundX, Configurations.GROUND_SCROLL_SPEED, delta);
        roadX = updatePos(roadX, Configurations.ROAD_SCROLL_SPEED, delta);
        treesX = updatePos(treesX, Configurations.TREES_SCROLL_SPEED, delta);
        cloudsX = updatePos(cloudsX, Configurations.CLOUDS_SCROLL_SPEED, delta);
    }

    private float updatePos(float layerX, float speed, float delta) {
        if (model.isPlaneInGame()) {
            layerX -= (speed * delta);
            if (layerX <= -Configurations.WORLD_WIDTH) {
                layerX += Configurations.WORLD_WIDTH;
            }
        } else if (!model.isPlaneInGame()) {
            layerX = -Configurations.WORLD_WIDTH + 1;
        }
        return layerX;
    }

    /**
     * Draws the background layers
     * 
     * @param batch
     */
    protected void draw(SpriteBatch batch) {
        drawBg(batch, groundLayer, groundX);
        drawBg(batch, roadLayer, roadX);
        drawBg(batch, treesLayer, treesX);
        drawBg(batch, cloudsLayer, cloudsX);
    }

    private void drawBg(SpriteBatch batch, TextureRegion layer, float layerX) {
        batch.draw(layer, layerX, 0, Configurations.WORLD_WIDTH,
                Configurations.WORLD_HEIGHT);
        batch.draw(layer, layerX + Configurations.WORLD_WIDTH, 0,
                Configurations.WORLD_WIDTH,
                Configurations.WORLD_HEIGHT);
    }

    /**
     * Disposes the batch
     */
    protected void dispose() {
        batch.dispose();
    }

}
