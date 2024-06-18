package PlaneMane.model.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameAssets implements IGameAssets {
    private AssetManager assets = new AssetManager();
    private boolean loaded = false;

    public GameAssets() {
        assets.load("atlaspacked/planemane.atlas", TextureAtlas.class);
    }

    @Override
    public boolean update() {
        loaded = assets.update();
        return loaded;
    }

    @Override
    public TextureRegion getRegion(String name) {
        if (!loaded) {
            throw new IllegalStateException("Assets not finished loading.");
        }
        return assets.get("atlaspacked/planemane.atlas", TextureAtlas.class).findRegion(name);
    }

    @Override
    public boolean allAssetsLoaded() {
        return loaded;
    }

    @Override
    public void disposeAssets() {
        assets.dispose();
        loaded = false;
    }

    // For testing purposes
    void setAssetManager(AssetManager assetManager) {
        this.assets = assetManager;
    }
}