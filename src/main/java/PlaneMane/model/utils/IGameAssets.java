package PlaneMane.model.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface IGameAssets {

    /**
     * Get the named TextureRegion from the game assets.
     * 
     * @param name the name of the region to retrieve.
     * @return the TextureRegion with the given name. If no region with the
     *         specified name is found, null is returned.
     */
    TextureRegion getRegion(String name);

    /**
     * Update the loaded game assets.
     * 
     * @return the game assets currently loaded.
     */
    boolean update();

    /**
     * Boolean to check if all assets are loaded. If false is returned, use the
     * {@link #update()} method to load the assets.
     * 
     * @return a boolean representing the status of assets loaded.
     */
    boolean allAssetsLoaded();

    /**
     * Disposes all game assets.
     */
    void disposeAssets();

}