package PlaneMane.model.factories;

import PlaneMane.model.entities.Entity;

public interface EntityFactory {

    /**
     * Used to create an object
     * 
     * @return Entity object
     */
    public Entity createObject();

    /**
     * Used to add an entity
     * 
     * @param entity
     */
    public void addEntity(char entity);

    /**
     * Used to get the spawn interval
     * 
     * @return float
     */
    public float getSpawnInterval();

    /**
     * Used to set the spawn interval
     */
    public void setSpawnInterval(float spawnInterval);
}
