package PlaneMane.view;

import PlaneMane.model.Configurations;
import PlaneMane.model.entities.Plane;
import PlaneMane.model.utils.IGameAssets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class for drawing the hearts
 */
public class HeartView {
    private int energyLevel;
    private Plane plane;
    private SpriteBatch batch;
    private ViewableGameModel model;
    protected TextureRegion hearts1, hearts2, hearts3, hearts4, hearts5, hearts6, hearts7;
    protected boolean assetsInit = false;

    /**
     * Constructor for the HeartView.
     * 
     * @param plane
     * @param model
     * @param batch
     */
    public HeartView(Plane plane, ViewableGameModel model, SpriteBatch batch) {

        this.model = model;
        this.batch = batch;
        this.plane = plane;
    }

    protected void initAssets() {
        if (!assetsInit) {

            IGameAssets assets = model.getAssets();

            hearts1 = assets.getRegion("hearts1");
            hearts2 = assets.getRegion("hearts2");
            hearts3 = assets.getRegion("hearts3");
            hearts4 = assets.getRegion("hearts4");
            hearts5 = assets.getRegion("hearts5");
            hearts6 = assets.getRegion("hearts6");
            hearts7 = assets.getRegion("hearts7");
            assetsInit = true;
        }
    }

    protected TextureRegion getHeartTexture() {
        initAssets();
        energyLevel = plane.getEnergyLevel();
        if (energyLevel > 85) {
            return hearts7;
        } else if (energyLevel > 70) {
            return hearts6;
        } else if (energyLevel > 55) {
            return hearts5;
        } else if (energyLevel > 40) {
            return hearts4;
        } else if (energyLevel > 25) {
            return hearts3;
        } else if (energyLevel > 10) {
            return hearts2;
        } else {
            return hearts1;
        }
    }

    /**
     * Draws the hearts
     * 
     * @param batch
     */
    protected void draw(SpriteBatch batch) {
        drawHearts(batch);
    }

    private void drawHearts(SpriteBatch batch) {
        TextureRegion heart = getHeartTexture();
        batch.draw(heart, Configurations.WORLD_WIDTH - Configurations.WORLD_WIDTH / 8,
                Configurations.WORLD_HEIGHT - Configurations.WORLD_HEIGHT / 6, Configurations.WORLD_WIDTH / 2 * 8 / 32,
                Configurations.WORLD_HEIGHT / 2 * 8 / 32);

    }

    /**
     * Disposes the batch
     */
    protected void dispose() {
        batch.dispose();
    }

}
