package PlaneMane.model.factories;

import java.util.Random;
import com.badlogic.gdx.math.MathUtils;

import PlaneMane.model.Configurations;
import PlaneMane.model.GameModel;
import PlaneMane.model.entities.Energy;
import PlaneMane.model.entities.Entity;
import PlaneMane.model.entities.Point;

/**
 * Factory for creating random objects Creates both energy and point objects
 * with
 * random height and width
 * 
 */
public class RandomObjectFactory implements EntityFactory {

    private GameModel model;
    private float[] height;
    float spawnInterval;
    String objects;
    Random Random;

    public RandomObjectFactory(GameModel model, float[] height) {
        this.model = model;
        this.height = height;
        this.objects = "EC";
        this.spawnInterval = 10;
        Random = new Random();

    }

    @Override
    public Entity createObject() {
        float objectWidth = MathUtils.random(Configurations.WORLD_HEIGHT / 25f, Configurations.WORLD_HEIGHT / 15f);
        float objectHeight = objectWidth * (16f / 9f);
        int randomIndex = MathUtils.random(height.length - 1);
        float randomHeight = height[randomIndex] * Configurations.WORLD_HEIGHT;

        int randomInt = Random.nextInt(objects.length());
        char c = objects.charAt(randomInt);

        if (c == 'E') {
            return new Energy(model.getWorld(), Configurations.WORLD_WIDTH, randomHeight, objectWidth, objectHeight,
                    model);
        } else if (c == 'C') {
            return new Point(model.getWorld(), Configurations.WORLD_WIDTH, randomHeight, objectWidth, objectHeight,
                    model, objects);
        } else {
            return null;
        }
    }

    @Override
    public void addEntity(char entity) {
        if (entity == 'E' || entity == 'C') {
            objects += entity;
            if (spawnInterval > 1) {
                spawnInterval -= 1;
            }
        } else {
            return;
        }
    }

    @Override
    public float getSpawnInterval() {
        return spawnInterval;
    }

    @Override
    public void setSpawnInterval(float spawnInterval) {
        this.spawnInterval = spawnInterval;
    }
}