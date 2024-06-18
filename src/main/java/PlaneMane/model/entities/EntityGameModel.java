package PlaneMane.model.entities;

import com.badlogic.gdx.physics.box2d.World;

import PlaneMane.model.utils.IGameAssets;

/**
 * Interface for the game model for the methods necessary for the entities
 */
public interface EntityGameModel {

    /**
     * Returns the world
     * 
     * @return World
     */
    public World getWorld();

    /**
     * Adds an entity to remove
     * 
     * @param entity
     */
    public void addEntityToRemove(Entity entity);

    /**
     * Returns the game time
     * 
     * @return int
     */
    public int getGameTime();

    /**
     * Returns the assets
     *
     * @return IGameAssets
     */
    public IGameAssets getAssets();

    /**
     * Returns the plane
     *
     * @return Plane
     */
    public Plane getPlane();

    /**
     * Adds points
     *
     * @param points
     */
    public void addPoints(int points);
}
