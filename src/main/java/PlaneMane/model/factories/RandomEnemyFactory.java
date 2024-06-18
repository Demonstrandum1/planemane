package PlaneMane.model.factories;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import PlaneMane.model.Configurations;
import PlaneMane.model.GameModel;
import PlaneMane.model.entities.Enemy;
import PlaneMane.model.entities.EntityGameModel;
import PlaneMane.model.entities.PinkBird;
import PlaneMane.model.entities.WhiteBird;

/**
 * Factory for creating random enemies
 * Creates both white and pink birds with random height and width
 * 
 */
public class RandomEnemyFactory implements EntityFactory {

    private EntityGameModel model;
    private float[] height;
    private float spawnInterval;
    String birds;
    Random Random;

    public RandomEnemyFactory(GameModel model, float[] height) {
        this.model = model;
        this.height = height;
        this.birds = "WWW";
        this.spawnInterval = 5;
        Random = new Random();
    }

    @Override
    public Enemy createObject() {
        float enemyHeight = MathUtils.random(Configurations.WORLD_WIDTH / 15, Configurations.WORLD_WIDTH / 25);
        float enemyWidth = enemyHeight * 16 / 9;
        int randomIndex = MathUtils.random(height.length - 1);
        float randomHeight = height[randomIndex] * Configurations.WORLD_HEIGHT;

        int randomInt = Random.nextInt(birds.length());
        char c = birds.charAt(randomInt);

        if (c == 'W') {
            return new WhiteBird(model.getWorld(), Configurations.WORLD_WIDTH, randomHeight, enemyWidth, enemyHeight,
                    model);
        } else if (c == 'P') {
            return new PinkBird(model.getWorld(), Configurations.WORLD_WIDTH, randomHeight, enemyWidth, enemyHeight,
                    model);
        } else {
            return null;
        }
    }

    @Override
    public void addEntity(char entity) {
        if (entity == 'W' || entity == 'P') {
            birds += entity;
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
